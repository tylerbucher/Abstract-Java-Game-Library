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

import org.ajgl.collision.AABB;
import org.ajgl.collision.SAT;
import org.ajgl.graphics.Graphics;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

/**
 * This class is designed to be a multi-purpose class that most game object
 * should extend.
 * @author Tyler Bucher
 */
public class GameObject extends Drawable implements Collidable {
    
    private AABB aabb;  // The Axis aligned Bounding box of this object
    public int dx, dy;  // The current displacement of x, and y
            
    /**
     * Creates a GameObject object that has color and texture.
     * @param vertices - Vertices of the convex shape
     * @param colorData - RGB or RGBA color data
     * @param textureData - Localized texture vertices
     * @param GL_SHAPE_TYPE - The shape type of which will be rendered by OpenGL
     * @param texture - The texture of the Drawable object
     */
    public GameObject(float[] vertices, float[] colorData, float[] textureData, int GL_SHAPE_TYPE, Texture texture) {
        super(vertices, colorData, textureData, GL_SHAPE_TYPE, texture);
        aabb = new AABB(GameObject._calcVertices(vertices));
    }
    
    /**
     * Creates a GameObject object with only color specified.
     * @param vertices - Vertices of the convex shape
     * @param colorData - RGB or RGBA color data
     * @param GL_SHAPE_TYPE - The shape type of which will be rendered by OpenGL
     */
    public GameObject(float[] vertices, float[] colorData, int GL_SHAPE_TYPE) {
        super(vertices, colorData, GL_SHAPE_TYPE);
        aabb = new AABB(GameObject._calcVertices(vertices));
    }
    
    /**
     * Creates a GameObject object with only texture specified.
     * @param vertices - Vertices of the convex shape
     * @param textureData - Localized texture vertices
     * @param GL_SHAPE_TYPE - The shape type of which will be rendered by OpenGL
     * @param texture - The texture of the Drawable object
     */
    public GameObject(float[] vertices, float[] textureData, int GL_SHAPE_TYPE, Texture texture) {
        super(vertices, textureData, GL_SHAPE_TYPE, texture);
        aabb = new AABB(GameObject._calcVertices(vertices));
    }
    
    /**
     * Creates a GameObject object that is white by default.
     * @param vertices - Vertices of the convex shape
     * @param GL_SHAPE_TYPE - The shape type of which will be rendered by OpenGL
     */
    public GameObject(float[] vertices, int GL_SHAPE_TYPE) {
        super(vertices, GL_SHAPE_TYPE);
        aabb = new AABB(GameObject._calcVertices(vertices));
    }
    
    /**
     * Calculate the AABB vertices.
     * @param position - The position to base the AABB off of
     * @return The vertices of the AABB
     */
    private static float[] _calcVertices(float[] position) {
        // Initialization of maximum and minimum positions
        float _XMin = position[0], _XMax = position[0];
        float _YMin = position[1], _YMax = position[1];
        // Obtains the height and width for a mock bounding box
        for (int i = 2; i < position.length - 1; i += 2) {
            if (position[i] < _XMin) _XMin = position[i];
            else if (position[i] > _XMax) _XMax = position[i];
            if (position[i + 1] < _YMin) _YMin = position[i + 1];
            else if (position[i + 1] > _YMax) _YMax = position[i + 1];
        }
        // Return the position of AABB
        return new float[] {_XMin, _YMin, _XMin, _YMax, _XMax, _YMax, _XMax, _YMin};
    }
    
    /**
     * Updates the local and VBO vertices.
     */
    public void updatePosition() {
        // If the object needs to be updated
        if (dx != 0 || dy != 0) {
            for (int i = 0; i < vertices.length - 1; i += 2) {
                vertices[i] += dx; vertices[i + 1] += dy;
            }
        }
    }
    
    /**
     * Finalize or conclude the updating of this object.
     */
    public void finalizeUpdate() {
        this.calcNonLocOrigin(vertices);
        this.updateSubBufferData(vertexData, vertices);
        aabb.setVertices(GameObject._calcVertices(vertices));
        aabb.updateSubBufferData(aabb.vertexData, aabb.vertices);
        dx = 0; dy = 0;
    }
    
    /**
     * Sets the AABB for this object.
     * @param aabb - The AABB to be set
     */
    public void setAabb(AABB aabb) {
        this.aabb = aabb;
    }
    
    /**
     * Returns the Axis Aligned Bounding Box.
     * @return The AABB associated with this object
     */
    public AABB getAabb() {
        return aabb;
    }
    
    /**
     * Sets the vertices of the polygon and updates the AABB.
     */
    @Override
    public void setVertices(float[] vertices) {
        super.setVertices(vertices);
        aabb.setVertices(GameObject._calcVertices(vertices));
    }
    
    @Override
    public boolean contains(float[] vertices) {
        return SAT.contains(this.vertices, vertices);
    }
    
    @Override
    public boolean intersects(float[] vertices) {
        return SAT.isColliding(this.vertices, vertices);
    }
    
    /**
     * Returns a string representation of the object.
     * @return The object in string format
     */
    @Override
    public String toString() {
        return "AJGL Primary GameObject";
    }

    @Override
    protected void calcNonLocOrigin(float[] vertices) {
        // Initialization of maximum and minimum positions
        float _XMin = vertices[0], _XMax = vertices[0];
        float _YMin = vertices[1], _YMax = vertices[1];
        // Obtains the height and width for a mock bounding box
        for (int i = 2; i < vertices.length - 1; i += 2) {
            if (vertices[i] < _XMin) _XMin = vertices[i];
            else if (vertices[i] > _XMax) _XMax = vertices[i];
            if (vertices[i + 1] < _YMin) _YMin = vertices[i + 1];
            else if (vertices[i + 1] > _YMax) _YMax = vertices[i + 1];
        }
        // Height and width of the mock bounding box
        float width = _XMax - _XMin;
        float height = _YMax - _YMin;
        // Sets the non-localized origin
        this.origin = new Vector2f(_XMin + (width / 2), _YMin + (height / 2));
    }

    @Override
    public void draw() {
        if (textureData == 0) Graphics.drawShape(vertexData, colorData, 2, alpha, vertCount, GL_SHAPE_TYPE);
        // Draw a object that has only texture if there is no color
        else if (colorData == 0) Graphics.drawShape(vertexData, textureData, 2, alpha, vertCount, GL_SHAPE_TYPE, texture);
        // Draw a object that has color and a texture
        else Graphics.drawShape(vertexData, colorData, textureData, 2, alpha, 2, vertCount, GL_SHAPE_TYPE, texture);
    }
    
    @Override
    public void destroy() {
        super.destroy();
        aabb.destroy();
    }
}
