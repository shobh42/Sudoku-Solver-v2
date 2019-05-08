import java.util.List;

public abstract class PuzzleSolvingStrategy {

    public final void solve(Cell[][] sudokuPuzzle){

        List<Cell> cellToBeFilled = findCells(sudokuPuzzle);
        //findTheCell();
        //checkTheValueIsPresent();
        //removeTheValue();
    }

    public abstract List<Cell>findCells(Cell[][] sudokuPuzzle);
}
