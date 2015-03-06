/**
 * AJGL, an abstract java game library that provides useful functions for making a game.
 * Copyright (C) 2014 Tyler Bucher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.ajgl.collision;

import org.lwjgl.util.vector.Vector2f;

/**
 * This class was designed to use The Hyperplane Separation Theorem with 2D
 * objects.
 * @author Tyler Bucher
 */
public final class SAT {
    
    /**
     * Projects the first vector onto the second vector.
     * @param vectorA - The first vector
     * @param vectorB - The second vector
     * @return The new projected vector result
     */
    private static Vector2f vectorProj(Vector2f vectorA, Vector2f vectorB) {
        float vectorMult = Vector2f.dot(vectorA, vectorB) / (vectorB.x * vectorB.x + vectorB.y * vectorB.y);
        return new Vector2f(vectorMult * vectorB.x, vectorMult * vectorB.y);
    }
    
    /**
     * Calculates the distance between two points.
     * @param a - Point a
     * @param b - Point b
     * @return The distance between the two points
     */
    private static float distance(Vector2f a, Vector2f b) {
        double x = a.x - b.x; double y = a.y - b.y;
        return (float) Math.sqrt((x*x) + (y*y));
    }
    
    /**
     * Checks to see if the line segments intersect each other.
     * @param a - Point 1 of line ab
     * @param b - Point 2 of line ab
     * @param c - Point 1 of line cd
     * @param d - Point 2 of line cd
     * @return True if the fist line segment intersects the second line segment
     */
    private static boolean isLineColliding(Vector2f a, Vector2f b, Vector2f c, Vector2f d) {
        // Check if point c is on line ab
        float ab = distance(a,b);   // The distance from point a to point b
        float ac = distance(a,c);   // The distance from point a to point c
        float bc = distance(b,c);   // The distance from point b to point c
        float add = ac + bc;        // The added distance of ac + bc
        if(add <= ab+0.1f && add >= ab-0.1f) return true;
        // Check if point d is on line ab
        float ad = distance(a,d);   // The distance from point a to point d
        float bd = distance(b,d);   // The distance from point b to point d
        add = ad + bd;              // The added distance of ad + bd
        if(add <= ab+0.1f && add >= ab-0.1f) return true;
        // Checks if point a is on line cd
        float cd = distance(c,d);   // The distance from point c to point d
        add = ac + ad;              // The added distance of ac + ad
        if(add <= cd+0.1f && add >= cd-0.1f) return true;
        // Checks if point b is on line cd
        add = bc + bd;              // The added distance of bc + bd
        if(add <= cd+0.1f && add >= cd-0.1f) return true;
        return false;
    }
    
    /**
     * Checks to see if the fist line segment contains the second line segment.
     * @param lineA1 - Point 1 of line 1
     * @param lineA2 - Point 2 of line 1
     * @param lineB1 - Point 1 of line 2
     * @param lineB2 - Point 2 of line 2
     * @return True if the fist line segment contains the second
     */
    private static boolean isLineContaining(Vector2f lineA1, Vector2f lineA2, Vector2f lineB1, Vector2f lineB2) {
        // Return true if line A contains line b
        if (lineB1.x <= Math.max(lineA1.x, lineA2.x) && lineB1.x >= Math.min(lineA1.x, lineA2.x) && 
                lineB1.y <= Math.max(lineA1.y, lineA2.y) && lineB1.y >= Math.min(lineA1.y, lineA2.y) && 
                lineB2.x <= Math.max(lineA1.x, lineA2.x) && lineB2.x >= Math.min(lineA1.x, lineA2.x) && 
                lineB2.y <= Math.max(lineA1.y, lineA2.y) && lineB2.y >= Math.min(lineA1.y, lineA2.y)) 
            return true;
        return false;
    }
    
    /**
     * Gets the two most distant points in the array.
     * @param array - An array of points
     * @return The two most distant points
     */
    private static Vector2f[] maxDistance(Vector2f[] array) {
        // Initial variables
        float dist = 0;
        int index1 = 0, index2 = 1;
        // Check for the two most distant points
        for(int i=0;i<array.length-1;i++) {
            for(int j=i+1;j<array.length;j++) {
                float distance = SAT.distance(array[j], array[i]);
                if(distance > dist) {
                    dist = distance;
                    index1 = i; index2 = j;
                }
            }
        }
        return new Vector2f[] {array[index1], array[index2]};
    }
    
