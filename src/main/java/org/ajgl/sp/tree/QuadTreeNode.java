package org.ajgl.sp.tree;

import java.util.List;
import java.util.Vector;

public abstract class QuadTreeNode<T> {

    
    public static int maxObjects;       // The max objects the nodes can have
    public static int maxLevels;        // The max levels the QuadTree can have
    
    private double x, y;                // The x and y position of this node
    private double width, height;       // The width and height of the node
    private final int currentLevel;     // States the current level of this node
    
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

    public void add(T object) {
        if(parent) {
            for(int i=0;i<4;i++)
                if(intersects(object, children[i]))
                    children[i].add(object);
            return;
        }
        
        objectList.add(object);
        
        if(objectList.size() > maxObjects && currentLevel < maxLevels) {
            split();
            parent = true;
            
            for(T t : objectList)
                add(t);
            objectList.clear();
        }
    }

    public void remove(T object) {
        if(parent) {
            for(int i=0;i<4;i++)
                if(intersects(object, children[i]))
                    children[i].remove(object);
            return;
        }
        
        objectList.remove(object);
    }
    
    public boolean isObjectColliding(T object) {
        if(parent)
            for(int i=0;i<4;i++)
                if(intersects(object, children[i]))
                    children[i].isObjectColliding(object);
        else
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

    protected abstract void split();
    
    public abstract boolean intersects(T object, QuadTreeNode<T> node);
    
    protected abstract boolean checkObject(T checker, T checkie);
}
