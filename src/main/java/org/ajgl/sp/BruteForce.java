package org.ajgl.sp;

import java.util.ArrayList;
import java.util.List;

public abstract class BruteForce<T> {
    
    public List<T> objectList;
    
    public BruteForce() {
        objectList = new ArrayList<T>();
    }
    
    public boolean checkCollision(T object) {
        for(T t : objectList)
            if(this.check(object, t))
                return true;
        return false;
    }
    
    public List<T> getCollisions(T object) {
        List<T> collList = new ArrayList<T>();
        for(T t : objectList)
            if(this.check(object, t))
                collList.add(t);
        return collList;
    }
    
    public abstract boolean check(T checker, T checkie);
}
