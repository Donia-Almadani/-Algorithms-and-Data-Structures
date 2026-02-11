# Water Distribution Network MST Project

## 📝 Introduction
This project models a **water distribution network** as an **undirected weighted graph**, where:
- Nodes represent water junctions
- Edges represent pipeline connections with construction costs  

The goal is to identify the **Minimum Spanning Tree (MST)** - the combination of all junctions with the **least total cost**, without cycles.  

We implemented **two classical greedy algorithms** in Java:
- **Kruskal’s Algorithm**
- **Min-Heap-based Prim’s Algorithm**

The graph is read from an input file, and both algorithms are tested and compared for **performance and result accuracy**.

---

## 📂 Project Structure
- `src/`         # Java source code
- `graph.txt`    # Input graph file
- `output.txt`   # MST output
- `README.md`    # Project description

 
---

## 💡 Algorithm Comparison

### 1. Theoretical
- **Kruskal’s Algorithm:** O(E log E) — better for **sparse graphs**
- **Prim’s Algorithm:** O(E log V) — better for **dense graphs**  

### 2. Experimental Results
- Kruskal consistently outperforms Prim in **execution time** in all tested cases.
- Prim is still accurate and produces the correct MST.

---

## ⚙️ How to Run
1. Open the project in **Eclipse** or your preferred Java IDE
2. Make sure `graph.txt` is in the project directory
3. Run either:
   - `KruskalMST.java`
   - `PrimMST.java`
4. Results will be saved to `output.txt` and displayed in the console

---

## 📄 Conclusion
The project successfully implements and compares **Kruskal and Min-Heap-based Prim algorithms** for computing MST. Kruskal was faster in all experimental trials, though the graph was randomly generated and may not reflect real-world data. Overall, this project provides an **experimental analysis of two classical greedy algorithms** for MST computation.
