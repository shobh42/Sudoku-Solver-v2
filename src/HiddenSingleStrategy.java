import java.util.*;

/**
 * Created by Shobhit on 11/15/2016.
 */
public class HiddenSingleStrategy extends PuzzleSolvingStrategy{
    private int count;
    private double totalTime;

    public HiddenSingleStrategy(){
        count = 0;
        totalTime = 0;
    }

    @Override
    public List<CellCoordinate> findCellCoordinates(SudokuPuzzle sudokuPuzzle) {
        System.out.println("Inside Hidden Single Strategy");
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

                char candidate = candidates[index];
                if (checkCandidateIsNotPresentInRow(row, col, puzzle, candidate) ||
                        checkCandidateIsNotPresentInColumn(row, col, puzzle, candidate)) {
                    cellToUpdate.add(new CellCoordinate(row, col, candidate));
                }
            }
        }

        return cellToUpdate;
    }

    @Override
    public boolean removeTheCandidate(List<CellCoordinate> cellToUpdate, SudokuPuzzle sudokuPuzzle) {
        boolean stateChanged = false;
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        for (CellCoordinate cell: cellToUpdate){
            int row = cell.getRow();
            int col = cell.getCol();
            if(puzzle[row][col].getSize() == 1){
                continue;
            }

            char candidate = (char) cell.getCandidate();
            Set<Character> s = new HashSet<>();
            s.add(candidate);
            puzzle[row][col].getCandidates().remove(candidate);
            puzzle[row][col] = new Cell(s);
            stateChanged = true;
            count++;
            sudokuPuzzle.updateRemainingCell();
        }

        return stateChanged;
    }

    public int getCount() {
        return count;
    }

    public double getTotalTime() {
        return totalTime;
    }

    private boolean checkCandidateIsNotPresentInRow(int candidateRow, int candidateColumn, Cell [][]puzzle, char candidate){
        int size = puzzle.length;
        for(int otherCandidateRow = 0; otherCandidateRow < size; otherCandidateRow++){
            if(otherCandidateRow != candidateRow){
                Set<Character> candidates =  puzzle[otherCandidateRow][candidateColumn].getCandidates();
                if(candidates.contains(candidate)){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkCandidateIsNotPresentInColumn(int candidateRow, int candidateColumn, Cell [][]puzzle, char candidate){
        int size = puzzle.length;
        for(int otherCandidateColumn = 0; otherCandidateColumn<size; otherCandidateColumn++){
            if(otherCandidateColumn != candidateColumn){
                Set<Character> candidates =  puzzle[candidateRow][otherCandidateColumn].getCandidates();
                if(candidates.contains(candidate)){
                    return false;
                }
            }
        }

        return true;
    }

    public String toString(){
        return "Hidden Single has eliminated " + count + " and has taken " + totalTime/1000;
    }
}

