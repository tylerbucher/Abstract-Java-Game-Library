package org.ajgl.collision.axisaligned;

public class Quadrilateral {

    public static boolean vQuadIntersect2D(double[] vertices, double... values) {
        return vertices[4] < values[0] || vertices[0] > values[4] 
                || vertices[5] > values[1] || vertices[1] < values[5];
    }
    
    public static boolean vQuadContains2D(double[] vertices, double... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1]
                && values[6] <= vertices[6] && values[7] >= vertices[7];
    }
    
    public static boolean vQuadIntersect3D(double[] vertices, double... values) {
        return false;
    }
    
    public static boolean vQuadContains3D(double[] vertices, double... values) {
        return false;
    }
}
