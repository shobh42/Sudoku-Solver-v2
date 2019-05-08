import java.util.HashSet;
import java.util.Set;

public class PuzzleSizeValidator {

    private Set<Integer> validPuzzleSize;

    public PuzzleSizeValidator(){
        validPuzzleSize = initializePuzzleSize();
    }

    public boolean isSizeIsValid(int puzzleSize){
        return validPuzzleSize.contains(puzzleSize);
    }

    private Set<Integer> initializePuzzleSize() {
        Set<Integer> allowedSize = new HashSet<>();
        for(int i = 2; i*i < 100; i++){
            allowedSize.add(i * i);
        }

        return allowedSize;
    }
}
