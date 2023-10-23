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

/* Graphs 4: DFS and BFS
 */
interface DFS_BFS {
    List<Vertex> depthFirstSearch(String name);

    List<Vertex> depthFirstSearch(Vertex v);

    List<Vertex> breadthFirstSearch(String name);

    List<Vertex> breadthFirstSearch(Vertex v);

    /*  three extra credit methods */
    List<Vertex> depthFirstRecur(String name);

    List<Vertex> depthFirstRecur(Vertex v);

    void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities
 */
interface EdgeListWithCities {
    void graphFromEdgeListData(String fileName) throws FileNotFoundException;

    int edgeCount();

    int vertexCount(); //count the vertices in the object

    boolean isReachable(String source, String target);

    boolean isConnected();
}

class Vertex implements VertexInterface {
    private final String name;
    private final ArrayList<Vertex> adjacencies = new ArrayList<>();

    public Vertex(String alpha) {
        name = alpha;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Vertex> getAdjacencies() {
        return adjacencies;
    }

    public void addAdjacent(Vertex v) {
        adjacencies.add(v);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        sb.append(" [");
        for (Vertex s : adjacencies) {
            sb.append(s.getName()).append(" ");
        }
        if (!adjacencies.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }
}

public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities {
    private final ArrayList<Vertex> vertices = new ArrayList<>();
    private final Map<String, Integer> nameToIndex = new TreeMap<>();
    private ArrayList<Vertex> rec;

    public List<Vertex> depthFirstSearch(String name) {
        return depthFirstSearch(getVertex(name));
    }

    public List<Vertex> depthFirstSearch(Vertex v) {
        ArrayList<Vertex> ret = new ArrayList<>();
        Stack<Vertex> s = new Stack<>();
        s.push(v);
        while (!s.isEmpty()) {
            Vertex c = s.pop();
            if (!ret.contains(c)) {
                ret.add(c);
            }
            for (Vertex q : c.getAdjacencies()) {
                if (!ret.contains(q)) {
                    s.push(q);
                }
            }
        }
        return ret;
    }

    public List<Vertex> breadthFirstSearch(String name) {
        return breadthFirstSearch(getVertex(name));
    }

    public List<Vertex> breadthFirstSearch(Vertex v) {
        ArrayList<Vertex> ret = new ArrayList<>();
        Queue<Vertex> s = new LinkedList<>();
        s.add(v);
        while (!s.isEmpty()) {
            Vertex c = s.remove();
            if (!ret.contains(c)) {
                ret.add(c);
            }
            for (Vertex q : c.getAdjacencies()) {
                if (!ret.contains(q)) {
                    s.add(q);
                }
            }
        }
        return ret;
    }

    public List<Vertex> depthFirstRecur(String name) {
        return depthFirstRecur(getVertex(name));
    }

    public List<Vertex> depthFirstRecur(Vertex v) {
        rec = new ArrayList<>();
        depthFirstRecurHelper(v, v.getAdjacencies());
        System.out.println(this);
        return rec;
    }

    public void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable) {
        if (!rec.contains(v)) {
            rec.add(v);
        }
        for (int i = reachable.size() - 1; i >= 0; --i) {
            if (!rec.contains(reachable.get(i))) {
                depthFirstRecurHelper(reachable.get(i), reachable.get(i).getAdjacencies());
            }
        }
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int i) {
        return vertices.get(i);
    }

    public Vertex getVertex(String vertexName) {
        return vertices.get(nameToIndex.get(vertexName));
    }

    public Map<String, Integer> getVertexMap() {
        return nameToIndex;
    }

    public void addVertex(String v) {
        if (!nameToIndex.containsKey(v)) {
            nameToIndex.put(v, vertices.size());
            vertices.add(new Vertex(v));
        }
    }

    public void addEdge(String source, String target) {
        addVertex(source);
        addVertex(target);
        getVertex(source).addAdjacent(getVertex(target));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append(v).append("\n");
        }
        return sb.toString();
    }

    public void graphFromEdgeListData(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            String[] sp = data.split(" ");
            addEdge(sp[0], sp[1]);
        }
        sc.close();
    }

    public int edgeCount() {
        int e = 0;
        for (Vertex v : vertices) {
            e += v.getAdjacencies().size();
        }
        return e;
    }

    public int vertexCount() {
        return vertices.size();
    }

    public boolean isReachable(String source, String target) {
        List<Vertex> reach = depthFirstSearch(source);
        for (Vertex v : reach) {
            if (v.getName().equals(target)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        for(Vertex v: vertices){
            for(Vertex w: vertices){
                if(v != w) {
                    if(!isReachable(v.getName(), w.getName()) && isReachable(w.getName(), v.getName())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
