import java.util.ArrayList;
import java.util.List;

public class RowEliminationStrategy extends EliminationStrategy
{
    private int count;
    private double totalTime;

    public RowEliminationStrategy(){
        count = 0;
        totalTime = 0;
    }

    public int getCount() {
        return count;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public String toString(){
        return "Row Elimination has eliminated " + count + " and has taken " + totalTime/1000;
    }

    @Override
    public List<Cell> findCells(Cell[][] sudokuPuzzle) {
        List<Cell> cellWithSizeOne = new ArrayList<>();
        for(int row = 0; row < sudokuPuzzle.length; row++){

            for(int col = 0; col < sudokuPuzzle.length; col++){

                if(sudokuPuzzle[row][col].getSize() == 1){
                    cellWithSizeOne.add(sudokuPuzzle[row][col]);
                }
            }
        }

        return cellWithSizeOne;
    }
}
