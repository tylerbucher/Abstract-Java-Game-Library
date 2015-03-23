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

package org.ajgl.graphics;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.ajgl.OpenGLInfo;
import org.ajgl.graphics.UtilAnnotations.BeginMode;
import org.ajgl.graphics.UtilAnnotations.GLDataType;
import org.lwjgl.opengl.GL11;


/**
 * This class draws shapes to the screen through OpenGL. The 
 * rendering method that this class uses is "Vertex Arrays". Please 
 * note that of this moment (03/8/2015) OpenGL has not deprecated vertex 
 * arrays. But they are not often used in modern OpenGL.
 * @author Tyler Bucher
 */
public final class VertexArrays {
    
    /**
     * Sets the color pointer of the vertex array. You need to enable 
     * {@link org.lwjgl.opengl.GL11#GL_COLOR_ARRAY GL_COLOR_ARRAY} before 
     * you can use this method.
     * @param size - The number of points per color data (i.e. 3-RGB, 4-RGBA)
     * @param stride - The stride offset; used for interleaving data
     * @param vertices - The color vertices
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_COMPAT_PROFILE")
    public static void colorPointer(int size, int stride, FloatBuffer vertices) {
        GL11.glColorPointer(size, stride, vertices);
    }
    
    /**
     * Sets the texture pointer of the vertex array. You need to enable 
     * {@link org.lwjgl.opengl.GL11#GL_TEXTURE_COORD_ARRAY GL_TEXTURE_COORD_ARRAY} before 
     * you can use this method.
     * @param size - The number of points per texture data (i.e. 1-1D, 2-2D, 3-3D)
     * @param stride - The stride offset; used for interleaving data
     * @param vertices - The texture vertices
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_COMPAT_PROFILE")
    public static void texturePointer(int size, int stride, FloatBuffer vertices) {
        GL11.glTexCoordPointer(size, stride, vertices);
    }
    
    /**
     * Sets the vertex pointer of the vertex array. You need to enable 
     * {@link org.lwjgl.opengl.GL11#GL_VERTEX_ARRAY GL_VERTEX_ARRAY} before 
     * you can use this method.
     * @param vertexPointData - The number of points per vertex data (i.e. 1-1D, 2-2D, 3-3D)
     * @param stride - The stride offset; used for interleaving data
     * @param vertices - The vertex vertices
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_COMPAT_PROFILE")
    public static void vertexPointer(int vertexPointData, int stride, FloatBuffer vertices) {
        // point to and draw vertex array
        GL11.glVertexPointer(vertexPointData, stride, vertices);
    }
    
    /**
     * Draws the vertex array; Uses redundant vertices.
     * @param beginMode - The OpenGL begin mode
     * @param vertexNumber - The number of vertices
     * @param first - The start point of the array
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_COMPAT_PROFILE")
    public static void drawArrays(@BeginMode int beginMode, int vertexNumber, int first) {
        GL11.glDrawArrays(beginMode, first, vertexNumber);
    }
    
    /**
     * Draws the vertex array; Does not use redundant vertices.
     * @param beginMode - The OpenGL begin mode
     * @param dataType - The OpenGL dataType
     * @param vertexNumber - The number of vertices
     * @param indices - The index vertices
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_COMPAT_PROFILE")
    public static void drawElements(@BeginMode int beginMode, @GLDataType int dataType, int vertexNumber, ByteBuffer indices) {
        GL11.glDrawElements(beginMode, vertexNumber, dataType, indices);
    }
}
