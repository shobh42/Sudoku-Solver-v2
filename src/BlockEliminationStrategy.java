import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BlockEliminationStrategy extends PuzzleSolvingStrategy {

    private int count;
    private double totalTime;

    public BlockEliminationStrategy(){
        count = 0;
        totalTime = 0;
    }

    @Override
    public List<CellCoordinate> findCellCoordinates(SudokuPuzzle sudokuPuzzle) {
        System.out.println("Inside Block Elimination");
        List<CellCoordinate> cellWithSizeOne = new ArrayList<>();
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        int sqrt = (int) Math.sqrt(puzzle.length);
        for(int row = 0; row < puzzle.length; row+=sqrt){

            for(int col = 0; col < puzzle.length; col+=sqrt){

                for(int currentBlockRow = row; currentBlockRow < row + sqrt; currentBlockRow++){

                    for(int currentBlockCol = col; currentBlockCol < col + sqrt; currentBlockCol++){

                        if(puzzle[currentBlockRow][currentBlockCol].getSize() == 1){
                            cellWithSizeOne.add(new CellCoordinate(currentBlockRow, currentBlockCol));
                        }
                    }
                }
            }
        }

        return cellWithSizeOne;
    }

    @Override
    public List<CellCoordinate> findCandidateCellCoordinates(List<CellCoordinate> cellToUseForElimination, SudokuPuzzle sudokuPuzzle) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        int sqrt = (int) Math.sqrt(puzzle.length);
        for(CellCoordinate cell: cellToUseForElimination){

            char candidate = (Character) puzzle[cell.getRow()][cell.getCol()].getCandidates().toArray()[0];
            int candidateRow = cell.getRow();
            int candidateCol = cell.getCol();

            for(int row = (candidateRow/sqrt) * sqrt; row < ((candidateRow/sqrt) * sqrt) + sqrt; row++){

                for(int col = (candidateCol/sqrt) * sqrt; col < ((candidateCol/sqrt) * sqrt) + sqrt; col++){

                    if(puzzle[row][col].getCandidates().size() > 1
                            && puzzle[row][col].getCandidates().contains(candidate)){
                        cellToUpdate.add(new CellCoordinate(row, col, candidate));
                    }
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
        return "Block Elimination has eliminated " + count + " and has taken " + totalTime/1000;
    }
}