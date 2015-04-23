package org.ajgl.collision;

import java.util.List;
import java.util.Vector;

public abstract class DataTreeNode<T> {
    
    private Vector<T> objectList;       // The object list of this node
    
    public abstract void add(double x, double y, double z, T object);
    
    public abstract void remove(T object);
    
    public abstract List<T> getCollisions(T object);
    
    protected abstract void split();
    
    public Vector<T> getObjectList() {
        return objectList;
    }
    
    public abstract void draw();
}
