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

package org.ajgl.test.graphics.shaders;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.ajgl.graphics.DisplayList;
import org.ajgl.graphics.Immediate;
import org.ajgl.graphics.VertexArrayObject;
import org.ajgl.graphics.VertexArrays;
import org.ajgl.graphics.VertexBufferedObject;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;


/**
 * This class is designed to test all of the 
 * modern/sudo modern OpenGL drawing functions.
 * @author Tyler Bucher
 */
@SuppressWarnings("deprecation")
public class ShaderTest {

    
    // ======================================= Immediate Mode ===============================================
    public static void immidateDraw() {
        int beginMode = GL11.GL_TRIANGLES;
        float[] vertices = {10,10,0, 50,10,0, 10,50,0};
        float[] colorVertices = {1,0,0, 0,1,0, 0,0,1};
        
        Immediate.beginDraw(beginMode); {
            // First point
            GL20.glVertexAttrib3f(1, colorVertices[0], colorVertices[1], colorVertices[2]);
            GL20.glVertexAttrib3f(0, vertices[0], vertices[1], vertices[2]);
            // Second point
            GL20.glVertexAttrib3f(1, colorVertices[3], colorVertices[4], colorVertices[5]);
            GL20.glVertexAttrib3f(0, vertices[3], vertices[4], vertices[5]);
            // Third point
            GL20.glVertexAttrib3f(1, colorVertices[6], colorVertices[7], colorVertices[8]);
            GL20.glVertexAttrib3f(0, vertices[6], vertices[7], vertices[8]);
        } Immediate.endDraw();
    }
    // ======================================= Immediate Mode ===============================================
    
    // ======================================= Display Lists ================================================
    
    public static void displayListDraw() {
        int handler = DisplayList.createDisplayListHandler(1);
        int beginMode = GL11.GL_TRIANGLES;
        int compileMode = GL11.GL_COMPILE;
        
        float[] vertices = {100,10,0, 150,50,0, 200,10,0};
        float[] colorVertices = {1,0,0, 0,1,0, 0,0,1};
        
        DisplayList.newList(handler, compileMode); {
            Immediate.beginDraw(beginMode); {
                // First point
                GL20.glVertexAttrib3f(1, colorVertices[0], colorVertices[1], colorVertices[2]);
                GL20.glVertexAttrib3f(0, vertices[0], vertices[1], vertices[2]);
                // Second point
                GL20.glVertexAttrib3f(1, colorVertices[3], colorVertices[4], colorVertices[5]);
                GL20.glVertexAttrib3f(0, vertices[3], vertices[4], vertices[5]);
                // Third point
                GL20.glVertexAttrib3f(1, colorVertices[6], colorVertices[7], colorVertices[8]);
                GL20.glVertexAttrib3f(0, vertices[6], vertices[7], vertices[8]);
            } Immediate.endDraw();
        } DisplayList.endList();
        
        DisplayList.drawList(handler);
    }
    // ======================================= Display Lists ================================================
    
    // ======================================= Vertex Arrays ================================================
    private static FloatBuffer vertexBuffer;
    private static FloatBuffer colorBuffer;
    
    static {
        // Vertex buffer setup
        vertexBuffer = BufferUtils.createFloatBuffer(9);
        vertexBuffer.put(new float[]{250,50,0, 300,10,0, 250,10,0});
        vertexBuffer.flip();
        // Color buffer setup
        colorBuffer = BufferUtils.createFloatBuffer(9);
        colorBuffer.put(new float[]{1,0,0, 0,1,0, 0,0,1});
        colorBuffer.flip();
    }
    
    public static void vertexArrayDraw() {
        int beginMode = GL11.GL_TRIANGLES;
        // Enable pointers
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        // Setup drawing
        GL20.glVertexAttribPointer(0, 3, false, 0, vertexBuffer);   // Vertex pointer
        GL20.glVertexAttribPointer(1, 3, false, 0, colorBuffer);   // Color pointer
        VertexArrays.drawArrays(beginMode, 3, 0);   // Draw object
        // Disable pointers
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        
    }
    // ======================================= Vertex Arrays ================================================
    
    // ======================================= Vertex Buffered Object =======================================
    private static int vbovertexhandler;
    private static int vbocolorhandler;
    private static int vboindexhandler;
    
    static {
        // Vertex buffer setup
        FloatBuffer vertexBufferVBO = BufferUtils.createFloatBuffer(9);
        vertexBufferVBO.put(new float[]{450,50,0, 400,10,0, 350,50,0});
        vertexBufferVBO.flip();
        // VBO vertex handler
        vbovertexhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, vertexBufferVBO);
        // Color buffer setup
        FloatBuffer colorBufferVBO = BufferUtils.createFloatBuffer(9);
        colorBufferVBO.put(new float[]{1,0,0, 0,1,0, 0,0,1});
        colorBufferVBO.flip();
        // VBO color handler
        vbocolorhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, colorBufferVBO);
        // Index buffer setup
        IntBuffer indexBufferVBO = BufferUtils.createIntBuffer(3);
        indexBufferVBO.put(new int[]{0,1,2});
        indexBufferVBO.flip();
        // VBO color handler
        vboindexhandler = VertexBufferedObject.createVboHandler(GL15.GL_ELEMENT_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, indexBufferVBO);
    }
    
    public static void vboDraw() {
        // Enable pointers
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        // Index pointer
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboindexhandler);
        GL11.glIndexPointer(GL11.GL_FLOAT, 0, 0);
        // Vertex pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbovertexhandler);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        // Color pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbocolorhandler);
        GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, 0, 0);
        // Draw VBO
        VertexBufferedObject.drawVboElements(GL11.GL_TRIANGLES, 3, GL11.GL_UNSIGNED_INT, 0);
        // Clear binding
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        // Disable pointers
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
    }
    // ======================================= Vertex Buffered Object =======================================
    
    // ======================================= Vertex Array Object ==========================================
    private static int vaohandler;
    private static int vbobvertexhandler;
    private static int vbobcolorhandler;
    
    static {
        // Vertex buffer setup
        FloatBuffer vertexBufferVAO = BufferUtils.createFloatBuffer(9);
        vertexBufferVAO.put(new float[]{600,10,0, 550,50,0, 500,10,0});
        vertexBufferVAO.flip();
        // VBO vertex handler
        vbobvertexhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, vertexBufferVAO);
        // Color buffer setup
        FloatBuffer colorBufferVAO = BufferUtils.createFloatBuffer(9);
        colorBufferVAO.put(new float[]{1,0,0, 0,1,0, 0,0,1});
        colorBufferVAO.flip();
        // VBO color handler
        vbobcolorhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, colorBufferVAO);
        // VAO object handler
        vaohandler = VertexArrayObject.createVaoHandler();
        // VAO Setup
        VertexArrayObject.bindVao(vaohandler); {
            // Enable pointers
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            // Vertex pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobvertexhandler);
            GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
            // Color pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobcolorhandler);
            GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, 0, 0);
            // Unbind
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
            // Disable pointers
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
        } VertexArrayObject.bindVao(0);  // Unbind VAO
    }
    
    public static void vaoDraw() {
        // Bind VAO
        VertexArrayObject.bindVao(vaohandler); {
            // Enable pointers
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            // draw VBO
            VertexBufferedObject.drawVboArrays(GL11.GL_TRIANGLES, 0, 3);
            // Disable pointers
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
        } VertexArrayObject.bindVao(0); // Unbind VAO
    }
    // ======================================= Vertex Array Object ==========================================

}