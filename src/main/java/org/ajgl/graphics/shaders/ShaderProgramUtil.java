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

package org.ajgl.graphics.shaders;

import org.lwjgl.opengl.GL20;


/**
 * @author Tyler
 *
 */
public final class ShaderProgramUtil {
    
    /**
     * Creates a new shader program in OpenGL.
     */
    public static int createProgram() {
        return GL20.glCreateProgram();
    }
    
    /**
     * Attaches a shader to a OpenGL shader program.
     * @param programID - The id of the OpenGL shader program.
     * @param shaderID - The id of the OpenGL shader.
     */
    public static void attachShader(int programID, int shaderID) {
        GL20.glAttachShader(programID, shaderID);
    }
    
    /**
     * Detaches a shader from a OpenGL shader program.
     * @param programID - The id of the OpenGL shader program.
     * @param shaderID - The id of the OpenGL shader.
     */
    public static void detachShader(int programID, int shaderID) {
        GL20.glDetachShader(programID, shaderID);
    }
    
    /**
     * Links a OpenGL shader program.
     */
    public static void link(int programID) {
        GL20.glLinkProgram(programID);
    }
    
    /**
     * Validates a OpenGL shader program.
     */
    public static void validate(int programID) {
        GL20.glValidateProgram(programID);
    }
}
