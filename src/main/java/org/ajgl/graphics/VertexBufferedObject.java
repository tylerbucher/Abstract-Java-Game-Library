/*
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

import org.ajgl.graphics.UtilAnnotations.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.nio.*;

/**
 * This class draws shapes to the screen through OpenGL. The
 * rendering method that this class uses is "Vertex Buffered Objects".
 * Vertex Buffered Objects are often used in modern OpenGL.
 *
 * @author Tyler Bucher
 * @see GlBufferFunction
 */
@GlBufferFunction
public final class VertexBufferedObject {

    /**
     * Creates a vertex buffer object handler.
     *
     * @param bufferTarget the {@link GlBufferTarget buffer target} to use.
     * @param drawMode     the OpenGL draw mode of the object.
     * @param vertices     the vertices of the object.
     * @return the vertex buffer object handler.
     *
     * @see GlBufferTarget
     * @see GlDrawMode
     */
    @SuppressWarnings ("Duplicates")
    public static int createVboHandler(@GlBufferTarget int bufferTarget, @GlDrawMode int drawMode, ByteBuffer vertices) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(bufferTarget, handler);
        GL15.glBufferData(bufferTarget, vertices, drawMode);
        GL15.glBindBuffer(bufferTarget, 0);
        return handler;
    }

    /**
     * Creates a vertex buffer object handler.
     *
     * @param bufferTarget the {@link GlBufferTarget buffer target} to use.
     * @param drawMode     the OpenGL draw mode of the object.
     * @param vertices     the vertices of the object.
     * @return the vertex buffer object handler.
     *
     * @see GlBufferTarget
     * @see GlDrawMode
     */
    @SuppressWarnings ("Duplicates")
    public static int createVboHandler(@GlBufferTarget int bufferTarget, @GlDrawMode int drawMode, ShortBuffer vertices) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(bufferTarget, handler);
        GL15.glBufferData(bufferTarget, vertices, drawMode);
        GL15.glBindBuffer(bufferTarget, 0);
        return handler;
    }

    /**
     * Creates a vertex buffer object handler.
     *
     * @param bufferTarget the {@link GlBufferTarget buffer target} to use.
     * @param drawMode     the OpenGL draw mode of the object.
     * @param vertices     the vertices of the object.
     * @return the vertex buffer object handler.
     *
     * @see GlBufferTarget
     * @see GlDrawMode
     */
    @SuppressWarnings ("Duplicates")
    public static int createVboHandler(@GlBufferTarget int bufferTarget, @GlDrawMode int drawMode, IntBuffer vertices) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(bufferTarget, handler);
        GL15.glBufferData(bufferTarget, vertices, drawMode);
        GL15.glBindBuffer(bufferTarget, 0);
        return handler;
    }

    /**
     * Creates a vertex buffer object handler.
     *
     * @param bufferTarget the {@link GlBufferTarget buffer target} to use.
     * @param drawMode     the OpenGL draw mode of the object.
     * @param vertices     the vertices of the object.
     * @return the vertex buffer object handler.
     *
     * @see GlBufferTarget
     * @see GlDrawMode
     */
    @SuppressWarnings ("Duplicates")
    public static int createVboHandler(@GlBufferTarget int bufferTarget, @GlDrawMode int drawMode, FloatBuffer vertices) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(bufferTarget, handler);
        GL15.glBufferData(bufferTarget, vertices, drawMode);
        GL15.glBindBuffer(bufferTarget, 0);
        return handler;
    }

    /**
     * Creates a vertex buffer object handler.
     *
     * @param bufferTarget the {@link GlBufferTarget buffer target} to use.
     * @param drawMode     the OpenGL draw mode of the object.
     * @param vertices     the vertices of the object.
     * @return the vertex buffer object handler.
     *
     * @see GlBufferTarget
     * @see GlDrawMode
     */
    @SuppressWarnings ("Duplicates")
    public static int createVboHandler(@GlBufferTarget int bufferTarget, @GlDrawMode int drawMode, DoubleBuffer vertices) {
        // Initialize vertex VBO handler
        int handler = GL15.glGenBuffers();
        GL15.glBindBuffer(bufferTarget, handler);
        GL15.glBufferData(bufferTarget, vertices, drawMode);
        GL15.glBindBuffer(bufferTarget, 0);
        return handler;
    }

    /**
     * Draws a vertex buffered object; Uses redundant vertices.
     *
     * @param beginMode    the OpenGL begin mode.
     * @param first        the start point of the array.
     * @param vertexNumber the number of vertices.
     * @see GlBeginMode
     */
    public static void drawVboArrays(@GlBeginMode int beginMode, int first, int vertexNumber) {
        // Draw vertex buffer object
        GL11.glDrawArrays(beginMode, first, vertexNumber);
    }

    /**
     * Draws a vertex buffered object; Does not use redundant vertices.
     *
     * @param beginMode    the OpenGL begin mode.
     * @param vertexNumber the number of vertices.
     * @param dataType     the OpenGL dataType.
     * @param offSet       initial offset for the data.
     * @see GlBeginMode
     * @see GlDataType
     */
    public static void drawVboElements(@GlBeginMode int beginMode, int vertexNumber, @GlDataType int dataType, int offSet) {
        // Draw vertex buffer object
        GL11.glDrawElements(beginMode, vertexNumber, dataType, offSet);
    }
}
