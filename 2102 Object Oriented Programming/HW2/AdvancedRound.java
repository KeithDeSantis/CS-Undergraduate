import java.util.LinkedList;

/**
 * An advanced round in a tournament, meaning the second, third, etc. rounds.
 */
public class AdvancedRound extends AbsRound{

    LinkedList<IContestant> contestants; // winners from previous round

    public AdvancedRound(LinkedList<Match> matches, LinkedList<IContestant> contestants) {
        super(matches);
        this.contestants = contestants;
    }

    /**
     * Checks if a team is among the winners from the previous round (is in the list for this round).
     * @param team The team being checked.
     * @return True if the team is in the list of contestants for this round, therefore won a previous match
     */
    public boolean isWinner(IContestant team) {
        return this.contestants.contains(team);
    }

    /**
     * Adds a contestant to the list of previous winners.
     * @param aContestant The contestant being added.
     * @return The advanced round with the new contestant added to its list.
     */
    public AdvancedRound addContestant(IContestant aContestant) {
        this.contestants.add(aContestant);
        return this;
    }
}
