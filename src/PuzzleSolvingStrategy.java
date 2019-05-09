import java.util.List;

public abstract class PuzzleSolvingStrategy {

    public final boolean solve(Cell[][] sudokuPuzzle){

        List<CellCoordinate> cellWithCandidate = findCellCoordinates(sudokuPuzzle);
        List<CellCoordinate> cellContainingCandidate = checkCandidateIsPresent(cellWithCandidate, sudokuPuzzle);
        return removeTheCandidate(cellContainingCandidate);
    }

    public abstract List<CellCoordinate> findCellCoordinates(Cell[][] sudokuPuzzle);

    public abstract List<CellCoordinate> checkCandidateIsPresent(List<CellCoordinate> cellWithCandidate, Cell[][] sudokuPuzzle);

    public abstract boolean removeTheCandidate(List<CellCoordinate> cellContainingCandidate);
}
