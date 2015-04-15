package org.ajgl.math;

import java.nio.Buffer;

public class Matrix2d {
    
    public double m00, m01;
    public double m10, m11;
    
    public Matrix2d() {
        this.loadIdentity();
    }
    
    public Matrix2d(Matrix2d matrix) {
        Matrix2d.copyMatrix(matrix, this);
    }
    
    public Matrix2d loadIdentity() {
        m00 = 1; m01 = 0;
        m10 = 0; m11 = 1;
        
        return this;
    }
    
    public Matrix2d add(Matrix2d matrix) {
        m00 += matrix.m00; m01 += matrix.m01;
        m10 += matrix.m10; m11 += matrix.m11;
        
        return this;
    }
    
    public Matrix2d subtract(Matrix2d matrix) {
        m00 -= matrix.m00; m01 -= matrix.m01;
        m10 -= matrix.m10; m11 -= matrix.m11;
        
        return this;
    }
    
    public Matrix2d multiply(Matrix2d matrix) {
        Matrix2d orig = new Matrix2d(this);
        
        m00 = (orig.m00*matrix.m00)+(orig.m01*matrix.m10);
        m10 = (orig.m10*matrix.m00)+(orig.m11*matrix.m10);
        
        m01 = (orig.m00*matrix.m01)+(orig.m01*matrix.m11);
        m11 = (orig.m10*matrix.m01)+(orig.m11*matrix.m11);
        
        return this;
    }
    
    public Vector2d multiply(Vector2d vector) {
        Vector2d newVector = new Vector2d();
        newVector.x = (vector.x*this.m00) + (vector.y*this.m10);
        newVector.y = (vector.x*this.m01) + (vector.y*this.m11);
        
        return newVector;
    }
    
    public Matrix2d multiply(double value) {
        m00 *= value; m01 *= value;
        m10 *= value; m11 *= value;
        
        return this;
    }
    
    public Matrix2d divide(double value) {
        m00 /= value; m01 /= value;
        m10 /= value; m11 /= value;
        
        return this;
    }
    
    public Matrix2d negate() {
        return this.multiply(-1);
    }
    
    public <B extends Buffer> B getBuffer(Class<B> bufferClass) {
        double[] array = {m00, m10,
                          m01, m11};
        return bufferClass.cast(VectorUtils.glGenDataBuffer(bufferClass, array));
    }
    
    public static Matrix2d createMatrix(Vector2d col1, Vector2d col2) {
        Matrix2d matrix = new Matrix2d();
        matrix.m00 = col1.x; matrix.m01 = col2.x;
        matrix.m10 = col1.y; matrix.m11 = col2.y;
        
        return matrix;
    }
    
    public static Matrix2d copyMatrix(Matrix2d src, Matrix2d des) {
        des.m00 = src.m00; des.m01 = src.m01;
        des.m10 = src.m10; des.m11 = src.m11;
        
        return des;
    }
}
