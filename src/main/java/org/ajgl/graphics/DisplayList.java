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

import org.ajgl.graphics.UtilAnnotations.GlCompileMode;
import org.ajgl.graphics.UtilAnnotations.GlDataType;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

/**
 * This class draws shapes to the screen through OpenGL. The
 * rendering method that this class uses is "Display Lists". This
 * class does not draw shapes it wraps other rendering functions
 * in a list for faster speed. This rendering method has been deprecated since
 * OpenGL 3.0.
 *
 * @author Tyler Bucher
 */
public final class DisplayList {

    /**
     * @param number the number of display lists to create.
     * @return a display list handler based on the number
     * of display lists.
     */
    public static int createDisplayListHandler(int number) {
        return GL11.glGenLists(number);
    }

    /**
     * Creates a new list definition.
     *
     * @param listID      the display list handler.
     * @param compileMode the OpenGL compile mode.
     * @see GlCompileMode
     */
    public static void newList(int listID, @GlCompileMode int compileMode) {
        GL11.glNewList(listID, compileMode);
    }

    /**
     * Ends the current list definition.
     */
    public static void endList() {
        GL11.glEndList();
    }

    /**
     * Draws a single display list.
     *
     * @param listID the display list handler.
     */
    public static void drawList(int listID) {
        GL11.glCallList(listID);
    }

    /**
     * Draws many display Lists.
     *
     * @param listID   the display list handler.
     * @param dataType the OpenGL Data type.
     * @param lists    the lists to draw.
     * @see GlDataType
     */
    public static void drawLists(int listID, @GlDataType int dataType, ByteBuffer lists) {
        GL11.glListBase(listID);
        GL11.glCallLists(dataType, lists);
    }
}
