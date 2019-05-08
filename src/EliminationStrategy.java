import java.util.List;

public abstract class EliminationStrategy extends PuzzleSolvingStrategy {


    @Override
    public abstract List<Cell> findCells(Cell[][] sudokuPuzzle);
}
