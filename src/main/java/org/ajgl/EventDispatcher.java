package org.ajgl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Dispatches events to the appropriate functions, should only be called from the main thread.
 *
 * @author Tyler Bucher
 */
public final class EventDispatcher {

    /**
     * The {@link HashMap} of plugins and their methods that have the EventHandler annotation.
     */
    private static final Map<Class<?>, Object> instanceMap = new HashMap<>();

    /**
     * The {@link HashMap} of {@link Event} and their corresponding methods.
     */
    private static final Map<Class<? extends Event>, List<Method>> eventMap = new HashMap<>();

    /**
     * The Comparator for the type method, used in sorting. (Lowest priority gets executed last but has final say).
     */
    private static Comparator<Method> methodComparator = (method1, method2)->{
        final int method1Priority = method1.getAnnotation(EventHandler.class).priority();
        final int method2Priority = method2.getAnnotation(EventHandler.class).priority();
        return method1Priority - method2Priority;
    };

    /**
     * Dispatches events to the proper plugins and methods.
     *
     * @param event the {@link Event} to dispatch
     */
    public static void dispatchEvent(Event event) {
        // Dispatch low-level events
        Class<?> clazz = event.getClass();
        while (Event.class.isAssignableFrom(clazz)) {
            EventDispatcher.subDispatchEvent(clazz, event);
            clazz = clazz.getSuperclass();
        }
    }

    /**
     * Dispatches an {@link Event} to a specific {@link Event} class.
     *
     * @param clazz the receiving class of the event.
     * @param event the {@link Event} to be dispatched.
     */
    private static void subDispatchEvent(Class<?> clazz, Event event) {
        List<Method> methodList = eventMap.get(clazz);
        if (methodList != null) {
            for (Method m : methodList) {
                try {
                    m.invoke(instanceMap.get(m.getDeclaringClass()), event);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Registers a class and its methods to the {@link EventDispatcher}.
     *
     * @param eventClass the class that has the {@link EventHandler} annotation attached to at least one method.
     */
    public static void registerEvents(Class<?> eventClass) {
        ArrayList<Method> tempMethods = new ArrayList<>();
        // Check to see if annotation is present on class and method
        for (Method m : eventClass.getMethods())
            if (m.isAnnotationPresent(EventHandler.class)) {
                // Add method to list
                tempMethods.add(m);
                // Add unknown events to the list
                Class<? extends Event> value = m.getAnnotation(EventHandler.class).value();
                if (!eventMap.containsKey(value))
                    eventMap.put(value, new ArrayList<>());
            }
        // Attempts to create and add new instance to instanceMap
        try {
            instanceMap.put(eventClass, eventClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // Adds the methods the Event map
        for (Method m : tempMethods) {
            List<Method> list = eventMap.get(m.getAnnotation(EventHandler.class).value());
            list.add(m);
            list.sort(methodComparator);
        }
    }

}
