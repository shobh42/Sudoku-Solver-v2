import java.util.ArrayList;
import java.util.List;

public class ColumnEliminationStrategy extends EliminationStrategy {
    private int count;
    private double totalTime;

    public ColumnEliminationStrategy(){
        count = 0;
        totalTime = 0;
    }

    @Override
    public List<Cell> findCells(Cell[][] sudokuPuzzle) {
        List<Cell> cellWithSizeOne = new ArrayList<>();
        for(int col = 0; col < sudokuPuzzle.length; col++){

            for(int row = 0; row < sudokuPuzzle.length; row++){

                if(sudokuPuzzle[row][col].getSize() == 1){
                    cellWithSizeOne.add(sudokuPuzzle[row][col]);
                }
            }
        }

        return cellWithSizeOne;
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
