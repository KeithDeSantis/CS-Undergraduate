public class RugbyTeam implements IContestant {

    String county;
    String jerseyColor;
    boolean intimidationRitual;
    int wins;
    int losses;


    public RugbyTeam(String country, String jerseyColor, boolean intimidationRitual, int wins, int losses) {
        this.county = country;
        this.jerseyColor = jerseyColor;
        this.intimidationRitual = intimidationRitual;
        this.wins = wins;
        this.losses = losses;
    }

    public boolean expectToBeat(RugbyTeam otherTeam) {
        if (intimidationRitual && !otherTeam.intimidationRitual) {
            return true;
        } else if (!intimidationRitual && otherTeam.intimidationRitual) {
            return false;
        }
        return (wins - losses) > (otherTeam.wins - otherTeam.losses);
    }
}