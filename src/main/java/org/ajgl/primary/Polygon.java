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
package org.ajgl.primary;

/**
 * This class is intended to be implemented by object that are polygons.
 * @author Tyler Bucher
 */
public interface Polygon {
    
    /**
     * Returns the number of vertices the polygon has.
     * @return The number of vertices the polygon has
     */
    public int getVertCount();
    
    /**
     * Sets the vertices of the Polygon.
     * @param vertices - The vertices to be set
     */
    public void setVertices(float[] vertices);
    
    /**
     * Returns the vertices of the polygon.
     * @return The current vertices of the polygon
     */
    public float[] getVertices();
}
