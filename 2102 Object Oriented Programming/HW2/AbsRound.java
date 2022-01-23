import java.util.LinkedList;

/**
 * Represents the abstract idea of a round in a tournament, not specifying level.
 */
public abstract class AbsRound implements IWinner{

    LinkedList<Match> matches;

    public AbsRound(LinkedList<Match> matches) {
        this.matches = matches;
    }

    /**
     * Gets all the winners from each match in the round.
     * @return A list of all the winners.
     */
    public LinkedList<IContestant> getMatchWinners() {
        LinkedList<IContestant> winners = new LinkedList<IContestant>();
        for(Match aMatch : this.matches) {
            winners.add(aMatch.winner());
        }
        return winners;
    }

    /**
     * Counts how many winners there are in a round.
     * @return The number of winners.
     */
    public int getNumWinners() {
        int counter = 0;
        for(IContestant aWinner : this.getMatchWinners()) {
            counter ++;
        }
        return counter;
    }

    /**
     * Adds a match to a round.
     * @param aMatch The match being added.
     * @return The round with the new match added to its list.
     */
    public AbsRound addMatch(Match aMatch) {
        this.matches.add(aMatch);
        return this;
    }
}
