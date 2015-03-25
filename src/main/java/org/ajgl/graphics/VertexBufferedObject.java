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
import org.ajgl.graphics.UtilAnnotations.GlBeginMode;
import org.ajgl.graphics.UtilAnnotations.GlBufferFunction;
import org.ajgl.graphics.UtilAnnotations.GlDataType;
import org.ajgl.graphics.UtilAnnotations.GlDrawMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;


/**
 * This class draws shapes to the screen through OpenGL. The 
 * rendering method that this class uses is "Vertex Buffered Objects". 
 * Vertex Buffered Objects are often used in modern OpenGL.
 * @author Tyler Bucher
 */
@GlBufferFunction
public final class VertexBufferedObject {
    
    /**
     * Creates a vertex buffer object handler.
     * @param drawMode - The OpenGL draw mode of the object.
     * @param vertices - The vertices of the object
     * @return The int value of the handler. 
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_CORE_PROFILE")
    public static int createVboHandler(int bufferTarget, @GlDrawMode int drawMode, FloatBuffer vertices) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(bufferTarget, handler);
        GL15.glBufferData(bufferTarget, vertices, drawMode);
        GL15.glBindBuffer(bufferTarget, 0);
        return handler;
    }
    
    /**
     * Draws a vertex buffered object; Uses redundant vertices.
     * @param beginMode - The OpenGL begin mode
     * @param first - The start point of the array
     * @param vertexNumber - The number of vertices
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_CORE_PROFILE")
    public static void drawVboArrays(@GlBeginMode int beginMode, int first, int vertexNumber) {
        // Draw vertex buffer object
        GL11.glDrawArrays(beginMode, first, vertexNumber);
    }
    
    /**
     * Draws a vertex buffered object; Does not use redundant vertices.
     * @param beginMode - The OpenGL begin mode
     * @param vertexNumber - The number of vertices
     * @param dataType - The OpenGL dataType
     * @param offSet - initial offset for the data
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_CORE_PROFILE")
    public static void drawVboElements(@GlBeginMode int beginMode, int vertexNumber, @GlDataType int dataType, int offSet) {
        // Draw vertex buffer object
        GL11.glDrawElements(beginMode, vertexNumber, dataType, offSet);
    }
    
}
