/**
 * 
 */
package org.ajgl.game.core2d;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;


/**
 * @author Tyler
 *
 */
public class MRectangle extends Rectangle{

    public float dx;
    public float dy;
    
    /**
     * @param graphicsData
     */
    public MRectangle(float[] graphicsData) {
        super(graphicsData);
    }
    
    public void move() {
        if(dx != 0 || dy != 0) {
            for(int i=0;i<24;i+=7) {
                graphicsData[i] += dx;
                graphicsData[i+1] += dy;
            } dx = 0; dy = 0;
            
            FloatBuffer dataBufferVBO = BufferUtils.createFloatBuffer(graphicsData.length);
            dataBufferVBO.put(graphicsData);
            dataBufferVBO.flip();
            
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboHandler);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, dataBufferVBO, GL15.GL_DYNAMIC_DRAW);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        }
    }
}
