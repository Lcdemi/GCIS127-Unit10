package unit10.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyGraph<E> implements Graph<E> {
    private final Map<E, Vertex<E>> vertices;

    public AdjacencyGraph() {
        this.vertices = new HashMap<>();
    }

    @Override
    public void add(E value) {
        Vertex<E> vertex = new Vertex<>(value);
        this.vertices.put(value, vertex);
    }

    @Override
    public boolean contains(E value) {
        return this.vertices.containsKey(value);
    }

    @Override
    public int size() {
        return this.vertices.size();
    }

    @Override
    public void connectDirected(E a, E b) {
        if(vertices.containsKey(a) && vertices.containsKey(b)) {
            Vertex<E> vertexA = this.vertices.get(a);
            Vertex<E> vertexB = this.vertices.get(b);
            
            vertexA.connect(vertexB);
        }
    }

    @Override
    public void connectUndirected(E a, E b) {
        if(vertices.containsKey(a) && vertices.containsKey(b)) {
            Vertex<E> vertexA = this.vertices.get(a);
            Vertex<E> vertexB = this.vertices.get(b);
            
            vertexA.connect(vertexB);
            vertexB.connect(vertexA);
        }
    }

    @Override
    public boolean connected(E a, E b) {
        Vertex<E> vertexA = this.vertices.get(a);
        Vertex<E> vertexB = this.vertices.get(b);

        return vertexA.connected(vertexB);
    }

    protected Map<E, Vertex<E>> getVertices() {
        return this.vertices;
    }
    
    @Override
    public List<E> bfSearch(E start, E end) {
        //retrieve the vertices
        Vertex<E> Start = vertices.get(start);
        Vertex<E> End = vertices.get(end);

        //add start to both structures
        LinkedList<Vertex<E>> queue = new LinkedList<>();
        queue.add(Start);
        Map<Vertex<E>, Vertex<E>> predecessors = new HashMap<>();
        predecessors.put(Start, null);

        //implement BFS loop
        while(queue.size() > 0) {
            Vertex<E> v = queue.remove(0);
            for(Vertex<E> n : v.getNeighbors()) {
                if(!predecessors.containsKey(n)) {
                    queue.add(n);
                    predecessors.put(n, v);
                }
            }
        }
        return makePath(predecessors, End);
    }

    private List<E> makePath(Map<Vertex<E>, Vertex<E>> predecessors, Vertex<E> end) {
        if(!predecessors.containsKey(end)) {
            return null;
        } else {
            LinkedList<E> path = new LinkedList<>();
            Vertex<E> current = end;
            while(current != null) {
                path.add(0, current.getValue());
                current = predecessors.get(current);
            }
            return path;
        }
    }

    @Override
    public List<E> dfSearch(E start, E end) {
        //retrieve the vertices
        Vertex<E> Start = vertices.get(start);
        Vertex<E> End = vertices.get(end);

        //add start to visited set
        Set<Vertex<E>> visited = new HashSet<>();
        visited.add(Start);

        return visitDFS(Start, End, visited);
    }

    private List<E> visitDFS(Vertex<E> V, Vertex<E> E, Set<Vertex<E>> visited) {
        if(V == E) {
            List<E> path = new LinkedList<>();
            path.add(E.getValue());
            return path;
        } else {
            for(Vertex<E> N : V.getNeighbors()) {
                if(!visited.contains(N)) {
                    visited.add(N);
                    List<E> path = visitDFS(N, E, visited);
                    if(path != null) {
                        path.add(0, V.getValue());
                        return path;
                    }
                }
            }
            return null;
        }
    }

    public int countConnectedComponents() {
        int connectedComponents = 0;
        Set<E> searchedVertices = new HashSet<>();

        for(Vertex<E> vertex1 : this.vertices.values()) {
            if(!searchedVertices.contains(vertex1.getValue())) {
                for(E element : bfSearchForPredecessors(vertex1.getValue())) {
                    if (!searchedVertices.contains(element)) {
                        searchedVertices.add(element);
                    } else {
                        connectedComponents--;
                        break;
                    }
                }
                // for (E a : searchedVertices) {
                //     System.out.print(a);
                // }
                // System.out.println();
                connectedComponents++;
            }
        }
        return connectedComponents;
    }

    private Set<E> bfSearchForPredecessors(E start) {
        //retrieve the vertices
        Vertex<E> Start = vertices.get(start);

        //add start to both structures
        LinkedList<Vertex<E>> queue = new LinkedList<>();
        queue.add(Start);
        Set<E> predecessors = new HashSet<>();
        predecessors.add(Start.getValue());

        //implement BFS loop
        while(queue.size() > 0) {
            Vertex<E> v = queue.remove(0);
            for(Vertex<E> n : v.getNeighbors()) {
                if(!predecessors.contains(n.getValue())) {
                    queue.add(n);
                    predecessors.add(n.getValue());
                }
            }
        }
        return predecessors;
    }

    @Override
    public String toString() {
        String newString = "";
        for(E value : vertices.keySet()) {
            newString += value.toString() + ": " + vertices.get(value).getNeighbors() + "\n";
        }
        return newString;
    }
}
