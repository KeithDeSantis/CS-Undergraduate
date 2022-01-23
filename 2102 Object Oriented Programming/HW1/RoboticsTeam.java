// Class Representing a Robotics Team

public class RoboticsTeam implements IContestant{

    String school;

    String specialFeature;

    int previousScore; // 0 if never before competed

    public RoboticsTeam(String school, String specialFeature, int previousScore) {
        this.school = school;
        this.specialFeature = specialFeature;
        this.previousScore = previousScore;
    }

    public boolean expectToBeat(RoboticsTeam competitor) {  // Takes in another team and tells which team is expected to win
        if(this.previousScore > competitor.previousScore) {
            return true;
        }
        if(this.previousScore < competitor.previousScore) {
            return false;
        } else {
            return false;
        }
    }
}
