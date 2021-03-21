import java.util.LinkedList;

public class AdvancedRound extends AbsRound {

LinkedList<IContestant> contestants;
LinkedList<IContestant> winners;


    public AdvancedRound(LinkedList<Match> matches, LinkedList<IContestant> contestants){
        super(matches);
        this.contestants = contestants;
         this.winners = super.getMatchWinners();
    }


    public boolean isWinner(IContestant contestant){

        return winners.contains(contestant);
    }
}
