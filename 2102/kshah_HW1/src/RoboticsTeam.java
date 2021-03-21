public class RoboticsTeam implements IContestant {

    String school;
    String specialFeatures;
    double previousScore;

    public RoboticsTeam(String school, String specialFeatures, double previousScore) {
        this.school = school;
        this.specialFeatures = specialFeatures;
        this.previousScore = previousScore;
    }
    public boolean expectToBeat(RoboticsTeam otherTeam) {
        return previousScore > otherTeam.previousScore;
    }
}
