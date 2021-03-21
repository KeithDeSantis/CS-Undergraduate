import java.util.LinkedList;

public class Tournament {
    LinkedList<IWinner> rounds = new LinkedList<IWinner>();
    public Tournament(LinkedList<IWinner> rounds) {
        this.rounds =rounds;
    }

    /**
     * Determine if a contestant has won at least half of the matches
     * @param contestant
     * @return boolean
     */
    public boolean finalWinnerIsValid(IContestant contestant){
        int numRounds = rounds.size();
        int roundsWon = 0;
        for (IWinner round : rounds) {
            if (round.isWinner(contestant)){
                roundsWon ++;
            }
        }
        return roundsWon >= numRounds/2;
    }
}
