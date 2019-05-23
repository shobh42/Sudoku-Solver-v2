import java.util.ArrayList;
import java.util.List;

public class ColumnEliminationStrategy extends PuzzleSolvingStrategy {
    private int count;
    private double totalTime;

    public ColumnEliminationStrategy(){
        count = 0;
        totalTime = 0;
    }

    @Override
    public List<CellCoordinate> findCellCoordinates(SudokuPuzzle sudokuPuzzle) {
        System.out.println("Inside Column Elimination");
        List<CellCoordinate> cellWithSizeOne = new ArrayList<>();
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        for(int col = 0; col < puzzle.length; col++){

            for(int row = 0; row < puzzle.length; row++){

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
            int candidateCol = cell.getCol();
            for(int row = 0; row < puzzle.length; row++){
                if(puzzle[row][candidateCol].getCandidates().size() > 1
                        && puzzle[row][candidateCol].getCandidates().contains(candidate)){
                    cellToUpdate.add(new CellCoordinate(row, candidateCol, candidate));
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
            count++;
            if(puzzle[row][col].getSize() == 1){

                sudokuPuzzle.updateRemainingCell();
            }
        }

        return stateChanged;
    }
//    @Override
//    public boolean solve(int size, Cell[][] puzzle) {
//        System.out.println("In Column strategy");
//        boolean stateChanged = false;
//        long startTime = System.currentTimeMillis();
//        for(int candidateColumn = 0; candidateColumn < size; candidateColumn++){
//
//            for(int candidateRow = 0; candidateRow < size; candidateRow++){
//
//                Cell candidateCell = puzzle[candidateRow][candidateColumn];
//                if(candidateCell.getSize() == 1){
//                    char candidate = (char) candidateCell.getCandidates().toArray()[0];
//                    if(candidateColumn == 4 && candidate == '8'){
//                        int i = 0;
//                    }
//                    for(int otherCandidateRow = 0; otherCandidateRow < size; otherCandidateRow++) {
//
//                        if (otherCandidateRow != candidateRow && puzzle[otherCandidateRow][candidateColumn].getSize() > 1 &&
//                                puzzle[otherCandidateRow][candidateColumn].getCandidates().contains(candidate)) {
//                            puzzle[otherCandidateRow][candidateColumn].getCandidates().remove(candidate);
//                            stateChanged = true;
//                            count++;
//                        }
//                    }
//                }
//            }
//        }
//        long endTime = System.currentTimeMillis();
//        totalTime+= (endTime - startTime);
//        return stateChanged;
//    }

    public double getTotalTime() {
        return totalTime;
    }

    public int getCount(){
        return count;
    }

    public String toString(){
        return "Column Elimination has eliminated " + count + " and has taken " + totalTime/1000;
    }
}
