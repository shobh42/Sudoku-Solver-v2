import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class BlockEliminationStrategy implements SolvingStrategy {

    private int count;
    private double totalTime;

    public BlockEliminationStrategy(){
        count = 0;
        totalTime = 0;
    }

    public int getCount() {
        return count;
    }

    public double getTotalTime() {
        return totalTime;
    }

    @Override
    public boolean solve(int size, Cell[][] puzzle) {

        System.out.println("In block elimination");
        int sqrtOfSize = (int)Math.sqrt(size);
        boolean stateChanged = false;
        long startTime = System.currentTimeMillis();
        for(int candidateRow = 0; candidateRow < size; candidateRow+=sqrtOfSize){

            for(int candidateColumn = 0; candidateColumn < size; candidateColumn+=sqrtOfSize) {

                for (int otherCandidateRow = candidateRow;
                         otherCandidateRow < (candidateRow + sqrtOfSize) && otherCandidateRow < size;
                         otherCandidateRow++) {

                        for (int otherCandidateColumn = candidateColumn;
                             otherCandidateColumn < (candidateColumn + sqrtOfSize) && otherCandidateColumn < size;
                             otherCandidateColumn++) {

                            Cell otherCandidateCell = puzzle[otherCandidateRow][otherCandidateColumn];
                            if (otherCandidateCell.getSize() == 1) {
                                stateChanged = stateChanged || removeCandidateFromBlock(puzzle, candidateRow, candidateColumn,
                                        otherCandidateRow, otherCandidateColumn);
                                if(stateChanged){
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        long endTime   = System.currentTimeMillis();
        totalTime+= (endTime - startTime);

        return stateChanged;
    }

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