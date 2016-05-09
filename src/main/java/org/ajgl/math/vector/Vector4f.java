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
 * This class is designed to be a 4d vector.
 * @author Tyler Bucher
 */
public class Vector4f extends Vector3f {

    public float w;    // 4th dimensional value
    
    /**
     * Default Vector4d constructor.
     */
    public Vector4f() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
        
        buffer = MemoryUtil.memAllocFloat(4);
        updateBuffer();
    }
    
    /**
     * Creates a Vector4d with four values.
     * @param x - x value.
     * @param y - y value.
     * @param z - z value.
     * @param w - w value.
     */
    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        
        buffer = MemoryUtil.memAllocFloat(4);
        updateBuffer();
    }
    
    /**
     * Duplicates a vector to the current vector.
     * @param vector - Vector to be duplicated.
     */
    public Vector4f(Vector4f vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
        this.w = vector.w;
        
        buffer = MemoryUtil.memAllocFloat(4);
        updateBuffer();
    }
    
    /**
     * Adds two vectors together.
     * @param vector - the vector to be added.
     * @return This vector.
     */
    public Vector4f add(Vector4f vector) {
        super.add(vector);
        
        return this;
    }
    
    /**
     * Subtracts to vectors from each other.
     * @param vector - Vector to be subtracted.
     * @return This vector.
     */
    public Vector4f subtract(Vector4f vector) {
        super.subtract(vector);
        
        return this;
    }
    
    /**
     * Performs the dot product on this vector.
     * @param vector - Vector to be dotted with.
     * @return The resulting number.
     */
    public float dot(Vector4f vector) {
        return super.dot(vector);
    }
    
    /**
     * Performs the cross product on this vector.
     * @param vector - Vector to be crossed with.
     * @return The resulting vector.
     */
    public Vector4f cross(Vector4f vector) {
        float cx = (this.y * vector.z) - (this.z * vector.y);
        float cy = (this.z * vector.x) - (this.x * vector.z);
        float cz = (this.x * vector.y) - (this.y * vector.x);
        
        return new Vector4f(cx, cy, cz, vector.w);
    }
    
    /**
     * Negates this vector.
     */
    @Override
    public Vector4f negate() {
        super.negate();
        
        return this;
    }
    
    /**
     * Normalizes this vector.
     */
    @Override
    public Vector4f normalize() {
        super.normalize();
        
        return this;
    }
    
    /**
     * Returns the magnitude of this vector.
     */
    @Override
    public float getMagnitude() {
        return (float) Math.sqrt((x*x)+(y*y)+(z*z));
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
        return "Vector4f [x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + "]";
    }
    
    @Override
    protected void finalize() {
        MemoryUtil.memFree(buffer);
    }
}
