/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mst_program;

import java.io.*;
import java.util.*;

/**
 *
 * @author pc
 */
public class Main {
	
	public static String outputFile = "output.txt";
    public static PrintWriter writer;

public static void main(String[] args) throws FileNotFoundException {

	try {
    Graph graph = new Graph();
    graph.readFromFile("graph.txt");
    
    writer = new PrintWriter(outputFile);

    System.out.println("\n--- (Vertices) ---");
    writer.println("\n--- (Vertices) ---");
    
    for (Vertex v : graph.getVertices()) {
        System.out.println(v.getLabel());
        writer.println(v.getLabel());
    }

    System.out.println("\n---  (Edges) ---");
    writer.println("\n---  (Edges) ---");
    
    for (Edge e : graph.getEdges()) {
        System.out.println(e.getU().getLabel() + " - "
                + e.getV().getLabel()
                + " (weight: " + e.getWeight() + ")");
        writer.println(e.getU().getLabel() + " - "
                + e.getV().getLabel()
                + " (weight: " + e.getWeight() + ")");
    }
    
	System.out.println("\n" + "-".repeat(70));
	writer.println("\n" + "-".repeat(70));

    // Run Prim's Algorithm first
     PrimAlg prim = new PrimAlg();
     prim.findMST(graph);
     prim.printResult("Min-Heap Prim");

 	System.out.println("\n" + "-".repeat(70));
	writer.println("\n" + "-".repeat(70));

    // Then Kruskal’s Algorithm
    KruskalAlg kruskal = new KruskalAlg();
    kruskal.findMST(graph);
    kruskal.printResult("KruskalAlg");
    
    //calling method "comparison" of Prim & Kruskal Running Time
    comparison();
	
	} catch(FileNotFoundException e) {
		System.out.println("xxx");
	} finally {
		if(writer != null)	writer.close();
	}
}

//Generate  random, connected, undirected weighted graph
	public static Graph makeGraph(int vertices, int edges) {
		if(edges < vertices-1) {
			System.out.println("cann't create a connected graph (edges must be >= vertices-1");
			writer.println("cann't create a connected graph (edges must be >= vertices-1");
			return null;
		}
		
		Graph g = new Graph();
		Random random = new Random();	//initialize the random number generator
		
		//Create Vertices
		List<String> vertexLabels = new ArrayList<>(vertices);
		
		for(int i=1 ; i <= vertices ; i++) {
			vertexLabels.add("J" + i);
			//call getOrCreateVertex to ensure all vertices are created
			//& added to the graph
			g.getOrCreateVertex("J" + i);
		}
		
		//get the vertices
		List<Vertex> vertexList = g.getVertices();
		
		//ensure connectivity (build spanning tree)
		Set<Integer> connectedIndices = new HashSet<>();
		//start with the first vertex "index 0"
		connectedIndices.add(0);
		//list to store indices of vertices NOT YET CONNECTED
		List<Integer> unconnectedIndices = new ArrayList<>();
		
		//add indices of all remaining vertices to the unconnected list
		for(int i=1 ; i<vertices ; i++ ) {
			unconnectedIndices.add(i);
		}
		int edgeCount = 0;
		
		//build the spanning tree : loop runs edges-1 to guarantee connectivity 
		while(!unconnectedIndices.isEmpty()) {
			//convert the SET of connected indices to array for RANDOM selection
			Integer[] connectedArray = connectedIndices.toArray(new Integer[0]);
			//select a random source vertex u from the connected set
			int uIndex = connectedArray[random.nextInt(connectedArray.length)];
			
			//select random destination vertex v from the unconnected set & remove it
			int vIndex = unconnectedIndices.remove(random.nextInt(unconnectedIndices.size()));
			
			String uLabel = vertexList.get(uIndex).getLabel();	//get the label of the source vertex
			String vLabel = vertexList.get(vIndex).getLabel();	//get the label of the destination vertex
			
			//random weight (1-1000)
			int weight = random.nextInt(1000)+1;
			
			//add the edge
			g.addEdge(uLabel, vLabel, weight);
			edgeCount++;
			connectedIndices.add(vIndex);
		}
		
		//add the remaining random edges until count edges is reached
		while(edgeCount < edges) {
	
			int uIndex = random.nextInt(vertices);	//select random index for a vertex u from ALL vertices
			int vIndex = random.nextInt(vertices);	///select random index for a vertex v from ALL vertices
			
			//avoid self loop
			if (uIndex != vIndex) {
				String uLabel = vertexList.get(uIndex).getLabel();	//get the label of vertex u
				String vLabel = vertexList.get(vIndex).getLabel();	//get the label of vertex v
				
				int weight = random.nextInt(1000)+1;	//assign random weight to the new dense edge
				
				int initialSize = g.getEdges().size();
				g.addEdge(uLabel, vLabel, weight);
				
				//increment edgeCount only if the edge was actually added
				if(g.getEdges().size() > initialSize) {
					edgeCount++;
				}
			}
		}
		
		return g;
		
	}
	
