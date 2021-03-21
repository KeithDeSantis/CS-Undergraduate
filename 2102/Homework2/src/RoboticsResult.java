public class RoboticsResult  implements IResult{

    IContestant team1;
    IContestant team2;
    double team1points;
    int team1tasks;
    boolean team1fell;
    double team2points;
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
    public double getScore(double points, int tasks, boolean fell){
        int penalty = 5;
        double score = points;
        score = score + tasks;
        if (fell){
            score = score - penalty;
        }
        return score;
    }
    public boolean isValid(){
        return team1points < 16 && team2points < 16 && team1tasks < 8 && team2tasks < 8;
    }
    public IContestant getWinner(){
        if(getScore(team1points, team1tasks, team1fell) > getScore(team2points, team2tasks, team2fell)){
            return team1;
        }else{
            return team2;
        }
    }
}
