import java.util.List;

public abstract class PuzzleSolvingStrategy {

    public final boolean solve(SudokuPuzzle sudokuPuzzle){

        List<CellCoordinate> cellToUseForElimination = findCellCoordinates(sudokuPuzzle);
        List<CellCoordinate> cellToUpdate = findCandidateCellCoordinates(cellToUseForElimination, sudokuPuzzle);
        return removeTheCandidate(cellToUpdate, sudokuPuzzle);
    }

    public abstract List<CellCoordinate> findCellCoordinates(SudokuPuzzle sudokuPuzzle);

    public abstract List<CellCoordinate> findCandidateCellCoordinates(List<CellCoordinate> cellWithCandidate, SudokuPuzzle sudokuPuzzle);

    public abstract boolean removeTheCandidate(List<CellCoordinate> cellContainingCandidate, SudokuPuzzle sudokuPuzzle);
}
