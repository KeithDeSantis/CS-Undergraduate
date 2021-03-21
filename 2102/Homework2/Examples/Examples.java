import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class Examples {
    public Examples(){}

    RugbyTeam testRugbyTeam1 = new RugbyTeam("USA", "blue", true, 77, 0);
    RugbyTeam testRugbyTeam2 = new RugbyTeam("China", "red", true, 53, 12);
    RugbyTeam testRugbyTeam3 = new RugbyTeam("Russia", "red", false, 13, 59);

    RoboticsTeam testRoboticsTeam1 = new RoboticsTeam("WPI", "hammer", 16);
    RoboticsTeam testRoboticsTeam2 = new RoboticsTeam("Union", "saw", 355);
    RoboticsTeam testRoboticsTeam3 = new RoboticsTeam("RPI", "wheels", 2);

    RugbyResult testRugbyResult1 = new RugbyResult(testRugbyTeam1, testRugbyTeam2, 120, 110);
    RugbyResult testRugbyResult2 = new RugbyResult(testRugbyTeam1, testRugbyTeam3, 149, 15);
    RugbyResult testRugbyResult3 = new RugbyResult(testRugbyTeam1, testRugbyTeam2, 100, 88);
    RugbyResult testRugbyResultInvalid = new RugbyResult(testRugbyTeam1, testRugbyTeam3, 23, 200);

    RoboticsResult testRoboticsResult1 = new RoboticsResult(testRoboticsTeam1, testRoboticsTeam2, 13.2, 2, false, 5, 7, true);
    RoboticsResult testRoboticsResult2 = new RoboticsResult(testRoboticsTeam1, testRoboticsTeam3, 5, 1, false, 5, 7, true);
    RoboticsResult testRoboticsResult3 = new RoboticsResult(testRoboticsTeam2, testRoboticsTeam3, 13.2, 2, false, 5, 7, true);
    RoboticsResult testRoboticsResultInvalid = new RoboticsResult(testRoboticsTeam1, testRoboticsTeam2, -3, 0, true, 33, 85, true);

    Match testMatch1 = new Match(testRugbyTeam1, testRugbyTeam2, testRugbyResult1);
    Match testMatch2 = new Match(testRoboticsTeam1, testRoboticsTeam2, testRoboticsResult1);
    Match testMatchInvalid = new Match(testRugbyTeam1, testRugbyTeam3, testRugbyResultInvalid);



    @Test
    public void testIsValid(){
        assertTrue(testRugbyResult1.isValid());
        assertFalse(testRugbyResultInvalid.isValid());
        assertTrue(testRoboticsResult1.isValid());
        assertFalse(testRoboticsResultInvalid.isValid());
    }
    @Test
    public void testGetScore(){
        assertEquals(testRoboticsResult1.getScore(13.2, 2, false), 15.2, .01);
        assertEquals(testRoboticsResult2.getScore(5, 7, true), 7.0, .01);
        assertEquals(testRoboticsResultInvalid.getScore(33, 85, true), 113, .01);
    }
    @Test
    public void testWinner(){
        assertEquals(testMatch1.winner(), testRugbyTeam1);
        assertEquals(testMatch2.winner(), testRoboticsTeam1);
        assertNull(testMatchInvalid.winner());
    }
    @Test
    public void testExpectToBeat(){
        assertTrue(testRugbyTeam1.expectToBeat(testRugbyTeam2));
        assertTrue(testRugbyTeam2.expectToBeat(testRugbyTeam3));
        assertFalse(testRoboticsTeam1.expectToBeat(testRoboticsTeam2));
        assertTrue(testRoboticsTeam1.expectToBeat(testRoboticsTeam3));
    }

    //HW2
    //rugby matches
    Match ruMatch1 = new Match(testRugbyTeam1, testRugbyTeam2, testRugbyResult1);
    Match ruMatch2 = new Match(testRugbyTeam1, testRugbyTeam3, testRugbyResultInvalid);
    Match ruMatch3 = new Match(testRugbyTeam1, testRugbyTeam2, testRugbyResult1);
    Match ruMatch4 = new Match(testRugbyTeam1, testRugbyTeam3, testRugbyResult2);
    //robotics matches
    Match roMatch1 = new Match(testRoboticsTeam1, testRoboticsTeam2, testRoboticsResult1);
    Match roMatch2 = new Match(testRoboticsTeam1, testRoboticsTeam3, testRoboticsResult2);
    Match roMatch3 = new Match(testRoboticsTeam2, testRoboticsTeam3, testRoboticsResult3);
    Match roMatch4 = new Match(testRoboticsTeam1, testRoboticsTeam3, testRoboticsResultInvalid);

    LinkedList ruAll = new LinkedList(Arrays.asList(ruMatch1,ruMatch2,ruMatch3,ruMatch4));
    LinkedList ru12 = new LinkedList(Arrays.asList(ruMatch1,ruMatch2));
    LinkedList ru34 = new LinkedList(Arrays.asList(ruMatch3,ruMatch4));

    LinkedList roAll = new LinkedList(Arrays.asList(roMatch1,roMatch2,roMatch3,roMatch4));
    LinkedList ro12 = new LinkedList(Arrays.asList(roMatch1,roMatch2));
    LinkedList ro34 = new LinkedList(Arrays.asList(roMatch3,roMatch4));

    InitRound fullRuRound = new InitRound(ruAll);
    InitRound ruRound12 = new InitRound(ru12);
    InitRound ruRound34 = new InitRound(ru34);

    InitRound fullRoRound = new InitRound(roAll);
    InitRound roRound12 = new InitRound(ro12);
    InitRound roRound34 = new InitRound(ro34);

    LinkedList<IContestant> fakeContestantListRugby = new LinkedList<IContestant> (Arrays.asList(testRugbyTeam1,testRugbyTeam2,testRugbyTeam3));
    LinkedList<IContestant> fakeContestantListRobot = new LinkedList<IContestant> (Arrays.asList(testRoboticsTeam1,testRoboticsTeam2,testRoboticsTeam3));

    AdvancedRound fullRuRoundA = new AdvancedRound(ruAll,fakeContestantListRugby);
    AdvancedRound ruRound12A = new AdvancedRound(ru12,fakeContestantListRugby);
    AdvancedRound ruRound34A = new AdvancedRound(ru34,fakeContestantListRugby);

    AdvancedRound fullRoRoundA = new AdvancedRound(roAll,fakeContestantListRobot);
    AdvancedRound roRound12A = new AdvancedRound(ro12,fakeContestantListRobot);
    AdvancedRound roRound34A = new AdvancedRound(ro34,fakeContestantListRobot);

    LinkedList<IWinner> initTournamentListRu = new LinkedList<IWinner>(Arrays.asList(fullRuRound, ruRound12, ruRound34));
    LinkedList<IWinner> initTournamentListRo = new LinkedList<IWinner>(Arrays.asList(fullRuRound, ruRound12, ruRound34));
    LinkedList<IWinner> advTournamentListRu = new LinkedList<IWinner>(Arrays.asList(fullRuRoundA, ruRound12A, ruRound34A));
    LinkedList<IWinner> advTournamentListRo = new LinkedList<IWinner>(Arrays.asList(fullRuRoundA, ruRound12A, ruRound34A));

    Tournament initTournamentRu = new Tournament(initTournamentListRu);
    Tournament initTournamentRo = new Tournament(initTournamentListRo);
    Tournament advTournamentRu = new Tournament(advTournamentListRu);
    Tournament advTournamentRo = new Tournament(advTournamentListRo);


    @Test
    public void testGetMatchWinners() {
        //rugby
        assertEquals(Arrays.asList(testRugbyTeam1,testRugbyTeam1,testRugbyTeam1), fullRuRound.getMatchWinners());
        assertEquals(Arrays.asList(testRugbyTeam1), ruRound12.getMatchWinners());
        assertEquals(Arrays.asList(testRugbyTeam1, testRugbyTeam1), ruRound34.getMatchWinners());
        //robotics
        assertEquals(Arrays.asList(testRoboticsTeam1, testRoboticsTeam3, testRoboticsTeam2), fullRoRound.getMatchWinners());
        assertEquals(Arrays.asList(testRoboticsTeam1, testRoboticsTeam3), roRound12.getMatchWinners());
        assertEquals(Arrays.asList(testRoboticsTeam2), roRound34.getMatchWinners());
        //rugby
        assertEquals(Arrays.asList(testRugbyTeam1,testRugbyTeam1,testRugbyTeam1), fullRuRoundA.getMatchWinners());
        assertEquals(Arrays.asList(testRugbyTeam1), ruRound12A.getMatchWinners());
        assertEquals(Arrays.asList(testRugbyTeam1, testRugbyTeam1), ruRound34A.getMatchWinners());
        //robotics
        assertEquals(Arrays.asList(testRoboticsTeam1, testRoboticsTeam3, testRoboticsTeam2), fullRoRoundA.getMatchWinners());
        assertEquals(Arrays.asList(testRoboticsTeam1, testRoboticsTeam3), roRound12A.getMatchWinners());
        assertEquals(Arrays.asList(testRoboticsTeam2), roRound34A.getMatchWinners());
    }
    @Test
    public void testGetNumWinners() {
        //rugby
        assertEquals(3, fullRuRound.getNumWinners());
        assertEquals(1, ruRound12.getNumWinners());
        assertEquals(2, ruRound34.getNumWinners());
        //robotics
        assertEquals(3, fullRoRound.getNumWinners());
        assertEquals(2, roRound12.getNumWinners());
        assertEquals( 1, roRound34.getNumWinners());
        //rugby
        assertEquals(3, fullRuRoundA.getNumWinners());
        assertEquals(1, ruRound12A.getNumWinners());
        assertEquals(2, ruRound34A.getNumWinners());
        //robotics
        assertEquals(3, fullRoRoundA.getNumWinners());
        assertEquals(2, roRound12A.getNumWinners());
        assertEquals( 1, roRound34A.getNumWinners());
    }
    @Test
    public void testIsWinner() {
        assertTrue(fullRuRound.isWinner(testRugbyTeam1));
        assertFalse(fullRuRound.isWinner(testRugbyTeam2));
        assertTrue(fullRuRoundA.isWinner(testRugbyTeam1));
        assertFalse(fullRuRoundA.isWinner(testRugbyTeam2));

        assertTrue(fullRoRound.isWinner(testRoboticsTeam1));
        assertFalse(fullRoRound.isWinner(testRugbyTeam2));
        assertTrue(fullRoRoundA.isWinner(testRoboticsTeam3));
        assertTrue(fullRoRoundA.isWinner(testRoboticsTeam2));
    }
    @Test
    public void testFinalWinnerIsValid() {
        assertTrue(initTournamentRu.finalWinnerIsValid(testRugbyTeam1));
        assertFalse(advTournamentRu.finalWinnerIsValid(testRugbyTeam2));
        assertFalse(initTournamentRo.finalWinnerIsValid(testRoboticsTeam1));
        assertFalse(advTournamentRo.finalWinnerIsValid(testRoboticsTeam2));
    }

}