/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Tyler Bucher
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.ajgl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
     * Internal class logger for errors.
     */
    private static final Logger logger = LogManager.getLogger(EventDispatcher.class.getName());

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
                    logger.error("subDispatchEvent: Exception", e);
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
            logger.error("Unable to fully register an event", e);
        }
        // Adds the methods the Event map
        for (Method m : tempMethods) {
            List<Method> list = eventMap.get(m.getAnnotation(EventHandler.class).value());
            list.add(m);
            list.sort(methodComparator);
        }
    }
}
