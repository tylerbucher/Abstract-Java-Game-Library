/**
 * 
 */
package org.ajgl.test.concurrent;

import org.ajgl.concurrent.Event;
import org.ajgl.concurrent.EventHandler;


/**
 * @author Tyler
 *
 */
public class EventTest {
    
    @EventHandler(priority = 1, value = Event.class)
    public static void eventMethod(Event event) {
        System.out.println("Event Name: "+event.name);
    }
}
