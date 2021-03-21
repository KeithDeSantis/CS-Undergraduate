public class RoboticsResult implements IResult {

    IContestant team1;
    IContestant team2;
    double team1points;
    double team2points;
    double team1score;
    double team2score;
    int team1tasks;
    int team2tasks;
    boolean team1fell;
    boolean team2fell;

    public RoboticsResult(RoboticsTeam team1, RoboticsTeam team2,
                          double team1points, int team1tasks, boolean team1fell,
                          double team2points, int team2tasks, boolean team2fell) {
        this.team1 = team1;
        this.team1points = team1points;
        this.team1tasks = team1tasks;
        this.team1fell = team1fell;
        this.team1score = getScore(team1points, team1tasks, team1fell);
        this.team2score = getScore(team2points, team2tasks, team2fell);
        this.team2 = team2;
        this.team2points = team2points;
        this.team2tasks = team2tasks;
        this.team2fell = team2fell;

    }


    public boolean isValid() {
        return team1tasks < 8 && team2tasks < 8 && team1points < 16 && team2points < 16;
    }

    public double getScore(double points, int tasks, boolean fell) {
        double total = points + tasks;
        if (fell) {
            total -= 5;
        }
        return total;
    }

    public IContestant getWinner() {

        if (isValid() && Math.max(team1score, team2score) == team1score) {
            return team1;
        } else if(isValid() && Math.max(team1score, team2score) == team2score){
            return team2;
        }
        return null;

    }

}
