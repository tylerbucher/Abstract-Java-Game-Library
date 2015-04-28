package org.ajgl.sp;

import java.util.ArrayList;
import java.util.List;

public abstract class BruteForce<T> {
    
    public List<T> objectList;
    
    public BruteForce() {
        objectList = new ArrayList<T>();
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
}
