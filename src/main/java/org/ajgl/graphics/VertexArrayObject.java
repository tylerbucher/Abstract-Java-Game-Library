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
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.ajgl.OpenGLInfo;
import org.ajgl.graphics.UtilAnnotations.GlBufferTarget;
import org.ajgl.graphics.UtilAnnotations.GlDataType;
import org.ajgl.graphics.UtilAnnotations.GlDrawMode;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


/**
 * This class is designed to be supporting class for Vertex Buffered Objects.
 * This class encapsulates the vertex buffered object, and does not directly
 * draw any objects.
 * @author Tyler Bucher
 */
public class VertexArrayObject {
    
    /**
     * Creates a vertex array handler.
     * @return The int value of the handler
     */
    @OpenGLInfo(doc = false, openGLVersion = "3.0", profile = "OPENGL_CORE_PROFILE")
    public static int createVaoHandler() {
        return GL30.glGenVertexArrays();
    }
    
    /**
     * Binds a Vertex array object.
     * @param arrayHandler - The vertex array handler to be bound.
     */
    @OpenGLInfo(doc = false, openGLVersion = "3.0", profile = "OPENGL_CORE_PROFILE")
    public static void bindVao(int arrayHandler) {
        GL30.glBindVertexArray(arrayHandler);
    }
    
    /**
     * Creates a vertex buffer object handler.
     * @param drawMode - The OpenGL draw mode of the object.
     * @param vertices - The vertices of the object
     * @return The int value of the handler. 
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_CORE_PROFILE")
    public static int createVboHandler(@GlBufferTarget int bufferTarget, @GlDrawMode int drawMode, ByteBuffer vertices, 
           int index, int size, @GlDataType int type, boolean normalized, int stride, int pointerOffset) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(bufferTarget, handler);
        GL15.glBufferData(bufferTarget, vertices, drawMode);
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointerOffset);
        GL15.glBindBuffer(bufferTarget, 0);
        return handler;
    }
    
    /**
     * Creates a vertex buffer object handler.
     * @param drawMode - The OpenGL draw mode of the object.
     * @param vertices - The vertices of the object
     * @return The int value of the handler. 
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_CORE_PROFILE")
    public static int createVboHandler(@GlBufferTarget int bufferTarget, @GlDrawMode int drawMode, ShortBuffer vertices, 
            int index, int size, @GlDataType int type, boolean normalized, int stride, int pointerOffset) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(bufferTarget, handler);
        GL15.glBufferData(bufferTarget, vertices, drawMode);
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointerOffset);
        GL15.glBindBuffer(bufferTarget, 0);
        return handler;
    }
    
    /**
     * Creates a vertex buffer object handler.
     * @param drawMode - The OpenGL draw mode of the object.
     * @param vertices - The vertices of the object
     * @return The int value of the handler. 
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_CORE_PROFILE")
    public static int createVboHandler(@GlBufferTarget int bufferTarget, @GlDrawMode int drawMode, IntBuffer vertices, 
            int index, int size, @GlDataType int type, boolean normalized, int stride, int pointerOffset) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(bufferTarget, handler);
        GL15.glBufferData(bufferTarget, vertices, drawMode);
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointerOffset);
        GL15.glBindBuffer(bufferTarget, 0);
        return handler;
    }
    
    /**
     * Creates a vertex buffer object handler.
     * @param drawMode - The OpenGL draw mode of the object.
     * @param vertices - The vertices of the object
     * @return The int value of the handler. 
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_CORE_PROFILE")
    public static int createVboHandler(@GlBufferTarget int bufferTarget, @GlDrawMode int drawMode, FloatBuffer vertices, 
            int index, int size, @GlDataType int type, boolean normalized, int stride, int pointerOffset) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(bufferTarget, handler);
        GL15.glBufferData(bufferTarget, vertices, drawMode);
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointerOffset);
        GL15.glBindBuffer(bufferTarget, 0);
        return handler;
    }
    
    /**
     * Creates a vertex buffer object handler.
     * @param drawMode - The OpenGL draw mode of the object.
     * @param vertices - The vertices of the object
     * @return The int value of the handler. 
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_CORE_PROFILE")
    public static int createVboHandler(@GlBufferTarget int bufferTarget, @GlDrawMode int drawMode, DoubleBuffer vertices, 
            int index, int size, @GlDataType int type, boolean normalized, int stride, int pointerOffset) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(bufferTarget, handler);
        GL15.glBufferData(bufferTarget, vertices, drawMode);
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointerOffset);
        GL15.glBindBuffer(bufferTarget, 0);
        return handler;
    }
}
