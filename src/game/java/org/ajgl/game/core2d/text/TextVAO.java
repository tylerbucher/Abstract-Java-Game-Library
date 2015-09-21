/**
 * 
 */
package org.ajgl.game.core2d.text;

import java.nio.FloatBuffer;

import org.ajgl.game.core2d.Rectangle;
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
public class TextVAO {
    
    protected float[] graphicsData;
    protected int vboHandler;
    protected int vaoHandler;
    
    public TextVAO(float[] graphicsData) {
        this.graphicsData = graphicsData;
        this.vboHandler = TextVAO.createVboHandler(graphicsData);
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
            GL20.glEnableVertexAttribArray(2);
            // Vertex pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboHandler);
            GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 9*Float.BYTES, 0);
            // Color pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboHandler);
            GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, 9*Float.BYTES, 3*Float.BYTES);
            // Texture pointer
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboHandler);
            GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, 9*Float.BYTES, 7*Float.BYTES);
            // Unbind
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
            // Disable pointers
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(2);
        } GL30.glBindVertexArray(0);
    }
    
    public void draw() {
        GL30.glBindVertexArray(vaoHandler); {
            // Enable pointers
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            GL20.glEnableVertexAttribArray(2);
            // draw VBO
            VertexBufferedObject.drawVboArrays(GL11.GL_QUADS, 0, graphicsData.length/9);
            // Disable pointers
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(2);
        } GL30.glBindVertexArray(0);
    }
    
    public void bufferUpdate(float[] graphicsData) {
        this.graphicsData = graphicsData;
        FloatBuffer dataBufferVBO = BufferUtils.createFloatBuffer(graphicsData.length);
        dataBufferVBO.put(graphicsData);
        dataBufferVBO.flip();
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboHandler);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, dataBufferVBO, GL15.GL_DYNAMIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
}
