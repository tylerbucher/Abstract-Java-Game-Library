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
import org.ajgl.graphics.Graphics.BeginMode;
import org.lwjgl.opengl.GL11;


/**
 * @author Tyler Bucher
 */
@Deprecated
public class Immediate {
    
    /**
     * Draws a shape using glBegin / Primitive mode.
     * @param beginMode - OpenGL begin mode
     * @param values - Array of vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void twoPointdraw(@BeginMode int beginMode, float... values) {
        GL11.glBegin(beginMode);
        for(int i=0;i<values.length-1;i+=2)
            GL11.glVertex2f(values[i], values[i+1]);
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode.
     * @param beginMode - OpenGL begin mode
     * @param values - Array of vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void threePointdraw(@BeginMode int beginMode, float... values) {
        GL11.glBegin(beginMode);
        for(int i=0;i<values.length-2;i+=3)
            GL11.glVertex3f(values[i], values[i+1], values[i+2]);
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode.
     * @param beginMode - OpenGL begin mode
     * @param values - Array of vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void fourPointdraw(@BeginMode int beginMode, float... values) {
        GL11.glBegin(beginMode);
        for(int i=0;i<values.length-3;i+=4)
            GL11.glVertex4f(values[i], values[i+1], values[i+2], values[i+3]);
        GL11.glEnd();
    }
}
