import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        while(sudokuPuzzle.getState() == SudokuState.UNSOLVED){
            if(!solvePuzzleUsingStrategy()){
                solvePuzzleUsingGuessStrategy();
            }
        }

        return solvedPuzzle;
    }

    private void solvePuzzleUsingGuessStrategy() {
        GuessStrategy solver = new GuessStrategy(sudokuPuzzle);
        solver.solve();
        printPuzzle();
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
