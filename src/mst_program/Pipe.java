/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mst_program;

/**
 *
 * @author pc
 */
public class Pipe extends Edge {
    
    /**
     * Constructor for the Pipe class.
     * It simply passes all parameters up to the constructor
     * of the parent 'Edge' class using 'super()'.
     *
     * @param src The source vertex (junction).
     * @param dest The destination vertex (junction).
     * @param weight The cost (weight) of this pipe.
     */
    public Pipe(Vertex src, Vertex dest, int weight) {
        // 'super' calls the constructor of the parent (Edge) class
        super(src, dest, weight);
    }
}
