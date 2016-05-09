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
public class Vector4s extends Vector3s {

    public short w;    // 4th dimensional value
    
    /**
     * Default Vector4d constructor.
     */
    public Vector4s() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
        
        buffer = MemoryUtil.memAllocShort(4);
        updateBuffer();
    }
    
    /**
     * Creates a Vector4d with four values.
     * @param x - x value.
     * @param y - y value.
     * @param z - z value.
     * @param w - w value.
     */
    public Vector4s(short x, short y, short z, short w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        
        buffer = MemoryUtil.memAllocShort(4);
        updateBuffer();
    }
    
    /**
     * Duplicates a vector to the current vector.
     * @param vector - Vector to be duplicated.
     */
    public Vector4s(Vector4s vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
        this.w = vector.w;
        
        buffer = MemoryUtil.memAllocShort(4);
        updateBuffer();
    }
    
    /**
     * Adds two vectors together.
     * @param vector - the vector to be added.
     * @return This vector.
     */
    public Vector4s add(Vector4s vector) {
        super.add(vector);
        
        return this;
    }
    
    /**
     * Subtracts to vectors from each other.
     * @param vector - Vector to be subtracted.
     * @return This vector.
     */
    public Vector4s subtract(Vector4s vector) {
        super.subtract(vector);
        
        return this;
    }
    
    /**
     * Performs the dot product on this vector.
     * @param vector - Vector to be dotted with.
     * @return The resulting number.
     */
    public short dot(Vector4s vector) {
        return super.dot(vector);
    }
    
    /**
     * Performs the cross product on this vector.
     * @param vector - Vector to be crossed with.
     * @return The resulting vector.
     */
    public Vector4s cross(Vector4s vector) {
        short cx = (short) ((this.y * vector.z) - (this.z * vector.y));
        short cy = (short) ((this.z * vector.x) - (this.x * vector.z));
        short cz = (short) ((this.x * vector.y) - (this.y * vector.x));
        
        return new Vector4s(cx, cy, cz, vector.w);
    }
    
    /**
     * Negates this vector.
     */
    @Override
    public Vector4s negate() {
        super.negate();
        
        return this;
    }
    
    /**
     * Normalizes this vector.
     */
    @Override
    public Vector4s normalize() {
        super.normalize();
        
        return this;
    }
    
    /**
     * Returns the magnitude of this vector.
     */
    @Override
    public short getMagnitude() {
        return (short) Math.sqrt((x*x)+(y*y)+(z*z));
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
        return "Vector4s [x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + "]";
    }
    
    @Override
    protected void finalize() {
        MemoryUtil.memFree(buffer);
    }
}
