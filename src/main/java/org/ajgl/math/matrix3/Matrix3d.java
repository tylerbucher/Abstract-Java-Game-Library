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

package org.ajgl.math.matrix3;

import java.nio.DoubleBuffer;

import org.ajgl.math.matrix2.Matrix2d;
import org.ajgl.math.vector.Vector3d;
import org.lwjgl.BufferUtils;

/**
 * This class is designed to be a 3x3 matrix.
 * @author Tyler Bucher
 */
public class Matrix3d extends Matrix2d {
    
    public double /*m00, m01,*/ m02;    // First row
    public double /*m10, m11,*/ m12;    // Second row
    public double   m20, m21,   m22;    // Third row
    
    /**
     * Default Matrix constructor.
     */
    public Matrix3d() {
        this.loadIdentity();
    }
    
    /**
     * Copies a matrix to this matrix.
     * @param matrix - Matrix to be copied.
     */
    public Matrix3d(Matrix3d matrix) {
        Matrix3d.copyMatrix(matrix, this);
    }
    
    /**
     * Loads the identity matrix.
     */
    @Override
    public Matrix3d loadIdentity() {
        super.loadIdentity(); m02 = 0;
                              m12 = 0;
        m20 = 0; m21 = 0;     m22 = 1;
        
        return this;
    }
    
    /**
     * Adds a matrix to this matrix.
     * @param matrix - Matrix to be added.
     * @return This matrix.
     */
    public Matrix3d add(Matrix3d matrix) {
        super.add(matrix);                    m02 += matrix.m02;
                                              m12 += matrix.m12;
        m20 += matrix.m20; m21 += matrix.m21; m22 += matrix.m22;
        
        return this;
    }
    
    /**
     * Subtracts a matrix from this matrix.
     * @param matrix - Matrix to be subtracted.
     * @return This matrix.
     */
    public Matrix3d subtract(Matrix3d matrix) {
        super.subtract(matrix);               m02 -= matrix.m02;
                                              m12 -= matrix.m12;
        m20 -= matrix.m20; m21 -= matrix.m21; m22 -= matrix.m22;
        
        return this;
    }
    
    /**
     * Multiplies this matrix by another matrix.
     * @param matrix - Matrix to be multiplied.
     * @return This Matrix.
     */
    public Matrix3d multiply(Matrix3d matrix) {
        Matrix3d orig = new Matrix3d(this);
        
        m00 = (orig.m00*matrix.m00)+(orig.m01*matrix.m10)+(orig.m02*matrix.m20);
        m10 = (orig.m10*matrix.m00)+(orig.m11*matrix.m10)+(orig.m12*matrix.m20);
        m20 = (orig.m20*matrix.m00)+(orig.m21*matrix.m10)+(orig.m22*matrix.m20);
        
        m01 = (orig.m00*matrix.m01)+(orig.m01*matrix.m11)+(orig.m02*matrix.m21);
        m11 = (orig.m10*matrix.m01)+(orig.m11*matrix.m11)+(orig.m12*matrix.m21);
        m21 = (orig.m20*matrix.m01)+(orig.m21*matrix.m11)+(orig.m22*matrix.m21);
        
        m02 = (orig.m00*matrix.m02)+(orig.m01*matrix.m12)+(orig.m02*matrix.m22);
        m12 = (orig.m10*matrix.m02)+(orig.m11*matrix.m12)+(orig.m12*matrix.m22);
        m22 = (orig.m20*matrix.m02)+(orig.m21*matrix.m12)+(orig.m22*matrix.m22);
        
        return this;
    }
    
    /**
     * Multiplies this matrix by a scalar value.
     */
    @Override
    public Matrix3d multiply(double value) {
        super.multiply(value);      m02 *= value;
                                    m12 *= value;
        m20 *= value; m21 *= value; m22 *= value;
        
        return this;
    }
    
    /**
     * Divides this matrix by a scalar value.
     */
    @Override
    public Matrix3d divide(double value) {
        super.divide(value);        m02 /= value;
                                    m12 /= value;
        m20 /= value; m21 /= value; m22 /= value;
        
        return this;
    }
    
    /**
     * Negates this matrix.
     */
    @Override
    public Matrix3d negate() {
        return this.multiply(-1);
    }
    
    /**
     * Returns the buffer version of this matrix.
     */
    public DoubleBuffer getBuffer() {
        double[] array = {m00, m10, m20,
                          m01, m11, m21, 
                          m02, m12, m22};
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(array.length);
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
    public static Matrix3d createMatrix(Vector3d col1, Vector3d col2, Vector3d col3) {
        Matrix3d matrix = new Matrix3d();
        matrix.m00 = col1.x; matrix.m01 = col2.x; matrix.m02 = col3.x;
        matrix.m10 = col1.y; matrix.m11 = col2.y; matrix.m12 = col3.y;
        matrix.m20 = col1.z; matrix.m21 = col2.z; matrix.m22 = col3.z;
        
        return matrix;
    }
    
    /**
     * Copies a matrix.
     * @param src - Source matrix.
     * @param des - destination matrix.
     * @return The destination matrix.
     */
    public static Matrix3d copyMatrix(Matrix3d src, Matrix3d des) {
        des.m00 = src.m00; des.m01 = src.m01; des.m02 = src.m02;
        des.m10 = src.m10; des.m11 = src.m11; des.m12 = src.m12;
        des.m20 = src.m20; des.m21 = src.m21; des.m22 = src.m22;
        
        return des;
    }
}
