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

import org.ajgl.OpenGLInfo;
import org.ajgl.graphics.UtilAnnotations.GlBeginMode;
import org.ajgl.graphics.UtilAnnotations.GlBufferFunction;
import org.ajgl.graphics.UtilAnnotations.GlDataType;
import org.lwjgl.opengl.GL11;


/**
 * <p>This class draws shapes to the screen through OpenGL. The 
 * rendering method that this class uses is "Vertex Arrays". Please 
 * note that of this moment (03/8/2015) OpenGL has not deprecated vertex 
 * arrays. But they are not often used in modern OpenGL.</p>
 * <p>The VertexArrays class has been tagged with the GlBufferFunction
 * because it uses all of the same functions. There in one major difference 
 * that you should take not of, the difference is that you do not need to call
 * {@link org.lwjgl.opengl.GL15#glBindBuffer(int, int) glBindBuffer} for any 
 * reason. The reason is because vertex arrays pass the buffer directly to
 * the pointer function.</p>
 * @author Tyler Bucher
 */
@GlBufferFunction
public final class VertexArrays {
    
    /**
     * Draws the vertex array; Uses redundant vertices.
     * @param beginMode - The OpenGL begin mode
     * @param vertexNumber - The number of vertices
     * @param first - The start point of the array
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_COMPAT_PROFILE")
    public static void drawArrays(@GlBeginMode int beginMode, int vertexNumber, int first) {
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
    public static void drawElements(@GlBeginMode int beginMode, @GlDataType int dataType, int vertexNumber, ByteBuffer indices) {
        GL11.glDrawElements(beginMode, vertexNumber, dataType, indices);
    }
}
