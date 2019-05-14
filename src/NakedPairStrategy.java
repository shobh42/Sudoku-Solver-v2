import java.util.*;


/**
 * If any two cell in row, column or block has the same pair of values of size 2, then the rest of the corresponding row, column
 * block does not supposed to have those value. This strategy eliminates those values from the rest of the cell
 */
public class NakedPairStrategy extends PuzzleSolvingStrategy{

    private int count;
    private double totalTime;

    public NakedPairStrategy(){
        count = 0;
        totalTime = 0;
    }

    public int getCount() {
        return count;
    }

    public double getTotalTime() {
        return totalTime;
    }

    private List<CellCoordinate> removeValuesFromColumn(int r, int c, Cell[][] puzzle, Set<Character> values) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        int size = puzzle.length;
        for(int r1 = 0; r1 < size; r1++){
            Set<Character> cellFromValuesToRemove = puzzle[r1][c].getCandidates();
            if(r != r1 && cellFromValuesToRemove.size() > 2 ){
                char value1 = (Character)values.toArray()[0];
                char value2 = (Character)values.toArray()[1];
                if(cellFromValuesToRemove.contains(value1)){
                    cellToUpdate.add(new CellCoordinate(r1, c, value1));
                }

                if(cellFromValuesToRemove.contains(value2)){
                    cellToUpdate.add(new CellCoordinate(r1, c, value2));
                }
            }
        }

