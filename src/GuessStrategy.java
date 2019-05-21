import java.util.HashSet;
import java.util.Set;

public class GuessStrategy {

    private SudokuPuzzle sudokuPuzzle;

    public GuessStrategy(SudokuPuzzle puzzle){
        this.sudokuPuzzle = puzzle;
    }

    public void solve(){
        System.out.println("Inside Brute Force");
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        int size = puzzle.length;
        for(int row = 0; row < size; row++){

            for(int col = 0; col < size; col++){

                Cell candidateCell = puzzle[row][col];
                if(candidateCell.getSize() > 1){
                    Object[] cand = puzzle[row][col].getCandidates().toArray();
                    Character[] candidates = new Character[cand.length];
                    for(int temp = 0; temp < cand.length; temp++){
                        candidates[temp] = (Character) cand[temp];
                    }

                    for(int i = 0; i < candidates.length; i++){
                        Set<Character> s = new HashSet<>();
                        s.add(candidates[i]);
                        puzzle[row][col] = new Cell(s);
                        sudokuPuzzle.updateRemainingCell();
                        return;
                    }
                }
            }
        }
    }
}