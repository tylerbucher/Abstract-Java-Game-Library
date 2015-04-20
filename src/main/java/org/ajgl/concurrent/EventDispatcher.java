/**
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

package org.ajgl.concurrent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Tyler
 *
 */
public final class EventDispatcher {
    
    /*** The hash map of plugins and their methods that have the EventHandler annotation.*/
    private static final Map<Class<?>, Object> instanceMap = new HashMap<>();
    /*** The hash map of events and their corresponding methods */
    private static final Map<Class<? extends Event>, List<Method>> eventMap = new HashMap<>();
    
    /**
     * Dispatches events to the proper plugins and methods.
     * @param event - The event to dispatch
     */
    public static synchronized void dispatchEvent(Event event) {
        // Dispatch low-level events
        Class<?> clazz = event.getClass();
        while(Event.class.isAssignableFrom(clazz)) {
            EventDispatcher.subDispatchEvent(clazz, event);
            clazz = clazz.getSuperclass();
        }
    }
    
    /**
     * Dispatches a event to a specific event class.
     * @param clazz - The receiving class of the event.
     * @param event - The event to be passed.
     */
    private static synchronized void subDispatchEvent(Class<?> clazz, Event event) {
        List<Method> methodList = eventMap.get(clazz);
        if(methodList != null) {
            for(Method m : methodList) {
                try {
                    m.invoke(instanceMap.get(m.getDeclaringClass()), event);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    // TODO
                }
            }
        }
    }
    
    /**
     * Registers a class and its methods to the EventDispatcher.
     * @param listener - The instance of a class that has the EventHandler
     * annotation attached to at least one method.
     */
    public static synchronized void registerEvents(Class<?> eventClass) {
        ArrayList<Method> tempMethods = new ArrayList<>();
        // Check to see if annotation is present on class and method
        for (Method m : eventClass.getMethods())
            if (m.isAnnotationPresent(EventHandler.class)) {
                // Add method to list
                tempMethods.add(m);
                // Add unknown events to the list
                Class<? extends Event> value = m.getAnnotation(EventHandler.class).value();
                if(!eventMap.containsKey(value))
                    eventMap.put(value, new ArrayList<Method>());
            }
        // Attempts to create and add new instance to instanceMap
        try {
            instanceMap.put(eventClass, eventClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO
        }
        // Adds the methods the Event map
        for(Method m : tempMethods) {
            List<Method> list = eventMap.get(m.getAnnotation(EventHandler.class).value());
            list.add(m);    // TODO
            
            Collections.sort(list, new Comparator<Method>() {

                public int compare(Method method1, Method mehtod2) {
                    int method1Priority = method1.getAnnotation(EventHandler.class).priority();
                    int method2Priority = mehtod2.getAnnotation(EventHandler.class).priority();
                    return method1Priority - method2Priority;
                }
            });
        }
    }
    
}
