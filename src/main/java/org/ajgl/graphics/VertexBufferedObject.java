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

import java.nio.FloatBuffer;

import org.ajgl.OpenGLInfo;
import org.ajgl.graphics.UtilAnnotations.BeginMode;
import org.ajgl.graphics.UtilAnnotations.DrawMode;
import org.ajgl.graphics.UtilAnnotations.GLDataType;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;


/**
 * @author Tyler Bucher
 */
public final class VertexBufferedObject {
    
    /**
     * Creates a vertex buffer object handler.
     * @param drawMode - The OpenGL draw mode of the object.
     * @param vertices - The vertices of the object
     * @return The int value of the handler. 
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.5", status = "Release")
    public static int createVboHandler(@DrawMode int drawMode, FloatBuffer vertices) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, handler);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, drawMode);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        return handler;
    }
    
    /**
     * Binds the color vertices. You need to enable 
     * {@link org.lwjgl.opengl.GL11#GL_COLOR_ARRAY GL_COLOR_ARRAY} before 
     * you can use this method.
     * @param colorHandler - The vertex buffered object color handler
     * @param colorPointData - The number of points per color data (i.e. 3-RGB, 4-RGBA)
     * @param stride - The stride offset; used for interleaving data
     * @param offSet - initial offset for the data
     * @param dataType - The OpenGL dataType
     */
    public static void colorPointer(int colorHandler, int colorPointData, int stride, int offSet, @GLDataType int dataType) {
        // bind color data
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorHandler);
        GL11.glColorPointer(colorPointData, dataType, stride, offSet);
    }
    
    /**
     * Binds the local texture vertices. You need to enable 
     * {@link org.lwjgl.opengl.GL11#GL_TEXTURE_COORD_ARRAY GL_TEXTURE_COORD_ARRAY} before 
     * you can use this method.
     * @param textureHandler - The vertex buffered object texture handler
     * @param texturePointData - The number of points per texture data (i.e. 1-1D, 2-2D, 3-3D)
     * @param stride - The stride offset; used for interleaving data
     * @param offSet - initial offset for the data
     * @param dataType - The OpenGL dataType
     */
    public static void texturePointer(int textureHandler, int texturePointData, int stride, int offSet, @GLDataType int dataType) {
        // bind color data
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, textureHandler);
        GL11.glTexCoordPointer(texturePointData, dataType, stride, offSet);
    }
    
    /**
     * Draws a vertex buffered object; Uses redundant vertices. You need to enable 
     * {@link org.lwjgl.opengl.GL11#GL_VERTEX_ARRAY GL_VERTEX_ARRAY} before 
     * you can use this method.
     * @param vertexHandler - The vertex buffered object vertex handler
     * @param vertexPointData - The number of points per vertex data (i.e. 1-1D, 2-2D, 3-3D)
     * @param vertexNumber - The number of vertices
     * @param first - The start point of the array
     * @param stride - The stride offset; used for interleaving data
     * @param offSet - initial offset for the data
     * @param dataType - The OpenGL dataType
     * @param beginMode - The OpenGL begin mode
     */
    public static void drawVboArrays(int vertexHandler, int vertexPointData, int vertexNumber, int first, int stride, 
            int offSet, @GLDataType int dataType, @BeginMode int beginMode) {
        // bind vertex data
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexHandler);
        GL11.glVertexPointer(vertexPointData, dataType, stride, offSet);
        // Draw vertex buffer object
        GL11.glDrawArrays(beginMode, first, vertexNumber);
    }
    
    /**
     * Draws a vertex buffered object; Uses redundant vertices. You need to enable 
     * {@link org.lwjgl.opengl.GL11#GL_VERTEX_ARRAY GL_VERTEX_ARRAY} before 
     * you can use this method.
     * @param vertexHandler - The vertex buffered object vertex handler
     * @param vertexPointData - The number of points per vertex data (i.e. 1-1D, 2-2D, 3-3D)
     * @param vertexNumber - The number of vertices
     * @param stride - The stride offset; used for interleaving data
     * @param offSet - initial offset for the data
     * @param dataType - The OpenGL dataType
     * @param beginMode - The OpenGL begin mode
     */
    public static void drawVboElements(int vertexHandler, int vertexPointData, int vertexNumber, int stride, 
            int offSet, @GLDataType int dataType, @BeginMode int beginMode) {
        // bind vertex data
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexHandler);
        GL11.glVertexPointer(vertexPointData, dataType, stride, offSet);
        // Draw vertex buffer object
        GL11.glDrawElements(beginMode, vertexNumber, dataType, offSet);
    }
    
}
