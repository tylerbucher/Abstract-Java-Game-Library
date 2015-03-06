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
 * This class is designed to dispatch a mouse move event.
 * @author Tyler Bucher
 */
public class MouseMoveEvent extends MouseEvent {
    
    private float dx, dy;       // The displacement for the mouse in the x and y direction

    /**
     * Creates a new mouse move event.
     * @param dx - The displacement in the x direction
     * @param dy - The displacement in the y direction
     */
    public MouseMoveEvent(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the displacement in the x direction
     * @return The displacement in the x direction
     */
    public float getDx() {
        return dx;
    }

    /**
     * Returns the displacement in the y direction
     * @return The displacement in the y direction
     */
    public float getDy() {
        return dy;
    }
    
    @Override
    public String getName() {
        return "MouseMoveEvent";
    }
}
