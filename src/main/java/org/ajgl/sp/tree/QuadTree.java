package org.ajgl.sp.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class QuadTree<O> {
    
    private QuadTreeNode<O> rootNode;
    
    public void insert(O object) {
        insert(object, rootNode);
    }
    
    private void insert(O object, QuadTreeNode<O> node) {
        if(node.parent) {
            for(int i=0;i<4;i++)
                if(intersects(object, node.children[i]))
                    insert(object, node.children[i]);
            return;
        }
        
        node.objectList.add(object);
        
        if(node.objectList.size() > QuadTreeNode.maxObjects && node.currentLevel < QuadTreeNode.maxLevels) {
            node.split();
            
            for(O o : node.objectList)
                insert(o, node);
            node.objectList.clear();
        }
    }
    
    public void remove(O object) {
        remove(object, rootNode);
    }
    
    private void remove(O object, QuadTreeNode<O> node) {
        if(node.parent) {
            for(int i=0;i<4;i++)
                if(intersects(object, node.children[i]))
                    remove(object, node.children[i]);
            return;
        }
        
        node.objectList.remove(object);
    }
    
    public boolean isObjectColliding(O object) {
        return isObjectColliding(object, rootNode);
    }
    
    private boolean isObjectColliding(O object, QuadTreeNode<O> node) {
        if(node.parent) {
            for(int i=0;i<4;i++)
                if(intersects(object, node.children[i]))
                    isObjectColliding(object, node);
        } else {
            return node.isObjectColliding(object);
        }
        return false;
    }
    
    public List<O> getPossibleObjectCollisions(O object) {
        List<O> collisionList = new ArrayList<O>();
        getPossibleObjectCollisions(object, rootNode, collisionList);
        return collisionList;
    }

    private void getPossibleObjectCollisions(O object, QuadTreeNode<O> node, List<O> collisionList) {
        if(node.parent) {
            for(int i=0;i<4;i++)
                if(intersects(object, node.children[i]))
                    getPossibleObjectCollisions(object, node.children[i], collisionList);
            return;
        }
        collisionList.addAll(node.objectList);
    }
    
    public List<O> getObjectCollisions(O object) {
        List<O> collisionList = new ArrayList<O>();
        getCollisions(object, rootNode, collisionList);
        return collisionList;
    }
    
    private void getCollisions(O object, QuadTreeNode<O> node, List<O> collisionList) {
        if(node.parent) {
            for(int i=0;i<4;i++)
                if(intersects(object, node.children[i]))
                    getCollisions(object, node.children[i], collisionList);
            return;
        }
        node.getCollisions(object, collisionList);
    }
    
    public abstract boolean intersects(O object, QuadTreeNode<O> node);
}
