package unit10.graphs;

import java.util.List;

public interface Graph<E> {
    public void add(E value);

    public boolean contains(E value);

    public int size();

    public void connectDirected(E a, E b);

    public void connectUndirected(E a, E b);

    public boolean connected(E a, E b);

    public default List<E> bfSearch(E start, E end) {
        throw new UnsupportedOperationException("BFS not implemented");
    }

    public default List<E> dfSearch(E start, E end) {
        throw new UnsupportedOperationException("DFS not implemented");
    }

    public default int countConnectedComponents() {
        throw new UnsupportedOperationException();
    }
}
