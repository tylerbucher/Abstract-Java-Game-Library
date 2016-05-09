/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Tyler Bucher
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.ajgl.math.vector;

import org.lwjgl.system.MemoryUtil;


/**
 * This class is designed to be a 3d vector.
 * @author Tyler Bucher
 */
public class Vector3d extends Vector2d {

    public double z;    // 3rd dimensional value
    
    /**
     * Default Vector3d constructor.
     */
    public Vector3d() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        
        buffer = MemoryUtil.memAllocDouble(3);
        updateBuffer();
    }
    
    /**
     * Creates a Vector3d with three values.
     * @param x - x value.
     * @param y - y value.
     * @param z - z value.
     */
    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        
        buffer = MemoryUtil.memAllocDouble(3);
        updateBuffer();
    }
    
    /**
     * Duplicates a vector to the current vector.
     * @param vector - Vector to be duplicated.
     */
    public Vector3d(Vector3d vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
        
        buffer = MemoryUtil.memAllocDouble(3);
        updateBuffer();
    }
    
    /**
     * Adds two vectors together.
     * @param vector - the vector to be added.
     * @return This vector.
     */
    public Vector3d add(Vector3d vector) {
        super.add(vector);
        this.z += vector.z;
        
        return this;
    }
    
    /**
     * Subtracts to vectors from each other.
     * @param vector - Vector to be subtracted.
     * @return This vector.
     */
    public Vector3d subtract(Vector3d vector) {
        super.subtract(vector);
        this.z += -vector.z;
        
        return this;
    }
    
    /**
     * Performs the dot product on this vector.
     * @param vector - Vector to be dotted with.
     * @return The resulting number.
     */
    public double dot(Vector3d vector) {
        return super.dot(vector) + (this.z *= vector.z);
    }
    
    /**
     * Performs the cross product on this vector.
     * @param vector - Vector to be crossed with.
     * @return The resulting vector.
     */
    public Vector3d cross(Vector3d vector) {
        double cx = (this.y * vector.z) - (this.z * vector.y);
        double cy = (this.z * vector.x) - (this.x * vector.z);
        double cz = (this.x * vector.y) - (this.y * vector.x);
        
        return new Vector3d(cx, cy, cz);
    }
    
    /**
     * Negates this vector.
     */
    @Override
    public Vector3d negate() {
        super.negate();
        z = -z;
        
        return this;
    }
    
    /**
     * Normalizes this vector.
     */
    @Override
    public Vector3d normalize() {
        double mag = this.getMagnitude();
        this.x /= mag;
        this.y /= mag;
        this.z /= mag;
        
        return this;
    }
    
    /**
     * Scales this vector by a value scalar value.
     */
    @Override
    public Vector3d scale(double value) {
        super.scale(value);
        this.z *= value;
        
        return this;
    }
    
    /**
     * Returns the magnitude of this vector.
     */
    @Override
    public double getMagnitude() {
        return Math.sqrt((x*x)+(y*y)+(z*z));
    }
    
    /**
     * Updates the buffer version of this matrix.
     */
    public void updateBuffer() {
        buffer.clear();
        buffer.put(x).put(y);
        buffer.flip();
    }
    
    @Override
    public String toString() {
        return "Vector3d [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
    
    @Override
    protected void finalize() {
        MemoryUtil.memFree(buffer);
    }
}
