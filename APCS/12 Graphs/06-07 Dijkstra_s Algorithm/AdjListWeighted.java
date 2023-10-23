// Name:   
// Date:

import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */


interface wVertexInterface {
    String getName();

    double getMinDistance();

    void setMinDistance(double m);

    wVertex getPrevious();   //for Dijkstra 7

    void setPrevious(wVertex v);  //for Dijkstra 7

    ArrayList<Edge> getAdjacencies();

    void addEdge(wVertex v, double weight);

    int compareTo(wVertex other);
}


interface AdjListWeightedInterface {
    List<wVertex> getVertices();

    Map<String, Integer> getNameToIndex();

    wVertex getVertex(int i);

    wVertex getVertex(String vertexName);

    void addVertex(String v);

    void addEdge(String source, String target, double weight);

    void minimumWeightPath(String vertexName);   //Dijkstra's
}

interface AdjListWeightedInterfaceWithCities {
    List<String> getShortestPathTo(wVertex v);

    AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException;
}

class Edge {
    public final wVertex target;  //if it's public, you don't need accessor methods
    public final double weight;   //if it's public, you don't need accessor methods

    public Edge(wVertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
}

class wVertex implements Comparable<wVertex>, wVertexInterface {
    private final String name;
    private ArrayList<Edge> adjacencies = new ArrayList<>();
    private double minDistance = Double.POSITIVE_INFINITY;
    private wVertex prev = null;

    wVertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Edge> getAdjacencies() {
        return adjacencies;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double m) {
        minDistance = m;
    }

    public wVertex getPrevious() {
        return prev;
    }

    public void setPrevious(wVertex v) {
        prev = v;
    }

    public void addEdge(wVertex v, double weight) {
        adjacencies.add(new Edge(v, weight));
    }

    public int compareTo(wVertex o) {
        return 0;
    }
}

public class AdjListWeighted implements AdjListWeightedInterface, AdjListWeightedInterfaceWithCities {
    private List<wVertex> vertices = new ArrayList<>();
    private Map<String, Integer> nameToIndex = new HashMap<>();

    public List<wVertex> getVertices() {
        return vertices;
    }

    public Map<String, Integer> getNameToIndex() {
        return nameToIndex;
    }

    public wVertex getVertex(int i) {
        return vertices.get(i);
    }

    public void minimumWeightPath(String vertexName) {
        for (wVertex i : vertices) {
            i.setMinDistance(Double.POSITIVE_INFINITY);
        }
        wVertex source = getVertex(vertexName);
        PriorityQueue<wVertex> pq = new PriorityQueue<>();
        pq.add(source);
        source.setMinDistance(0);
        while (!pq.isEmpty()) {
            wVertex dq = pq.poll();
            for (Edge i : dq.getAdjacencies()) {
                if (i.target.getMinDistance() > dq.getMinDistance() + i.weight) {
                    i.target.setMinDistance(dq.getMinDistance() + i.weight);
                    i.target.setPrevious(dq);
                    pq.add(i.target);
                }
            }
        }

    }

    public void addEdge(String source, String target, double weight) {
        addVertex(source);
        addVertex(target);
        getVertex(source).addEdge(getVertex(target), weight);
    }

    public void addVertex(String v) {
        if (!nameToIndex.containsKey(v)) {
            nameToIndex.put(v, vertices.size());
            vertices.add(new wVertex(v));
        }
    }

    public wVertex getVertex(String vertexName) {
        return vertices.get(nameToIndex.get(vertexName));
    }

    public List<String> getShortestPathTo(wVertex v) {
        ArrayList<String> path = new ArrayList<>();
        path.add(v.getName());
        wVertex cur = v.getPrevious();
        while(cur != null) {
            path.add(cur.getName());
            cur = cur.getPrevious();
        }
        Collections.reverse(path);
        return path;
    }

    public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException {
        Scanner name = new Scanner(vertexNames);
        AdjListWeighted ret = new AdjListWeighted();
        int n = Integer.parseInt(name.nextLine());
        for(int i = 0; i < n; ++i){
            ret.addVertex(name.nextLine());
        }
        name.close();
        Scanner edge = new Scanner(edgeListData);
        while(edge.hasNextLine()) {
            String data = edge.nextLine();
            String[] sp = data.split(" ");
            ret.addEdge(sp[0], sp[1], Double.parseDouble(sp[2]));
        }
        edge.close();
        return ret;
    }
}