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
import java.util.*;

public abstract class MSTAlgorithm {
    protected List<Edge> result = new ArrayList<>();

    public abstract List<Edge> findMST(Graph g);

    public int totalWeight() {
        int total = 0;
        for (Edge e : result) {
            total += e.getWeight();
        }
        return total;
    }

    public void printResult(String algorithmName) {
        System.out.println("MST (" + algorithmName + "):");
        Main.writer.println("MST (" + algorithmName + "):");
        for (Edge e : result) {
            System.out.println(e);
            Main.writer.println(e);
        }
        System.out.println("Total = " + totalWeight());
        Main.writer.println("Total = " + totalWeight());
    }
}

