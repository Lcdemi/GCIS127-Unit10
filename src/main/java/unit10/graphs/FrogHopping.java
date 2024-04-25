package unit10.graphs;

import java.io.IOException;
//import java.util.Arrays;
import java.util.List;
import static unit10.FileLineReader.open;

public class FrogHopping {
    private AdjacencyGraph<Hole> graph;
    private char[][] arrayBoard;
    private final int ROWS, COLS;

    public FrogHopping(String filename) throws IOException{
        // Initialize all of the fields
        graph = new AdjacencyGraph<>();
        // Construct the array and add all vertices to the graph
        int counter = 0;
        for(String line : open(filename)) {
            if(counter > 0) {
                String[] letters = line.split(" "); //splits the lines by char
                char[] rowChars = new char[letters.length];
                for(int i = 0; i < letters.length; i++) {
                    char currentChar = letters[i].charAt(0);
                    rowChars[i] = currentChar;
                }
                arrayBoard[counter-1] = rowChars; //adds characters to arrayBoard
                counter++;
                //System.out.println(counter);
            } else {
                String[] dimensions = line.split(" ");
                //System.out.println(Arrays.toString(dimensions))
                arrayBoard = new char[Integer.parseInt(dimensions[0])][Integer.parseInt(dimensions[1])]; //creates the character board
                counter++;
            }
        }
        ROWS = arrayBoard.length; //sets rows
        COLS = arrayBoard[0].length; //sets cols
        // for(char[] array : arrayBoard) {    testing
        //     System.out.println(Arrays.toString(array));
        // }
        // Connect vertices
        connectVertices();          
    }

    private void connectVertices(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if(arrayBoard[i][j] == 'H' && !graph.getVertices().containsKey(new Hole(i, j))) { //if char = H and the hole does not exist in AdjacencyGraph
                    graph.add(new Hole(i, j));
                } else if(arrayBoard[i][j] == 'P'){
                    if(i + 1 < ROWS && i > 0 && arrayBoard[i+1][j] == 'H' && arrayBoard[i-1][j] == 'H') { //down
                        connect(i+1, j, i-1, j);
                    }
                    if(j + 1 < COLS && j > 0 && arrayBoard[i][j+1] == 'H' && arrayBoard[i][j-1] == 'H') { //sideright
                        connect(i, j+1, i, j-1);
                    }
                    if(i + 1 < ROWS && j + 1 < COLS && i > 0 && j > 0 && arrayBoard[i+1][j+1] == 'H' && arrayBoard[i-1][j-1] == 'H') { //downright
                        connect(i+1, j+1, i-1, j-1);
                    }
                    if(i > 0 && j + 1 < COLS && i + 1 < ROWS && j > 0 && arrayBoard[i-1][j+1] == 'H' && arrayBoard[i+1][j-1] == 'H') { //topright
                        connect(i-1, j+1, i+1, j-1);
                    }
                }
            }
        }
    }

    private void connect(int i, int j, int iminus, int jminus) { //private function for connectVertices which connects holes together
        if(!graph.getVertices().containsKey(new Hole(i, j))) {
            graph.add(new Hole(i, j));
        }
        if(!graph.getVertices().containsKey(new Hole(iminus, jminus))) {
            graph.add(new Hole(iminus, jminus));
        }
        graph.connectUndirected(new Hole(i, j), new Hole(iminus, jminus));
    }
    
    // The frog can't jump to a hole which was visited before
    public List<Hole> dfPathV0(Hole start, Hole end) {
        return graph.dfSearch(start, end);
    }

    public String toString() {
        return graph.toString();
    }

    // The frog can't jump over the same peg more than once
    public List<Hole> dfPathV1(Hole start, Hole end) {
        return null;
    }
    public static void main(String[] args) throws IOException {
        FrogHopping frog = new FrogHopping("data\\dfs\\frog_3.txt");
        System.out.println(frog);
        Hole start = new Hole(2, 1);
        Hole end = new Hole(0, 5);
        System.out.println(frog.dfPathV0(start, end)); //[(2,1), (0,3), (2,5), (2,3), (0,5)]
        System.out.println(frog.dfPathV1(start, end)); //[(2,1), (0,3), (2,5), (2,3), (0,3), (0,5)]
        System.out.println("The frog game graph representation:");
        
    }
}
