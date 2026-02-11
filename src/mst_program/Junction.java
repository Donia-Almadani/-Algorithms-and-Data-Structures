/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mst_program;

/**
 *Represents a "Junction", which is the concrete implementation of a Vertex.
 * This class inherits all its properties (label, adj) and methods 
 * from the parent 'Vertex' class.
 * In your 'Graph' class, you will create instances of 'Junction' 
 * when you add new vertices.
 * 
 * @author pc
 */
public class Junction extends Vertex {
    
    /**
     * Constructor for the Junction class.
     * It simply passes the label up to the constructor
     * of the parent 'Vertex' class using 'super()'.
     *
     * @param label The string label for this junction (e.g., "J1").
     */
    public Junction(String label) {
        // 'super' calls the constructor of the parent (Vertex) class
        super(label);
    }
}

