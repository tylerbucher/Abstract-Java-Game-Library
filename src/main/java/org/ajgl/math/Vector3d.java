/**
 * 
 */
package org.ajgl.math;

import java.nio.Buffer;


/**
 * @author Tyler
 *
 */
public class Vector3d extends Vector2d {

    public double z;
    
    public Vector3d(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }
    
    public Vector3d(Vector3d vector) {
        super(vector);
        this.z = vector.z;
    }
    
    public Vector3d add(Vector3d vector) {
        super.add(vector);
        this.z += vector.z;
        
        return this;
    }
    
    public Vector3d subtract(Vector3d vector) {
        super.subtract(vector);
        this.z += -vector.z;
        
        return this;
    }
    
    public double dot(Vector3d vector) {
        return super.dot(vector) + (this.z *= vector.z);
    }
    
    public Vector3d cross(Vector3d vector) {//TODO
        double cz = (this.x * vector.y) - (this.y * vector.x);
        
        return new Vector3d(0.0, 0.0, cz);
    }
    
    public Vector3d negate() {
        super.negate();
        z = -z;
        
        return this;
    }
    
    public Vector3d normalize() {
        double mag = this.getMagnitude();
        this.x /= mag;
        this.y /= mag;
        this.z /= mag;
        
        return this;
    }
    
    public Vector3d scale(double value) {
        super.scale(value);
        this.z *= value;
        
        return this;
    }
    
    public double getMagnitude() {
        return Math.sqrt((x*x)+(y*y)+(z*z));
    }
    
    @Override
    public <B extends Buffer> B getBuffer(Class<B> bufferClass) {
        return bufferClass.cast(VectorUtils.glGenDataBuffer(bufferClass, x, y, z));
    }
}
