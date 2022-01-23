// Class Representing a Rugby Team

public class RugbyTeam implements IContestant{

    String country;

    String jerseyColor;

    boolean intimidationRitual;

    int wins;

    int losses;

    public RugbyTeam(String country, String jerseyColor, boolean intimidationRitual, int wins, int losses) {
        this.country = country;
        this.jerseyColor = jerseyColor;
        this.intimidationRitual = intimidationRitual;
        this.wins = wins;
        this.losses = losses;
    }

    public boolean expectToBeat(RugbyTeam competitor) { // Takes in another team and produces true if this team is expected to beat the inputted team
        if(this.intimidationRitual^competitor.intimidationRitual) {
            if(this.intimidationRitual) {
                return true;
            } else {
                return false;
            }
        } else {
             if(this.wins - this.losses > competitor.wins - competitor.losses) {
                 return true;
             }
             if(this.wins - this.losses < competitor.wins - competitor.losses) {
                 return false;
             } else {
                 return false;
             }
        }
    }
}
