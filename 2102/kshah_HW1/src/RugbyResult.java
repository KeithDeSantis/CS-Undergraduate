public class RugbyResult implements IResult {

    IContestant team1;
    IContestant team2;
    double team1points;
    double team2points;


    public RugbyResult(RugbyTeam team1, RugbyTeam team2,
                       double team1points, double team2points) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1points = team1points;
        this.team2points = team2points;
    }


    public boolean isValid() {
        return team1points < 150 && team2points < 150;
    }

    public IContestant getWinner() {
        if (isValid() && team1points == team2points) {
            return null;

        } else if (isValid() && Math.max(team1points, team2points) == team1points) {
            return team1;
        } else if (isValid() && Math.max(team1points, team2points) == team2points) {
            return team2;
        }
        return null;

    }

}
