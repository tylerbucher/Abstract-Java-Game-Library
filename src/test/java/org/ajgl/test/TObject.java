package org.ajgl.test;

import org.ajgl.primary.GameObject;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

/**
 * 
 * This class was designed to test the implementation of the GameObject
 * @author Tyler Bucher
 */
public class TObject extends GameObject {
    
    /**
     * 
     * Main Constructor for TObject
     * @param position Position of the object
     * @param color Color of the Object
     * @param texture Texture coordinates of the object
     * @param sides The number of sides of the object
     * @param tex The texture of the object
     */
    public TObject(float[] position, float[] colorData, float[] textureData, int gL_SHAPE_TYPE, Texture texture) {
        super(position, colorData, textureData, gL_SHAPE_TYPE, texture);
        //this.setOrigin(new Vector2f(-562, -306));
    }
    
    @Override
    public String toString() {
        return "TObject";
    }
    
}
