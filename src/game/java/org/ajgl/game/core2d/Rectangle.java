/**
 * 
 */
package org.ajgl.game.core2d;

import java.nio.FloatBuffer;

import org.ajgl.graphics.UtilAnnotations.GlBufferTarget;
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
public class Rectangle {
    
    protected float[] graphicsData;
    protected int vboHandler;
    protected int vaoHandler;
    
    public Rectangle(float[] graphicsData) {
        this.graphicsData = graphicsData;
        this.vboHandler = Rectangle.createVboHandler(graphicsData);
        this.vaoHandler = GL30.glGenVertexArrays();
        
        this.vaoSetup();
    }
    
    private static int createVboHandler(float[] data) {
        FloatBuffer dataBufferVBO = BufferUtils.createFloatBuffer(data.length);
        dataBufferVBO.put(data);
        dataBufferVBO.flip();
        return VertexBufferedObject.createVboHandler(GL15.GL_ARRAY_BUFFER, GL15.GL_DYNAMIC_DRAW, dataBufferVBO);
    }
    
    private void vaoSetup() {
        GL30.glBindVertexArray(vaoHandler); {
            // Enable pointers
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            // Vertex pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboHandler);
            GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 7*Float.BYTES, 0);
            // Color pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboHandler);
            GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, 7*Float.BYTES, 3*Float.BYTES);
            // Unbind
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
            // Disable pointers
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
        } GL30.glBindVertexArray(0);
    }
    
    public void draw() {
        GL30.glBindVertexArray(vaoHandler); {
            // Enable pointers
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            // draw VBO
            VertexBufferedObject.drawVboArrays(GL11.GL_QUADS, 0, 4);
            // Disable pointers
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
        } GL30.glBindVertexArray(0);
    }
    
    public static float[] CreateGraphicsData(float x, float y, float width, float height, float... colorData) {
        return new float[]{x, y, 0.0f, colorData[0], colorData[1], colorData[2], colorData[3], 
            x, y+height, 0.0f, colorData[4], colorData[5], colorData[6], colorData[7], 
            x+width, y+height, 0.0f, colorData[8], colorData[9], colorData[10], colorData[11], 
            x+width, y, 0.0f, colorData[12], colorData[13], colorData[14], colorData[15]};
    }
    
    public void bufferUpdate() {
        FloatBuffer dataBufferVBO = BufferUtils.createFloatBuffer(graphicsData.length);
        dataBufferVBO.put(graphicsData);
        dataBufferVBO.flip();
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboHandler);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, dataBufferVBO, GL15.GL_DYNAMIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
    
    /**
     * @return the graphicsData
     */
    public synchronized float[] getGraphicsData() {
        return graphicsData;
    }
}
