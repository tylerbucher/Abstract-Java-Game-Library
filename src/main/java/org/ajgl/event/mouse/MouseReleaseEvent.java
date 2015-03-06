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
package org.ajgl.event.mouse;


/**
 * This class is designed to dispatch a mouse release event.
 * @author Tyler Bucher
 */
public class MouseReleaseEvent extends MouseEvent {
    
    private int buttonReleased;     // The integer value of the button released
    
    /**
     * Creates a new mouse release event.
     * @param buttonReleased - The button released
     */
    public MouseReleaseEvent(int buttonReleased) {
        this.buttonReleased = buttonReleased;
    }

    /**
     * Returns the mouse button that was released.
     * @return The mouse button that was released
     */
    public int getButtonReleased() {
        return buttonReleased;
    }
    
    @Override
    public String getName() {
        return "MouseReleaseEvent";
    }
    
}
