package org.ajgl.sp.tree;

import java.util.List;
import java.util.Vector;

public class QuadTreeNode<T> {

    
    public static int maxObjects;           // The max objects the nodes can have
    public static int maxLevels;            // The max levels the QuadTree can have
    
    public final int currentLevel;          // States the current level of this node
    public Vector<T> objectList;            // The object list of this node
    
    protected boolean parent;               // States if this node is a parent
    protected QuadTreeNode<T>[] children;   // The child nodes of this node
    
    private double x, y;                    // The x and y position of this node
    private double width, height;           // The width and height of the node
    
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

    
    
    public boolean isObjectColliding(T object) {
            for(T t : objectList)
                if(checkObject(object, t))
                    return true;
        return false;
    }
    
    public void getPossibleObjectCollisions(T object, List<T> collisionList) {
        if(parent) {
            for(int i=0;i<4;i++)
                if(intersects(object, children[i]))
                    children[i].getPossibleObjectCollisions(object, collisionList);
            return;
        }
        collisionList.addAll(objectList);
    }

    public void getCollisions(T object, List<T> collisionList) {
        if(parent) {
            for(int i=0;i<4;i++)
                if(intersects(object, children[i]))
                    children[i].isObjectColliding(object);
            return;
        }
        
        for(T t : objectList)
            if(checkObject(object, t))
                collisionList.add(t);
        
    }

    @SuppressWarnings("unchecked")
    protected void split() {
        parent = true;
        children = (QuadTreeNode<T>[]) new QuadTreeNode<?>[4];
        double hWidth = width/2, hHeight = height/2;
        
        children[0] = new QuadTreeNode<T>(x,y+hHeight,hWidth,hHeight,currentLevel+1);
        children[1] = new QuadTreeNode<T>(x+hWidth,y+hHeight,hWidth,hHeight,currentLevel+1);
        children[2] = new QuadTreeNode<T>(x,y,hWidth,hHeight,currentLevel+1);
        children[3] = new QuadTreeNode<T>(x+hWidth,y,hWidth,hHeight,currentLevel+1);
    }
    
    public QuadTreeNode<T>[] getChildren() {
        return children;
    }
    
    protected abstract boolean checkObject(T checker, T checkie);
}
