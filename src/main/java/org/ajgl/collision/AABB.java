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
package org.ajgl.collision;

import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPolygonMode;

import org.ajgl.graphics.Graphics;
import org.ajgl.primary.Collidable;
import org.ajgl.primary.Drawable;
import org.lwjgl.util.vector.Vector2f;

/**
 * This class is designed to be a Bounding box.
 * @author Tyler Bucher
 */
public class AABB extends Drawable implements Collidable {
    
    private float dx, dy;   // The current displacement of x, and y
    private float x, y;     // The x and y position of the AABB
    private float height;   // The height of the AABB
    private float width;    // The width of AABB
    
    /**
     * Creates an Axis Aligned Bounding Box off of the given vertices.
     * @param position - The position to base the AABB off of
     */
    public AABB(float[] position) {
        super(position, GL_QUADS);
    }
    
    public AABB(float x, float y, float height, float width) {
        super(new float[]{x, y, x, y+height, x+width, y+height, x+width, y}, GL_QUADS);
    }
    
    /**
     * Calculates this objects non-localized origin.
     * @param vertices - Vertices of a convex shape
     */
    @Override
    protected void calcNonLocOrigin(float[] vertices) {
        this.origin = new Vector2f(x+(width/2), y+(height/2));
    }
    
    /**
     * Updates the vertices of the AABB based of the given Vertices.
     * @param position - The position to base the AABB off of
     */
    public void updateVertices() {
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
        this.dx = 0; this.dy = 0;
    }
    
    /**
     * Sets the new position of the AABB.
     * @param x - The new x position
     * @param y - The new y position
     */
    public void setPosition(float x, float y) {
        this.x = x; this.y = y;
        this.vertices = new float[]{x, y, x, y+height, x+width, y+height, x+width, y};
    }
    
    /**
     * Sets the width of the AABB.
     * @param width - The new width of the AABB
     */
    public void setWidth(float width) {
        this.width = width;
        this.vertices = new float[]{x, y, x, y+height, x+width, y+height, x+width, y};
    }
    
    /**
     * Returns the width of the AABB.
     * @return The width of the AABB
     */
    public float getWidth() {
        return width;
    }
    
    /**
     * Sets the height of the AABB.
     * @param height - The new height of the AABB
     */
    public void setHeight(float height) {
        this.height = height;
        this.vertices = new float[]{x, y, x, y+height, x+width, y+height, x+width, y};
    }
    
    /**
     * Returns the height of the AABB.
     * @return The height of the AABB
     */
    public float getHeight() {
        return height;
    }
    
    @Override
    public boolean contains(float[] vertices) {
        if (vertices[0] >= this.vertices[0] && vertices[1] >= this.vertices[1] && 
                vertices[4] <= this.vertices[4] && vertices[5] <= this.vertices[5]) 
            return true;
        return false;
    }
    
    @Override
    public boolean intersects(float[] vertices) {
        if (this.vertices[4] >= vertices[0] && this.vertices[5] >= vertices[1] && 
                this.vertices[0] <= vertices[4] && this.vertices[1] <= vertices[5]) 
            return true;
        return false;
    }
    
    @Override
    public void draw() {
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glLineWidth(1.0f);
        Graphics.drawShape(vertexData, colorData, 2, 3, vertCount, GL_SHAPE_TYPE);
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    }
}
