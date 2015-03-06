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

import static org.lwjgl.opengl.GL11.GL_COLOR_ARRAY;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColorPointer;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;
import java.util.Vector;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

/**
 * This class is designed to handle Vertex Buffered Object creation and drawing.
 * @author Tyler Bucher
 */
public final class Graphics {
    
    private static final Vector<Integer> vboArray = new Vector<Integer>();    // An array of all of the Vertex Buffered Objects
    
    /**
     * Creates the Vertex Buffered Object specified by vboPoints.
     * @param vboPoints - The float array of vertex coordinates for the vertex buffered object; n(x, y, z)
     * @return The id of the Vertex Buffered Object
     */
    public static int createVboHandler(float[] vboPoints) {
        // Create data vertices
        FloatBuffer vertexData = BufferUtils.createFloatBuffer(vboPoints.length);
        vertexData.put(vboPoints);
        vertexData.flip();
        // Initialize vertex VBO handler
        int vboVertexHandle = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glBufferData(GL_ARRAY_BUFFER, vertexData, GL_DYNAMIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        // Add the handler to a class array for memory leakage proposes
        vboArray.add(vboVertexHandle);
        return vboVertexHandle;
    }
    
    /**
     * Draws as shape using vertex buffered objects.
     * @param vertexHandler - The integer value associated with the vertex data handler
     * @param colorHandler - The integer value associated with the color data handler
     * @param numVertexPoint - The number of points per vertex data (i.e. 1-1D, 2-2D, 3-3D)
     * @param numColorPoint - The number of points per color data (i.e. 3-RGB, 4-RGBA)
     * @param vertices - The number of vertices the shape contains
     * @param GL_SHAPE_TYPE - The Open-GL shape type
     */
    public static void drawShape(int vertexHandler, int colorHandler, int numVertexPoint, int numColorPoint, int vertices, int GL_SHAPE_TYPE) {
        // Enable the vertex and color arrays
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        // Bind the vertex data handler to the array
        glBindBuffer(GL_ARRAY_BUFFER, vertexHandler);
        glVertexPointer(numVertexPoint, GL_FLOAT, 0, 0L);
        // Bind the color data handler to the array
        glBindBuffer(GL_ARRAY_BUFFER, colorHandler);
        glColorPointer(numColorPoint, GL_FLOAT, 0, 0L);
        // Attempt to draw the shape
        glDrawArrays(GL_SHAPE_TYPE, 0, vertices);
        // Disable the vertex and color arrays
        glDisableClientState(GL_COLOR_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);
    }
    
    /**
     * Draws as shape using vertex buffered objects.
     * @param vertexHandler - The integer value associated with the vertex data handler
     * @param textureHandler - The integer value associated with the texture data handler
     * @param numVertexPoint - The number of points per vertex data (i.e. 1-1D, 2-2D, 3-3D)
     * @param numTexturePoint - The number of points per Texture (i.e. 1-1D, 2-2D, 3-3D)
     * @param vertices - The number of vertices the shape contains
     * @param GL_SHAPE_TYPE - The Open-GL shape type
     * @param texture - The texture object associated with the texture
     */
    public static void drawShape(int vertexHandler, int textureHandler, int numVertexPoint, int numTexturePoint, int vertices, int GL_SHAPE_TYPE, Texture texture) {
        glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        // Enable the vertex and color arrays
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        // Bind the vertex data handler to the array
        glBindBuffer(GL_ARRAY_BUFFER, vertexHandler);
        glVertexPointer(numVertexPoint, GL_FLOAT, 0, 0L);
        // Bind the texture data handler to the array
        glBindBuffer(GL_ARRAY_BUFFER, textureHandler);
        GL11.glTexCoordPointer(numTexturePoint, GL_FLOAT, 0, 0L);
        // Attempt to draw the shape
        glDrawArrays(GL_SHAPE_TYPE, 0, vertices);
        // Disable the vertex and color arrays
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);
    }
    
    /**
     * Draws as shape using vertex buffered objects.
     * @param vertexHandler - The integer value associated with the vertex data handler
     * @param colorHandler - The integer value associated with the color data handler
     * @param textureHandler - The integer value associated with the texture data handler
     * @param numVertexPoint - The number of points per vertex data (i.e. 1-1D, 2-2D, 3-3D)
     * @param numColorPoint - The number of points per color data (i.e. 3-RGB, 4-RGBA)
     * @param numTexturePoint - The number of points per Texture (i.e. 1-1D, 2-2D, 3-3D)
     * @param vertices - The number of vertices the shape contains
     * @param GL_SHAPE_TYPE - The Open-GL shape type
     * @param texture - The texture object associated with the texture
     */
    public static void drawShape(int vertexHandler, int colorHandler, int textureHandler, int numVertexPoint, int numColorPoint, int numTexturePoint, int vertices, int GL_SHAPE_TYPE, Texture texture) {
        glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        // Enable the vertex and color arrays
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        // Bind the vertex data handler to the array
        glBindBuffer(GL_ARRAY_BUFFER, vertexHandler);
        glVertexPointer(numVertexPoint, GL_FLOAT, 0, 0L);
        // Bind the texture data handler to the array
        glBindBuffer(GL_ARRAY_BUFFER, textureHandler);
        GL11.glTexCoordPointer(numColorPoint, GL_FLOAT, 0, 0L);
        // Bind the color data handler to the array
        glBindBuffer(GL_ARRAY_BUFFER, colorHandler);
        glColorPointer(numColorPoint, GL_FLOAT, 0, 0L);
        // Attempt to draw the shape
        glDrawArrays(GL_SHAPE_TYPE, 0, vertices);
        // Disable the vertex and color arrays
        glDisableClientState(GL_COLOR_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);
    }
    
    /**
     * Deletes/removes the specified vertex buffered object handler.
     */
    public static boolean clearVboHandler(int handler) {
        if (vboArray.contains(handler)) {
            glDeleteBuffers(handler);
            vboArray.remove(handler);
            return true;
        }
        return false;
    }
    
    /**
     * Deletes/removes all of the vertex buffered object handlers.
     */
    public static void clearVboHandlers() {
        for (int e : vboArray)
            glDeleteBuffers(e);
        vboArray.clear();
    }
}
