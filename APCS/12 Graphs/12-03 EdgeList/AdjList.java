// Name:   
// Date:

import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList
 */
interface VertexInterface {
    String toString(); // Don't use commas in the list.  Example: "C [C D]"

    String getName();

    ArrayList<Vertex> getAdjacencies();

    void addAdjacent(Vertex v);
}

interface AdjListInterface {
    List<Vertex> getVertices();

    Vertex getVertex(int i);

    Vertex getVertex(String vertexName);

    Map<String, Integer> getVertexMap();

    void addVertex(String v);

    void addEdge(String source, String target);

    String toString();  //returns all vertices with their edges (omit commas)
}

class Vertex implements VertexInterface {
    private final String name;
    private ArrayList<Vertex> adjacencies = new ArrayList<>();

    public Vertex(String alpha) {
        name = alpha;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Vertex> getAdjacencies() {
        return adjacencies;
    }

    @Override
    public void addAdjacent(Vertex v) {
        adjacencies.add(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        sb.append(" [");
        for(Vertex s: adjacencies) {
            sb.append(s.getName()).append(" ");
        }
        if(!adjacencies.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }
}

public class AdjList implements AdjListInterface// , DFS_BFS , EdgeListWithCities
{
    private final ArrayList<Vertex> vertices = new ArrayList<>();
    private final Map<String, Integer> nameToIndex = new TreeMap<>();
    /*  three extra credit methods, recursive version  */
    List<Vertex> depthFirstRecur(String name) {
        return null;
    }

    List<Vertex> depthFirstRecur(Vertex v) {
        return null;
    }

    void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable) {

    }

    @Override
    public List<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public Vertex getVertex(int i) {
        return vertices.get(i);
    }

    @Override
    public Vertex getVertex(String vertexName) {
        return vertices.get(nameToIndex.get(vertexName));
    }

    @Override
    public Map<String, Integer> getVertexMap() {
        return nameToIndex;
    }

    @Override
    public void addVertex(String v) {
        if(!nameToIndex.containsKey(v)) {
            nameToIndex.put(v, vertices.size());
            vertices.add(new Vertex(v));
        }
    }

    @Override
    public void addEdge(String source, String target) {
        addVertex(source);
        addVertex(target);
        getVertex(source).addAdjacent(getVertex(target));
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Vertex v: vertices) {
            sb.append(v).append("\n");
        }
        return sb.toString();
    }
}


