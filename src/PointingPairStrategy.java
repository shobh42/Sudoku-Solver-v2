import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 Created by Shobhit on 11/16/2016.
 */

/***
 * A pointing pair occurs when a candidate appears in a block, and that candidate is also aligned on the same row or column.
 * This means that you know that the candidate MUST occur in one of the two squares in the block, and because of that,
 * you can eliminate that candidate from any other cells in the row or column that the candidate is aligned on.
 */
public class PointingPairStrategy extends PuzzleSolvingStrategy {

    private boolean stateChanged;
    private int count;
    private double totalTime;

    public PointingPairStrategy(){
        count = 0;
        totalTime = 0;
    }

    @Override
    public List<CellCoordinate> findCellCoordinates(SudokuPuzzle sudokuPuzzle) {
        System.out.println("Inside Pointing Pair Strategy");
        List<CellCoordinate> cellWithSizeGreaterThanOne = new ArrayList<>();
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        int sqrt = (int) Math.sqrt(puzzle.length);
        for(int row = 0; row < puzzle.length; row+=sqrt){

            for(int col = 0; col < puzzle.length; col+=sqrt){

                if(puzzle[row][col].getSize() > 1){
                    cellWithSizeGreaterThanOne.add(new CellCoordinate(row, col));
                }
            }
        }

        return cellWithSizeGreaterThanOne;
    }

    @Override
    public List<CellCoordinate> findCandidateCellCoordinates(List<CellCoordinate> cellToUseForElimination, SudokuPuzzle sudokuPuzzle) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        for(CellCoordinate cell: cellToUseForElimination){

            int row = cell.getRow();
            int col = cell.getCol();
            Object[] cand = puzzle[row][col].getCandidates().toArray();
            Character[] candidates = new Character[cand.length];
            for (int temp = 0; temp < cand.length; temp++) {
                candidates[temp] = (Character) cand[temp];
            }

            for (int index = 0; index < candidates.length; index++) {
                boolean blockHasValue = checkCandidateIsPresentInBlock(row, col, puzzle, candidates[index]);
                boolean otherRowHasValue = checkCandidateIsPresentInOtherRowExcludingCurrentBlock(row, col, puzzle, candidates[index]);
                boolean otherColHasValue = checkCandidateIsPresentInOtherColumnExcludingCurrentBlock(row, col, puzzle, candidates[index]);

                if(!blockHasValue && (otherRowHasValue || otherColHasValue)){
                    if(!otherRowHasValue){
                        cellToUpdate = getTheCandidateFromRow(row, col, puzzle, candidates[index]);
                    }else{
                        cellToUpdate = removeTheCandidateFromColumn(row, col, puzzle, candidates[index]);
                    }
                }
            }
        }

        return cellToUpdate;
    }

    @Override
    public boolean removeTheCandidate(List<CellCoordinate> cellContainingCandidate, SudokuPuzzle sudokuPuzzle) {
        boolean stateChanged = false;
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        for (CellCoordinate cell: cellContainingCandidate){

            int row = cell.getRow();
            int col = cell.getCol();
            if(puzzle[row][col].getSize() == 1){
                continue;
            }

            Character candidate = (Character) cell.getCandidate();
            puzzle[row][col].getCandidates().remove(candidate);
            stateChanged = true;
            if(puzzle[row][col].getSize() == 1){
                count++;
                sudokuPuzzle.updateRemainingCell();
            }
        }

        return stateChanged;
    }

    private List<CellCoordinate> removeTheCandidateFromColumn(int r, int c, Cell[][] puzzle, char valueToRemove) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for (int col = 0; col < size; col++) {
            if ((col / sqrtOfSize) != (c / sqrtOfSize)) {
                if (puzzle[r][col].getSize() > 1) {
                    Set<Character> possibleValues = puzzle[r][col].getCandidates();
                    if (possibleValues.contains(valueToRemove)) {
                        cellToUpdate.add(new CellCoordinate(r, col, valueToRemove));
                    }
                }
            }
        }

        return cellToUpdate;
    }

    private List<CellCoordinate> getTheCandidateFromRow(int r, int c, Cell[][] puzzle, char valueToRemove) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for (int row = 0; row < size; row++) {
            if ((row / sqrtOfSize) != (r / sqrtOfSize)) {

                if (puzzle[row][c].getSize() > 1) {
                    Set<Character> possibleValues = puzzle[row][c].getCandidates();
                    if (possibleValues.contains(valueToRemove)) {
                        cellToUpdate.add(new CellCoordinate(row, c, valueToRemove));
                    }
                }
            }
        }

        return cellToUpdate;
    }

    private boolean checkCandidateIsPresentInOtherColumnExcludingCurrentBlock(int r, int c, Cell[][] puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for (int c1 = 0; c1 < size; c1++) {
            if (c/sqrtOfSize != c1/sqrtOfSize) {
                Set<Character> possibleValues = puzzle[r][c1].getCandidates();
                if (possibleValues.contains(valueToFind)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCandidateIsPresentInOtherRowExcludingCurrentBlock(int r, int c, Cell[][] puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        //for (int r1 = (r / sqrtOfSize) * sqrtOfSize; r1 < ((r / sqrtOfSize) * sqrtOfSize) + sqrtOfSize; r1++) {
        for (int r1 = 0; r1 < size; r1++){
            if (r1/sqrtOfSize != r/sqrtOfSize) {
                Set<Character> possibleValues = puzzle[r1][c].getCandidates();
                if (possibleValues.contains(valueToFind)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkCandidateIsPresentInBlock(int r, int c, Cell[][] puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        for (int r1 = (r / sqrtOfSize) * sqrtOfSize; r1 < ((r / sqrtOfSize) * sqrtOfSize) + sqrtOfSize; r1++) {

            for (int c1 = (c / sqrtOfSize) * sqrtOfSize; c1 < ((c / sqrtOfSize) * sqrtOfSize) + sqrtOfSize; c1++) {
                if (r1 != r) {
                    Set<Character> possibleValues = puzzle[r1][c1].getCandidates();
                    if (possibleValues.contains(valueToFind)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public String toString(){
        return "Pointing Pair has eliminated " + count + " and has taken " + totalTime/1000;
    }

    public int getCount() {
        return count;
    }

    public double getTotalTime() {
        return totalTime;
    }
}