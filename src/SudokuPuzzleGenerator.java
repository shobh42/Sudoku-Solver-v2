import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SudokuPuzzleGenerator {

    public Cell[][] generatePuzzle(String filePath)
            throws IllegalCharacterException, IOException, InvalidPuzzleException {
        //Puzzle-9x9-0401.txt
        //Puzzle-9x9-0901
        //Puzzle-9x9-0101.txt
        File file = new File("C:\\Users\\Shobhit\\Desktop\\repos\\Sudoku-Solver\\src\\puzzles\\Puzzle-25x25-0903.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        final int PUZZLE_SIZE = Integer.valueOf(reader.readLine());
        if(!new PuzzleSizeValidator().isSizeIsValid(PUZZLE_SIZE)) {
            throw new InvalidPuzzleException("Puzzle size " + PUZZLE_SIZE + " is not a valid size");
        }

        Set<Character> validCharacters = generateValidCandidates(PUZZLE_SIZE, reader);
        Cell[][] puzzle = generatePuzzleUtil(PUZZLE_SIZE, reader, validCharacters);
        boolean isPuzzleValid = new SudokuPuzzleValidator(validCharacters, puzzle, PUZZLE_SIZE).isValid();
        if(!isPuzzleValid){
            throw new InvalidPuzzleException("Sudoku Puzzle is not valid");
        }

        return puzzle;
    }

    private Set<Character> generateValidCandidates(int size, BufferedReader reader) throws IOException {
        Set<Character> validCharSet = new HashSet<>();
        String validCharLine = reader.readLine();
        String []values = validCharLine.split(" ");
        for(int i = 0; i < size; i++){
            validCharSet.add(values[i].charAt(0));
        }

        return validCharSet;
    }

    private Cell[][] generatePuzzleUtil(int size, BufferedReader reader, Set<Character> validCharacters) throws IOException, IllegalCharacterException {
        Cell[][] puzzle = new Cell[size][size];
        String line;
        int row = 0;
        while ((line = reader.readLine()) != null){
            String []values = line.split(" ");
            for(int col = 0; col < size; col++){
                Set<Character> possibleValues = new HashSet<>();

                if(values[col].equals("-")){
                    for(Character value : validCharacters){
                        possibleValues.add(value);
                    }
                }else{
                    if(!validCharacters.contains(values[col].charAt(0))){
                        throw new IllegalCharacterException("Valid character list does not have "+ values[col]);
                    }

                    possibleValues.add(values[col].charAt(0));
                }

                Cell cell = new Cell(possibleValues);
                puzzle[row][col] = cell;
            }

            row++;
        }

        return puzzle;
    }
}
