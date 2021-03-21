public class RoboticsTeam implements IContestant {

    String school;
    String feature;
    int previousScore;

    public RoboticsTeam(String school, String feature, int previousScore) {

        this.school = school;
        this.feature = feature;
        this.previousScore = previousScore;
    }
    public boolean expectToBeat(RoboticsTeam opposition){
        return previousScore > opposition.previousScore;
    }
}
