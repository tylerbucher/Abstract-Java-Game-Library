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

package org.ajgl.math.matrix2;

import java.nio.IntBuffer;

import org.ajgl.math.vector.Vector2i;
import org.lwjgl.BufferUtils;

/**
 * This class is designed to be a 2x2 matrix.
 * @author Tyler Bucher
 */
public class Matrix2i {
    
    public int m00, m01; // First row
    public int m10, m11; // Second row
    
    /**
     * Default Matrix constructor.
     */
    public Matrix2i() {
        this.loadIdentity();
    }
    
    /**
     * Copies a matrix to this matrix.
     * @param matrix - Matrix to be copied.
     */
    public Matrix2i(Matrix2i matrix) {
        Matrix2i.copyMatrix(matrix, this);
    }
    
    /**
     * Loads the identity matrix.
     */
    public Matrix2i loadIdentity() {
        m00 = 1; m01 = 0;
        m10 = 0; m11 = 1;
        
        return this;
    }
    
    /**
     * Adds a matrix to this matrix.
     * @param matrix - Matrix to be added.
     * @return This matrix.
     */
    public Matrix2i add(Matrix2i matrix) {
        m00 += matrix.m00; m01 += matrix.m01;
        m10 += matrix.m10; m11 += matrix.m11;
        
        return this;
    }
    
    /**
     * Subtracts a matrix from this matrix.
     * @param matrix - Matrix to be subtracted.
     * @return This matrix.
     */
    public Matrix2i subtract(Matrix2i matrix) {
        m00 -= matrix.m00; m01 -= matrix.m01;
        m10 -= matrix.m10; m11 -= matrix.m11;
        
        return this;
    }
    
    /**
     * Multiplies this matrix by another matrix.
     * @param matrix - Matrix to be multiplied.
     * @return This Matrix.
     */
    public Matrix2i multiply(Matrix2i matrix) {
        Matrix2i orig = new Matrix2i(this);
        
        m00 = (orig.m00*matrix.m00)+(orig.m01*matrix.m10);
        m10 = (orig.m10*matrix.m00)+(orig.m11*matrix.m10);
        
        m01 = (orig.m00*matrix.m01)+(orig.m01*matrix.m11);
        m11 = (orig.m10*matrix.m01)+(orig.m11*matrix.m11);
        
        return this;
    }
    
    /**
     * Multiplies this matrix by a scalar value.
     */
    public Vector2i multiply(Vector2i vector) {
        Vector2i newVector = new Vector2i();
        newVector.x = (vector.x*this.m00) + (vector.y*this.m10);
        newVector.y = (vector.x*this.m01) + (vector.y*this.m11);
        
        return newVector;
    }
    
    public Matrix2i multiply(int value) {
        m00 *= value; m01 *= value;
        m10 *= value; m11 *= value;
        
        return this;
    }
    
    /**
     * Divides this matrix by a scalar value.
     */
    public Matrix2i divide(int value) {
        m00 /= value; m01 /= value;
        m10 /= value; m11 /= value;
        
        return this;
    }
    
    /**
     * Negates this matrix.
     */
    public Matrix2i negate() {
        return this.multiply(-1);
    }
    
    /**
     * Returns the buffer version of this matrix.
     */
    public IntBuffer getBuffer() {
        int[] array = {m00, m10,
                          m01, m11};
        IntBuffer buffer = BufferUtils.createIntBuffer(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }
    
    /**
     * Creates a matrix by four vectors.
     * @param col1 - First column.
     * @param col2 - Second column.
     * @param col3 - Third column.
     * @return The new matrix.
     */
    public static Matrix2i createMatrix(Vector2i col1, Vector2i col2) {
        Matrix2i matrix = new Matrix2i();
        matrix.m00 = col1.x; matrix.m01 = col2.x;
        matrix.m10 = col1.y; matrix.m11 = col2.y;
        
        return matrix;
    }
    
    /**
     * Copies a matrix.
     * @param src - Source matrix.
     * @param des - destination matrix.
     * @return The destination matrix.
     */
    public static Matrix2i copyMatrix(Matrix2i src, Matrix2i des) {
        des.m00 = src.m00; des.m01 = src.m01;
        des.m10 = src.m10; des.m11 = src.m11;
        
        return des;
    }
}
