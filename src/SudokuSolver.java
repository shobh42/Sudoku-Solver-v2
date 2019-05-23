import java.io.IOException;
import java.util.*;

public class SudokuSolver {

    private SudokuPuzzle sudokuPuzzle;
    private List<PuzzleSolvingStrategy> solvingStrategies;
    private SudokuPuzzleGenerator puzzleGenerator;
    private List<SolvedPuzzle> solvedPuzzle;

    public SudokuSolver(){
        this.puzzleGenerator = new SudokuPuzzleGenerator();
        solvedPuzzle = new ArrayList<>();
        initializeStrategy();
    }

    private void initializeStrategy() {
        solvingStrategies = Arrays.asList(new RowEliminationStrategy(), new ColumnEliminationStrategy(),
                new BlockEliminationStrategy(), new HiddenSingleStrategy(), new BoxReductionStrategy(),
                new NakedPairStrategy());
    }


    public List<SolvedPuzzle> solve(String path) throws InvalidPuzzleException, IOException, IllegalCharacterException {
        sudokuPuzzle = puzzleGenerator.generatePuzzle(path);
        solvePuzzle();
        return solvedPuzzle;
    }

    private void solvePuzzle() {
        while(sudokuPuzzle.getState() == SudokuState.UNSOLVED){
            if(!solvePuzzleUsingStrategy()){
                solvePuzzleUsingGuessStrategy();
            }
        }

        if(new SudokuPuzzleValidator(puzzleGenerator.getValidCharacters(), sudokuPuzzle.getSudokuPuzzle()).isValid()){
            solvedPuzzle.add(new SolvedPuzzle(sudokuPuzzle.getSudokuPuzzle(), solvingStrategies));
            System.out.println("FOUND THE SOLUTION");
        }

        System.out.println("TIME TO BACKTRACK");
        return;
    }

    private void solvePuzzleUsingGuessStrategy() {
        //GuessStrategy solver = new GuessStrategy(sudokuPuzzle);
        System.out.println("Inside Brute Force");
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        int size = puzzle.length;
        for(int row = 0; row < size; row++){

            for(int col = 0; col < size; col++){

                Cell candidateCell = puzzle[row][col];
                if(candidateCell.getSize() > 1){
                    Set<Character> candidates = puzzle[row][col].getCandidates();
                    Object[] cand = puzzle[row][col].getCandidates().toArray();
                    Character[] candidatesArray = new Character[cand.length];
                    for(int temp = 0; temp < cand.length; temp++){
                        candidatesArray[temp] = (Character) cand[temp];
                    }

                    for(int i = 0; i < candidatesArray.length; i++){
                        Set<Character> s = new HashSet<>();
                        s.add(candidatesArray[i]);
                        puzzle[row][col] = new Cell(s);
                        sudokuPuzzle.updateRemainingCell();
                        printPuzzle();
                        solvePuzzle();
                        puzzle[row][col] = new Cell(candidates);
                        sudokuPuzzle.restoreRemainingCell();
                    }
                }
            }
        }
        //solver.solve();

    }

    private boolean solvePuzzleUsingStrategy(){
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        int strategyNumber = 0;
        while(sudokuPuzzle.getState() == SudokuState.UNSOLVED && strategyNumber < solvingStrategies.size()){
            if(solvingStrategies.get(strategyNumber).solve(sudokuPuzzle)){
                if(strategyNumber != 0){
                    strategyNumber = 0;
                }else{
                    strategyNumber++;
                }

            }else{
                strategyNumber++;
            }

            printPuzzle();
            if(sudokuPuzzle.getState() == SudokuState.SOLVED){
                solvedPuzzle.add(new SolvedPuzzle(puzzle, solvingStrategies));
                break;
            }
        }

        return sudokuPuzzle.getState() == SudokuState.SOLVED;
    }

    private void printPuzzle(){
        for (int i = 0; i < sudokuPuzzle.getSudokuPuzzle().length; i++){

            for(int j = 0; j < sudokuPuzzle.getSudokuPuzzle().length; j++){
                System.out.print(sudokuPuzzle.getSudokuPuzzle()[i][j].getCandidates()+ " ");
            }

            System.out.println();
        }

        System.out.println("-----------------------------------------");
    }
}
