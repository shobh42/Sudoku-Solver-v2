import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Driver {

    public static void main(String[] args) {
        //System.out.println("Enter the path of the puzzle file");
        try {
           List<SolvedPuzzle> solvedPuzzle = new SudokuSolver().solve(null);

            System.out.println("Printing Solution");
           for(SolvedPuzzle puzzle : solvedPuzzle){
               printPuzzle(puzzle.getSolvedPuzzle());
               System.out.println("<><><><><><><><><><><><><>");
           }
//           for(PuzzleSolvingStrategy strategy: solvedPuzzle.get(0).strategiesInfo()){
//                System.out.println(strategy.toString());
//                System.out.println();
//            }
        } catch (InvalidPuzzleException e) {
            e.printStackTrace();
        } catch (IllegalCharacterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printPuzzle(List<List<Character>> puzzle){
        for (int i = 0; i < puzzle.size(); i++){

            for(int j = 0; j < puzzle.size(); j++){
                System.out.print(puzzle.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println("<><><><><><><><><><><><><>");

    }
}
