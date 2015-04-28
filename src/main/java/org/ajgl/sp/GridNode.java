/**
 * 
 */
package org.ajgl.sp;

import java.util.List;


/**
 * @author Tyler
 *
 */
public class GridNode<T> {
    
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
    
    
}
