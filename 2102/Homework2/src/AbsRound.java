import java.util.LinkedList;

public abstract class AbsRound implements IWinner {
    LinkedList<Match> matches = new LinkedList<Match>();

    public AbsRound(LinkedList<Match> matches) {
        this.matches = matches;
    }

    public LinkedList<IContestant> getMatchWinners() {
        LinkedList<IContestant> winners = new LinkedList<IContestant>();
        for (Match m : matches) {
            if (m.winner() != null) {
                winners.add(m.winner());
            }
        }
        return winners;
    }

    /**
     * Get the number of winners
     *
     * @return int
     */
    public int getNumWinners() {
        return getMatchWinners().size();
    }

    /**
     * Checks to see if contestant was won a match
     *
     * @param contestant
     * @return boolean
     */
    public abstract boolean isWinner(IContestant contestant);

}
