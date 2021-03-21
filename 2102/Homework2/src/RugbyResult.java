public class RugbyResult  implements IResult {
    RugbyTeam team1;
    RugbyTeam team2;
    double team1points;
    double team2points;

    public RugbyResult(RugbyTeam team1, RugbyTeam team2, double team1points, double team2points) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1points = team1points;
        this.team2points = team2points;
    }
    public boolean isValid(){
        return team1points < 150 && team2points < 150;
    }

    public IContestant getWinner(){
        if(this.team1points > this.team2points){
            return this.team1;
        }else{
            return this.team2;
        }
    }
}
