package org.ajgl.math;

import java.nio.Buffer;

public class Vector2d {
    
    public double x;
    public double y;
    
    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector2d(Vector2d vector) {
        this.x = vector.x;
        this.y = vector.y;
    }
    
    public Vector2d add(Vector2d vector) {
        this.x += vector.x;
        this.y += vector.y;
        
        return this;
    }
    
    public Vector2d subtract(Vector2d vector) {
        this.x += -vector.x;
        this.y += -vector.y;
        
        return this;
    }
    
    public double dot(Vector2d vector) {
        return (this.x *= vector.x) + (this.y *= vector.y);
    }
    
    public Vector3d cross(Vector2d vector) {
        double cz = (this.x * vector.y) - (this.y * vector.x);
        
        return new Vector3d(0.0, 0.0, cz);
    }
    
    public Vector2d negate() {
        x = -x;
        y = -y;
        
        return this;
    }
    
    public Vector2d normalize() {
        double mag = this.getMagnitude();
        this.x /= mag;
        this.y /= mag;
        
        return this;
    }
    
    public Vector2d scale(double value) {
        this.x *= value;
        this.y *= value;
        
        return this;
    }
    
    public double getMagnitude() {
        return Math.hypot(x, y);
    }
    
    public <B extends Buffer> B getBuffer(Class<B> bufferClass) {
        return bufferClass.cast(VectorUtils.glGenDataBuffer(bufferClass, x, y));
    }
}
