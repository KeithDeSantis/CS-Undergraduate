public class RugbyTeam implements IContestant {

    String country;
    String jerseyColor;
    boolean ritual;
    int wins;
    int losses;

    public RugbyTeam(String country, String jerseyColor, boolean ritual, int wins, int losses){

        this.country = country;
        this.jerseyColor = jerseyColor;
        this.ritual = ritual;
        this.wins = wins;
        this.losses = losses;
    }

    public boolean expectToBeat(RugbyTeam opposition){
        if(ritual && !opposition.ritual){
            return true;
        }else if(!ritual && opposition.ritual) {
            return false;
        }else return (wins - losses) > (opposition.wins - opposition.losses);
    }
}
