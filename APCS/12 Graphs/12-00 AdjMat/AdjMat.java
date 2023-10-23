// Name:   
// Date:

import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix {
    void addEdge(int source, int target);

    void removeEdge(int source, int target);

    boolean isEdge(int from, int to);

    String toString();   //returns the grid as a String

    int edgeCount();

    List<Integer> getNeighbors(int source);
    //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
    boolean isEdge(String from, String to);

    Map<String, Integer> getVertices();

    void readNames(String fileName) throws FileNotFoundException;

    void readGrid(String fileName) throws FileNotFoundException;

    void displayVertices();

    void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd {
    int getCost(int from, int to);

    int getCost(String from, String to);

    void allPairsWeighted();
}

public class AdjMat implements AdjacencyMatrix//,Warshall//,Floyd 
{
    private int[][] grid = null;   //adjacency matrix representation
    private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
    /*for Warshall's Extension*/ ArrayList<String> nameList = null;  //reverses the map, index-->name

    public AdjMat(int size) {
        grid = new int[size][size];
    }

    @Override
    public void addEdge(int source, int target) {
        grid[source][target] = 1;
    }

    @Override
    public void removeEdge(int source, int target) {
        grid[source][target] = 0;
    }

    @Override
    public boolean isEdge(int from, int to) {
        return grid[from][to] == 1;
    }

    @Override
    public int edgeCount() {
        int c = 0;
        for(int[] i: grid) {
            for(int j: i) {
                if(j == 1) {
                    ++c;
                }
            }
        }
        return c;
    }

    @Override
    public List<Integer> getNeighbors(int source) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < grid[source].length; ++i) {
            if(grid[source][i] == 1) {
                list.add(i);
            }
        }
        return list;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int[] i: grid) {
            for(int j: i) {
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
