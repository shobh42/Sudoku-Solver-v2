public class SudokuPuzzle {

    private Cell[][] sudokuPuzzle;
    private int remainingCell;
    private SudokuState state;

    public SudokuPuzzle(Cell [][] sudokuPuzzle, int remainingCell){
        this.sudokuPuzzle = sudokuPuzzle;
        this.remainingCell = remainingCell;
        state = SudokuState.UNSOLVED;
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
        if(remainingCell == 0){
            state = SudokuState.SOLVED;
        }
    }

    public void restoreRemainingCell(){
        remainingCell+=1;
        state = SudokuState.UNSOLVED;
    }
}
