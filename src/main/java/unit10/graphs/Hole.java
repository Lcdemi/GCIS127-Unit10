package unit10.graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hole{
    private final int row, col;
    public Hole(int row, int col){
        this.row = row;
        this.col = col;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
    @Override
    public int hashCode() { //default generated hashCode from VSCode
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + col;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Hole) {
            Hole other = (Hole)obj;
            return this.col == other.col && this.row == other.row;
        } else {
            return false;
        }
    }
    public static void main(String[] args) {
        Map<Hole, Integer> map = new HashMap<>();
        map.put(new Hole(0, 0), 0);
        map.put(new Hole(0, 3), 0);
        map.put(new Hole(5, 2), 5);
        System.out.println(map.containsKey(new Hole(5, 2))); //true
        System.out.println(map.get(new Hole(5, 2))); //5 
        
        //problem
        Graph<Hole> adjacencyMap = new AdjacencyGraph<>();
        Hole hole0_1 = new Hole(0, 1);
        Hole hole2_1 = new Hole(2, 1);
        Hole hole0_3 = new Hole(0, 3);
        Hole hole2_3 = new Hole(2, 3);
        Hole hole0_5 = new Hole(0, 5);
        Hole hole2_5 = new Hole(2, 5);
        adjacencyMap.add(hole0_1);
        adjacencyMap.add(hole2_1);
        adjacencyMap.add(hole0_3);
        adjacencyMap.add(hole2_3);
        adjacencyMap.add(hole0_5);
        adjacencyMap.add(hole2_5);
        adjacencyMap.connectUndirected(hole0_1, hole2_1);
        adjacencyMap.connectUndirected(hole0_1, hole2_3);
        adjacencyMap.connectUndirected(hole2_1, hole0_3);
        adjacencyMap.connectUndirected(hole0_3, hole2_3);
        adjacencyMap.connectUndirected(hole0_3, hole2_5);
        adjacencyMap.connectUndirected(hole2_3, hole0_5);
        adjacencyMap.connectUndirected(hole0_3, hole0_5);
        adjacencyMap.connectUndirected(hole2_3, hole2_5);

        List<Hole> dfsPath = adjacencyMap.dfSearch(hole2_1, hole0_5);
        System.out.println(dfsPath);

        List<Hole> bfsPath = adjacencyMap.bfSearch(hole2_1, hole0_5);
        System.out.println(bfsPath);
    }
}