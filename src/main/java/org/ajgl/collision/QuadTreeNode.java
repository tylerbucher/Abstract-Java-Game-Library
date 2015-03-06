/**
 * AJGL, an abstract java game library that provides useful functions for making a game.
 * Copyright (C) 2014 Tyler Bucher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
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
import static org.lwjgl.opengl.GL11.glPolygonMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.ajgl.graphics.Graphics;
import org.ajgl.primary.GameObject;
import org.lwjgl.opengl.GL11;

/**
 * This class is designed to to act as a QuadTree node and should not be
 * extended unless necessary.
 * @author Tyler Bucher
 */
public class QuadTreeNode extends GameObject {
    
    private int maxSize;                    // The maximum number of object in a node
    private int curlevel;                   // The current level of this node
    private boolean children;               // Specifies if this node has any children
    private float width, height;            // The height and width of the node
    
    private Vector<GameObject> objectList;  // The list of objects in this specific node
    private QuadTreeNode[] quadList;        // The list of children nodes this node has
    
    /**
     * Creates a new node for the QuadTree (Usually a child node).
     */
    protected QuadTreeNode() {
        super(new float[] {0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f}, GL11.GL_POINT);
        this.setHeight(10.0f);
        this.setWidth(10.0f);
        
        objectList = new Vector<>();
    }
    
    /**
     * Creates a new node for the QuadTree (Usually a child node).
     * @param width - The width of the node
     * @param height - The height of this node
     * @param x - The x position of this node
     * @param y - The y position of this node
     * @param maxSize - The maximum number of object in a node
     * @param curlevel - The current depth level of the node
     */
    protected QuadTreeNode(float width, float height, float x, float y, int maxSize, int curlevel) {
        super(new float[] {x, y, x, y + height, x + width, y + height, x + width, y}, GL11.GL_QUADS);
        this.setHeight(height);
        this.setWidth(width);
        this.maxSize = maxSize;
        this.curlevel = curlevel;
        
        objectList = new Vector<>();
    }
    
    /**
     * Finds all the nodes this object belongs to.
     * @param gameObject - The GameObject to be checked
     * @param list - The list to be returned containing possible
     * collisions
     * @return The list of possible collisions
     */
    private List<GameObject> findNodeOfObject(GameObject gameObject, List<GameObject> list) {
        if (children)
            for (int i = 0; i < 4; i++)
                quadList[i].findNodeOfObject(gameObject, list);
        else if (objectList.contains(gameObject))
            list.addAll(objectList);
        return list;
    }
    
    /**
     * Checks to see if the quadTree contains a object.
     * @param gameObject - The object to check
     * @return True if the quadTree contains the object
     */
    public boolean contains(GameObject gameObject) {
        try {
            rContains(gameObject);
        } catch (Exception e) {
            return true;
        }
        return false;
    }
    
    /**
     * Throw exception if node contains object
     * @param gameObject - The object to check for
     * @throws Exception - Thrown if quadtree owns this object
     */
    private void rContains(GameObject gameObject) throws Exception {
        if(children) {
            for(int i=0;i<4;i++)
                quadList[i].contains(gameObject);
            return;
        }
        else if(objectList.contains(gameObject)){
            throw new Exception();
        }
    }
    
    /**
     * Creates children nodes for this node.
     */
    public void split() {
        // Creates child nodes
        quadList = new QuadTreeNode[] {
                new QuadTreeNode(width / 2, height / 2, vertices[0], vertices[1], maxSize, curlevel+1), 
                new QuadTreeNode(width / 2, height / 2, vertices[0], vertices[1] + (height / 2), maxSize, curlevel+1), 
                new QuadTreeNode(width / 2, height / 2, vertices[0] + (width / 2), vertices[1] + (height / 2), maxSize, curlevel+1), 
                new QuadTreeNode(width / 2, height / 2, vertices[0] + (width / 2), vertices[1], maxSize, curlevel+1)};
        children = true;
    }
    
    /**
     * Adds an object the this node or to its child nodes.
     * @param gameObject - The object to add
     */
    public void add(GameObject gameObject) {
        // If this object has children add the object to the correct child node
        if (children) {
            for (int i = 0; i < 4; i++)
                // If the object intersects with any child node add it to it
                if (quadList[i].getAabb().intersects(gameObject.getVertices()))
                    quadList[i].add(gameObject);
            return;
        }
        // Ads the object this node
        objectList.add(gameObject);
        
        if (objectList.size() > getMaxSize() && curlevel < 6) {
            split();
            // Increase size of [splitSize] because it split
            QuadTree.splitSize++;
            for (int j = 0; j < objectList.size(); j++)
                add(objectList.get(j));
            objectList.clear();
        }
    }
    
    /**
     * Removes an object from all nodes.
     * @param gameObject - The object to be removed
     */
    public void remove(GameObject gameObject) {
        // If this node has children remove object from children
        if (children) {
            for (int i = 0; i < 4; i++)
                quadList[i].remove(gameObject);
            return;
        }
        // If objectList has this hash in it remove it
        int index = objectList.indexOf(gameObject);
        if (index != -1)
            objectList.remove(index);
    }
    
    /**
     * Gets possible collisions with e give HashCode.
     * @param gameObject - The GameObject to be checked
     * @return A Vector list of the possible collisions
     */
    public List<GameObject> gPC(GameObject gameObject) {
        return this.findNodeOfObject(gameObject, new ArrayList<GameObject>());
    }
    
    /**
     * Updates an object in all nodes. If the object is not in the QuadTree to
     * start with this method will add it.
     * @param gameObject - The object to be updated
     */
    public void updateObject(GameObject gameObject) {
        remove(gameObject);
        add(gameObject);
    }
    
    /**
     * True if this node has children nodes.
     * @return True if and only if this node has children nodes
     */
    public boolean hasChildren() {
        return children;
    }
    
    /**
     * Sets the width of the QuadTreeNode.
     * @param width - The new width of the QuadTreeNode
     */
    public void setWidth(float width) {
        this.width = width;
    }
    
    /**
     * Sets the height of the QuadTreeNode.
     * @param height - The new height of the QuadTreeNode
     */
    public void setHeight(float height) {
        this.height = height;
    }
    
    /**
     * Sets this nodes object list.
     * @param objectList - The vector list to be set
     */
    public void setObjectList(Vector<GameObject> objectList) {
        this.objectList = (Vector<GameObject>) objectList;
    }
    
    /**
     * Returns the max size of this node.
     * @return The max size of this node
     */
    public int getMaxSize() {
        return maxSize;
    }
    
    /**
     * Returns the width of the QuadTreeNode.
     * @return The width of the QuadTreeNode
     */
    public float getWidth() {
        return width;
    }
    
    /**
     * Returns the height of the QuadTreeNode.
     * @return The height of the QuadTreeNode
     */
    public float getHeight() {
        return height;
    }
    
    /**
     * Returns this nodes object list.
     * @return The objects hashes in a integer vector
     */
    public Vector<GameObject> getObjectList() {
        return objectList;
    }
    
    @Override
    public void draw() {
        // Draw the outline of this node
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        Graphics.drawShape(vertexData, getColorData(), 2, 4, getVertCount(), getGL_SHAPE_TYPE());
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        // Draw the children if there are any
        if (children) 
            for (int i = 0; i < quadList.length; i++)
                quadList[i].draw();
    }
    
    @Override
    public String toString() {
        // Return the node by using its position
        return Arrays.toString(vertices);
    }
}
