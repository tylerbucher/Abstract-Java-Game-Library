/**
 * AJGL, an abstract java game library that provides useful functions for making a game.
 * Copyright (C) 2014 Tyler Bucher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.ajgl.primary;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;

import java.nio.FloatBuffer;

import org.ajgl.graphics.Graphics;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

/**
 * This class is designed to be extended by objects that are convex polygons and
 * need to be drawn to the screen.
 * @author Tyler Bucher
 */
public abstract class Drawable implements ConvexPolygon {
    
    protected int alpha;            // States weather or not to use alpha: 3 for no, 4 for yes
    
    protected int vertexData;       // VBO handler for the object
    protected int colorData;        // VBO handler for the color
    protected int textureData;      // VBO handler for the texture
    protected int GL_SHAPE_TYPE;    // The lwjgl shape type of this object
    
    protected float[] color;        // The color of this object
    protected Texture texture;      // The texture of this object
    protected Vector2f origin;      // The origin point of this object
    protected float[] vertices;     // The points of the this object
    
    protected int vertCount;        // The number of vertices this object has
    
    /**
     * Creates a Drawable object that has color and texture.
     * @param vertices - Vertices of the convex shape
     * @param colorData - RGB or RGBA color data
     * @param textureData - Localized texture vertices
     * @param GL_SHAPE_TYPE - The shape type of which will be rendered by OpenGL
     * @param texture - The texture of the Drawable object
     */
    public Drawable(float[] vertices, float[] colorData, float[] textureData, int GL_SHAPE_TYPE, Texture texture) {
        // Initialization of primitive object data
        this.vertices = vertices;
        this.vertCount = vertices.length / 2;
        this.color = colorData;
        this.alpha = colorData.length / vertCount == 4 ? 4 : 3;
        // Initialization of VBO data
        this.vertexData = Graphics.createVboHandler(vertices);
        this.colorData = Graphics.createVboHandler(colorData);
        this.textureData = Graphics.createVboHandler(textureData);
        this.GL_SHAPE_TYPE = GL_SHAPE_TYPE;
        this.texture = texture;
        // Calculation of non-localized origin
        this.calcNonLocOrigin(this.vertices);
    }
    
    /**
     * Creates a Drawable object that only color specified.
     * @param vertices - Vertices of the convex shape
     * @param colorData - RGB or RGBA color data
     * @param GL_SHAPE_TYPE - The shape type of which will be rendered by OpenGL
     */
    public Drawable(float[] vertices, float[] colorData, int GL_SHAPE_TYPE) {
        // Initialization of primitive object data
        this.vertices = vertices;
        this.vertCount = vertices.length / 2;
        this.color = colorData;
        this.alpha = colorData.length / vertCount == 4 ? 4 : 3;
        // Initialization of VBO data
        this.vertexData = Graphics.createVboHandler(vertices);
        this.colorData = Graphics.createVboHandler(colorData);
        this.GL_SHAPE_TYPE = GL_SHAPE_TYPE;
        // Calculation of non-localized origin
        this.calcNonLocOrigin(this.vertices);
    }
    
    /**
     * Creates a Drawable object with only texture specified.
     * @param vertices - Vertices of the convex shape
     * @param textureData - Localized texture vertices
     * @param GL_SHAPE_TYPE - The shape type of which will be rendered by OpenGL
     * @param texture - The texture of the Drawable object
     */
    public Drawable(float[] vertices, float[] textureData, int GL_SHAPE_TYPE, Texture texture) {
        // Initialization of primitive object data
        this.vertices = vertices;
        this.vertCount = vertices.length / 2;
        // Initialization of VBO data
        this.vertexData = Graphics.createVboHandler(vertices);
        this.textureData = Graphics.createVboHandler(textureData);
        this.GL_SHAPE_TYPE = GL_SHAPE_TYPE;
        this.texture = texture;
        // Calculation of non-localized origin
        this.calcNonLocOrigin(this.vertices);
    }
    
