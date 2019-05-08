import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuSolver {

    private Cell[][] sudokuPuzzle;
    private List<SolvingStrategy> solvingStrategies;
    private SudokuPuzzleGenerator puzzleGenerator;
    private List<SolvedPuzzle> solvedPuzzle;
    private Enum state;

    public SudokuSolver(){
        this.puzzleGenerator = new SudokuPuzzleGenerator();
        solvedPuzzle = new ArrayList<>();
        state = SudokuState.UNSOLVED;
        initializeStrategy();
    }

    private void initializeStrategy() {
        solvingStrategies = Arrays.asList(new RowEliminationStrategy(), new ColumnEliminationStrategy(),
                new BlockEliminationStrategy(), new HiddenSingleStrategy(), new BoxReductionStrategy(), new NakedPairStrategy());

    }


    public List<SolvedPuzzle> solve(String path) throws InvalidPuzzleException, IOException, IllegalCharacterException {
        sudokuPuzzle = puzzleGenerator.generatePuzzle(path);
        while(state == SudokuState.UNSOLVED){
            if(!solvePuzzleUsingStrategy()){
                solvePuzzleUsingBruteForce();
            }
        }

        return solvedPuzzle;
    }

    private void solvePuzzleUsingBruteForce() {
        BruteForceSolver solver = new BruteForceSolver(sudokuPuzzle);
        solver.solve();
        printPuzzle();
    }

    private boolean solvePuzzleUsingStrategy(){
        int size = sudokuPuzzle.length;
        int strategyNumber = 0;
        boolean puzzleIsSolved = false;
        while(!puzzleIsSolved && strategyNumber < solvingStrategies.size()){
            if(solvingStrategies.get(strategyNumber).solve(size, sudokuPuzzle)){
                strategyNumber = 0;
            }else{
                strategyNumber++;
            }

            printPuzzle();
            puzzleIsSolved = isPuzzleSolved();
            if(puzzleIsSolved){
                state = SudokuState.SOLVED;
                puzzleIsSolved = true;
                solvedPuzzle.add(new SolvedPuzzle(sudokuPuzzle, solvingStrategies));
                break;
            }
        }

        return puzzleIsSolved;
    }

    private boolean isPuzzleSolved() {

        for(int i = 0; i < sudokuPuzzle.length; i++){

            for(int j = 0; j < sudokuPuzzle.length; j++){

                Cell currentCell =sudokuPuzzle[i][j];
                //System.out.print(currentCell.getCandidates() + " ");
                if(currentCell.getSize() != 1){
                    return false;
                }

            }

            //System.out.println();
        }

        return true;
    }

    private void printPuzzle(){
        for (int i = 0; i < sudokuPuzzle.length; i++){

            for(int j = 0; j < sudokuPuzzle.length; j++){
                System.out.print(sudokuPuzzle[i][j].getCandidates()+ " ");
            }

            System.out.println();
        }

        System.out.println("-----------------------------------------");
    }
}
