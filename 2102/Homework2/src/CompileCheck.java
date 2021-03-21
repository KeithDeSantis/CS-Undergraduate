import java.util.LinkedList;

public class CompileCheck {

  public static void main(String[] args) {
    
    /* 
     These are all nonsense calls just designed to check that
     the correct class and interface names are defined and
     that methods have the expected input and output types.
     These are not checking for correct answers beyond types.
     */
    
    // dummy variables for the outputs
    boolean boolResult;
    IContestant contResult;
    double dbResult;
    int intResult;
    LinkedList<IContestant> contestants;
    
    // Create samples of each contestant, result, and match
    RugbyTeam rugbyTeamCheck = new RugbyTeam("WPI", "maroon", false, 4, 6);
    RoboticsTeam robotsTeamCheck = new RoboticsTeam("WPI","goat cheese", 7);
    RugbyResult rugbyResultCheck = new RugbyResult(rugbyTeamCheck, rugbyTeamCheck, 10.5,15.2);
    RoboticsResult robotsResultCheck = new RoboticsResult(robotsTeamCheck, robotsTeamCheck, 3.1, 5, true, 3.2, 2, false);
    Match rugbyMatchCheck = new Match(rugbyTeamCheck, rugbyTeamCheck, rugbyResultCheck);
    Match robotsMatchCheck = new Match(robotsTeamCheck, robotsTeamCheck, robotsResultCheck);
    
    InitRound iRound = new InitRound(new LinkedList<Match>());
    AdvancedRound aRound = new AdvancedRound(new LinkedList<Match>(), new LinkedList<IContestant>());
    Tournament tnmt = new Tournament(new LinkedList<IWinner>());
    
    // Try calling all methods
    
    // isValid
    boolResult = rugbyResultCheck.isValid();
    boolResult = robotsResultCheck.isValid();
    
    // getScore
    dbResult = robotsResultCheck.getScore(3.2, 1, false);
    
    // getWinner
    contResult = rugbyResultCheck.getWinner();
    contResult = robotsResultCheck.getWinner();
    
    // winner
    contResult = rugbyMatchCheck.winner();
    contResult = robotsMatchCheck.winner();
    
    // expectToBeat
    boolResult = rugbyTeamCheck.expectToBeat(rugbyTeamCheck);
    boolResult = robotsTeamCheck.expectToBeat(robotsTeamCheck);
    
    // getMatchWinners
    contestants = iRound.getMatchWinners();
    contestants = aRound.getMatchWinners();
    
    // getNumWinners
    intResult = iRound.getNumWinners();
    intResult = aRound.getNumWinners();
    
    // isWinner
    boolResult = iRound.isWinner(rugbyTeamCheck);
    boolResult = aRound.isWinner(robotsTeamCheck);
    
    // finalWinnerIsValid
    boolResult = tnmt.finalWinnerIsValid(rugbyTeamCheck);
    
    System.out.println("Congrats! Your program compiled and ran!");
    
  }
}