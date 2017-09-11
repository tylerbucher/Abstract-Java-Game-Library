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

import org.ajgl.graphics.UtilAnnotations.GlBeginFunction;
import org.ajgl.graphics.UtilAnnotations.GlBeginMode;
import org.lwjgl.opengl.GL11;

/**
 * This class draws shapes to the screen through OpenGL. The
 * rendering method that this class uses is "Primitive". Please
 * note that this class should never be used unless you have some
 * god-awful reason to use it. This rendering method has also been
 * deprecated in modern OpenGL.
 *
 * @author Tyler Bucher
 * @see GlBeginFunction
 */
@GlBeginFunction
public final class Immediate {

    /**
     * Begin a new drawing mode.
     *
     * @param beginMode the {@link UtilAnnotations.GlBeginMode BeginMode} to use.
     * @see GlBeginMode
     */
    public static void beginDraw(@GlBeginMode int beginMode) {
        GL11.glBegin(beginMode);
    }

    /**
     * Ends the current drawing mode.
     */
    public static void endDraw() {
        GL11.glEnd();
    }
}
