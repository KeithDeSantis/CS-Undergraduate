import java.util.LinkedList;

public abstract class AbsRound implements IWinner{

    LinkedList<Match> matches = new LinkedList();


    public AbsRound(LinkedList<Match> matches){
        this.matches = matches;

    }

    public LinkedList getMatchWinners(){
        LinkedList<IContestant> winners = new LinkedList<IContestant>();
        for(Match match : matches){
            if(match.winner()!=null){
                winners.add(match.winner());
            }
        }
        return winners;
    }

    public int getNumWinners(){
        return getMatchWinners().size();
    }

    public abstract boolean isWinner(IContestant contestant);

}
