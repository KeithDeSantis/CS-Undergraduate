public class Match {

    IContestant team1;
    IContestant team2;
    IResult result;

    public Match(IContestant team1, IContestant team2, IResult result) {
        this.team1 = team1;
        this.team2 = team2;
        this.result = result;

    }


    public IContestant winner() {
        if (!result.isValid()) {
            return null;
        }

        return result.getWinner();
    }

}

