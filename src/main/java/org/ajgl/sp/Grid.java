package org.ajgl.sp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Grid<O, N extends GridNode<O>> {

    public double x, y;
    public double width, height;
    public double nodesX, nodesY;
    private List<N> nodeList;
    
    public Grid() {
        x = 0; y = 0;
        width = 600;
        height = 600;
        nodeList = new ArrayList<N>();
    }
    
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
        
        nodeList.clear();
        for(int i=0;i<nodesHeight;i++)
            for(int j=0;j<nodesWidth;j++)
                addNodes(j*nodesWidth, i*nodesHeight, nodesWidth, nodesHeight);
    }
    
    public abstract void addNodes(double x, double y, double width, double height);
    
    public void insert(O object) {
        for(int i=0;i<nodeList.size();i++)
            if(intersects(object, nodeList.get(i)))
                nodeList.get(i).objectList.add(object);
    }
    
    public abstract boolean intersects(O obejct, N node);
    
    public boolean isObjectColliding(O object) {
        for(int i=0;i<nodeList.size();i++)
            if(intersects(object, nodeList.get(i)))
                if(nodeList.get(i).isObjectColliding(object))
                    return true;
        return false;
    }
    
    public List<O> getPossibleObjectCollisions(O object) {
        List<O> collList = new ArrayList<O>();
        for(int i=0;i<nodeList.size();i++)
            if(intersects(object, nodeList.get(i)))
                collList.addAll(nodeList.get(i).objectList);
        
        collList.removeAll(Collections.singleton(object));
        return collList;
    }
    
    public List<O> getObjectCollisions(O object) {
        List<O> collList = new ArrayList<O>();
        for(int i=0;i<nodeList.size();i++)
            if(intersects(object, nodeList.get(i)))
                collList.addAll(nodeList.get(i).getObjectCollisions(object));
        return collList;
    }
    
    public List<N> getNodeList() {
        return nodeList;
    }
}
