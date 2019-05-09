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
    public List<CellCoordinate> checkCandidateIsPresent(List<CellCoordinate> cellWithCandidate, Cell[][] sudokuPuzzle) {
        List<CellCoordinate> cellWithCandidateToRemove = new ArrayList<>();
        for(CellCoordinate cell: cellWithCandidate){

            char candidate = (Character) sudokuPuzzle[cell.getRow()][cell.getCol()].getCandidates().toArray()[0];
            int candidateRow = cell.getRow();
            int candidateCol = cell.getCol();
            for(int col = 0; col < sudokuPuzzle.length; col++){
                if(sudokuPuzzle[candidateRow][col].getCandidates().size() > 1
                        && sudokuPuzzle[candidateRow][col].getCandidates().contains(candidate)){
                    cellWithCandidateToRemove.add(new CellCoordinate(candidateRow, col));
                }
            }

            for(int row = 0; row < sudokuPuzzle.length; row++){
                if(sudokuPuzzle[row][candidateCol].getCandidates().size() > 1
                        && sudokuPuzzle[row][candidateCol].getCandidates().contains(candidate)){
                    cellWithCandidateToRemove.add(new CellCoordinate(row, candidateCol));
                }
            }
        }

        return cellWithCandidateToRemove;
    }

    @Override
    public boolean removeTheCandidate(List<CellCoordinate> cellContainingCandidate) {
        return false;
    }
}
