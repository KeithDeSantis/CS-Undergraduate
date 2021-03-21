import java.util.LinkedList;

public class Tournament {

    LinkedList<IWinner> allRounds = new LinkedList();

    public Tournament(LinkedList<IWinner> rounds) {
        this.allRounds = rounds;
    }


    public boolean finalWinnerIsValid(IContestant contestant) {
        int numRounds = allRounds.size();
        int winCount = 0;

        for (IWinner round : allRounds) {
            if (round.isWinner(contestant)) {
                winCount++;
            }
        }
        return winCount >= numRounds / 2;
    }
}