    /**
     * Creates a Drawable object that is white by default.
     * @param vertices - Vertices of the convex shape
     * @param GL_SHAPE_TYPE - The shape type of which will be rendered by OpenGL
     */
    public Drawable(float[] vertices, int GL_SHAPE_TYPE) {
        // Initialization of primitive object data
        this.vertices = vertices;
        this.vertCount = vertices.length / 2;
        this.color = new float[(vertices.length / 2) * 4];
        java.util.Arrays.fill(color, 1);
        this.alpha = 4;
        // Initialization of VBO data
        this.vertexData = Graphics.createVboHandler(vertices);
        this.colorData = Graphics.createVboHandler(color);
        this.GL_SHAPE_TYPE = GL_SHAPE_TYPE;
        // Calculation of non-localized origin
        this.calcNonLocOrigin(this.vertices);
    }
    
    /**
     * Calculates this objects non-localized origin.
     * @param vertices - Vertices of a convex shape
     */
    protected abstract void calcNonLocOrigin(float[] vertices);
    
    /**
     * Draws this object to the screen.
     */
    public abstract void draw();
    
    /**
     * Destroys the object by removing the VBO buffers.
     */
    public void destroy() {
        Graphics.clearVboHandler(vertexData);
        Graphics.clearVboHandler(colorData);
        if(textureData != 0)
            Graphics.clearVboHandler(textureData);
    }
    
    /**
     * Updates a VBO's data based on its id.
     * @param id - The id of the data handler
     * @param target - The new data to be set
     */
    public void updateSubBufferData(int id, float[] target) {
        // Float buffer of data
        FloatBuffer buffer = BufferUtils.createFloatBuffer(target.length);
        buffer.put(target);
        buffer.flip();
        // OpenGL updating the data
        glBindBuffer(GL_ARRAY_BUFFER, id);
        GL15.glBufferSubData(GL_ARRAY_BUFFER, 0, buffer);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    
    /**
     * Returns the Vertex Buffered Object Handler associated with the vertices.
     * @return The VBO Handler associated with the vertices
     */
    public int getVertexData() {
        return vertexData;
    }
    
    /**
     * Returns the Vertex Buffered Object handler associated with the color.
     * @return The VBO handler associated with the color
     */
    public int getColorData() {
        return colorData;
    }
    
    /**
     * Returns the Vertex Buffered Object handler associated with the texture.
     * @return The VBO handler associated with the
     */
    public int getTextureData() {
        return textureData;
    }
    
    /**
     * Returns the lwjgl shape type of this object.
     * @return The lwjgl shape type of this object
     */
    public int getGL_SHAPE_TYPE() {
        return GL_SHAPE_TYPE;
    }
    
    /**
     * Sets the color, or color overlay of the object if it has a texture.
     * @param color - The new color that will be set, the color must still
     * remain RGB or RGBA as it was
     */
    public void setColor(float[] color) {
        // Updates the VBO color data and sets the color of the object-
        // -in one fatal swoop
        this.updateSubBufferData(colorData, this.color = color);
    }
    
    /**
     * Returns the color, or color overlay of the object if it has a texture.
     * @return The current color of the object
     */
    public float[] getColor() {
        return color;
    }
    
    /**
     * Sets the texture of the object.
     * @param texture - The new texture to be set
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
        if(this.textureData == 0)
            this.textureData = Graphics.createVboHandler(new float[]{-1.0f, 0, -1.0f, -1.0f, 0, -1.0f, 0, 0});
    }
    
    /**
     * Sets the texture of the object and the desired orientation.
     * @param textureData - Localized texture vertices
     * @param texture - The new texture to be set
     */
    public void setTexture(float[] textureData, Texture texture) {
        this.textureData = Graphics.createVboHandler(textureData);
        this.texture = texture;
    }
    
    /**
     * Returns the current texture of the object.
     * @return The current texture of the object
     */
    public Texture getTexture() {
        return texture;
    }
    
    /**
     * Returns the current non-localized origin of the object.
     * @return The current non-localized origin of the object
     */
    public Vector2f getOrigin() {
        return origin;
    }
    
    @Override
    public void setVertices(float[] vertices) {
        // Updates the VBO vertex data and sets the vertex of the object-
        // -in one fatal swoop
        this.vertices = vertices;
        //this.updateSubBufferData(vertexData, this.vertices = vertices);
        this.calcNonLocOrigin(vertices);
    }
    
    @Override
    public float[] getVertices() {
        return vertices;
    }
    
    @Override
    public int getVertCount() {
        return vertCount;
    }
}
