/**
 * 
 */
package org.ajgl.sp;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Tyler
 *
 */
public abstract class GridNode<T> {
    
    public List<T> objectList;
    private double x, y;
    private double width, height;
    
    /**
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public GridNode(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean isObjectColliding(T object) {
        for(T t : objectList)
            if(checkObject(object, t))
                return true;
        return false;
    }
    
    public List<T> getObjectCollisions(T object) {
        List<T> collList = new ArrayList<T>();
        for(T t : objectList)
            if(checkObject(object, t))
                collList.add(t);
        return collList;
    }
    
    protected abstract boolean checkObject(T checker, T checkie);

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }
}
