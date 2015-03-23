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
    
    // ======================================= Vertex Arrays ================================================
    private static FloatBuffer vertexBuffer;
    private static FloatBuffer colorBuffer;
    
    // ======================================= Vertex buffered object =======================================
    private static int vbovertexhandler;
    private static int vbocolorhandler;
    
    // ======================================= Vertex array object ==========================================
    private static int vaohandler;
    private static int vbobvertexhandler;
    private static int vbobcolorhandler;
    
    static {
        // ======================================= Vertex Arrays ================================================
        vertexBuffer = BufferUtils.createFloatBuffer(6);
        vertexBuffer.put(new float[]{250, 50, 300, 10, 250, 10});
        vertexBuffer.flip();
        colorBuffer = BufferUtils.createFloatBuffer(9);
        colorBuffer.put(new float[]{1,0,0, 0,1,0, 0,0,1});
        colorBuffer.flip();
        
        // ======================================= Vertex buffered object =======================================
        FloatBuffer vertexBufferVBO = BufferUtils.createFloatBuffer(6);
        vertexBufferVBO.put(new float[]{450, 50, 400, 10, 350, 50});
        vertexBufferVBO.flip();
        vbovertexhandler = VertexBufferedObject.createVboHandler(GL15.GL_DYNAMIC_DRAW, vertexBufferVBO);
        
        FloatBuffer colorBufferVBO = BufferUtils.createFloatBuffer(9);
        colorBufferVBO.put(new float[]{1,0,0, 0,1,0, 0,0,1});
        colorBufferVBO.flip();
        vbocolorhandler = VertexBufferedObject.createVboHandler(GL15.GL_DYNAMIC_DRAW, colorBufferVBO);
        
        // ======================================= vertex array object ==========================================
        FloatBuffer vertexBufferVAO = BufferUtils.createFloatBuffer(6);
        vertexBufferVAO.put(new float[]{600, 10, 550, 50, 500, 10});
        vertexBufferVAO.flip();
        vbobvertexhandler = VertexBufferedObject.createVboHandler(GL15.GL_DYNAMIC_DRAW, vertexBufferVAO);
        
        FloatBuffer colorBufferVAO = BufferUtils.createFloatBuffer(9);
        colorBufferVAO.put(new float[]{1,0,0, 0,1,0, 0,0,1});
        colorBufferVAO.flip();
        vbobcolorhandler = VertexBufferedObject.createVboHandler(GL15.GL_DYNAMIC_DRAW, colorBufferVAO);
        
        vaohandler = VertexArrayObject.createVaoHandler();
        
        VertexArrayObject.bindVao(vaohandler);
        {
            VertexBufferedObject.vertexPointer(vbobvertexhandler, 2, 0, 0, GL11.GL_FLOAT);
            VertexBufferedObject.colorPointer(vbobcolorhandler, 3, 0, 0, GL11.GL_FLOAT);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        }
        VertexArrayObject.bindVao(0);
    }
    
    
    public static void immidateDraw() {
        int beginMode = GL11.GL_TRIANGLES;
        float[] vertices = {10, 10, 50, 10, 10, 50};
        float[] colorVertices = {1,0,0,1, 0,1,0,1, 0,0,1,1};
        
        //Immediate.twoPointdraw(beginMode, vertices, colorVertices);
    }
    
    public static void displayListDraw() {
        int handler = DisplayList.createDisplayListHandler(1);
        int beginMode = GL11.GL_TRIANGLES;
        int compileMode = GL11.GL_COMPILE;
        
        float[] vertices = {100, 10, 150, 50, 200, 10};
        float[] colorVertices = {1,0,0,1, 0,1,0,1, 0,0,1,1};
        
        DisplayList.newList(handler, compileMode);
        {
            //Immediate.twoPointdraw(beginMode, vertices, colorVertices);
        }
        DisplayList.endList();
        
        DisplayList.drawList(handler);
    }
    
    public static void vertexArrayDraw() {
        int beginMode = GL11.GL_TRIANGLES;
        
        Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
        
        VertexArrays.vertexPointer(2, 0, vertexBuffer);
        VertexArrays.colorPointer(3, 0, colorBuffer);
        VertexArrays.drawArrays(beginMode, 3, 0);
        
        Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
    }
    
    public static void vboDraw() {
        Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
        
        VertexBufferedObject.vertexPointer(vbovertexhandler, 2, 0, 0, GL11.GL_FLOAT);
        VertexBufferedObject.colorPointer(vbocolorhandler, 3, 0, 0, GL11.GL_FLOAT);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        
        VertexBufferedObject.drawVboArrays(GL11.GL_TRIANGLES, 0, 3);
        
        Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
    }
    
    public static void vaoDraw() {
        VertexArrayObject.bindVao(vaohandler);
        {
            Graphics.enableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
            
            VertexBufferedObject.drawVboArrays(GL11.GL_TRIANGLES, 0, 3);
            
            Graphics.disableClientSideState(GL11.GL_VERTEX_ARRAY, GL11.GL_COLOR_ARRAY);
        }
        VertexArrayObject.bindVao(0);
    }
}
