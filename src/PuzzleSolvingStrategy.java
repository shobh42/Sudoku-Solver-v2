public class PuzzleSolvingStrategy {

    public final void solve(Cell[][] puzzle){

        loopThroughAllCell();
        findTheCell();
        checkTheValueIsPresent();
        removeTheValue();
    }
}
