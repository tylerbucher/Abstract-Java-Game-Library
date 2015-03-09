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
import org.ajgl.graphics.UtilAnnotations.BeginMode;
import org.ajgl.graphics.UtilAnnotations.CompileMode;
import org.ajgl.graphics.UtilAnnotations.GLDataType;
import org.lwjgl.opengl.GL11;


/**
 * This class draws shapes to the screen through OpenGL. The 
 * rendering method that this class uses is "Display Lists". Like
 * the {@link org.ajgl.graphics.Immediate Immediate} class this 
 * class should never be used unless you have some god-awful reason 
 * to use it. The reason being is because it is the same fucking code
 * as {@link org.ajgl.graphics.Immediate Immediate} the only difference 
 * is it is wrapped in a list.
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
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static int createDisplayListHandler(int number) {
        return GL11.glGenLists(number);
    }
    
    /**
     * Compiles or and draws a display list.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param values - The array of vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileTwoPointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, float... values) {
        GL11.glNewList(listID, compileMode);
            GL11.glBegin(beginMode);
            for(int i=0;i<values.length-1;i+=2)
                GL11.glVertex2f(values[i], values[i+1]);
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Compiles or and draws a display list.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param values - The array of vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileThreePointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, float... values) {
        GL11.glNewList(listID, compileMode);
            GL11.glBegin(beginMode);
            for(int i=0;i<values.length-2;i+=3)
                GL11.glVertex3f(values[i], values[i+1], values[i+2]);
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Compiles or and draws a display list.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param values - The array of vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileFourPointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, float... values) {
        GL11.glNewList(listID, compileMode);
            GL11.glBegin(beginMode);
            for(int i=0;i<values.length-3;i+=4)
                GL11.glVertex4f(values[i], values[i+1], values[i+2], values[i+3]);
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Compiles or and draws a display list with color.
     * Note that {@code colorValues} should have four values per vertex.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param vertexValues - The array of vertices
     * @param colorValues - The array of color vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileTwoPointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, float[] vertexValues, float[] colorValues) {
        GL11.glNewList(listID, compileMode);
            GL11.glBegin(beginMode);
            for(int i=0, j=0;i<vertexValues.length-1;i+=2, j+=4) {
                GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
                GL11.glVertex2f(vertexValues[i], vertexValues[i+1]);
            }
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Compiles or and draws a display list with color.
     * Note that {@code colorValues} should have four values per vertex.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param vertexValues - The array of vertices
     * @param colorValues - The array of color vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileThreePointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, float[] vertexValues, float[] colorValues) {
        GL11.glNewList(listID, compileMode);
            GL11.glBegin(beginMode);
            for(int i=0, j=0;i<vertexValues.length-2;i+=3, j+=4) {
                GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
                GL11.glVertex3f(vertexValues[i], vertexValues[i+1], vertexValues[i+2]);
            }
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Compiles or and draws a display list with color.
     * Note that {@code colorValues} should have four values per vertex.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param vertexValues - The array of vertices
     * @param colorValues - The array of color vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileFourPointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, float[] vertexValues, float[] colorValues) {
        GL11.glNewList(listID, compileMode);
            GL11.glBegin(beginMode);
            for(int i=0, j=0;i<vertexValues.length-3;i+=4, j+=4){
                GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
                GL11.glVertex4f(vertexValues[i], vertexValues[i+1], vertexValues[i+2], vertexValues[i+3]);
            }
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Compiles or and draws a display list with a texture.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param textureID - The texture id to be used
     * @param vertexValues - The array of vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileTwoPointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, 
            int textureID, float[] vertexValues, float[] textureValues) {
        GL11.glNewList(listID, compileMode);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glBegin(beginMode);
            for(int i=0;i<vertexValues.length-1;i+=2) {
                GL11.glTexCoord2f(textureValues[i], textureValues[i+1]);
                GL11.glVertex2f(vertexValues[i], vertexValues[i+1]);
            }
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Compiles or and draws a display list with a texture.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param textureID - The texture id to be used
     * @param vertexValues - The array of vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileThreePointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, 
            int textureID, float[] vertexValues, float[] textureValues) {
        GL11.glNewList(listID, compileMode);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glBegin(beginMode);
            for(int i=0, j=0;i<vertexValues.length-2;i+=3, j+=2) {
                GL11.glTexCoord2f(textureValues[j], textureValues[j+1]);
                GL11.glVertex3f(vertexValues[i], vertexValues[i+1], vertexValues[i+2]);
            }
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Compiles or and draws a display list with a texture.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param textureID - The texture id to be used
     * @param vertexValues - The array of vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileFourPointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, 
            int textureID, float[] vertexValues, float[] textureValues) {
        GL11.glNewList(listID, compileMode);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glBegin(beginMode);
            for(int i=0, j=0;i<vertexValues.length-3;i+=4, j+=2) {
                GL11.glTexCoord2f(textureValues[j], textureValues[j+1]);
                GL11.glVertex4f(vertexValues[i], vertexValues[i+1], vertexValues[i+2], vertexValues[i+3]);
            }
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Compiles or and draws a display list with color and a texture.
     * Note that {@code colorValues} should have four values per vertex.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param textureID - The texture id to be used
     * @param vertexValues - The array of vertices
     * @param colorValues - The array of color vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileTwoPointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, 
            int textureID, float[] vertexValues, float[] colorValues, float[] textureValues) {
        GL11.glNewList(listID, compileMode);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glBegin(beginMode);
            for(int i=0, j=0;i<vertexValues.length-1;i+=2, j+=4) {
                GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
                GL11.glTexCoord2f(textureValues[i], textureValues[i+1]);
                GL11.glVertex2f(vertexValues[i], vertexValues[i+1]);
            }
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Compiles or and draws a display list with color and a texture.
     * Note that {@code colorValues} should have four values per vertex.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param textureID - The texture id to be used
     * @param vertexValues - The array of vertices
     * @param colorValues - The array of color vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileThreePointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, 
            int textureID, float[] vertexValues, float[] colorValues, float[] textureValues) {
        GL11.glNewList(listID, compileMode);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glBegin(beginMode);
            for(int i=0, j=0, k=0;i<vertexValues.length-2;i+=3, j+=4, k+=2) {
                GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
                GL11.glTexCoord2f(textureValues[k], textureValues[k+1]);
                GL11.glVertex3f(vertexValues[i], vertexValues[i+1], vertexValues[i+2]);
            }
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Compiles or and draws a display list with color and a texture.
     * Note that {@code colorValues} should have four values per vertex.
     * @param listID - The display list handler
     * @param beginMode - The OpenGL begin mode
     * @param compileMode - The OpenGL compile mode
     * @param textureID - The texture id to be used
     * @param vertexValues - The array of vertices
     * @param colorValues - The array of color vertices
     * @param textureValues - Array of local texture vertices
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void compileFourPointList(int listID, @BeginMode int beginMode, @CompileMode int compileMode, 
            int textureID, float[] vertexValues, float[] colorValues, float[] textureValues) {
        GL11.glNewList(listID, compileMode);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glBegin(beginMode);
            for(int i=0, j=0, k=0;i<vertexValues.length-3;i+=4, j+=4, k+=2){
                GL11.glColor4f(colorValues[j], colorValues[j+1], colorValues[j+2], colorValues[j+3]);
                GL11.glTexCoord2f(textureValues[k], textureValues[k+1]);
                GL11.glVertex4f(vertexValues[i], vertexValues[i+1], vertexValues[i+2], vertexValues[i+3]);
            }
            GL11.glEnd();
        GL11.glEndList();
    }
    
    /**
     * Draws a single display list.
     * @param listID - The display list handler.
     */
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
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
    @OpenGLInfo(fwdCompatible = false, openGLVersion = "1.1", status = "Deprecated")
    public static void drawLists(int listID, int number, @GLDataType int dataType, ByteBuffer lists) {
        GL11.glListBase(listID);
        GL11.glCallLists(number, dataType, lists);
    }
}
