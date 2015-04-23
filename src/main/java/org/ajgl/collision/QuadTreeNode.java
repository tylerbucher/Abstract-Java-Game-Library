/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Tyler Bucher
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.ajgl.collision;

import java.util.List;
import java.util.Vector;

/**
 * 
 * @author Tyler Bucher
 *
 * @param <T>
 */
public class QuadTreeNode<T> extends DataTreeNode<T> {
    
    public static int maxObjects;       // The max objects the nodes can have
    
    public double x, y;                 // The x and y position of this node
    public double width, height;        // The width and height of the node
    public final int currentLevel;      // States the current level of this node
    
    private boolean parent;             // States if this node is a parent
    private QuadTreeNode<T>[] children; // The child nodes of this node
    private Vector<T> objectList;       // The object list of this node
    
    /**
     * Creates a new QuadTree node (Usually a child node).
     * @param x - The x position of this node.
     * @param y - The y position of this node.
     * @param width - The width of the node.
     * @param height - The height of this node.
     * @param currentLevel - The current depth level of the node.
     */
    public QuadTreeNode(double x, double y, double width, double height, int currentLevel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.currentLevel = currentLevel;
    }
    
    public boolean isParent() {
        return parent;
    }

    @Override
    public void add(double x, double y, double z, T object) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remove(T object) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<T> getCollisions(T object) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void split() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        
    }
}
