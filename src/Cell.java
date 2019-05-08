import java.util.Set;

public class Cell<T> {

    private Set<T> possibleCandidate;

    public Cell(Set <T> possibleCandidate){
        this.possibleCandidate = possibleCandidate;
    }

    public int getSize(){
        return possibleCandidate.size();
    }

    public Set<T> getCandidates(){
        return possibleCandidate;
    }
}
