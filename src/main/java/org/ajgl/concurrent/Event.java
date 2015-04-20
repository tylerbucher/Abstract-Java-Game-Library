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


/**
 * This class was designed to be extended by other higher level Events.
 * @author Tyler Bucher
 */
public abstract class Event implements Comparable<Event>{
    
    private boolean canceled = false;   // States weather the event has been canceled
    public final String name;           // States the name of the event
    
    
    public Event() {
        this.name = "Event";
    }
    
    /**
     * Creates an event with a name and priority.
     * @param name - The name of the event.
     * @param priority - The priority of the event.
     */
    public Event(String name) {
        this.name = name;
    }
    
    /**
     * Sets the event canceled or uncanceled.
     * @param canceled - True to set the event canceled, false otherwise.
     */
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
    
    /**
     * Returns true if the Event was canceled.
     * @return True if the event was canceled.
     */
    public boolean isCanceled() {
        return canceled;
    }
}
