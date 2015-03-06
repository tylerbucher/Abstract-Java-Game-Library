/**
 * 
 */
package org.ajgl.test;

import org.ajgl.event.Event;
import org.ajgl.primary.GameObject;

/**
 * Test the implementation of the event
 * @author Tyler Bucher
 */
public class VboMoveEvent extends Event {
    
    private boolean canceled;       // States weather the event has been canceled
    private GameObject object;      // The object of importance
    private float displacement;     // The displacement of the object
    
    /**
     * 
     * The constructor for the event
     * @param object The object of importance
     * @param displacement The displacement of the object
     */
    public VboMoveEvent(GameObject object, float displacement) {
        this.object = object;
        this.displacement = displacement;
    }
    
    /**
     * @return the object
     */
    public GameObject getObject() {
        return object;
    }
    
    /**
     * @return the displacement
     */
    public float getDisplacement() {
        return displacement;
    }
    
    @Override
    public String getName() {
        return "VboMoveEvent";
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }
    
}
