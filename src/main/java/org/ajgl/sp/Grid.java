package org.ajgl.sp;

import java.util.ArrayList;
import java.util.List;

public class Grid<T> {

    public double x, y;
    public double width, height;
    public double nodesX, nodesY;
    private List<GridNode> nodeList;
    
    public Grid() {
        x = 0; y = 0;
        width = 600;
        height = 600;
        nodeList = new ArrayList<GridNode>();
    }
    
    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param nodesX
     * @param nodesY
     */
    public Grid(double x, double y, double width, double height, double nodesX, double nodesY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.nodesX = nodesX;
        this.nodesY = nodesY;
    }

    public void resize() {
        double nodesWidth = width / nodesX;
        double nodesHeight = height / nodesY;
        for(int i=0;i<nodesWidth;i++) {
            
        }
    }
    
    public List<GridNode> getNodeList() {
        return nodeList;
    }
}
