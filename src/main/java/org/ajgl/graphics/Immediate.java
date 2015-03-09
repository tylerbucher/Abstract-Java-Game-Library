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
import org.ajgl.graphics.UtilAnnotations.BeginMode;
import org.lwjgl.opengl.GL11;


/**
 * This class draws shapes to the screen through OpenGL. The 
 * rendering method that this class uses is "Primitive". Please 
 * note that this class should never be used unless you have some
 * god-awful reason to use it.
 * @author Tyler Bucher
 */
@Deprecated
public final class Immediate {
    
    /**
     * Draws a shape using glBegin / Primitive mode.
     * @param beginMode - OpenGL begin mode
     * @param vertexValues - Array of vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void twoPointdraw(@BeginMode int beginMode, float... vertexValues) {
        GL11.glBegin(beginMode);
        for(int i=0;i<vertexValues.length-1;i+=2)
            GL11.glVertex2f(vertexValues[i], vertexValues[i+1]);
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode.
     * @param beginMode - OpenGL begin mode
     * @param vertexValues - Array of vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void threePointdraw(@BeginMode int beginMode, float... vertexValues) {
        GL11.glBegin(beginMode);
        for(int i=0;i<vertexValues.length-2;i+=3)
            GL11.glVertex3f(vertexValues[i], vertexValues[i+1], vertexValues[i+2]);
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode.
     * @param beginMode - OpenGL begin mode
     * @param vertexValues - Array of vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void fourPointdraw(@BeginMode int beginMode, float... vertexValues) {
        GL11.glBegin(beginMode);
        for(int i=0;i<vertexValues.length-3;i+=4)
            GL11.glVertex4f(vertexValues[i], vertexValues[i+1], vertexValues[i+2], vertexValues[i+3]);
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode. adds and color to it. 
     * Note that {@code colorValues} should have four values per vertex.
     * @param beginMode - OpenGL begin mode
     * @param vertexValues - Array of vertices
     * @param colorValues - Array of color vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void twoPointdraw(@BeginMode int beginMode, float[] vertexValues, float[] colorValues) {
        GL11.glBegin(beginMode);
        for(int i=0, j=0;i<vertexValues.length-1;i+=2, j+=4) {
            GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
            GL11.glVertex2f(vertexValues[i], vertexValues[i+1]);
        }
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode. And adds color to it. 
     * Note that {@code colorValues} should have four values per vertex.
     * @param beginMode - OpenGL begin mode
     * @param vertexValues - Array of vertices
     * @param colorValues - Array of color vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void threePointdraw(@BeginMode int beginMode, float[] vertexValues, float[] colorValues) {
        GL11.glBegin(beginMode);
        for(int i=0, j=0;i<vertexValues.length-2;i+=3, j+=4) {
            GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
            GL11.glVertex3f(vertexValues[i], vertexValues[i+1], vertexValues[i+2]);
        }
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode. And adds color to it. 
     * Note that {@code colorValues} should have four values per vertex.
     * @param beginMode - OpenGL begin mode
     * @param vertexValues - Array of vertices
     * @param colorValues - Array of color vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void fourPointdraw(@BeginMode int beginMode, float[] vertexValues, float[] colorValues) {
        GL11.glBegin(beginMode);
        for(int i=0, j=0;i<vertexValues.length-3;i+=4, j+=4){
            GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
            GL11.glVertex4f(vertexValues[i], vertexValues[i+1], vertexValues[i+2], vertexValues[i+3]);
        }
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode. And adds a texture to it.
     * @param beginMode - OpenGL begin mode
     * @param textureID - The texture id to be used
     * @param vertexValues - Array of vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void twoPointdraw(@BeginMode int beginMode, int textureID, float[] vertexValues, float[] textureValues) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        GL11.glBegin(beginMode);
        for(int i=0;i<vertexValues.length-1;i+=2) {
            GL11.glTexCoord2f(textureValues[i], textureValues[i+1]);
            GL11.glVertex2f(vertexValues[i], vertexValues[i+1]);
        }
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode. And adds a texture to it.
     * @param beginMode - OpenGL begin mode
     * @param textureID - The texture id to be used
     * @param vertexValues - Array of vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void threePointdraw(@BeginMode int beginMode, int textureID, float[] vertexValues, float[] textureValues) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        GL11.glBegin(beginMode);
        for(int i=0, j=0;i<vertexValues.length-2;i+=3, j+=2) {
            GL11.glTexCoord2f(textureValues[j], textureValues[j+1]);
            GL11.glVertex3f(vertexValues[i], vertexValues[i+1], vertexValues[i+2]);
        }
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode. And adds a texture to it.
     * @param beginMode - OpenGL begin mode
     * @param textureID - The texture id to be used
     * @param vertexValues - Array of vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void fourPointdraw(@BeginMode int beginMode, int textureID, float[] vertexValues, float[] textureValues) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        GL11.glBegin(beginMode);
        for(int i=0, j=0;i<vertexValues.length-3;i+=4, j+=2) {
            GL11.glTexCoord2f(textureValues[j], textureValues[j+1]);
            GL11.glVertex4f(vertexValues[i], vertexValues[i+1], vertexValues[i+2], vertexValues[i+3]);
        }
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode. Adds color and texture to it. 
     * Note that {@code colorValues} should have four values per vertex.
     * @param beginMode - OpenGL begin mode
     * @param textureID - The texture id to be used
     * @param vertexValues - Array of vertices
     * @param colorValues - Array of color vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void twoPointdraw(@BeginMode int beginMode, int textureID, float[] vertexValues, float[] colorValues, float[] textureValues) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        GL11.glBegin(beginMode);
        for(int i=0, j=0;i<vertexValues.length-1;i+=2, j+=4) {
            GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
            GL11.glTexCoord2f(textureValues[i], textureValues[i+1]);
            GL11.glVertex2f(vertexValues[i], vertexValues[i+1]);
        }
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode. Adds color and texture to it. 
     * Note that {@code colorValues} should have four values per vertex.
     * @param beginMode - OpenGL begin mode
     * @param textureID - The texture id to be used
     * @param vertexValues - Array of vertices
     * @param colorValues - Array of color vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void threePointdraw(@BeginMode int beginMode, int textureID, float[] vertexValues, float[] colorValues, float[] textureValues) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        GL11.glBegin(beginMode);
        for(int i=0, j=0, k=0;i<vertexValues.length-2;i+=3, j+=4, k+=2) {
            GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
            GL11.glTexCoord2f(textureValues[k], textureValues[k+1]);
            GL11.glVertex3f(vertexValues[i], vertexValues[i+1], vertexValues[i+2]);
        }
        GL11.glEnd();
    }
    
    /**
     * Draws a shape using glBegin / Primitive mode. Adds color and texture to it. 
     * Note that {@code colorValues} should have four values per vertex.
     * @param beginMode - OpenGL begin mode
     * @param textureID - The texture id to be used
     * @param vertexValues - Array of vertices
     * @param colorValues - Array of color vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void fourPointdraw(@BeginMode int beginMode, int textureID, float[] vertexValues, float[] colorValues, float[] textureValues) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        GL11.glBegin(beginMode);
        for(int i=0, j=0, k=0;i<vertexValues.length-3;i+=4, j+=4, k+=2){
            GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
            GL11.glTexCoord2f(textureValues[k], textureValues[k+1]);
            GL11.glVertex4f(vertexValues[i], vertexValues[i+1], vertexValues[i+2], vertexValues[i+3]);
        }
        GL11.glEnd();
    }
}
