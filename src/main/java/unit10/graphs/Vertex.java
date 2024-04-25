package unit10.graphs;

import java.util.HashSet;
import java.util.Set;

public class Vertex<E> {
    private E value;
    private final Set<Vertex<E>> neighbors;

    public Vertex(E value) {
        this.value = value;
        this.neighbors = new HashSet<>();
    }

    public E getValue() {
        return this.value;
    }

    public void connect(Vertex<E> neighbor) {
        this.neighbors.add(neighbor);
    }

    public boolean connected(Vertex<E> neighbor) {
        return this.neighbors.contains(neighbor);
    }

    public Set<Vertex<E>> getNeighbors() {
        return this.neighbors;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
