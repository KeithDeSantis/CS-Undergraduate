import java.util.LinkedList;

/**
 * A tournament with multiple rounds.
 */
public class Tournament {

    LinkedList<IWinner> rounds;

    public Tournament(LinkedList<IWinner> rounds) {
        this.rounds = rounds;
    }

    /**
     * Checks if a given contestant is a valid winner to the tournament.
     * @param team The contestant being checked.
     * @return True if the contestant had won at least half of the tournament's rounds and is always a valid previous winner in advanced rounds.
     */
    public boolean finalWinnerIsValid(IContestant team) {
        int numWins = 0;
        for(IWinner round : this.rounds) {
            if(round.isWinner(team) && round.getMatchWinners().contains(team)) {
                numWins++;
            }
        }
        if(numWins >= this.rounds.size()/2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a round to a tournament.
     * @param aRound The round being added.
     * @return The tournament with the new round added.
     */
    public Tournament addRound(IWinner aRound) {
        this.rounds.add(aRound);
        return this;
    }
}
