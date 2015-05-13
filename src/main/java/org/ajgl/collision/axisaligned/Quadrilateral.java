package org.ajgl.collision.axisaligned;

public class Quadrilateral {

    /**
     * 0---3
     * |   |
     * |   |
     * 1---2
     * 
     * @param vertices
     * @param values
     * @return
     */
    public static boolean vQuadIntersect2D(double[] vertices, double... values) {
        return vertices[4] > values[0] || vertices[0] < values[4] 
                || vertices[3] < values[1] || vertices[1] > values[3];
    }
    
    /**
     * 0---3
     * |   |
     * |   |
     * 1---2
     * 
     * @param vertices
     * @param values
     * @return
     */
    public static boolean vQuadContains2D(double[] vertices, double... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1]
                && values[4] <= vertices[4] && values[5] >= vertices[5];
    }
    
    /**
     * 
     *    4---7
     *   /|  /|
     *  0---3 |
     *  | 5-|-6
     *  |/  |/ 
     *  1---2
     * 
     * @param vertices
     * @param values
     * @return
     */
    public static boolean vQuadIntersect3D(double[] vertices, double... values) {
        return vertices[6] > values[0] || vertices[0] < values[6] ||
                vertices[4] < values[1] || vertices[1] > values[4] ||
                vertices[14] > values[2] || vertices[2] < values[14];
    }
    
    /**
     * 
     *    4---7
     *   /|  /|
     *  0---3 |
     *  | 5-|-6
     *  |/  |/ 
     *  1---2
     * 
     * @param vertices
     * @param values
     * @return
     */
    public static boolean vQuadContains3D(double[] vertices, double... values) {
        return values[0] >= vertices[0] && values[1] <= vertices[1] && values[2] >= vertices[2]
                && values[18] <= vertices[18] && values[19] >= vertices[19] && values[20] <= vertices[20];
    }
}
