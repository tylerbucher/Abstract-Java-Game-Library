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

/**
 * This class was designed to be extended by other higher level Events.
 * @author Tyler Bucher
 */
public abstract class Event {
    
    /**
     * Sets the event canceled or not.
     * @param canceled - True to set the event canceled, false otherwise
     */
    public abstract void setCanceled(boolean canceled);
    
    /**
     * Returns true if the Event was canceled.
     * @return True if the event was canceled
     */
    public abstract boolean isCanceled();
    
    /**
     * Returns the given name of the event.
     * @return The name of the event
     */
    public abstract String getName();
}
