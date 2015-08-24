/**
 * 
 */
package org.ajgl.test.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.ajgl.game.core2d.texture.Texture;
import org.ajgl.game.core2d.texture.TextureLoader;
import org.ajgl.graphics.DisplayList;
import org.ajgl.graphics.Graphics;
import org.ajgl.graphics.Immediate;
import org.ajgl.graphics.VertexArrayObject;
import org.ajgl.graphics.VertexArrays;
import org.ajgl.graphics.VertexBufferedObject;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;


/**
 * @author Tyler
 *
 */
public class TextureTest {
    static Texture texture;
    static {
        texture = TextureLoader.LoadTexture("src/test/java/resource/texture/MyLogo1024.png", 8*1024);
    }
    
    // ======================================= Immediate Mode ===============================================
    public static void immidateDraw() {
        int beginMode = GL11.GL_QUADS;
        float[] vertices = {10,100, 60,100, 60,150, 10,150};
        float[] colorVertices = {1,0,0, 1,0,0, 1,0,0, 1,0,0};
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        Immediate.beginDraw(beginMode); {
            // First point
            GL11.glTexCoord2f(0, 1);
            GL11.glColor3f(colorVertices[0], colorVertices[1], colorVertices[2]);
            GL11.glVertex2f(vertices[0], vertices[1]);
            // Second point
            GL11.glTexCoord2f(1, 1);
            GL11.glColor3f(colorVertices[3], colorVertices[4], colorVertices[5]);
            GL11.glVertex2f(vertices[2], vertices[3]);
            // Third point
            GL11.glTexCoord2f(1, 0);
            GL11.glColor3f(colorVertices[6], colorVertices[7], colorVertices[8]);
            GL11.glVertex2f(vertices[4], vertices[5]);
            // Fourth point
            GL11.glTexCoord2f(0, 0);
            GL11.glColor3f(colorVertices[9], colorVertices[10], colorVertices[11]);
            GL11.glVertex2f(vertices[6], vertices[7]);
        } Immediate.endDraw();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
    // ======================================= Immediate Mode ===============================================
    
    // ======================================= Display Lists ================================================
    public static void displayListDraw() {
        int handler = DisplayList.createDisplayListHandler(1);
        int beginMode = GL11.GL_QUADS;
        int compileMode = GL11.GL_COMPILE;
        
        float[] vertices = {100,100, 200,100, 200,200, 100,200};
        float[] colorVertices = {0,1,0, 0,1,0, 0,1,0, 0,1,0};
        
        DisplayList.newList(handler, compileMode); {
            Immediate.beginDraw(beginMode); {
                // First point
                GL11.glTexCoord2f(0, 0);
                GL11.glColor3f(colorVertices[0], colorVertices[1], colorVertices[2]);
                GL11.glVertex2f(vertices[0], vertices[1]);
                // Second point
                GL11.glTexCoord2f(0, 1);
                GL11.glColor3f(colorVertices[3], colorVertices[4], colorVertices[5]);
                GL11.glVertex2f(vertices[2], vertices[3]);
                // Third point
                GL11.glTexCoord2f(1, 1);
                GL11.glColor3f(colorVertices[6], colorVertices[7], colorVertices[8]);
                GL11.glVertex2f(vertices[4], vertices[5]);
                // Fourth point
                GL11.glTexCoord2f(1, 0);
                GL11.glColor3f(colorVertices[9], colorVertices[10], colorVertices[11]);
                GL11.glVertex2f(vertices[6], vertices[7]);
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
        vertexBuffer = BufferUtils.createFloatBuffer(8);
        vertexBuffer.put(new float[]{250,100, 400,100, 400,250, 250,250});
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
        // Enable client State
        Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY, GL11.GL_TEXTURE_COORD_ARRAY);
        GL11.glVertexPointer(2, 0, vertexBuffer);       // Vertex pointer
        GL11.glColorPointer(3, 0, colorBuffer);         // Color pointer
        GL11.glTexCoordPointer(2, 0, textureBuffer);    // Texture pointer
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        VertexArrays.drawArrays(beginMode, 4, 0);   // Draw object
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        // Disable client state
        Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY, GL11.GL_TEXTURE_COORD_ARRAY);
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
        // Enable client state
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY, GL11.GL_INDEX_ARRAY, GL11.GL_TEXTURE_COORD_ARRAY);
        // Index pointer
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboindexhandler);
        GL11.glIndexPointer(GL11.GL_FLOAT, 0, 0);
        // Vertex pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbovertexhandler);
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);
        // Color pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbocolorhandler);
        GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0);
        // Texture pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbotexturehandler);
        GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, 0);
        // Draw VBO
        VertexBufferedObject.drawVboElements(GL11.GL_QUADS, 4, GL11.GL_UNSIGNED_INT, 0);
        // Alternate draw method
        //VertexBufferedObject.drawVboArrays(GL11.GL_TRIANGLES, 0, 3);
        // Clear binding
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        // Disable client state
        Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY, GL11.GL_INDEX_ARRAY, GL11.GL_TEXTURE_COORD_ARRAY);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
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
            // Vertex pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobvertexhandler);
            GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);
            // Color pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobcolorhandler);
            GL11.glColorPointer(3, GL11.GL_FLOAT, 0,  0);
            // Texture pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobtexturehandler);
            GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 0,  0);
            // Unbind
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        } VertexArrayObject.bindVao(0);  // Unbind VAO
    }
    
    public static void vaoDraw() {
        // Bind VAO
        VertexArrayObject.bindVao(vaohandler); {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
            // Enable client state
            Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY, GL11.GL_TEXTURE_COORD_ARRAY);
            // draw VBO
            VertexBufferedObject.drawVboArrays(GL11.GL_QUADS, 0, 4);
            // Disable client state
            Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY, GL11.GL_TEXTURE_COORD_ARRAY);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        } VertexArrayObject.bindVao(0); // Unbind VAO
    }
    // ======================================= Vertex Array Object ==========================================
}
