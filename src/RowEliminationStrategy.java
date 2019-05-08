public class RowEliminationStrategy implements SolvingStrategy
{
    private int count;
    private double totalTime;

    public RowEliminationStrategy(){
        count = 0;
        totalTime = 0;
    }

    @Override
    public boolean solve(int size, Cell[][] puzzle) {

        System.out.println("In Row strategy");
        boolean stateChanged = false;
        long startTime = System.currentTimeMillis();
        for(int candidateRow = 0; candidateRow < size; candidateRow++){

            for(int candidateColumn = 0; candidateColumn < size; candidateColumn++){

                Cell candidateCell = puzzle[candidateRow][candidateColumn];
                if(candidateCell.getSize() == 1){
                    char candidate = (char) candidateCell.getCandidates().toArray()[0];
                    for(int otherCandidateCol = 0; otherCandidateCol < size; otherCandidateCol++){

                        if(otherCandidateCol != candidateColumn && puzzle[candidateRow][otherCandidateCol].getSize() > 1
                                && puzzle[candidateRow][otherCandidateCol].getCandidates().contains(candidate)){

                            puzzle[candidateRow][otherCandidateCol].getCandidates().remove(candidate);
                            stateChanged = true;
                            count++;
                        }
                    }
                }
            }
        }

        long endTime = System.currentTimeMillis();
        totalTime+= (endTime - startTime);
        return stateChanged;
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
}
