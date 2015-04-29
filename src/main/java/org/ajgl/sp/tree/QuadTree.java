package org.ajgl.sp.tree;

import java.util.List;

public abstract class QuadTree<O, N extends QuadTreeNode<O>> {
    
    public boolean isObjectColliding(O object) {
        
        return false;
    }

    public List<O> getPossibleObjectCollisions(O object) {
        
        return null;
    }
    
    public List<O> getObjectCollisions(O object) {
        
        return null;
    }
}
