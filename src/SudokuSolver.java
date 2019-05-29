import java.io.IOException;
import java.util.*;

public class SudokuSolver {

    private SudokuPuzzle sudokuPuzzle;
    private List<PuzzleSolvingStrategy> solvingStrategies;
    private SudokuPuzzleGenerator puzzleGenerator;
    private List<SolvedPuzzle> solvedPuzzle;
    private int count = 0;
    private boolean toPrint = false;

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
        //System.out.println("COUNT IS "+count);
        return solvedPuzzle;
    }

    private void solvePuzzle() {
        while(sudokuPuzzle.getState() == SudokuState.UNSOLVED){
            if(!solvePuzzleUsingStrategy()){
                solvePuzzleUsingGuessStrategy();
            }
        }

//        if(sudokuPuzzle.getState() == SudokuState.SOLVED){
//            if(new SudokuPuzzleValidator(puzzleGenerator.getValidCharacters(), sudokuPuzzle.getSudokuPuzzle()).isValid()){
//                count++;
//                solvedPuzzle.add(new SolvedPuzzle(sudokuPuzzle.getSudokuPuzzle(), solvingStrategies));
//                System.out.println("FOUND THE SOLUTION");
//                //return;
//            }
//        }

        //System.out.println("TIME TO BACKTRACK");
        //return;
    }

    private void solvePuzzleUsingGuessStrategy(){
        //GuessStrategy solver = new GuessStrategy(sudokuPuzzle);
        System.out.println("Inside Brute Force");
        //Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        SudokuPuzzle sudokuPuzzleCopy = new SudokuPuzzle(sudokuPuzzle);
        Cell[][] puzzle = sudokuPuzzle.getSudokuPuzzle();
        Cell[][] toRestore = sudokuPuzzleCopy.getSudokuPuzzle();
        int size = puzzle.length;

        for(int row = 0; row < size; row++){

            for(int col = 0; col < size; col++){

                Cell candidateCell = puzzle[row][col];
                if(candidateCell.getSize() > 1){
                    //Set<Character> candidates = puzzle[row][col].getCandidates();
                    Object[] cand = puzzle[row][col].getCandidates().toArray();
                    Character[] candidatesArray = new Character[cand.length];
                    for(int temp = 0; temp < cand.length; temp++){
                        candidatesArray[temp] = (Character) cand[temp];
                    }

                    for(int i = 0; i < candidatesArray.length; i++){
                        Set<Character> s = new HashSet<>();
                        s.add(candidatesArray[i]);
                        System.out.println("putting "+candidatesArray[i] + " at " + row + "-" + col);
                        if(row == 0 && col == 4){

                            if(candidatesArray[i] == '3'){
                                //System.out.println("putting "+candidatesArray[i] + " at " + row + "-" + col);
                                toPrint = true;

//                                try{
//                                    Thread.sleep(2500);
//                                }catch (InterruptedException e){
//
//                                }

                            }
                        }

                        puzzle[row][col] = new Cell(s);
                        if(toPrint){
                            printPuzzle();
                        }

                        sudokuPuzzle.updateRemainingCell();
                        //printPuzzle();
                        solvePuzzle();
                        //System.out.println("Candidates are : "+candidates);
                        //System.out.println("Restoring");

                        sudokuPuzzle = sudokuPuzzleCopy;
                        puzzle = toRestore;
                        //printPuzzle(puzzle);
                        //sudokuPuzzle.restoreRemainingCell();
                    }
                }
            }
        }
        //sudokuPuzzle = temporary;

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

            //printPuzzle();
            if(toPrint){
                printPuzzle();
                try{
                    Thread.sleep(2500);
                }catch (InterruptedException e){

                }
            }

            if(sudokuPuzzle.getState() == SudokuState.SOLVED){
                //printPuzzle();
                if(new SudokuPuzzleValidator(puzzleGenerator.getValidCharacters(), sudokuPuzzle.getSudokuPuzzle()).isValid()) {
                    count++;
                    solvedPuzzle.add(new SolvedPuzzle(sudokuPuzzle.getSudokuPuzzle(), solvingStrategies));
                    System.out.println("FOUND THE SOLUTION");
                    //return;
                    break;
                }
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

    private void printPuzzle(Cell[][] puzzle){
        System.out.println("REstroing ouzzle");
        for (int i = 0; i < puzzle.length; i++){

            for(int j = 0; j < puzzle.length; j++){
                System.out.print(puzzle[i][j].getCandidates()+ " ");
            }

            System.out.println();
        }

        System.out.println("-----------------------------------------");
    }
}
