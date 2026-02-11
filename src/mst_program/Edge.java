/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mst_program;

/**
 *Represents an Edge (like a pipe) in the graph.
 * An Edge connects two Vertices (junctions) and has a specific weight (cost).
 *
 * This class implements Comparable to allow edges to be easily sorted
 * by their weight, which is essential for Kruskal's algorithm.
 *
 * @author pc 
 */
public class Edge implements Comparable<Edge> {
    // --- Attributes ---
    // Defined as private to follow Encapsulation principles
    // and match the UML diagram [cite: 79-80].

    /**
     * The first vertex (junction) the edge connects to.
     */
    private Vertex u;
    
    /**
     * The second vertex (junction) the edge connects to.
     */
    private Vertex v;
    
    /**
     * The weight or cost of this edge (e.g., construction cost).
     */
    private int weight;

    /**
     * Constructor for the Edge class.
     * @param u The starting vertex.
     * @param v The ending vertex.
     * @param weight The cost/weight of the edge.
     */
    public Edge(Vertex u, Vertex v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    
    // --- Getter Methods ---
    // Public methods to allow other classes to read the private attributes.
    
    /**
     * @return The first vertex (u).
     */
    public Vertex getU() { return u; }
    
    /**
     * @return The second vertex (v).
     */
    public Vertex getV() { return v; }
    
    /**
     * @return The weight (cost) of the edge.
     */
    public int getWeight() { return weight; }

    /**
     * Compares this edge to another edge based on weight.
     * Required by the "implements Comparable" interface.
     * This is crucial for sorting edges in Kruskal's algorithm.
     *
     * @param other The other edge to compare against.
     * @return A negative integer, zero, or a positive integer if this edge's
     * weight is less than, equal to, or greater than the other edge's weight.
     */
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    
    /**
     * Provides a string representation of the edge.
     * Useful for debugging and printing results.
     *
     * @return A string in the format "V1_label — V2_label : weight".
     */
    @Override
    public String toString() {
        // Assumes Vertex class has a getLabel() method
        return u + " - " + v + " : " + weight;
    }
}

