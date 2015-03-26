/**
 * 
 */
package org.ajgl.test.graphics;

import java.nio.FloatBuffer;

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
@SuppressWarnings("deprecation")
public class GraphicsTest {
    
    // ======================================= Immediate Mode ===============================================
    public static void immidateDraw() {
        int beginMode = GL11.GL_TRIANGLES;
        float[] vertices = {10, 10, 50, 10, 10, 50};
        float[] colorVertices = {1,0,0, 0,1,0, 0,0,1};
        
        Immediate.beginDraw(beginMode); {
            // First point
            GL11.glColor3f(colorVertices[0], colorVertices[1], colorVertices[2]);
            GL11.glVertex2f(vertices[0], vertices[1]);
            // Second point
            GL11.glColor3f(colorVertices[3], colorVertices[4], colorVertices[5]);
            GL11.glVertex2f(vertices[2], vertices[3]);
            // Third point
            GL11.glColor3f(colorVertices[6], colorVertices[7], colorVertices[8]);
            GL11.glVertex2f(vertices[4], vertices[5]);
        } Immediate.endDraw();
    }
    // ======================================= Immediate Mode ===============================================
    
    // ======================================= Display Lists ================================================
    public static void displayListDraw() {
        int handler = DisplayList.createDisplayListHandler(1);
        int beginMode = GL11.GL_TRIANGLES;
        int compileMode = GL11.GL_COMPILE;
        
        float[] vertices = {100, 10, 150, 50, 200, 10};
        float[] colorVertices = {1,0,0, 0,1,0, 0,0,1};
        
        DisplayList.newList(handler, compileMode); {
            Immediate.beginDraw(beginMode); {
                // First point
                GL11.glColor3f(colorVertices[0], colorVertices[1], colorVertices[2]);
                GL11.glVertex2f(vertices[0], vertices[1]);
                // Second point
                GL11.glColor3f(colorVertices[3], colorVertices[4], colorVertices[5]);
                GL11.glVertex2f(vertices[2], vertices[3]);
                // Third point
                GL11.glColor3f(colorVertices[6], colorVertices[7], colorVertices[8]);
                GL11.glVertex2f(vertices[4], vertices[5]);
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
        vertexBuffer = BufferUtils.createFloatBuffer(6);
        vertexBuffer.put(new float[]{250, 50, 300, 10, 250, 10});
        vertexBuffer.flip();
        // Color buffer setup
        colorBuffer = BufferUtils.createFloatBuffer(9);
        colorBuffer.put(new float[]{1,0,0, 0,1,0, 0,0,1});
        colorBuffer.flip();
    }
    
    public static void vertexArrayDraw() {
        int beginMode = GL11.GL_TRIANGLES;
        // Enable client State
        Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
        GL11.glVertexPointer(2, 0, vertexBuffer);   // Vertex pointer
        GL11.glColorPointer(3, 0, colorBuffer);     // Color pointer
        VertexArrays.drawArrays(beginMode, 3, 0);   // Draw object
        // Disable client state
        Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
    }
    // ======================================= Vertex Arrays ================================================
    
    // ======================================= Vertex Buffered Object =======================================
    private static int vbovertexhandler;
    private static int vbocolorhandler;
    
    static {
        // Vertex buffer setup
        FloatBuffer vertexBufferVBO = BufferUtils.createFloatBuffer(6);
        vertexBufferVBO.put(new float[]{450, 50, 400, 10, 350, 50});
        vertexBufferVBO.flip();
        // VBO vertex handler
        vbovertexhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, vertexBufferVBO);
        // Color buffer setup
        FloatBuffer colorBufferVBO = BufferUtils.createFloatBuffer(9);
        colorBufferVBO.put(new float[]{1,0,0, 0,1,0, 0,0,1});
        colorBufferVBO.flip();
        // VBO color handler
        vbocolorhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, colorBufferVBO);
    }
    
    public static void vboDraw() {
        // Enable client state
        Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
        // Vertex pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbovertexhandler);
        GL11.glVertexPointer(2, GL11.GL_FLOAT, 0, 0);
        // Color pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbocolorhandler);
        GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0);
        // Clear binding
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        // Draw VBO
        VertexBufferedObject.drawVboArrays(GL11.GL_TRIANGLES, 0, 3);
        // Disable client state
        Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
    }
    // ======================================= Vertex Buffered Object =======================================
    
    // ======================================= Vertex Array Object ==========================================
    private static int vaohandler;
    private static int vbobvertexhandler;
    private static int vbobcolorhandler;
    
    static {
        // Vertex buffer setup
        FloatBuffer vertexBufferVAO = BufferUtils.createFloatBuffer(6);
        vertexBufferVAO.put(new float[]{600, 10, 550, 50, 500, 10});
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
            // Vertex pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobvertexhandler);
            GL11.glVertexPointer(2, GL11.GL_FLOAT, 0, 0);
            // Color pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobcolorhandler);
            GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0);
            // Clear binding
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        } VertexArrayObject.bindVao(0);  // Unbind VAO
    }
    
    public static void vaoDraw() {
        // Bind VAO
        VertexArrayObject.bindVao(vaohandler); {
            // Enable client state
            Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
            // draw VBO
            VertexBufferedObject.drawVboArrays(GL11.GL_TRIANGLES, 0, 3);
            // Disable client state
            Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
        } VertexArrayObject.bindVao(0); // Unbind VAO
    }
    // ======================================= Vertex Array Object ==========================================
}
