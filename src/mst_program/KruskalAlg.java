/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mst_program;

import java.util.*;


/**
 *
 * @author pc
 */
public class KruskalAlg extends MSTAlgorithm{
   Map<Vertex,Vertex> parent = new HashMap<>();          
   Map<Vertex,Integer> rank = new HashMap<>();
     
   /**
    * Find the minimum spanning tree for a graph
    * @param g input graph 
    * @return MST edges
    */
    @Override 
    public List<Edge> findMST(Graph g) {
        List <Edge>edges = new ArrayList<>(g.getEdges());     //list of all edge objects in the graph
        int totalV = g.getVertices().size();
        int k = 0;                                            //counter to travers through the sorted edges 
        int ecounter = 0;                                     //number edges added to MST
        
        edges.sort(Comparator.comparingInt(Edge::getWeight));  //Sort edges 
        
        makeset(g);
        
        while ((ecounter < (totalV - 1)) && (k < edges.size())){    //totalEdges= |V|-1
            Edge edge = edges.get(k);                         //get the object edge
            k++;
            Vertex pu = find(parent,edge.getU());             //find the parent for vertex u
            Vertex pv = find(parent,edge.getV());              //find the parent for vertex v
                    
            if(!(pu.equals(pv))){                              //if adding an edge does not create cycle 
                    this.result.add(edge);                     //add it to result list 
                    union(parent, rank, pu, pv);               // do the union for the set u and set v
                    ecounter++;                                
            } 
        }
        return this.result;
    }
    /**
     * Initialize the parent list   
     * @param g graph
     */
    private void makeset(Graph g){
         for(Vertex v : g.getVertices()){     //take all the vertices in the graph                  
             parent.put(v, v);                //make the vertex a parent to its self                           
             rank.put(v, 0);                  // since it is a single vertex assign rank zero
        }
    }
    /**
     * Find the root of the set that vertex x belong to
     * @param parent mapping for each vertex to its parent 
     * @param x the vertex to find
     * @return the root of the set
     */
    private Vertex find(Map<Vertex, Vertex> parent, Vertex x) {
        Vertex p = parent.get(x);              //get the parent of vertex x
        if (!p.equals(x)) {                    //if the parent for x is not x, the root has not reached
            Vertex root = find(parent, p);     //recursivly call find() to reach the root 
            parent.put(x, root);               //make the root the direct parent of x, for faster future search
            return root;
        }
        return p;
    }
    /**
     * This method performs union for sets (add an edge in the MST)
     * @param parent mapping of vertex to their parent 
     * @param rank   mapping of vertex to their rank 
     * @param xRoot  the root of the first set 
     * @param yRoot  the root of the second set 
     */
     private void union(Map<Vertex, Vertex> parent, Map<Vertex, Integer> rank, Vertex xRoot, Vertex yRoot) {
        int rx = rank.getOrDefault(xRoot, 0);     //return the rank of xRoot if not found return 0 
        int ry = rank.getOrDefault(yRoot, 0);     //return the rank of yRoot if not found return 0 

        if (rx < ry) {                            // if rx rank is smaller than rank yx 
            parent.put(xRoot, yRoot);             // make yRoot parent for xRoot 
        } else if (rx > ry) {                     // if rank ry is smaller than rx
            parent.put(yRoot, xRoot);             // make xRoot parent for yRoot 
        } else {
            parent.put(yRoot, xRoot);             // when the ranks are equal
            rank.put(xRoot, rx + 1);              //Increment rank of xroot by 1 
        }
    }
        
    }
        