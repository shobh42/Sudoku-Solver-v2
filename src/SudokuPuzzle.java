import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SudokuPuzzle {

    private Cell[][] sudokuPuzzle;
    private int remainingCell;
    private SudokuState state;

    public SudokuPuzzle(Cell[][] sudokuPuzzle, int remainingCell){
        this.sudokuPuzzle = sudokuPuzzle;
        this.remainingCell = remainingCell;
        state = SudokuState.UNSOLVED;
    }

    public SudokuPuzzle(SudokuPuzzle sudokuPuzzle){
        this.state = sudokuPuzzle.getState();
        this.remainingCell = sudokuPuzzle.getRemainingCell();
        this.sudokuPuzzle = getCopy(sudokuPuzzle.getSudokuPuzzle());
    }

    public Cell[][] getSudokuPuzzle() {
        return sudokuPuzzle;
    }

    public int getRemainingCell() {
        return remainingCell;
    }

    public SudokuState getState() {
        return state;
    }

    public void updateRemainingCell(){
        remainingCell-=1;
//        if(remainingCell == 0){
//            state = SudokuState.SOLVED;
//        }
    }

    public void setState(SudokuState state){
        this.state = state;
    }

    public void restoreRemainingCell(){
        remainingCell+=1;
        state = SudokuState.UNSOLVED;
    }

    private Cell[][] getCopy(Cell[][] puzzle){
        int length = puzzle.length;
        Cell[][] copy = new Cell[length][length];
        for(int row = 0; row < length; row++){

            for(int col = 0; col < length; col++){

                Set<Character> chars = new HashSet<>();
                Iterator<Character> itr = puzzle[row][col].getCandidates().iterator(); // traversing over HashSet System.out.println("Traversing over Set using Iterator");
                while(itr.hasNext()) {
                    chars.add(itr.next());
                }

                copy[row][col] = new Cell(chars);
            }
        }

        return copy;
    }

    public void setPuzzle(Cell[][] newPuzzle){
        this.sudokuPuzzle = getCopy(newPuzzle);
    }
}
