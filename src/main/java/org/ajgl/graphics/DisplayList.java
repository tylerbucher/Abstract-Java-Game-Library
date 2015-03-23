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
import org.ajgl.graphics.UtilAnnotations.CompileMode;
import org.ajgl.graphics.UtilAnnotations.GLDataType;
import org.lwjgl.opengl.GL11;


/**
 * This class draws shapes to the screen through OpenGL. The 
 * rendering method that this class uses is "Display Lists". This 
 * class does not draw shapes it wraps other rendering functions 
 * in a list for faster speed. This class has been deprecated since 
 * OpenGL 3.0.
 * @author Tyler Bucher
 */
@Deprecated
public final class DisplayList {
    
    /**
     * Creates a display list handler based on the number
     * of display lists.
     * @param number - The number of display lists to create
     * @return The display list handler.
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_COMPAT_PROFILE")
    public static int createDisplayListHandler(int number) {
        return GL11.glGenLists(number);
    }
    
    /**
     * Creates a new list definition.
     * @param listID - The display list handler
     * @param compileMode - The OpenGL compile mode
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_COMPAT_PROFILE")
    public static void newList(int listID, @CompileMode int compileMode) {
        GL11.glNewList(listID, compileMode);
    }
    
    /**
     * Ends the current list definition.
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_COMPAT_PROFILE")
    public static void endList() {
        GL11.glEndList();
    }
    
    /**
     * Draws a single display list.
     * @param listID - The display list handler.
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_COMPAT_PROFILE")
    public static void drawList(int listID) {
        GL11.glCallList(listID);
    }
    
    /**
     * Draws many display Lists
     * @param listID - The display list handler
     * @param number - The number of lists to draw
     * @param dataType - The OpenGL Data type
     * @param lists - The lists to draw
     */
    @OpenGLInfo(doc = false, openGLVersion = "1.1", profile = "OPENGL_COMPAT_PROFILE")
    public static void drawLists(int listID, int number, @GLDataType int dataType, ByteBuffer lists) {
        GL11.glListBase(listID);
        GL11.glCallLists(number, dataType, lists);
    }
}
