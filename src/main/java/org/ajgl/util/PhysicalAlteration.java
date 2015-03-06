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
package org.ajgl.util;

/**
 * This class is designed to aid in physical object transformations.
 * @author Tyler Bucher
 */
public class PhysicalAlteration {
    
    /**
     * Translates the vertices by x and y.
     * @param position - The position of the vertices
     * @param x - The x displacement
     * @param y - The y displacement
     * @return The translated position
     */
    public static float[] Translate(float[] position, float x, float y) {
        float[] tmp = new float[position.length];
        for (int i = 0; i < position.length - 1; i += 2) {
            tmp[i] = position[i] += x;
            tmp[i + 1] = position[i + 1] += y;
        }
        return tmp;
    }
    
    /**
     * Mathematically rotate the given 2d vertices by the given angle.
     * @param theta - The angle to rotate the vertices
     * @return The (x, y) coordinate accordingly as assumed in the position of
     * the shape
     */
    public static float[] rotateMatrix(float[] position, float theta) {
        float[] tmp = new float[position.length];
        for (int i = 0; i < position.length - 1; i += 2) {
            tmp[i] = (float) ((position[i] * Math.cos(Math.toRadians(theta))) - (position[i + 1] * Math.sin(Math.toRadians(theta))));
            tmp[i + 1] = (float) ((position[i] * Math.sin(Math.toRadians(theta))) + (position[i + 1] * Math.cos(Math.toRadians(theta))));
        }
        return tmp;
    }
    
    /**
     * Rotates the given vertices by the given angle.
     * @param theta - The angle to rotate the vertices by
     * @return Only the rotated x coordinate
     */
    public static float[] rotateMatrixX(float[] position, float theta) {
        float[] tmp = new float[position.length / 2];
        for (int i = 0, j = 0; i < position.length - 1; i += 2) {
            tmp[j++] = (float) ((position[i] * Math.cos(Math.toRadians(theta))) - (position[i + 1] * Math.sin(Math.toRadians(theta))));
        }
        return tmp;
    }
    
    /**
     * Rotates the given vertices by the given angle.
     * @param theta - The angle to rotate the vertices by
     * @return Only the rotated y coordinate
     */
    public static float[] rotateMatrixY(float[] position, float theta) {
        float[] tmp = new float[position.length / 2];
        for (int i = 0, j = 0; i < position.length - 1; i += 2) {
            tmp[j++] = (float) ((position[i] * Math.sin(Math.toRadians(theta))) + (position[i + 1] * Math.cos(Math.toRadians(theta))));
        }
        return tmp;
    }
}
