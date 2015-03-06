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
package org.ajgl.graphics;

import org.ajgl.primary.GameObject;
import org.lwjgl.opengl.GL11;

/**
 * This class is designed to alter 2D objects.
 * @author Tyler Bucher
 */
public class GraphicAlteration {
    
    /**
     * Graphically rotates a GameObject by the given angle around its center.
     * @param object - The GameObject to be rotated
     * @param angle - The angle to rotate the GameObject
     */
    public static void rotate(GameObject object, float angle) {
        GL11.glTranslatef(-object.getOrigin().x, -object.getOrigin().y, 0);
        GL11.glRotatef(angle, 0, 0, 1);
        GL11.glTranslatef(object.getOrigin().x, object.getOrigin().y, 0);
    }
    
    /**
     * Graphically translate the current OpenGL context.
     * @param dx - The displacement in the x direction
     * @param dy - The displacement in the y direction
     */
    public static void translate(float dx, float dy) {
        GL11.glTranslatef(dx, dy, 0);
    }
}
