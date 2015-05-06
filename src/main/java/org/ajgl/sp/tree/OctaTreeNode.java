package org.ajgl.sp.tree;

import java.util.List;
import java.util.Vector;

public abstract class OctaTreeNode<O> {

    public static int maxObjects;           // The max objects the nodes can have
    public static int maxLevels;            // The max levels the QuadTree can have
    
    public final int currentLevel;          // States the current level of this node
    public Vector<O> objectList;            // The object list of this node
    
    protected boolean parent;               // States if this node is a parent
    protected OctaTreeNode<O>[] children;   // The child nodes of this node
    
    protected double x, y, z;               // The x and y position of this node
    protected double width, height, depth;  // The width and height of the node
    
    /**
     * Creates a new QuadTree node (Usually a child node).
     * @param x - The x position of this node.
     * @param y - The y position of this node.
     * @param width - The width of the node.
     * @param height - The height of this node.
     * @param currentLevel - The current depth level of the node.
     */
    public OctaTreeNode(double x, double y, double z, double width, double height, double depth, int currentLevel) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.currentLevel = currentLevel;
    }
    

    public OctaTreeNode<O>[] getChildren() {
        return children;
    }
    
    public boolean isObjectColliding(O object) {
        for(O o : objectList)
            if(checkObject(object, o))
                return true;
        return false;
    }

    public void getCollisions(O object, List<O> collisionList) {
        for(O t : objectList)
            if(checkObject(object, t))
                collisionList.add(t);
    }

    protected abstract void split();
    
    protected abstract boolean checkObject(O checker, O checkie);
}
