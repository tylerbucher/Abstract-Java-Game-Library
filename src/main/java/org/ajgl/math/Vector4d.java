package org.ajgl.math;

import java.nio.Buffer;

public class Vector4d extends Vector3d {

    public double w;
    
    public Vector4d() {
        super();
        this.w = 0;
    }
    
    public Vector4d(double x, double y, double z, double w) {
        super(x, y, z);
        this.w = w;
    }
    
    public Vector4d(Vector4d vector) {
        super(vector);
        this.w = vector.w;
    }
    
    public Vector4d add(Vector4d vector) {
        super.add(vector);
        
        return this;
    }
    
    public Vector4d subtract(Vector4d vector) {
        super.subtract(vector);
        
        return this;
    }
    
    public double dot(Vector4d vector) {
        return super.dot(vector);
    }
    
    public Vector4d cross(Vector4d vector) {
        double cx = (this.y * vector.z) - (this.z * vector.y);
        double cy = (this.z * vector.x) - (this.x * vector.z);
        double cz = (this.x * vector.y) - (this.y * vector.x);
        
        return new Vector4d(cx, cy, cz, vector.w);
    }
    
    public Vector4d negate() {
        super.negate();
        
        return this;
    }
    
    public Vector4d normalize() {
        double mag = this.getMagnitude();
        this.x /= mag;
        this.y /= mag;
        this.z /= mag;
        
        return this;
    }
    
    public double getMagnitude() {
        return Math.sqrt((x*x)+(y*y)+(z*z));
    }
    
    @Override
    public <B extends Buffer> B getBuffer(Class<B> bufferClass) {
        return bufferClass.cast(VectorUtils.glGenDataBuffer(bufferClass, x, y, z, w));
    }
}
