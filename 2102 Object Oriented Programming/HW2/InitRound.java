import java.util.LinkedList;

/**
 * Represents an initial round in a tournament.
 */
public class InitRound extends AbsRound{

    public InitRound(LinkedList<Match> matches) {
        super(matches);
    }

    /**
     * Checks if a contestant won a match in the round.
     * @param player The contestant whose record is being checked.
     * @return True if the contestant won a match in the round.
     */
    public boolean isWinner(IContestant player) {
        boolean checker = false;
        for(Match thematch : this.matches) {
            if(thematch.winner().equals(player)) {
                checker = true;
            }
        }
        return checker;
    }
}
