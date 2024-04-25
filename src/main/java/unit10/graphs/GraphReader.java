package unit10.graphs;

import static unit10.FileLineReader.open;

public class GraphReader {
    public static Graph<String> readGraph(String filename) {
        Graph<String> graph = new AdjacencyGraph<>();
        int counter = 0;

        for(String line : open(filename)) {
            if(counter >= 2) {
                String[] values = line.split(" ");

                for(int i = 0; i < values.length; i++) { //creates the vertices
                    graph.add(values[i]);
                }
                for(int j = 1; j < values.length; j++) { //creates the undirected connections
                    graph.connectUndirected(values[0], values[j]);
                }
            }
            counter++;
        }

        return graph;
    }

    public static void main(String[] args) {
        Graph<String> newgraph = readGraph("data\\bfs\\a_6_1.txt");
        System.out.println(newgraph);
    }
}
