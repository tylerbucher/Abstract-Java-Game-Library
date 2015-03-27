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
import org.ajgl.test.MainTest;
import org.ajgl.test.graphics.shaders.Shader;
import org.ajgl.test.graphics.shaders.ShaderProgram;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;


/**
 * @author Tyler
 *
 */
@SuppressWarnings("deprecation")
public class GraphicsTest {
    
    // ======================================= Load Shaders =================================================
//    private static Shader vertexShader;
//    private static Shader fragmentShader;
//    public static ShaderProgram shaderProgram;
//    
//    static {
//        vertexShader = Shader.loadShader(GL20.GL_VERTEX_SHADER, "src/test/java/org/ajgl/test/graphics/shaders/VertexShaderTest.glsl");
//        fragmentShader = Shader.loadShader(GL20.GL_FRAGMENT_SHADER, "src/test/java/org/ajgl/test/graphics/shaders/FragmentShaderTest.glsl");
//        
//        shaderProgram = new ShaderProgram();
//        shaderProgram.attachShader(vertexShader);
//        shaderProgram.attachShader(fragmentShader);
//        GL20.glValidateProgram(shaderProgram.getID());
////        shaderProgram.link();
//    }
    // ======================================= Load Shaders =================================================
    
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
    }
    
    public static void vboDraw() {
//        GL20.glUseProgram(shaderProgram.getID());
        // Enable client state
//        Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
        // Vertex pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbovertexhandler);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
//        GL11.glVertexPointer(2, GL11.GL_FLOAT, 0, 0);
        // Color pointer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbocolorhandler);
        GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, 0, 0);
//        GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0);
        // Clear binding
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        // Draw VBO
        VertexBufferedObject.drawVboArrays(GL11.GL_TRIANGLES, 0, 3);
        
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        // Disable client state
        Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
    }
    // ======================================= Vertex Buffered Object =======================================
    
    // ======================================= Vertex Array Object ==========================================
    private static int vaohandler;
    private static int vbobvertexhandler;
    private static int vbobcolorhandler;
    
    public static void setupVAO() {
        int v = GL20.glGetAttribLocation(MainTest.shaderProgram.getID(), "in_Position");
        int c = GL20.glGetAttribLocation(MainTest.shaderProgram.getID(), "in_Color");
        // Vertex buffer setup
        FloatBuffer vertexBufferVAO = BufferUtils.createFloatBuffer(9);
        vertexBufferVAO.put(new float[]{600,10,0, 550,50,0, 500,10,0});
        vertexBufferVAO.flip();
        // VBO vertex handler
//        vbobvertexhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, vertexBufferVAO, 2);
        // Color buffer setup
        FloatBuffer colorBufferVAO = BufferUtils.createFloatBuffer(9);
        colorBufferVAO.put(new float[]{1,0,0, 0,1,0, 0,0,1});
        colorBufferVAO.flip();
        // VBO color handler
//        vbobcolorhandler = VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, colorBufferVAO, 3);
        // VAO object handler
        
//        int vbobvertexhandler = GL15.glGenBuffers();
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobvertexhandler);
//        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBufferVAO, GL15.GL_STATIC_DRAW);
//        
//        int vbobcolorhandler = GL15.glGenBuffers();
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobcolorhandler);
//        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorBufferVAO, GL15.GL_STATIC_DRAW);
//        
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        
        vaohandler = VertexArrayObject.createVaoHandler();
        // VAO Setup
        VertexArrayObject.bindVao(vaohandler); {
//            GL20.glEnableVertexAttribArray(0);
//            GL20.glEnableVertexAttribArray(1);
            // VBO vertex handler
            vbobvertexhandler = VertexArrayObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, vertexBufferVAO, 
                    0, 3, GL11.GL_FLOAT, false, 0, 0);
            // VBO color handler
            vbobcolorhandler = VertexArrayObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, colorBufferVAO, 
                    1, 3, GL11.GL_FLOAT, false, 0, 0);
//            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobvertexhandler);
//            GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
//            
//            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbobcolorhandler);
//            GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, 0, 0);
//            
//            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
//            
//            GL20.glDisableVertexAttribArray(0);
//            GL20.glDisableVertexAttribArray(1);
            
        } VertexArrayObject.bindVao(0);  // Unbind VAO
    }
    
    public static void vaoDraw() {
//        GL20.glUseProgram(shaderProgram.getID());
        // Bind VAO
        VertexArrayObject.bindVao(vaohandler); {
            // Enable client state
//            Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
            int v = GL20.glGetAttribLocation(MainTest.shaderProgram.getID(), "in_Position");
            int c = GL20.glGetAttribLocation(MainTest.shaderProgram.getID(), "in_Color");
            
            GL20.glEnableVertexAttribArray(v);
            GL20.glEnableVertexAttribArray(c);
            // draw VBO
            VertexBufferedObject.drawVboArrays(GL11.GL_TRIANGLES, 0, 3);
            
            GL20.glDisableVertexAttribArray(v);
            GL20.glDisableVertexAttribArray(c);
            // Disable client state
//            Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
        } VertexArrayObject.bindVao(0); // Unbind VAO
    }
    // ======================================= Vertex Array Object ==========================================
}
