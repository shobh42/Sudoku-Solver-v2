import java.util.List;

public abstract class PuzzleSolvingStrategy {

    public final boolean solve(Cell[][] sudokuPuzzle){

        List<CellCoordinate> cellToUseForElimination = findCellCoordinates(sudokuPuzzle);
        List<CellCoordinate> cellToUpdate = findCandidateCellCoordinates(cellToUseForElimination, sudokuPuzzle);
        return removeTheCandidate(cellToUpdate, sudokuPuzzle);
    }

    public abstract List<CellCoordinate> findCellCoordinates(Cell[][] sudokuPuzzle);

    public abstract List<CellCoordinate> findCandidateCellCoordinates(List<CellCoordinate> cellWithCandidate, Cell[][] sudokuPuzzle);

    public abstract boolean removeTheCandidate(List<CellCoordinate> cellContainingCandidate, Cell[][] sudokuPuzzle);
}
