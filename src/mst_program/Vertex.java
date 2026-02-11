/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mst_program;

/**
 * Represents a Vertex (a "junction" or "node") in the graph.
 * A Vertex has a unique label (e.g., "J1") and maintains a list
 * of all edges connected to it (its adjacency list).
 *
 * @author pc
 */
import java.util.LinkedList;
import java.util.List;

public class Vertex {

    // --- Attributes ---
    // Both are private, as specified by the UML diagram.
    
    /**
     * The unique identifier for this vertex (e.g., "J1", "J2").
     */
    private String label;
    
    /**
     * The adjacency list. Stores all edges connected to this vertex.
     * This is crucial for graph traversal algorithms like Prim's.
     */
    private List<Edge> adj;

    /**
     * Constructor for the Vertex class.
     *
     * @param label The string label for this vertex.
     */
    public Vertex(String label) {
        this.label = label;
        // Initializes the adjacency list as an empty LinkedList.
        // This line is critical to prevent NullPointerException.
        this.adj = new LinkedList<>();
    }

    // --- Public Methods (Getters/Setters) ---
    
    /**
     * Public getter for the private 'label' attribute.
     *
     * @return The string label of the vertex.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Public getter for the private 'adj' (adjacency list) attribute.
     * This allows other algorithms (like Prim's) to read the list.
     *
     * @return The list of edges connected to this vertex.
     */
    public List<Edge> getAdj() {
        return adj;
    }
    
    /**
     * Public method to add an edge to this vertex's adjacency list.
     * This follows encapsulation (the Graph class doesn't add to the list
     * directly, it *asks* the Vertex to add it).
     *
     * @param e The edge to be added.
     */
    public void addAdjEdge(Edge e) {
        this.adj.add(e);
    }

    // --- Utility Methods ---
    
    /**
     * Overrides the default equals() method.
     * Two vertices are considered equal if their labels are the same.
     *
     * @param obj The object to compare against.
     * @return true if the objects are Vertices with the same label, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertex) {
            Vertex other = (Vertex) obj;
            return label.equals(other.label);
        }
        return false;
    }

    /**
     * Overrides the default hashCode() method.
     * This is important for data structures like HashMaps or HashSets.
     *
     * @return The hash code of the vertex's label.
     */
    @Override
    public int hashCode() {
        return label.hashCode();
    }

    /**
     * Overrides the default toString() method.
     *
     * @return The vertex's label.
     */
    @Override
    public String toString() {
        return label;
    }
}