// Class that represents the results of a Rugby match

public class RugbyResult implements IResult {

    double team1points;

    double team2points;

    RugbyTeam team1;

    RugbyTeam team2;

    public RugbyResult(RugbyTeam team1, RugbyTeam team2, double team1points, double team2points) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1points = team1points;
        this.team2points = team2points;
    }

    public boolean isValid() {  // determines if the match's results are valid and produces true if they are
        if (this.team1points < 150 && this.team2points < 150) {
            return true;
        } else {
            return false;
        }
    }

    public RugbyTeam getWinner() {
        if(this.team1points > this.team2points) {
            return this.team1;
        } else {
            return this.team2;
        }
    }
}
