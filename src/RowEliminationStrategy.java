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

    public int getCount() {
        return count;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public String toString(){
        return "Row Elimination has eliminated " + count + " and has taken " + totalTime/1000;
    }

    @Override
    public List<CellCoordinate> findCellCoordinates(Cell[][] sudokuPuzzle) {
        System.out.println("Inside Row Elimination");
        List<CellCoordinate> cellWithSizeOne = new ArrayList<>();
        for(int row = 0; row < sudokuPuzzle.length; row++){

            for(int col = 0; col < sudokuPuzzle.length; col++){

                if(sudokuPuzzle[row][col].getSize() == 1){
                    cellWithSizeOne.add(new CellCoordinate(row, col));
                }
            }
        }

        return cellWithSizeOne;
    }

    @Override
    public List<CellCoordinate> checkCandidateIsPresent(List<CellCoordinate> cellToUseForElimination, Cell[][] sudokuPuzzle) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        for(CellCoordinate cell: cellToUseForElimination){

            char candidate = (Character) sudokuPuzzle[cell.getRow()][cell.getCol()].getCandidates().toArray()[0];
            int candidateRow = cell.getRow();
            for(int col = 0; col < sudokuPuzzle.length; col++){
                if(sudokuPuzzle[candidateRow][col].getCandidates().size() > 1
                        && sudokuPuzzle[candidateRow][col].getCandidates().contains(candidate)){
                    cellToUpdate.add(new CellCoordinate(candidateRow, col, candidate));
                }
            }
        }

        return cellToUpdate;
    }

    @Override
    public boolean removeTheCandidate(List<CellCoordinate> cellToUpdate, Cell[][] sudokuPuzzle) {
        boolean stateChanged = false;
        for (CellCoordinate cell: cellToUpdate){

            int row = cell.getRow();
            int col = cell.getCol();
            if(sudokuPuzzle[row][col].getSize() == 1){
                continue;
            }

            Character candidate = (Character) cell.getCandidate();
            sudokuPuzzle[row][col].getCandidates().remove(candidate);
            stateChanged = true;
            if(sudokuPuzzle[row][col].getSize() == 1){
                count++;
            }
        }

        return stateChanged;
    }
}
