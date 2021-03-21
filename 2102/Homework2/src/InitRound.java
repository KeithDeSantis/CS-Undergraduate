import java.util.LinkedList;

public class InitRound extends AbsRound{
    public InitRound(LinkedList<Match> matches) {
        super(matches);
    }


    /**
     * Checks to see if contestant has won a match
     * @param contestant
     * @return boolean
     */
    public boolean isWinner(IContestant contestant) {
        return getMatchWinners().contains(contestant);
    }
}
