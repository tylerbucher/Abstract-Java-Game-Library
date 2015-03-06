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
package org.ajgl.collision;

import org.lwjgl.util.vector.Vector2f;

/**
 * This class is designed to be a Utility class aiding in vector related tasks.
 * @author Tyler Bucher
 */
class Vector {
    
    /**
     * Creates a normal vector using the x and y components.
     * @param xCom - The x component
     * @param yCom - The y component
     * @return The normal vector
     */
    public static Vector2f normal(float xCom, float yCom) {
        return new Vector2f(yCom/xCom, -1);
    }
    
    /**
     * Creates a normal vector from two points.
     * @param point - The first reference point
     * @param point2 - The second reference point
     * @return The normal vector
     */
    public static Vector2f normal(Vector2f point, Vector2f point2) {
        float slope = (point2.y-point.y)/(point2.x-point.x);
        if(Float.isInfinite(slope)) 
            return new Vector2f(1, 0);
        return new Vector2f(slope, -1);
    }
}
