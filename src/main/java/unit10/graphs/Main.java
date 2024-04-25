package unit10.graphs;

public class Main {
    public static void main(String[] args) {
        String[] files = {"data\\bfs\\a_13_5.txt", "data\\bfs\\a_22_3.txt", "data\\bfs\\a_26_4.txt", 
        "data\\bfs\\a_6_1.txt", "data\\bfs\\a_7_3.txt", "data\\bfs\\bipartite.txt", "data\\bfs\\k2.txt",
        "data\\bfs\\k2_2.txt", "data\\bfs\\k3.txt", "data\\bfs\\k3_3.txt", "data\\bfs\\k4.txt", 
        "data\\bfs\\k5.txt", "data\\bfs\\non_bipartite.txt", "data\\bfs\\v2.txt"};

        for(String file : files) {
            Graph<String> graph = GraphReader.readGraph(file);
            int connectedComponents = graph.countConnectedComponents();
            System.out.println(file.substring(9) + " connected components: " + connectedComponents);
        }
    }
}
