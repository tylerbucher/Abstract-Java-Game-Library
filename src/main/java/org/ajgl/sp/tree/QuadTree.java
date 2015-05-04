package org.ajgl.sp.tree;

import java.util.List;

public abstract class QuadTree<O, T extends QuadTreeNode<O>> {
    
    private T rootNode;
    
    public void insert(O object) {
        insert(object, rootNode);
    }
    
    public <N extends QuadTreeNode<O>> void insert(O object, N node) {
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
    
    public <N extends QuadTreeNode<O>> void remove(O object, N node) {
        if(node.parent) {
            for(int i=0;i<4;i++)
                if(intersects(object, node.children[i]))
                    remove(object, node.children[i]);
            return;
        }
        
        node.objectList.remove(object);
    }
    
    public <N extends QuadTreeNode<O>> boolean isObjectColliding(O object, N node) {
        if(node.parent) {
            for(int i=0;i<4;i++)
                if(intersects(object, node.children[i]))
                    isObjectColliding(object, node);
        } else {
            node.isObjectColliding(object);
        }
        return false;
    }

    public List<O> getPossibleObjectCollisions(O object) {
        
        return null;
    }
    
    public List<O> getObjectCollisions(O object) {
        
        return null;
    }
    
    public abstract <N extends QuadTreeNode<O>> boolean intersects(O object, N node);
    
    protected abstract boolean checkObject(O checker, O checkie);
}
