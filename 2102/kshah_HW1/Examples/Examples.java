import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class Examples {

    public Examples() {
    }

    RugbyTeam testTeam1 = new RugbyTeam("USA", "red", true, 10, 5);
    RugbyTeam testTeam2 = new RugbyTeam("RUSSIA", "blue", false, 10, 5);
    RugbyTeam testTeam3 = new RugbyTeam("AUSTRALIA", "yellow", true, 5, 10);

    RoboticsTeam testTeam4 = new RoboticsTeam("ABC", "powerful arm", 15);
    RoboticsTeam testTeam5 = new RoboticsTeam("DEF", "water", 14);

    RugbyResult testResult1 = new RugbyResult(testTeam1, testTeam2, 10.0, 8.0);
    RugbyResult testResult2 = new RugbyResult(testTeam1, testTeam3, 8, 9);
    RugbyResult testResult3 = new RugbyResult(testTeam2, testTeam3, 8, 8);

    RoboticsResult testResults4 = new RoboticsResult(testTeam4, testTeam5, 10, 4, false, 11, 5, true);
    RoboticsResult testResults5 = new RoboticsResult(testTeam4, testTeam5, 17, 4, false, 11, 5, true);
    RoboticsResult testResults6 = new RoboticsResult(testTeam4, testTeam5, 8, 3, false, 9, 3, false);

    Match match1 = new Match(testTeam1, testTeam2, testResult1);
    Match match2 = new Match(testTeam1, testTeam3, testResult2);
    Match match3 = new Match(testTeam2, testTeam3, testResult3);
    Match match4 = new Match(testTeam4, testTeam5, testResults4);
    Match match5 = new Match(testTeam4, testTeam5, testResults5);
    Match match6 = new Match(testTeam4, testTeam5, testResults6);

    @Test
    public void checkRugbyExpectToBeat() {
        assertTrue(testTeam1.expectToBeat(testTeam2));
        assertFalse(testTeam2.expectToBeat(testTeam1));
        assertTrue(testTeam1.expectToBeat(testTeam3));
    }

    @Test
    public void checkWinner() {
        assertEquals(match1.winner(), testTeam1);
        assertEquals(match2.winner(), testTeam3);
        assertEquals(match4.winner(), testTeam4);
        assertNull(match3.winner());
        assertNull(match5.winner());

    }

    @Test
    public void checkIsValid() {
        assertTrue(testResults4.isValid());
        assertFalse(testResults5.isValid());
    }

    @Test
    public void checkGetScore() {
        assertEquals(testResults4.getScore(10, 4, false), 14, .01);
        assertEquals(testResults4.getScore(11, 5, true), 11, .01);
    }

    @Test
    public void checkGetWinner() {
        assertEquals(testResults6.getWinner(), testTeam5);
        assertEquals(testResult1.getWinner(), testTeam1);
        assertEquals(testResult2.getWinner(), testTeam3);

    }

    //HW 2

    LinkedList<Match> listOfMatch1 = new LinkedList<Match>(Arrays.asList(match1, match2, match3));
    LinkedList<Match> listOfMatch2 = new LinkedList<Match>(Arrays.asList(match4, match5, match6));
    LinkedList<IContestant> listOfMatchWinners1 = new LinkedList<IContestant>(Arrays.asList(testTeam1, testTeam3));
    LinkedList<IContestant> listOfMatchWinners2 = new LinkedList<IContestant>(Arrays.asList(testTeam4,testTeam5));

    LinkedList<IContestant> listOfContestants1 = new LinkedList<IContestant>(Arrays.asList(testTeam1, testTeam2, testTeam3));

    InitRound initRound1 = new InitRound(listOfMatch1);
    InitRound initRound2 = new InitRound(listOfMatch2);

    AdvancedRound advancedRound1 = new AdvancedRound(listOfMatch1, listOfContestants1);

    AbsRound absRound1 = new InitRound(listOfMatch2);
    AbsRound absRound2 = new AdvancedRound(listOfMatch1, listOfContestants1);

    LinkedList<IWinner> listOfAbsRound = new LinkedList<IWinner>(Arrays.asList(absRound1,absRound2));

    Tournament tournament1 = new Tournament(listOfAbsRound);

    @Test
    public void checkFinalWinnerIsValid(){
        assertTrue(tournament1.finalWinnerIsValid(testTeam4));
        assertFalse(tournament1.finalWinnerIsValid(testTeam2));
    }

    @Test
    public void checkGetMatchWinners() {
        assertEquals(initRound1.getMatchWinners(), listOfMatchWinners1);
        assertEquals(initRound2.getMatchWinners(), listOfMatchWinners2);
    }

    @Test
    public void checkAdvRoundWinners() {
        assertTrue(advancedRound1.isWinner(testTeam1));
        assertFalse(advancedRound1.isWinner(testTeam2));
        assertTrue(advancedRound1.isWinner(testTeam3));
    }

    @Test
    public void checkInitRoundWinners(){
        assertTrue(initRound1.isWinner(testTeam1));
        assertFalse(initRound1.isWinner(testTeam2));
        assertTrue(initRound2.isWinner(testTeam4));
        assertTrue(initRound2.isWinner(testTeam5));
    }

    @Test
    public void checkGetNumWinners() {
        assertEquals(absRound1.getNumWinners(), 2);
        assertEquals(absRound2.getNumWinners(), 2);
    }

}