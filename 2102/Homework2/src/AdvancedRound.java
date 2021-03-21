import java.util.LinkedList;

public class AdvancedRound extends AbsRound{

    LinkedList<IContestant> prevWinners = new LinkedList<IContestant>();
    LinkedList<Match> matches = new LinkedList<Match>();
    LinkedList<IContestant> contestants = new LinkedList<IContestant>();

    public AdvancedRound(LinkedList<Match> matches, LinkedList<IContestant> contestants) {
        super(matches);
        this.contestants = contestants;
        this.prevWinners = super.getMatchWinners();
    }

    /**
     * Checks to see if contestant was won a match
     * @param contestant
     * @return boolean
     */
    public boolean isWinner(IContestant contestant){
        return prevWinners.contains(contestant);
    }


}
