package org.ajgl.math;

import java.nio.Buffer;

public class Vector2<T extends Number, B extends Buffer> {
    
    public T x;
    public T y;
    
     
    
    public Vector2(T x, T y) {
        this.x = x;
        this.y = y;
    }
    
    public B getBuffer() {
        B buffer;
    }
}
