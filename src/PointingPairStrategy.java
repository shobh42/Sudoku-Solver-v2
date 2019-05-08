import java.util.Set;

/**
 Created by Shobhit on 11/16/2016.
 */

/***
 * A pointing pair occurs when a candidate appears in a block, and that candidate is also aligned on the same row or column.
 * This means that you know that the candidate MUST occur in one of the two squares in the block, and because of that,
 * you can eliminate that candidate from any other cells in the row or column that the candidate is aligned on.
 */
public class PointingPairStrategy implements SolvingStrategy {

    private boolean stateChanged;
    private int count;
    private double totalTime;

    public PointingPairStrategy(){
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
        System.out.println("Inside Pointing Pair Strategy");
        long startTime = System.currentTimeMillis();
        for (int row = 0; row < size; row++) {

            for (int col = 0; col < size; col++) {

                if(puzzle[row][col].getCandidates().size() > 1){
                    Set<Character> candidates = puzzle[row][col].getCandidates();
                    for (int i = 0; i < candidates.size() && candidates.size() != 1; i++) {
                        char valueToCheck = (Character) candidates.toArray()[i];
                        boolean blockHasValue = checkCandidateIsPresentInBlock(row, col, puzzle, valueToCheck);
                        boolean rowHasValue = checkCandidateIsPresentInOtherRowExcludingCurrentBlock(row, col, puzzle, valueToCheck);
                        boolean colHasValue = checkCandidateIsPresentInOtherColumnExcludingCurrentBlock(row, col, puzzle, valueToCheck);

                        if(!blockHasValue && (rowHasValue || colHasValue)) {

                            if (rowHasValue) {
                                removeTheCandidateFromRow(row, col, puzzle, valueToCheck);
                            } else {
                                removeTheCandidateFromColumn(row, col, puzzle, valueToCheck);
                            }

                            stateChanged = true;
                        }
                    }
                }
            }
        }

        long endTime = System.currentTimeMillis();
        totalTime+= (endTime - startTime);
        return stateChanged;
    }

    private void removeTheCandidateFromColumn(int r, int c, Cell[][] puzzle, char valueToRemove) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for (int col = 0; col < size; col++) {
            if ((col / sqrtOfSize) != (c / sqrtOfSize)) {
                if (puzzle[r][col].getSize() > 1) {
                    Set<Character> possibleValues = puzzle[r][col].getCandidates();
                    if (possibleValues.contains(valueToRemove)) {
                        stateChanged = true;
                        possibleValues.remove(valueToRemove);
                        count++;
                    }
                }
            }
        }
    }

    private void removeTheCandidateFromRow(int r, int c, Cell[][] puzzle, char valueToRemove) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for (int row = 0; row < size; row++) {
            if ((row / sqrtOfSize) != (r / sqrtOfSize)) {

                if (puzzle[row][c].getSize() > 1) {
                    Set<Character> possibleValues = puzzle[row][c].getCandidates();
                    if (possibleValues.contains(valueToRemove)) {
                        stateChanged = true;
                        possibleValues.remove(valueToRemove);
                        count++;
                    }
                }
            }
        }
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
        //for (int r1 = (r / sqrtOfSize) * sqrtOfSize; r1 < ((r / sqrtOfSize) * sqrtOfSize) + sqrtOfSize; r1++) {
        for (int r1 = 0; r1 < size; r1++){
            if (r1/sqrtOfSize != r/sqrtOfSize) {
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
        return "Pointing Pair has eliminated " + count + " and has taken " + totalTime/1000;
    }
}