    /**
     * Checks to see if the fist set of vertices intersects the second set of
     * vertices.
     * @param vertices - The first set of vertices
     * @param vertices2 - The second set of vertices
     * @return True if the fist set intersects the second set of vertices
     */
    public static boolean isColliding(float[] vertices, float[] vertices2) {
        // Load all of the normals vectors of both shapes via 2 points 
        Vector2f[] normals = new Vector2f[(vertices.length + vertices2.length) / 2];
        int pos = 0;
        for (int i = 0; i < vertices.length - 3; i += 2)
            normals[pos++] = Vector.normal(new Vector2f(vertices[i], vertices[i + 1]), new Vector2f(vertices[i + 2], vertices[i + 3]));
        normals[pos++] = Vector.normal(new Vector2f(vertices[vertices.length - 2], vertices[vertices.length - 1]), new Vector2f(vertices[0], vertices[1]));
        for (int i = 0; i < vertices2.length - 3; i += 2)
            normals[pos++] = Vector.normal(new Vector2f(vertices2[i], vertices2[i + 1]), new Vector2f(vertices2[i + 2], vertices2[i + 3]));
        normals[pos++] = Vector.normal(new Vector2f(vertices2[vertices2.length - 2], vertices2[vertices2.length - 1]), new Vector2f(vertices2[0], vertices2[1]));
        // All point vectors for vertices and vertices2
        Vector2f[] vertVectors = new Vector2f[vertices.length / 2];
        Vector2f[] vert2Vectors = new Vector2f[vertices2.length / 2];
        for (int i = 0, j = 0; i < vertices.length - 1; i += 2, j++)
            vertVectors[j] = new Vector2f(vertices[i], vertices[i + 1]);
        for (int i = 0, j = 0; i < vertices2.length - 1; i += 2, j++)
            vert2Vectors[j] = new Vector2f(vertices2[i], vertices2[i + 1]);
        // Check all the vertex vectors against the normal vectors
        for (int i = 0; i < normals.length; i++) {
            // Magnitude list of the projected vectors
            Vector2f[] vertProj = new Vector2f[vertVectors.length];
            Vector2f[] vert2Proj = new Vector2f[vert2Vectors.length];
            // Project the vertices onto the normal vector
            for (int j = 0; j < vertVectors.length; j++)
                vertProj[j] = vectorProj(vertVectors[j], normals[i]);
            for (int j = 0; j < vert2Vectors.length; j++)
                vert2Proj[j] = vectorProj(vert2Vectors[j], normals[i]);
            // Get maximum and minimum points for vertices, and vertices2
            vertProj = maxDistance(vertProj);
            vert2Proj = maxDistance(vert2Proj);
            // Check to see if they are not colliding
            if (!isLineColliding(vertProj[0], vertProj[1], vert2Proj[0], vert2Proj[1])) return false;
        }
        return true;
    }
    
    /**
     * Checks to see if one set of vertices contain the second set of vertices.
     * @param vertices - The first set of vertices
     * @param vertices2 - The second set of vertices
     * @return True if the fist set completely contains the second set of
     * vertices
     */
    public static boolean contains(float[] vertices, float[] vertices2) {
        // Load all of the normals vectors of both shapes via 2 points 
        Vector2f[] normals = new Vector2f[(vertices.length + vertices2.length) / 2];
        int pos = 0;
        for (int i = 0; i < vertices.length - 3; i += 2)
            normals[pos++] = Vector.normal(new Vector2f(vertices[i], vertices[i + 1]), new Vector2f(vertices[i + 2], vertices[i + 3]));
        normals[pos++] = Vector.normal(new Vector2f(vertices[vertices.length - 2], vertices[vertices.length - 1]), new Vector2f(vertices[0], vertices[1]));
        for (int i = 0; i < vertices2.length - 3; i += 2)
            normals[pos++] = Vector.normal(new Vector2f(vertices2[i], vertices2[i + 1]), new Vector2f(vertices2[i + 2], vertices2[i + 3]));
        normals[pos++] = Vector.normal(new Vector2f(vertices2[vertices2.length - 2], vertices2[vertices2.length - 1]), new Vector2f(vertices2[0], vertices2[1]));
        // All point vectors for vertices and vertices2
        Vector2f[] vertVectors = new Vector2f[vertices.length / 2];
        Vector2f[] vert2Vectors = new Vector2f[vertices2.length / 2];
        for (int i = 0, j = 0; i < vertices.length - 1; i += 2, j++)
            vertVectors[j] = new Vector2f(vertices[i], vertices[i + 1]);
        for (int i = 0, j = 0; i < vertices2.length - 1; i += 2, j++)
            vert2Vectors[j] = new Vector2f(vertices2[i], vertices2[i + 1]);
        // Check all the vertex vectors against the normal vectors
        for (int i = 0; i < normals.length; i++) {
            // Magnitude list of the projected vectors
            Vector2f[] vertProj = new Vector2f[vertVectors.length];
            Vector2f[] vert2Proj = new Vector2f[vert2Vectors.length];
            // Project the vertices onto the normal vector
            for (int j = 0; j < vertVectors.length; j++)
                vertProj[j] = vectorProj(vertVectors[j], normals[i]);
            for (int j = 0; j < vert2Vectors.length; j++)
                vert2Proj[j] = vectorProj(vert2Vectors[j], normals[i]);
            // Get maximum and minimum points for vertices, and vertices2
            vertProj = maxDistance(vertProj);
            vert2Proj = maxDistance(vert2Proj);
            // Check to see if they are not colliding
            if (!isLineContaining(vertProj[0], vertProj[1], vert2Proj[0], vert2Proj[1])) return false;
        }
        return true;
    }
}
