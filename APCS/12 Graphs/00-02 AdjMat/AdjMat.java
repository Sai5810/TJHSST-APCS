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

public class AdjMat implements AdjacencyMatrix, Warshall, Floyd {
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
        for (int[] i : grid) {
            for (int j : i) {
                if (j > 0 && j < 9999) {
                    ++c;
                }
            }
        }
        return c;
    }

    @Override
    public List<Integer> getNeighbors(int source) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < grid[source].length; ++i) {
            if (grid[source][i] == 1) {
                list.add(i);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] i : grid) {
            for (int j : i) {
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean isEdge(String from, String to) {
        return grid[nameList.indexOf(from)][nameList.indexOf(to)] == 1;
    }

    @Override
    public Map<String, Integer> getVertices() {
        Map<String, Integer> m = new HashMap<>();
        for(String i: nameList) {
            m.put(i, 0);
        }
        return m;
    }

    @Override
    public void readNames(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        sc.nextLine();
        nameList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            nameList.add(s);
        }
        sc.close();
    }

    @Override
    public void readGrid(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        sc.nextLine();
        int i = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            int j = 0;
            for (String s1 : s.split(" ")) {
                grid[i][j] = Integer.parseInt(s1);
                ++j;
            }
            ++i;
        }
        sc.close();
    }

    @Override
    public void displayVertices() {
        int j = 0;
        for(String i: nameList) {
            System.out.println(j + "-" + i);
            ++j;
        }
    }

    @Override
    public void allPairsReachability() {
        int V = grid.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if(grid[i][j] == 1) {
                    for (int k = 0; k < V; k++) {
                        if(grid[j][k] == 1) {
                            grid[i][k] = 1;
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getCost(int from, int to) {
        return grid[from][to];
    }

    @Override
    public int getCost(String from, String to) {
        return grid[nameList.indexOf(from)][nameList.indexOf(to)];
    }

    @Override
    public void allPairsWeighted() {
        int V = grid.length;
        for(int i = 0; i < V; ++i) {
            for(int j = 0; j < V; ++j) {
                for(int k = 0; k < V; ++k) {
                    grid[j][k] = Math.min(grid[j][k], Math.min(grid[j][i] + grid[i][k], 9999));
                }
            }
        }
    }

    public List<String> getReachables(String city) {
        ArrayList<String> sb = new ArrayList<String>();
        //StringBuilder sb = new StringBuilder("[");
        int ind = nameList.indexOf(city);
        for(int i = 0; i < grid.length; ++i) {
            if(grid[ind][i] == 1) {
                sb.add(nameList.get(i));
                //sb.append(nameList.get(i)).append(", ");
            }
        }
        //sb.setLength(sb.length() - 2);
        //sb.append("]");
        return sb;
    }
}
