import java.util.ArrayList;
import java.util.List;

public class RowEliminationStrategy extends PuzzleSolvingStrategy
{
    private int count;
    private double totalTime;

    public RowEliminationStrategy(){
        count = 0;
        totalTime = 0;
    }

    @Override
    public List<CellCoordinate> findCellCoordinates(SudokuPuzzle sudokuPuzzle) {
        System.out.println("Inside Row Elimination");
        List<CellCoordinate> cellWithSizeOne = new ArrayList<>();
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        for(int row = 0; row < puzzle.length; row++){

            for(int col = 0; col < puzzle.length; col++){

                if(puzzle[row][col].getSize() == 1){
                    cellWithSizeOne.add(new CellCoordinate(row, col));
                }
            }
        }

        return cellWithSizeOne;
    }

    @Override
    public List<CellCoordinate> findCandidateCellCoordinates(List<CellCoordinate> cellToUseForElimination, SudokuPuzzle sudokuPuzzle) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        for(CellCoordinate cell: cellToUseForElimination){

            char candidate = (Character) puzzle[cell.getRow()][cell.getCol()].getCandidates().toArray()[0];
            int candidateRow = cell.getRow();
            for(int col = 0; col < puzzle.length; col++){
                if(puzzle[candidateRow][col].getCandidates().size() > 1
                        && puzzle[candidateRow][col].getCandidates().contains(candidate)){
                    cellToUpdate.add(new CellCoordinate(candidateRow, col, candidate));
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

    public int getCount() {
        return count;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public String toString(){
        return "Row Elimination has eliminated " + count + " and has taken " + totalTime/1000;
    }
}
