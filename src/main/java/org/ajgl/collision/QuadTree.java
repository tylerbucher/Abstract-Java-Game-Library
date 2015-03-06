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

import java.util.List;

import org.ajgl.primary.GameObject;

/**
 * This class is designed to be a 2d simplification object that helps with
 * object collision.
 * @author Tyler Bucher
 */
public class QuadTree {
    
    private int xSize;                      // The horizontal size of the QuadTree
    private int ySize;                      // The vertical size of the QuadTree
    private int maxSize;                    // The maximum number of object in a node
    
    private final QuadTreeNode rootNode;    //The root node of the QuadTree i.e. the first one
    
    static int splitSize = 0;               //The number sub nodes there are
    
    /**
     * A Simplified QuadTree used for efficient 2D collision Detection.
     */
    public QuadTree() {
        xSize = 255;
        ySize = 255;
        maxSize = 10;
        
        rootNode = new QuadTreeNode(xSize, ySize, 0, 0, maxSize, 0);
    }
    
    /**
     * A Simplified QuadTree used for efficient 2D collision Detection.
     * @param xSize - The horizontal size of the QuadTree
     * @param ySize - The vertical size of the QuadTree
     * @param maxSize - The Maximum size of the QuadTree
     */
    public QuadTree(int xSize, int ySize, int maxSize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.maxSize = maxSize;
        
        rootNode = new QuadTreeNode(xSize, ySize, 0, 0, maxSize, 0);
    }
    
    /**
     * Adds a object to the QuadTree.
     * @param gameObject - The GameObject to be added
     */
    public void add(GameObject gameObject) {
        this.rootNode.add(gameObject);
    }
    
    /**
     * Draws the QuadTree.
     */
    public void draw() {
        this.rootNode.draw();
    }
    
    /**
     * Returns an Vector list of possible collisions.
     * @param gameObject - The GameObject to be checked
     * @return Possible collisions with the given HashCode
     */
    public List<GameObject> getPossibleCollision(GameObject gameObject) {
        return this.rootNode.gPC(gameObject);
    }
    
    /**
     * Removes an object from the QuadTree.
     * @param gameObject - The GameObejct to be removed
     */
    public void remove(GameObject gameObject) {
        this.rootNode.remove(gameObject);
    }
    
    /**
     * Updates an object in all nodes. If the object is not in the QuadTree to
     * start with this method will add it.
     * @param gameObject - The GameObejct to be updated
     */
    public void updateObject(GameObject gameObject) {
        this.rootNode.updateObject(gameObject);
    }
    
    /**
     * Checks to see if the QuadTree contains a certain object.
     * @param gameObject - The object to be checked
     * @return True if this QuadTree contains the object
     */
    public boolean contains(GameObject gameObject) {
        return this.rootNode.contains(gameObject);
    }
}
