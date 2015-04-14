package org.ajgl.math;

public class Matrix4d extends Matrix3d {

    public double /*m00, m01, m02,*/ m03;
    public double /*m10, m11, m12,*/ m13;
    public double /*m20, m21, m22,*/ m23;
    public double   m30, m31, m32,   m33;
    
    public Matrix4d() {
        this.loadIdentity();
    }
    
    public Matrix4d(Matrix4d matrix) {
        Matrix4d.copyMatrix(matrix, this);
    }
    
    @Override
    public Matrix4d loadIdentity() {
        super.loadIdentity();      m03 = 0;
                                   m13 = 0;
                                   m23 = 0;
        m30 = 0; m31 = 0; m32 = 0; m33 = 1;
        
        return this;
    }
    
    public Matrix4d add(Matrix4d matrix) {
        super.add(matrix);                                       m03 += matrix.m03;
                                                                 m13 += matrix.m13;
                                                                 m23 += matrix.m23;
        m30 += matrix.m30; m31 += matrix.m31; m32 += matrix.m32; m33 += matrix.m33;
        
        return this;
    }
    
    public Matrix4d subtract(Matrix4d matrix) {
        super.add(matrix);                                       m03 -= matrix.m03;
                                                                 m13 -= matrix.m13;
                                                                 m23 -= matrix.m23;
        m30 -= matrix.m30; m31 -= matrix.m31; m32 -= matrix.m32; m33 -= matrix.m33;
        
        return this;
    }
    
    public Matrix3d multiply(Matrix3d matrix) {//TODO
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
    
    @Override
    public Matrix4d multiply(double value) {
        super.multiply(value);                     m03 *= value;
                                                   m13 *= value;
                                                   m23 *= value;
         m30 *= value; m31 *= value; m32 *= value; m33 *= value;
         
        return this;
    }
    
    @Override
    public Matrix4d negate() {
        return this.multiply(-1);
    }
    
    public static Matrix3d createMatrix(Vector4d col1, Vector4d col2, Vector4d col3, Vector4d col4) {
        Matrix4d matrix = new Matrix4d();
        matrix.m00 = col1.x; matrix.m01 = col2.x; matrix.m02 = col3.x; matrix.m03 = col4.x;
        matrix.m10 = col1.y; matrix.m11 = col2.y; matrix.m12 = col3.y; matrix.m13 = col4.y;
        matrix.m20 = col1.z; matrix.m21 = col2.z; matrix.m22 = col3.z; matrix.m23 = col4.z;
        matrix.m30 = col1.w; matrix.m31 = col2.w; matrix.m32 = col3.w; matrix.m33 = col4.w;
        
        return matrix;
    }
    
    public static Matrix4d copyMatrix(Matrix4d src, Matrix4d des) {
        des.m00 = src.m00; des.m01 = src.m01; des.m02 = src.m02; des.m03 = src.m03;
        des.m10 = src.m10; des.m11 = src.m11; des.m12 = src.m12; des.m13 = src.m13;
        des.m20 = src.m20; des.m21 = src.m21; des.m22 = src.m22; des.m23 = src.m23;
        des.m30 = src.m30; des.m31 = src.m31; des.m32 = src.m32; des.m33 = src.m33;
        
        return des;
    }
}
