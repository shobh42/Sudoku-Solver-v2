import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuPuzzleValidator {

    private List<Set<Character>> rows;
    private List<Set<Character>> cols;
    private List<Set<Character>> blocks;
    private Set<Character> validCandidates;
    private Cell[][] sudokuPuzzle;
    private int size;

    public SudokuPuzzleValidator(Set<Character> validCandidates, Cell[][] sudokuPuzzle, int size){
        this.validCandidates = validCandidates;
        this.sudokuPuzzle = sudokuPuzzle;
        this.size = size;
        initialize();
    }

    private void initialize() {
        rows = new ArrayList<>(size);
        cols = new ArrayList<>(size);
        blocks = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            blocks.add(new HashSet<>());
        }
    }


    public boolean isValid(){
        for(int i = 0; i < size; i++){

            for(int j = 0; j < size; j++){

                if(sudokuPuzzle[i][j].getSize() == 1){
                    char candidate = (char) sudokuPuzzle[i][j].getCandidates().toArray()[0];

                    boolean res1 = checkCandidatePresentInRow(i, candidate);
                    boolean res2 = checkCandidatePresentInColumn(j, candidate);
                    boolean res3 = checkCandidatePresentInBlock(i, j, candidate);
                    boolean res4 = checkCandidateIsValid(candidate);
                    if(res1 || res2  || res3 || res4){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean checkCandidateIsValid(char candidate) {
        return !validCandidates.contains(candidate);
    }

    private boolean checkCandidatePresentInBlock(int i, int j, char candidate) {
        int blockIndex = (i / (int)Math.sqrt(size)) * (int)Math.sqrt(size) + (j / (int)Math.sqrt(size));
        if(blocks.get(blockIndex).contains(candidate)){
            return true;
        }

        blocks.get(blockIndex).add(candidate);
        return false;
    }

    private boolean checkCandidatePresentInColumn(int j, char candidate) {
        if(cols.get(j).contains(candidate)){
            return true;
        }

        cols.get(j).add(candidate);
        return false;
    }

    private boolean checkCandidatePresentInRow(int i, char candidate) {
        if(rows.get(i).contains(candidate)){
            return true;
        }

        rows.get(i).add(candidate);
        return false;
    }
}
