/**
 * 
 */
package org.ajgl.game.core2d.texture;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;


/**
 * @author Tyler
 *
 */
public class Texture {
    
    private ByteBuffer image;

    private int width;
    private int height;
    private int comp;
    
    private int textureID;
    
    protected Texture(ByteBuffer image, int width, int height, int comp) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.comp = comp;
        
        textureID = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        if(comp == 3)
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, image);
        else
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    
    /**
     * @return the image
     */
    public ByteBuffer getImage() {
        return image;
    }

    
    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    
    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    
    /**
     * @return the comp
     */
    public int getComp() {
        return comp;
    }

    
    /**
     * @return the textureID
     */
    public int getTextureID() {
        return textureID;
    }
    
}
