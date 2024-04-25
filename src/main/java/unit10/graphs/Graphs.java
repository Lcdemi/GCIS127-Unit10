package unit10.graphs;

public class Graphs {

    public static Graph<String> makeGraph() {
        Graph<String> graph = new AdjacencyGraph<>();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.add("D");
        graph.add("E");
        graph.add("F");
        graph.add("G");
        graph.add("H");
        graph.add("I");
        graph.add("J");
        graph.add("K");

        graph.connectUndirected("A", "B");
        graph.connectUndirected("C", "D");
        graph.connectUndirected("C", "E");
        graph.connectUndirected("B", "E");
        graph.connectUndirected("D", "F");
        graph.connectUndirected("F", "G");
        graph.connectUndirected("I", "K");
        graph.connectUndirected("J", "K");

        graph.connectDirected("A", "C");
        graph.connectDirected("C", "H");
        graph.connectDirected("D", "B");
        graph.connectDirected("E", "F");
        graph.connectDirected("J", "I");

        return graph;
    }
}
