import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Box/Line reduction occurs when a candidate only appears in a row or column, and those candidates are also all in the same box.
 * When this happens, you know that the candidate MUST occur in that row or column because it can't appear anywhere else there,
 * so it CAN'T occur elsewhere in that box.
 */
public class BoxReductionStrategy extends PuzzleSolvingStrategy{

    private int count;
    private double totalTime;
    boolean stateChanged;

    public BoxReductionStrategy(){
        count = 0;
        totalTime = 0;
    }

    @Override
    public List<CellCoordinate> findCellCoordinates(Cell[][] sudokuPuzzle) {
        System.out.println("Inside Box Reduction Strategy");
        List<CellCoordinate> cellWithSizeGreaterThanOne = new ArrayList<>();
        int sqrt = (int) Math.sqrt(sudokuPuzzle.length);
        for(int row = 0; row < sudokuPuzzle.length; row+=sqrt){

            for(int col = 0; col < sudokuPuzzle.length; col+=sqrt){

                if(sudokuPuzzle[row][col].getSize() > 1){
                    cellWithSizeGreaterThanOne.add(new CellCoordinate(row, col));
                }
            }
        }

        return cellWithSizeGreaterThanOne;
    }

    @Override
    public List<CellCoordinate> findCandidateCellCoordinates(List<CellCoordinate> cellToUseForElimination, Cell[][] sudokuPuzzle) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        for(CellCoordinate cell: cellToUseForElimination){

            int row = cell.getRow();
            int col = cell.getCol();
            Object[] cand = sudokuPuzzle[row][col].getCandidates().toArray();
            Character[] candidates = new Character[cand.length];
            for (int temp = 0; temp < cand.length; temp++) {
                candidates[temp] = (Character) cand[temp];
            }

            for (int index = 0; index < candidates.length; index++) {
                boolean blockHasValue = checkCandidateIsPresentInBlock(row, col, sudokuPuzzle, candidates[index]);
                boolean otherRowHasValue = checkCandidateIsPresentInOtherRowExcludingCurrentBlock(row, col, sudokuPuzzle, candidates[index]);
                boolean otherColHasValue = checkCandidateIsPresentInOtherColumnExcludingCurrentBlock(row, col, sudokuPuzzle, candidates[index]);

                if(blockHasValue && (!otherRowHasValue || !otherColHasValue)){
                    if(!otherRowHasValue){
                        cellToUpdate = getTheCellFromTheBlockExcludingCandidateColumn(row, col, sudokuPuzzle, candidates[index]);
                    }else{
                        cellToUpdate = getTheCellFromTheBlockExcludingCandidateRow(row, col, sudokuPuzzle, candidates[index]);
                    }
                }
            }
        }

        return cellToUpdate;
    }

    @Override
    public boolean removeTheCandidate(List<CellCoordinate> cellContainingCandidate, Cell[][] sudokuPuzzle) {
        boolean stateChanged = false;
        for (CellCoordinate cell: cellContainingCandidate){
            int row = cell.getRow();
            int col = cell.getCol();
            if(sudokuPuzzle[row][col].getSize() == 1){
                continue;
            }

            char candidate = (char) cell.getCandidate();
            sudokuPuzzle[row][col].getCandidates().remove(candidate);
            stateChanged = true;
            if(sudokuPuzzle[row][col].getSize() == 1){
                count++;
            }
        }

        return stateChanged;
    }

    public int getCount() {
        return count;
    }

    public double getTotalTime() {
        return totalTime;
    }

    private List<CellCoordinate> getTheCellFromTheBlockExcludingCandidateRow(int candidateRow, int candidateColumn, Cell[][] puzzle, char candidateToRemove) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int r1 = (candidateRow/sqrtOfSize)*sqrtOfSize; r1 < ((candidateRow/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; r1++){

            for(int c1 = (candidateColumn/sqrtOfSize)*sqrtOfSize; c1 < ((candidateColumn/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; c1++){

                if(r1 != candidateRow){
                    Set <Character> possibleValues = puzzle[r1][c1].getCandidates();
                    if(possibleValues.contains(candidateToRemove)){
                        cellToUpdate.add(new CellCoordinate(r1, c1, candidateToRemove));
                    }
                }
            }
        }

        return cellToUpdate;
    }

    private List<CellCoordinate> getTheCellFromTheBlockExcludingCandidateColumn(int candidateRow, int candidateColumn, Cell[][] puzzle, char candidateToRemove) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int r1 = (candidateRow /sqrtOfSize)*sqrtOfSize; r1 < ((candidateRow /sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; r1++){

            for(int c1 = (candidateColumn/sqrtOfSize)*sqrtOfSize; c1 < ((candidateColumn/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; c1++){

                if(c1 != candidateColumn){
                    Set <Character> possibleValues = puzzle[r1][c1].getCandidates();

                    if(possibleValues.contains(candidateToRemove)){
                        cellToUpdate.add(new CellCoordinate(r1, c1, candidateToRemove));
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
        for (int r1 = 0; r1 < size; r1++) {
            if (r/sqrtOfSize != r1/sqrtOfSize) {
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
        return "Box Reduction has eliminated " + count + " and has taken " + totalTime/1000;
    }

}
