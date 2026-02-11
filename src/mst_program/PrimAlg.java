/*
 * Boilerplate license headers
 */
package mst_program;

import java.util.*;

/**
 * Implementation of Min-Heap based Prim's Algorithm
 */
public class PrimAlg extends MSTAlgorithm {

    @Override
    public List<Edge> findMST(Graph graph) {
        result.clear(); // Clear previous result
        
        List<Vertex> vertices = graph.getVertices();
        if (vertices.isEmpty()) return result;

        // Set to track vertices already in MST
        Set<Vertex> visited = new HashSet<>(); 
        // Min-Heap to store candidate edges, ordered by weight
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        // Start Prim's from the first vertex
        Vertex start = vertices.get(0);
        visited.add(start);

        // Add all edges connected to the start vertex to the heap
        for (Edge e : graph.getEdges()) {
            if (e.getU().equals(start) || e.getV().equals(start)) {
                minHeap.offer(e);
            }
        }

        // Loop until MST is complete (|V|-1 edges) or heap is empty
        while (!minHeap.isEmpty() && result.size() < vertices.size() - 1) {
            Edge edge = minHeap.poll(); // Get the lightest edge
            Vertex u = edge.getU();
            Vertex v = edge.getV();

            Vertex next = null;
            // Identify the unvisited vertex
            if (visited.contains(u) && !visited.contains(v)) next = v;
            else if (visited.contains(v) && !visited.contains(u)) next = u;
            else continue; // Skip edge if both vertices are already visited (creates a cycle)

            visited.add(next); // Add the new vertex to MST
            result.add(edge); // Add the edge to MST result

            // Update the heap with new edges from the 'next' vertex
            for (Edge e : graph.getEdges()) {
                // Check if edge connects 'next' to any unvisited vertex
                if ((e.getU().equals(next) && !visited.contains(e.getV())) ||
                    (e.getV().equals(next) && !visited.contains(e.getU()))) {
                    minHeap.offer(e);
                }
            }
        }
        return result; // Return the MST edges
    }
}