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

import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;


/**
 * This class is designed to be a 3d vector.
 * @author Tyler Bucher
 */
public class Vector3s extends Vector2s {

    public short z;    // 3rd dimensional value
    
    /**
     * Default Vector3d constructor.
     */
    public Vector3s() {
        super();
        this.z = 0;
    }
    
    /**
     * Creates a Vector3d with three values.
     * @param x - x value.
     * @param y - y value.
     * @param z - z value.
     */
    public Vector3s(short x, short y, short z) {
        super(x, y);
        this.z = z;
    }
    
    /**
     * Duplicates a vector to the current vector.
     * @param vector - Vector to be duplicated.
     */
    public Vector3s(Vector3s vector) {
        super(vector);
        this.z = vector.z;
    }
    
    /**
     * Adds two vectors together.
     * @param vector - the vector to be added.
     * @return This vector.
     */
    public Vector3s add(Vector3s vector) {
        super.add(vector);
        this.z += vector.z;
        
        return this;
    }
    
    /**
     * Subtracts to vectors from each other.
     * @param vector - Vector to be subtracted.
     * @return This vector.
     */
    public Vector3s subtract(Vector3s vector) {
        super.subtract(vector);
        this.z += -vector.z;
        
        return this;
    }
    
    /**
     * Performs the dot product on this vector.
     * @param vector - Vector to be dotted with.
     * @return The resulting number.
     */
    public short dot(Vector3s vector) {
        return (short) (super.dot(vector) + (this.z *= vector.z));
    }
    
    /**
     * Performs the cross product on this vector.
     * @param vector - Vector to be crossed with.
     * @return The resulting vector.
     */
    public Vector3s cross(Vector3s vector) {
        short cx = (short) ((this.y * vector.z) - (this.z * vector.y));
        short cy = (short) ((this.z * vector.x) - (this.x * vector.z));
        short cz = (short) ((this.x * vector.y) - (this.y * vector.x));
        
        return new Vector3s(cx, cy, cz);
    }
    
    /**
     * Negates this vector.
     */
    @Override
    public Vector3s negate() {
        super.negate();
        z = (short) -z;
        
        return this;
    }
    
    /**
     * Normalizes this vector.
     */
    @Override
    public Vector3s normalize() {
        short mag = this.getMagnitude();
        this.x /= mag;
        this.y /= mag;
        this.z /= mag;
        
        return this;
    }
    
    /**
     * Scales this vector by a value scalar value.
     */
    @Override
    public Vector3s scale(short value) {
        super.scale(value);
        this.z *= value;
        
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
     * Returns the buffer version of this vector.
     */
    @Override
    public ShortBuffer getBuffer() {
        short[] array = {x, y, z};
        
        ShortBuffer buffer = BufferUtils.createShortBuffer(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }
    
    @Override
    public String toString() {
        return "Vector3s [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
}
