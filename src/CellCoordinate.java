public class CellCoordinate<T> {

    private int row;
    private int col;
    private T candidate;

    public CellCoordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public CellCoordinate(int row, int col, T candidate) {
        this.row = row;
        this.col = col;
        this.candidate = candidate;

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public T getCandidate() {
        return candidate;
    }
}
