/**
 * AJGL, an abstract java game library that provides useful functions for making a game.
 * Copyright (C) 2014 Tyler Bucher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.ajgl.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ajgl.Logger;

/**
 * This class is designed to dispatch and register events accordingly.
 * @author Tyler Bucher
 */
public class EventDispatcher {
    
    private final Map<Class<?>, Object> pluginMap;                          // The hash map of plugins and their methods that have the EventHandler annotation
    private final Map<Class<? extends Event>, List<Method>> eventMap;     // The hash map of events and their corresponding methods
    
    /**
     * Creates a new EventDispatcher, there should only be one per game instance
     * unless otherwise specified.
     */
    public EventDispatcher() {
        pluginMap = new HashMap<>();
        eventMap = new HashMap<>();
    }
    
    
    /**
     * Dispatches events to the proper plugins and methods.
     * @param event - The event to dispatch
     */
    public void dispatchEvent(Event event) {
        // Dispatch low-level events
        Class<?> clazz = event.getClass();
        while(Event.class.isAssignableFrom(clazz)) {
            this.subDispatchEvent(clazz, event);
            clazz = clazz.getSuperclass();
        }
    }
    
    /**
     * Dispatches a event to a specific event class.
     * @param clazz - The receiving class of the event
     * @param event - The event to be passed
     */
    private void subDispatchEvent(Class<?> clazz, Event event) {
        List<Method> methodList = eventMap.get(clazz);
        if(methodList != null) {
            for(Method m : methodList) {
                try {
                    m.invoke(pluginMap.get(m.getDeclaringClass()), event);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    Logger.severe("Could not invoke method: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Registers a plugin and its methods to the EventDispatcher.
     * @param listener - The instance of a class that has the EventHandler
     * annotation attached to at least one method
     */
    public void registerEvents(Class<?> eventClass) {
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
        // Attempts to create and add new instance to pluginMap
        try {
            pluginMap.put(eventClass, eventClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            Logger.severe(e.getMessage());
        }
        // Adds the methods the Event map
        for(Method m : tempMethods)
            eventMap.get(m.getAnnotation(EventHandler.class).value()).add(m);
    }
}
