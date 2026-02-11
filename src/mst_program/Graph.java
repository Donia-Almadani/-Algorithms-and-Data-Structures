/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mst_program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * Represents the entire water network as a weighted, undirected graph.
 *
 * This class is the central data structure. It holds a list of all
 * Vertices (Junctions) and all Edges (Pipes). It is responsible for
 * reading the graph data from a file and constructing the objects.
 *
 * @author pc (Your Name)
 */
public class Graph {

    // --- Attributes ---
    // Defined as private, as specified by the UML diagram .
    
    /**
     * A list of all vertices (Junctions) in the graph.
     */
    private List<Vertex> vertices;
    
    /**
     * A list of all edges (Pipes) in the graph.
     * This list is essential for Kruskal's algorithm.
     */
    private List<Edge> edges;

    /**
     * Constructor for the Graph class.
     * Initializes the vertex and edge lists as empty ArrayLists.
     */
    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    /**
     * Reads the graph structure from a specified text file.
     * This method is public as required by the UML[cite: 67].
     *
     * @param path The file path (e.g., "graph.txt") to read from.
     */
    public void readFromFile(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            // Read and skip the first line (e.g., "6 7")
            // which contains the vertex/edge count.
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Loop through the remaining lines in the file
            while (scanner.hasNext()) {
                String label1 = scanner.next(); // e.g., "J1"
                String label2 = scanner.next(); // e.g., "J2"

                // Check if the next token is an integer (the weight)
                if (!scanner.hasNextInt()) {
                    System.out.println("File format error: Expected a weight (integer).");
                    Main.writer.println("File format error: Expected a weight (integer).");
                    break;
                }
                int weight = scanner.nextInt(); // e.g., 9

                // Call the UML-required addEdge method to add this edge
                this.addEdge(label1, label2, weight);
            }

            scanner.close(); // Close the scanner

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
            Main.writer.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Adds a new edge (Pipe) to the graph.
     * This method is public as required by the UML[cite: 68].
     * It creates the vertices if they don't already exist.
     *
     * @param uLabel The label of the first vertex (e.g., "J1").
     * @param vLabel The label of the second vertex (e.g., "J2").
     * @param w      The weight (cost) of the edge.
     */
    public void addEdge(String uLabel, String vLabel, int w) {
        // Get or create the vertex objects
        Vertex v1 = getOrCreateVertex(uLabel);
        Vertex v2 = getOrCreateVertex(vLabel);

        // Create the new Pipe (the concrete Edge object)
        Edge edge = new Pipe(v1, v2, w);

        // 1. Add the edge to the graph's main edge list (for Kruskal's)
        this.edges.add(edge);

        // 2. Add the edge to each vertex's adjacency list (for Prim's)
        //    (Uses the public 'addAdjEdge' method from the Vertex class)
        v1.addAdjEdge(edge);
        v2.addAdjEdge(edge);
    }


    /**
     * A private helper method to find a vertex by its label.
     * If the vertex doesn't exist, it creates a new one (as a Junction)
     * and adds it to the graph's vertex list.
     *
     * @param label The label of the vertex to find or create.
     * @return The existing or newly created Vertex.
     */
    public Vertex getOrCreateVertex(String label) {
        // Search for the vertex
        for (Vertex v : vertices) {
            if (v.getLabel().equals(label)) {
                return v;
            }
        }

        // Not found, so create a new one (as a concrete Junction)
        Vertex newVertex = new Junction(label);
        vertices.add(newVertex); // Add it to the main list
        return newVertex;
    }


    // --- Public Getters & Utility Methods ---
    // These are necessary for teammates to access the private data.

    /**
     * Utility method to print the graph's contents (for debugging).
     */
    
    public void printGraph() {
        System.out.println("Vertices: " + vertices.size());
        Main.writer.println("Vertices: " + vertices.size());
        for (Vertex v : vertices) {
            System.out.println(" - " + v.getLabel());
            Main.writer.println(" - " + v.getLabel());
        }

        System.out.println("Edges:");
        Main.writer.println("Edges:");
        
        for (Edge e : edges) {
            System.out.println(e.getU().getLabel() + " - " + e.getV().getLabel() + " : " + e.getWeight());
            Main.writer.println(e.getU().getLabel() + " - " + e.getV().getLabel() + " : " + e.getWeight());
        }
    }

    /**
     * Public getter for the list of vertices.
     *
     * @return The list of all vertices in the graph.
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Public getter for the list of edges.
     * (Crucial for Kruskal's algorithm).
     *
     * @return The list of all edges in the graph.
     */
    public List<Edge> getEdges() {
        return edges;
    }
}