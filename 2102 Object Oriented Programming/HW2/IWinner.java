import java.util.LinkedList;

/**
 * Represents a round in a tournament.
 */
public interface IWinner {

    public LinkedList<IContestant> getMatchWinners();

    public boolean isWinner(IContestant team);
}
