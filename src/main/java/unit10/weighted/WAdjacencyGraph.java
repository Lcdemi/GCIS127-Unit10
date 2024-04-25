package unit10.weighted;

import java.util.HashMap;
import java.util.Map;

public class WAdjacencyGraph<E> implements WGraph<E> {
    private final Map<E, WVertex<E>> vertices;
    
    public WAdjacencyGraph() {
        vertices = new HashMap<>();
    }

    @Override
    public void add(E value) {
        WVertex<E> vertex = new WVertex<>(value);
        vertices.put(value, vertex);
    }

    @Override
    public boolean contains(E value) {
        return vertices.containsKey(value);
    }

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    public void connect(E a, E b, double weight) {
        WVertex<E> vertexA = vertices.get(a);
        WVertex<E> vertexB = vertices.get(b);
        vertexA.connect(vertexB, weight);
        vertexB.connect(vertexA, weight);
    }

    @Override
    public boolean connected(E a, E b) {
        WVertex<E> vertexA = vertices.get(a);
        WVertex<E> vertexB = vertices.get(b);
        return vertexA.edge(vertexB) != null;
    }

    @Override
    public double weight(E a, E b) {
        WVertex<E> vertexA = vertices.get(a);
        WVertex<E> vertexB = vertices.get(b);
        return vertexA.edge(vertexB).getWeight();
    }

    @Override
    public WPath<E> dijkstrasPath(E start, E end) {
        //setup - part 1
        WVertex<E> Start = vertices.get(start);
        WVertex<E> End  = vertices.get(end);
        Map<WVertex<E>, PathTuple<E>> predecessors = new HashMap<>();
        TupleQueue<E> queue = new TupleQueue<>();

        for(E value : vertices.keySet()) {
            WVertex<E> vertex = vertices.get(value);
            PathTuple<E> tuple = new PathTuple<>(vertex);
            predecessors.put(vertex, tuple);
            queue.enqueue(tuple);
        }

        PathTuple<E> startTuple = predecessors.get(Start);
        startTuple.update(null, 0);

        //the main loop - part 2
        while(queue.size() > 0) {
            PathTuple<E> tuple = queue.dequeue();
            if(tuple.getDistance() == Double.POSITIVE_INFINITY) {
                break;
            }
            WVertex<E> vertex = tuple.getVertex();
            double distanceVertex = tuple.getDistance();
            for(Edge<E> edge : vertex.edges()) {
                WVertex<E> neighbor = edge.getTo();
                double distanceVertexNeighbor = distanceVertex + edge.getWeight();
                PathTuple<E> nTuple = predecessors.get(neighbor);
                nTuple.update(vertex, distanceVertexNeighbor);
            }
        }

        //building the path - part 3
        PathTuple<E> endTuple = predecessors.get(End);
        double distance = endTuple.getDistance();
        if(distance == Double.POSITIVE_INFINITY) {
            return null;
        } else {
            WPath<E> path = new WPath<>(End.getValue());
            WVertex<E> vertex = endTuple.getPredecessor();
            while(vertex != null) {
                path.prepend(vertex.getValue(), distance);
                distance = 0; //this is a hack
                PathTuple<E> vertexTuple = predecessors.get(vertex);
                vertex = vertexTuple.getPredecessor();
            }
            return path;
        }
    }
}
