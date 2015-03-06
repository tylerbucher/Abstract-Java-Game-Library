/**
 * 
 */
package org.ajgl.test;

import org.ajgl.Logger;
import org.ajgl.event.EventHandler;

/**
 * This class is designed to test basic event abstraction
 * @author Tyler
 */
public final class EventTest {
    
    /**
     * 
     * Logges when a VboMoveEvent is fired
     * @param event
     */
    @EventHandler(VboMoveEvent.class)
    public void onVboMoveEvent(VboMoveEvent event) {
        Logger.log("moveing: " + event.getDisplacement());
    }
}
