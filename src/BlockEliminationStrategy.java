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
    public List<CellCoordinate> findCellCoordinates(Cell[][] sudokuPuzzle) {
        System.out.println("Inside Block Elimination");
        List<CellCoordinate> cellWithSizeOne = new ArrayList<>();
        int sqrt = (int) Math.sqrt(sudokuPuzzle.length);
        for(int row = 0; row < sudokuPuzzle.length; row+=sqrt){

            for(int col = 0; col < sudokuPuzzle.length; col+=sqrt){

                for(int currentBlockRow = row; currentBlockRow < row + sqrt; currentBlockRow++){

                    for(int currentBlockCol = col; currentBlockCol < col + sqrt; currentBlockCol++){

                        if(sudokuPuzzle[currentBlockRow][currentBlockCol].getSize() == 1){
                            cellWithSizeOne.add(new CellCoordinate(row, col));
                        }
                    }
                }
            }
        }

        return cellWithSizeOne;
    }

    @Override
    public List<CellCoordinate> checkCandidateIsPresent(List<CellCoordinate> cellToUseForElimination, Cell[][] sudokuPuzzle) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        int sqrt = (int) Math.sqrt(sudokuPuzzle.length);
        for(CellCoordinate cell: cellToUseForElimination){

            char candidate = (Character) sudokuPuzzle[cell.getRow()][cell.getCol()].getCandidates().toArray()[0];
            int candidateRow = cell.getRow();
            int candidateCol = cell.getCol();

            for(int row = (candidateRow/sqrt) * sqrt; row < ((candidateRow/sqrt) * sqrt) + sqrt; row++){

                for(int col = (candidateCol/sqrt) * sqrt; col < ((candidateCol/sqrt) * sqrt) + sqrt; col++){

                    if(sudokuPuzzle[row][col].getCandidates().size() > 1
                            && sudokuPuzzle[row][col].getCandidates().contains(candidate)){
                        cellToUpdate.add(new CellCoordinate(row, candidateCol, candidate));
                    }
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

//    @Override
//    public boolean solve(int size, Cell[][] puzzle) {
//
//        System.out.println("In block elimination");
//        int sqrtOfSize = (int)Math.sqrt(size);
//        boolean stateChanged = false;
//        long startTime = System.currentTimeMillis();
//        for(int candidateRow = 0; candidateRow < size; candidateRow+=sqrtOfSize){
//
//            for(int candidateColumn = 0; candidateColumn < size; candidateColumn+=sqrtOfSize) {
//
//                for (int otherCandidateRow = candidateRow;
//                         otherCandidateRow < (candidateRow + sqrtOfSize) && otherCandidateRow < size;
//                         otherCandidateRow++) {
//
//                        for (int otherCandidateColumn = candidateColumn;
//                             otherCandidateColumn < (candidateColumn + sqrtOfSize) && otherCandidateColumn < size;
//                             otherCandidateColumn++) {
//
//                            Cell otherCandidateCell = puzzle[otherCandidateRow][otherCandidateColumn];
//                            if (otherCandidateCell.getSize() == 1) {
//                                stateChanged = stateChanged || removeCandidateFromBlock(puzzle, candidateRow, candidateColumn,
//                                        otherCandidateRow, otherCandidateColumn);
//                                if(stateChanged){
//                                    count++;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        long endTime   = System.currentTimeMillis();
//        totalTime+= (endTime - startTime);
//
//        return stateChanged;
//    }

    private boolean removeCandidateFromBlock(Cell[][] puzzle, int candidateRow, int candidateColumn,
                                          int currentBlockRowToCompare, int currentBlockColToCompare) {

        boolean stateChanged = false;
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(puzzle.length);
        for (int row = candidateRow;
             row < candidateRow + sqrtOfSize && row < size; row++) {

            for (int col = candidateColumn;
                 col < candidateColumn + sqrtOfSize && col < size; col++) {

                if(puzzle[row][col].getSize() != 1 &&
                        !(row == currentBlockRowToCompare && col == currentBlockColToCompare)){
                    char candidate = (char) puzzle[currentBlockRowToCompare][currentBlockColToCompare].getCandidates().toArray()[0];
                    Cell cell = puzzle[row][col];
                    if(cell.getCandidates().contains(candidate)){
                        cell.getCandidates().remove(candidate);
                        stateChanged = true;
                    }

                }
            }
        }

        return stateChanged;
    }

    public String toString(){
        return "Block Elimination has eliminated " + count + " and has taken " + totalTime/1000;
    }
}