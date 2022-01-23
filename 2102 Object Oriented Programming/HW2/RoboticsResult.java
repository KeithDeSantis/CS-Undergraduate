// Class that represents the results of a Robotics match

public class RoboticsResult implements IResult {

    double team1points;

    double team2points;

    RoboticsTeam team1;

    RoboticsTeam team2;

    int team1tasks;

    boolean team1fell;

    int team2tasks;

    boolean team2fell;

    public RoboticsResult(RoboticsTeam team1, RoboticsTeam team2,
                          double team1points, int team1tasks, boolean team1fell,
                          double team2points, int team2tasks, boolean team2fell) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1points = team1points;
        this.team1tasks = team1tasks;
        this.team1fell = team1fell;
        this.team2points = team2points;
        this.team2tasks = team2tasks;
        this.team2fell = team2fell;
    }

    public boolean isValid() { //determines if scores are valid possibilities and produces true if they are
        if (this.team1tasks < 8 && this.team2tasks < 8 && this.team1points <= 16 && this.team2points <= 16) {
            return true;
        } else {
            return false;
        }
    }

    public double getScore(double points, int tasks, boolean fell) {
        if(fell) {
            double finalScore = points + tasks - 5;
            return finalScore;
        } else {
            double finalScore = points + tasks;
            return finalScore;
        }
    }

    public RoboticsTeam getWinner() {
        if(this.getScore(this.team1points, this.team1tasks, this.team1fell) > this.getScore(this.team2points, this.team2tasks, this.team2fell)) {
            return this.team1;
        } else {
            return this.team2;
        }
    }
}