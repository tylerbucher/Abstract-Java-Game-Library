/**
 * 
 */
package org.ajgl.test.graphics.shaders;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.ajgl.game.core2d.texture.Texture;
import org.ajgl.game.core2d.texture.TextureLoader;
import org.ajgl.graphics.DisplayList;
import org.ajgl.graphics.Immediate;
import org.ajgl.graphics.VertexArrayObject;
import org.ajgl.graphics.VertexArrays;
import org.ajgl.graphics.VertexBufferedObject;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


/**
 * @author Tyler
 *
 */
public class ShaderTextureTest {
    
    static Texture texture;
    static {
        texture = TextureLoader.LoadTexture("src/test/java/resource/texture/MyLogo1024.png", 8*1024);
    }
    
    // ======================================= Immediate Mode ===============================================
    public static void immidateDraw() {
        int beginMode = GL11.GL_QUADS;
        float[] vertices = {10,100,0, 60,100,0, 60,150,0, 10,150,0};
        float[] colorVertices = {1,0,0, 1,0,0, 1,0,0, 1,0,0};
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        Immediate.beginDraw(beginMode); {
            // First point
            GL20.glVertexAttrib2f(2, 0, 1);
            GL20.glVertexAttrib3f(1, colorVertices[0], colorVertices[1], colorVertices[2]);
            GL20.glVertexAttrib3f(0, vertices[0], vertices[1], vertices[2]);
            // Second point
            GL20.glVertexAttrib2f(2, 1, 1);
            GL20.glVertexAttrib3f(1, colorVertices[3], colorVertices[4], colorVertices[5]);
            GL20.glVertexAttrib3f(0, vertices[3], vertices[4], vertices[5]);
            // Third point
            GL20.glVertexAttrib2f(2, 1, 0);
            GL20.glVertexAttrib3f(1, colorVertices[6], colorVertices[7], colorVertices[8]);
            GL20.glVertexAttrib3f(0, vertices[6], vertices[7], vertices[8]);
            // Fourth point
            GL20.glVertexAttrib2f(2, 0, 0);
            GL20.glVertexAttrib3f(1, colorVertices[9], colorVertices[10], colorVertices[11]);
            GL20.glVertexAttrib3f(0, vertices[9], vertices[10], vertices[11]);
        } Immediate.endDraw();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
    // ======================================= Immediate Mode ===============================================
    
    // ======================================= Display Lists ================================================
    
    public static void displayListDraw() {
        int handler = DisplayList.createDisplayListHandler(1);
        int beginMode = GL11.GL_QUADS;
        int compileMode = GL11.GL_COMPILE;
        
        float[] vertices = {100,100,0, 200,100,0, 200,200,0, 100,200,0};
        float[] colorVertices = {0,1,0, 0,1,0, 0,1,0, 0,1,0};
        
        DisplayList.newList(handler, compileMode); {
            Immediate.beginDraw(beginMode); {
                // First point
                GL20.glVertexAttrib2f(2, 0, 0);
                GL20.glVertexAttrib3f(1, colorVertices[0], colorVertices[1], colorVertices[2]);
                GL20.glVertexAttrib3f(0, vertices[0], vertices[1], vertices[2]);
                // Second point
                GL20.glVertexAttrib2f(2, 0, 1);
                GL20.glVertexAttrib3f(1, colorVertices[3], colorVertices[4], colorVertices[5]);
                GL20.glVertexAttrib3f(0, vertices[3], vertices[4], vertices[5]);
                // Third point
                GL20.glVertexAttrib2f(2, 1, 1);
                GL20.glVertexAttrib3f(1, colorVertices[6], colorVertices[7], colorVertices[8]);
                GL20.glVertexAttrib3f(0, vertices[6], vertices[7], vertices[8]);
                // Fourth point
                GL20.glVertexAttrib2f(2, 1, 0);
                GL20.glVertexAttrib3f(1, colorVertices[9], colorVertices[10], colorVertices[11]);
                GL20.glVertexAttrib3f(0, vertices[9], vertices[10], vertices[11]);
            } Immediate.endDraw();
        } DisplayList.endList();
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        DisplayList.drawList(handler);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
    // ======================================= Display Lists ================================================
    
    // ======================================= Vertex Arrays ================================================
    private static FloatBuffer vertexBuffer;
    private static FloatBuffer colorBuffer;
    private static FloatBuffer textureBuffer;
    
    static {
        // Vertex buffer setup
        vertexBuffer = BufferUtils.createFloatBuffer(12);
        vertexBuffer.put(new float[]{250,100,0, 400,100,0, 400,250,0, 250,250,0});
        vertexBuffer.flip();
        // Color buffer setup
        colorBuffer = BufferUtils.createFloatBuffer(12);
        colorBuffer.put(new float[]{0,0,1, 0,0,1, 0,0,1, 0,0,1});
        colorBuffer.flip();
        // Texture buffer setup
        textureBuffer = BufferUtils.createFloatBuffer(8);
        textureBuffer.put(new float[]{1,0, 0,0, 0,1, 1,1});
        textureBuffer.flip();
    }
    
    public static void vertexArrayDraw() {
        int beginMode = GL11.GL_QUADS;
        // Enable pointers
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        // Setup drawing
        GL20.glVertexAttribPointer(0, 3, false, 0, vertexBuffer);   // Vertex pointer
        GL20.glVertexAttribPointer(1, 3, false, 0, colorBuffer);   // Color pointer
        GL20.glVertexAttribPointer(2, 2, false, 0, textureBuffer);   // Texture pointer
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        VertexArrays.drawArrays(beginMode, 4, 0);   // Draw object
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        // Disable pointers
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        
    }
    // ======================================= Vertex Arrays ================================================
    
    // ======================================= Vertex Buffered Object =======================================
    private static int vbovertexhandler;
    private static int vbocolorhandler;
    private static int vbotexturehandler;
    private static int vboindexhandler;
    
    static {
        // Vertex buffer setup
        FloatBuffer vertexBufferVBO = BufferUtils.createFloatBuffer(12);
        vertexBufferVBO.put(new float[]{450,100,0, 650,100,0, 650,300,0, 450,300,0});
        vertexBufferVBO.flip();
        // VBO vertex handler
        vbovertexhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, vertexBufferVBO);
        // Color buffer setup
        FloatBuffer colorBufferVBO = BufferUtils.createFloatBuffer(12);
        colorBufferVBO.put(new float[]{0.5f,0.5f,0.5f, 0.5f,0.5f,0.5f, 0.5f,0.5f,0.5f, 0.5f,0.5f,0.5f});
        colorBufferVBO.flip();
        // VBO color handler
        vbocolorhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, colorBufferVBO);
        // Texture buffer setup
        FloatBuffer textureBufferVBO = BufferUtils.createFloatBuffer(8);
        textureBufferVBO.put(new float[]{1,1, 1,0, 0,0, 0,1});
        textureBufferVBO.flip();
        // VBO texture handler
        vbotexturehandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, textureBufferVBO);
        // Index buffer setup
        IntBuffer indexBufferVBO = BufferUtils.createIntBuffer(4);
        indexBufferVBO.put(new int[]{0,1,2,3});
        indexBufferVBO.flip();
        // VBO color handler
        vboindexhandler = VertexBufferedObject.createVboHandler(GL15.GL_ELEMENT_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, indexBufferVBO);
    }
    
    public static void vboDraw() {
        // Enable pointers
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        // Index pointer
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboindexhandler);
        GL11.glIndexPointer(GL11.GL_FLOAT, 0, 0);
        // Vertex pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbovertexhandler);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        // Color pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbocolorhandler);
        GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, 0, 0);
        // Texture pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbotexturehandler);
        GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, 0, 0);
        // Draw VBO
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        VertexBufferedObject.drawVboElements(GL11.GL_QUADS, 4, GL11.GL_UNSIGNED_INT, 0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        // Clear binding
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        // Disable pointers
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
    }
    // ======================================= Vertex Buffered Object =======================================
    
    // ======================================= Vertex Array Object ==========================================
    private static int vaohandler;
    private static int vbobvertexhandler;
    private static int vbobcolorhandler;
    private static int vbobtexturehandler;
    
    static {
        // Vertex buffer setup
        FloatBuffer vertexBufferVAO = BufferUtils.createFloatBuffer(12);
        vertexBufferVAO.put(new float[]{700,100,0, 1100,100,0, 1100,500,0, 700,500,0});
        vertexBufferVAO.flip();
        // VBO vertex handler
        vbobvertexhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, vertexBufferVAO);
        // Color buffer setup
        FloatBuffer colorBufferVAO = BufferUtils.createFloatBuffer(12);
        colorBufferVAO.put(new float[]{1,1,1, 1,1,1, 1,1,1, 1,1,1});
        colorBufferVAO.flip();
        // VBO color handler
        vbobcolorhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, colorBufferVAO);
        // Texture buffer setup
        FloatBuffer textureBufferVAO = BufferUtils.createFloatBuffer(8);
        textureBufferVAO.put(new float[]{0,1, 1,1, 1,0, 0,0});
        textureBufferVAO.flip();
        // VBO texture handler
        vbobtexturehandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, textureBufferVAO);
        // VAO object handler
        vaohandler = VertexArrayObject.createVaoHandler();
        // VAO Setup
        VertexArrayObject.bindVao(vaohandler); {
            // Enable pointers
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            GL20.glEnableVertexAttribArray(2);
            // Vertex pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobvertexhandler);
            GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
            // Color pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobcolorhandler);
            GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, 0, 0);
            // Texture pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobtexturehandler);
            GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, 0, 0);
            // Unbind
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
            // Disable pointers
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(2);
        } VertexArrayObject.bindVao(0);  // Unbind VAO
    }
    
    public static void vaoDraw() {
        // Bind VAO
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        VertexArrayObject.bindVao(vaohandler); {
            // Enable pointers
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            GL20.glEnableVertexAttribArray(2);
            // draw VBO
            VertexBufferedObject.drawVboArrays(GL11.GL_QUADS, 0, 4);
            // Disable pointers
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(2);
        } VertexArrayObject.bindVao(0); // Unbind VAO
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
    // ======================================= Vertex Array Object ==========================================

}
