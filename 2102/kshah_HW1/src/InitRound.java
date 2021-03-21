import java.util.LinkedList;

public class InitRound extends AbsRound {


    public InitRound(LinkedList<Match> matches) {
        super(matches);
    }


    public boolean isWinner(IContestant contestant){
        return getMatchWinners().contains(contestant);
    }


}