        return cellToUpdate;
    }

    private List<CellCoordinate> getTheCellFromRow(int r, int c, Cell[][] puzzle, Set<Character> values) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        int size = puzzle.length;
        for(int c1 = 0; c1 < size; c1++){
            Set<Character> cellFromValuesToRemove = puzzle[r][c1].getCandidates();
            if(c != c1 && cellFromValuesToRemove.size() > 2){
                char value1 = (Character)values.toArray()[0];
                char value2 = (Character)values.toArray()[1];
                if(cellFromValuesToRemove.contains(value1)){
                    cellToUpdate.add(new CellCoordinate(r, c1, value1));
                }

                if(cellFromValuesToRemove.contains(value2)){
                    cellToUpdate.add(new CellCoordinate(r, c1, value2));
                }
            }
        }

        return cellToUpdate;
    }

    private List<CellCoordinate> getTheCellFromBlock(int r, int c, Cell[][] puzzle, Set<Character> values) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        for(int r1 = (r/sqrtOfSize)*sqrtOfSize ; r1 < ((r/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; r1++){

            for(int c1 = (c/sqrtOfSize)*sqrtOfSize ; c1 < ((c/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; c1++){
                Set<Character> cellFromValuesToRemove = puzzle[r1][c1].getCandidates();

                if(r1 != r && c1!=c && cellFromValuesToRemove.size() > 2){
                    char value1 = (Character)values.toArray()[0];
                    char value2 = (Character)values.toArray()[1];
                    if(cellFromValuesToRemove.contains(value1)){
                        cellToUpdate.add(new CellCoordinate(r1, c1, value1));
                    }

                    if(cellFromValuesToRemove.contains(value2)){
                        cellToUpdate.add(new CellCoordinate(r1, c1, value2));
                    }
                }
            }
        }

        return cellToUpdate;
    }

    private boolean checkIfColumnHasPair(int r, int c, Cell[][] puzzle, Set<Character> values) {
        int size = puzzle.length;
        int countCellsWithSamePairs = 1;

        for(int r1 = 0; r1<size ; r1++){
            Set <Character> possibleValues = puzzle[r1][c].getCandidates();
            if(r1!=r && puzzle[r1][c].getSize()==2){
                if(values.toArray()[0]==possibleValues.toArray()[0] && values.toArray()[1]==possibleValues.toArray()[1]){
                    countCellsWithSamePairs++;
                }
            }
        }
        return (countCellsWithSamePairs == 2);
    }

    private boolean checkIfRowHasPair(int r, int c, Cell[][] puzzle, Set<Character> values) {
        int size = puzzle.length;
        int countCellsWithSamePairs = 1;

        for(int c1 = 0; c1<size ; c1++){

            Set <Character> possibleValues = puzzle[r][c1].getCandidates();
            if(c1!=c && puzzle[r][c1].getSize()==2){
                if(values.toArray()[0]==possibleValues.toArray()[0] && values.toArray()[1]==possibleValues.toArray()[1]){
                    countCellsWithSamePairs++;
                }
            }
        }
        return (countCellsWithSamePairs == 2);
    }


    private boolean checkIfBlockHasPair(int currentRowOfPair , int currentColumnOfPair, Cell[][]puzzle, Set<Character> values) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        int countCellsWithSamePairs = 1;
        for(int rowToCheck = (currentRowOfPair/sqrtOfSize)*sqrtOfSize ; rowToCheck < ((currentRowOfPair/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; rowToCheck++){

            for(int colToCheck = (currentColumnOfPair/sqrtOfSize)*sqrtOfSize   ; colToCheck < ((currentColumnOfPair/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; colToCheck++){
                Set <Character> possibleValues = puzzle[rowToCheck][colToCheck].getCandidates();

                if(rowToCheck != currentRowOfPair && colToCheck!=currentColumnOfPair && possibleValues.size()==2 ){

                    if(values.toArray()[0]==possibleValues.toArray()[0] && values.toArray()[1]==possibleValues.toArray()[1]){
                        countCellsWithSamePairs++;
                    }

                }
            }
        }

        return (countCellsWithSamePairs == 2);
    }

    private void printPuzzle(Cell [][] sudokuPuzzle){
        for (int i = 0; i < sudokuPuzzle.length; i++){

            for(int j = 0; j < sudokuPuzzle.length; j++){
                System.out.print(sudokuPuzzle[i][j].getCandidates()+ " ");
            }

            System.out.println();
        }

        System.out.println("-----------------------------------------");
    }

    public String toString(){
        return "Naked Pair has eliminated " + count + " and has taken " + totalTime/1000;
    }

    @Override
    public List<CellCoordinate> findCellCoordinates(Cell[][] sudokuPuzzle) {
        System.out.println("Inside Naked Pair Strategy");
        List<CellCoordinate> cellWithSizeTwo = new ArrayList<>();
        for(int row = 0; row < sudokuPuzzle.length; row++) {

            for (int col = 0; col < sudokuPuzzle.length; col++) {

                Cell cell = sudokuPuzzle[row][col];
                if (cell.getCandidates().size() == 2) {
                    cellWithSizeTwo.add(new CellCoordinate(row, col));
                }
            }
        }

        return cellWithSizeTwo;
    }

    @Override
    public List<CellCoordinate> checkCandidateIsPresent(List<CellCoordinate> cellToUseForElimination, Cell[][] sudokuPuzzle) {
        List<CellCoordinate> cellToUpdate = new ArrayList<>();
        for(CellCoordinate cell: cellToUseForElimination) {

            int row = cell.getRow();
            int col = cell.getCol();
            Set<Character> candidates = sudokuPuzzle[row][col].getCandidates();
            boolean isCandidatePresentInBlock = checkIfBlockHasPair(row, col, sudokuPuzzle, candidates);
            boolean isCandidatePresentInRow = checkIfRowHasPair(row, col, sudokuPuzzle, candidates);
            boolean isCandidatePresentInCol = checkIfColumnHasPair(row, col, sudokuPuzzle, candidates);
            if (isCandidatePresentInBlock) {
                cellToUpdate = getTheCellFromBlock(row, col, sudokuPuzzle, candidates);
            }
            if (isCandidatePresentInRow) {
                cellToUpdate = getTheCellFromRow(row, col, sudokuPuzzle, candidates);
            }
            if (isCandidatePresentInCol) {
                cellToUpdate = removeValuesFromColumn(row, col, sudokuPuzzle, candidates);
            }
        }

        return cellToUpdate;
    }

    @Override
    public boolean removeTheCandidate(List<CellCoordinate> cellContainingCandidate, Cell[][] sudokuPuzzle) {
        boolean stateChanged = false;
        for (CellCoordinate cell: cellContainingCandidate){
            int row = cell.getRow();
            int col = cell.getCol();
            if(sudokuPuzzle[row][col].getSize() == 1){
                continue;
            }

            char candidate = (char) cell.getCandidate();
            sudokuPuzzle[row][col].getCandidates().remove(candidate);
            stateChanged = true;
            if(sudokuPuzzle[row][col].getSize() == 1){
                count++;
            }
        }

        return stateChanged;
    }
}