	public static void comparison() {
		//the required test cases for(vertices, edges)
		int[][] testCases = { {1000, 10000} , {1000, 15000} , {1000, 25000} ,
							  {5000, 15000} , {5000, 25000} , 
							  {10000 , 15000} , {10000 , 25000}};
		
		System.out.println("\n" + "=".repeat(70));
		System.out.print("Comparison of Prim vs Kruskal Running Time");
		System.out.println("\n" + "=".repeat(70));
		writer.println("\n" + "=".repeat(70));
		writer.print("Comparison of Prim vs Kruskal Running Time");
		writer.println("\n" + "=".repeat(70));
		
		List<Double> PrimTimes = new ArrayList<>();
		List<Double> kruskalTimes = new ArrayList<>();

		
		for(int[] testCase : testCases) {
			int vertices = testCase[0];
			int edges = testCase[1];
			
			System.out.println("	Generating graph for case: vertices = " + vertices + " , edges = " + edges + "\n");
			System.out.printf("| %-11s | %-13s | %-21s | %-19s |\n","vertices", "edges", "prim time (ms)", "kruskal time (ms)");
			System.out.println("|" + "-".repeat(13) + "|" + "-".repeat(15) + "|" + "-".repeat(23) + "|" + "-".repeat(21) + "|");
			writer.println("	Generating graph for case: vertices = " + vertices + " , edges = " + edges + "\n");
			writer.printf("| %-11s | %-13s | %-21s | %-19s |\n","vertices", "edges", "prim time (ms)", "kruskal time (ms)");
			writer.println("|" + "-".repeat(13) + "|" + "-".repeat(15) + "|" + "-".repeat(23) + "|" + "-".repeat(21) + "|");
			
			Graph graph = makeGraph(vertices, edges);	//create the random connected graph
			
			long startTime, endTime;
			double timePrim, timeKruskal;
			
			//measure prim's running time
			startTime = System.currentTimeMillis();
			PrimAlg primAlgo = new PrimAlg();
			primAlgo.findMST(graph);
			endTime = System.currentTimeMillis();
			timePrim = endTime - startTime;
			PrimTimes.add(timePrim);
			
			//measure kruskal's running time
			startTime = System.currentTimeMillis();
			KruskalAlg kruskalAlgo = new KruskalAlg();
			kruskalAlgo.findMST(graph);
			endTime = System.currentTimeMillis();
			timeKruskal = endTime - startTime;
			kruskalTimes.add(timeKruskal);
			
			//display the results
			System.out.printf("|  %-18d%-20d%-27.4f%-14.4f  |\n", vertices, edges, timePrim, timeKruskal);
			System.out.println("|" + "-".repeat(13) + "|" + "-".repeat(15) + "|" + "-".repeat(23) + "|" + "-".repeat(21) + "|");
			System.out.println();
			writer.printf("|  %-18d%-20d%-27.4f%-14.4f  |\n", vertices, edges, timePrim, timeKruskal);
			writer.println("|" + "-".repeat(13) + "|" + "-".repeat(15) + "|" + "-".repeat(23) + "|" + "-".repeat(21) + "|");
			writer.println();
		}
		
		System.out.println("-----------------Comparison Results-----------------");
		System.out.printf("| %-14s | %-17s | %-29s |\n", "Prim Time (ms)", "Kruskal Time (ms)", "Comparison Results");
		writer.println("-----------------Comparison Results-----------------");
		writer.printf("| %-14s | %-17s | %-29s |\n", "Prim Time (ms)", "Kruskal Time (ms)", "Comparison Results");
		for(int i=0 ; i < testCases.length ; i++) {
			double primTime = PrimTimes.get(i);
			double kruskalTime = kruskalTimes.get(i);
			String result;
			
			if(primTime < kruskalTime)	result= String.format("Prim is faster by %.2f ms", kruskalTime-primTime );
			else if(primTime > kruskalTime)	result= String.format("kruskal is faster by %.2f ms", primTime-kruskalTime);
			else result = "Times are apporximatley equal";
			
			System.out.printf("| %-17.4f | %-19.4f | %-31s |\n", primTime, kruskalTime, result);
			writer.printf("| %-17.4f | %-19.4f | %-31s |\n", primTime, kruskalTime, result);
		}
		
	}
}