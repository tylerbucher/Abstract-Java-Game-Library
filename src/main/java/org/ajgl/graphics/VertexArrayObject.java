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

import org.ajgl.OpenGLInfo;
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
     * @return The int value of the handler.
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
}
