// Keith DeSantis

import org.junit.*;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class Examples {

    RugbyTeam Falcons = new RugbyTeam("Austria", "Red", true, 14, 3);
    RugbyTeam Squirrels = new RugbyTeam("Canada", "Blue", false, 5, 10);
    RugbyTeam Dads = new RugbyTeam("America", "White", true, 14, 3);
    RugbyTeam Eagles = new RugbyTeam("England", "Orange",true, 10, 5);

    RoboticsTeam Gizmos = new RoboticsTeam("WPI", "Hook", 0);
    RoboticsTeam Bruhs = new RoboticsTeam("RIT", "Gun", 5);
    RoboticsTeam Doodads = new RoboticsTeam("MIT", "Fire", 0);
    RoboticsTeam Jaguars = new RoboticsTeam("UMass Amherst", "Water Gun", 3);

    RugbyResult FvSr = new RugbyResult(Falcons, Squirrels, 43, 2);
    RugbyResult FvSr2 = new RugbyResult(Falcons, Squirrels, 10, 11);
    RugbyResult InvalidRugby = new RugbyResult(Falcons, Squirrels, 2435442, 1);
    RugbyResult EdgeCaseRugby = new RugbyResult(Falcons, Squirrels, 150, 9);
    RugbyResult FvDr = new RugbyResult(Falcons, Dads, 32, 12);
    RugbyResult DvEr = new RugbyResult(Dads, Eagles, 10, 4);

    RoboticsResult GvBr = new RoboticsResult(Gizmos, Bruhs, 7, 3, false, 2, 1, true);
    RoboticsResult GvBr2 = new RoboticsResult(Gizmos, Bruhs, 0, 4, true, 5, 5, true);
    RoboticsResult TooManyPoints = new RoboticsResult(Gizmos, Bruhs, 111110, 4, true, 5, 5, false);
    RoboticsResult EdgePoints = new RoboticsResult(Gizmos, Bruhs, 16, 4, true, 5, 5, true);
    RoboticsResult TooManyTasks = new RoboticsResult(Gizmos, Bruhs, 0, 442342, true, 5, 5, true);
    RoboticsResult EdgeTasks = new RoboticsResult(Gizmos, Bruhs, 0, 8, false, 5, 5, false);
    RoboticsResult DvJr = new RoboticsResult(Doodads, Jaguars, 7, 2, false, 5, 0, true);
    RoboticsResult GvDr = new RoboticsResult(Gizmos, Doodads, 13, 4, false, 7, 1, true);

    Match FvS = new Match(Falcons, Squirrels, FvSr);
    Match FvS2 = new Match(Falcons, Squirrels, FvSr2);
    Match FvSIn = new Match (Falcons, Squirrels, InvalidRugby);
    Match FvD = new Match(Falcons, Dads, FvDr);
    Match DvE = new Match(Dads, Eagles, DvEr);
    Match GvB = new Match(Gizmos, Bruhs, GvBr);
    Match GvB2 = new Match(Gizmos, Bruhs, GvBr2);
    Match GvBIn = new Match(Gizmos, Bruhs, TooManyPoints);
    Match DvJ = new Match(Doodads, Jaguars, DvJr);
    Match GvD = new Match(Gizmos, Doodads, GvDr);

    InitRound Round1ARugby = new InitRound(new LinkedList<Match>());
    InitRound Round1ARobotics = new InitRound(new LinkedList<Match>());
    InitRound Round1RugTourney = new InitRound(new LinkedList<Match>());
    InitRound Round1RobTourney = new InitRound(new LinkedList<Match>());

    AdvancedRound Round1BRugby = new AdvancedRound(new LinkedList<Match>(), new LinkedList<IContestant>());
    AdvancedRound Round1BRobotics = new AdvancedRound(new LinkedList<Match>(), new LinkedList<IContestant>());
    AdvancedRound Round2RugTourney = new AdvancedRound(new LinkedList<Match>(), new LinkedList<IContestant>());
    AdvancedRound Round2RobTourney = new AdvancedRound(new LinkedList<Match>(), new LinkedList<IContestant>());

    Tournament RugbyTourney = new Tournament(new LinkedList<IWinner>());
    Tournament RoboticsTourney = new Tournament(new LinkedList<IWinner>());

    LinkedList<IContestant> expectedList = new LinkedList<IContestant>(); //Used for tests

    @Before
    public void setup() {
        Round1ARugby.addMatch(FvS);
        Round1ARobotics.addMatch(GvB);
        Round1RugTourney.addMatch(FvS).addMatch(DvE);
        Round1RobTourney.addMatch(GvB).addMatch(DvJ);
        Round1BRugby.addMatch(FvS2);
        Round1BRugby.addContestant(Falcons).addContestant(Squirrels);
        Round1BRobotics.addMatch(GvB2);
        Round1BRobotics.addContestant(Gizmos).addContestant(Bruhs);
        Round2RugTourney.addMatch(FvD);
        Round2RugTourney.addContestant(Falcons).addContestant(Dads);
        Round2RobTourney.addMatch(GvD);
        Round2RobTourney.addContestant(Gizmos);
        Round2RobTourney.addContestant(Doodads);
        RugbyTourney.addRound(Round1RugTourney).addRound(Round2RugTourney);
        RoboticsTourney.addRound(Round1RobTourney).addRound(Round2RobTourney);
        expectedList = new LinkedList<IContestant>();
    }

    //isValid Tests
    @Test
    public void testValidRugby() { //A Valid Rugby Score
        assertTrue(FvSr.isValid());
    } // Valid Rugby Result
    @Test
    public void testInValidRugby() { //A Invalid Rugby Score
        assertFalse(InvalidRugby.isValid());
    } // Invalid Rugby Result
    @Test
    public void testEdgeRugby() { assertFalse(EdgeCaseRugby.isValid()); } // Edge Case for Rugby Result
    @Test
    public void testValidRobotics() { // Valid Robotics Result
        assertTrue(GvBr.isValid());
    } // Valid Robotics Result
    @Test
    public void testInValidRoboticPoints() { //Invalid Robotics Number of Points
        assertFalse(TooManyPoints.isValid());
    } // Invalid Robotics Points
    @Test
    public void testEdgeRoboticsPoints() { //Edge Robotics Number of Points
        assertTrue(EdgePoints.isValid());
    } // Edge Case for Robotics Points
    @Test
    public void testInValidRoboticsTasks() { //Invalid Number of Tasks Robotics
        assertFalse(TooManyTasks.isValid());
    } // Invalid Robotics Tasks
    @Test
    public void testEdgeRoboticsTasks() { // Edge Number of Tasks Robotics
        assertFalse(EdgeTasks.isValid());
    } // Edge Case for Robotics Tasks

    //getScore Tests
    @Test
    public void test2Fall() { assertEquals(GvBr.getScore(7, 4, true), 6, 0.001); } // Robot fell
    @Test
    public void test2DidntFall() { assertEquals(GvBr.getScore(7, 4, false), 11, 0.001); } // Robot didn't fall

    //winner Tests
    @Test
    public void testRugbyTeam1() { assertEquals(FvS.winner(), Falcons); } // Rugby where team 1 wins
    @Test
    public void testRugbyTeam2() { assertEquals(FvS2.winner(), Squirrels); } // Rugby where team 2 wins
    @Test
    public void testRugbyNull() { assertEquals(FvSIn.winner(), null); } // Rugby where match is invalid
    @Test
    public void testRoboticsTeam1() { assertEquals(GvB.winner(), Gizmos); } // Robotics where team 1 wins
    @Test
    public void testRoboticsTeam2() { assertEquals(GvB2.winner(), Bruhs); } // Robotics where team 2 wins
    @Test
    public void testRoboticsNull() { assertEquals(GvBIn.winner(), null); } // Robotics where match is invalid

    //expectToBeat Tests
    @Test
    public void testExpecttoBeatRugbyIntimidation() { assertTrue(Falcons.expectToBeat(Squirrels)); } // Rugby Case where input team doesn't have intimidation ritual and main team does
    @Test
    public void testDontExpecttoBeatRugbyIntimidation() { assertFalse(Squirrels.expectToBeat(Falcons)); } // Rugby Case where input team has intimidation ritual and main team doesn't
    @Test
    public void testExpecttoBeatRugbyPoints() { assertTrue(Falcons.expectToBeat(Eagles)); } // Rugby Case where input team has worse win to loss ratio and intimidation ritual isn't deciding factor
    @Test
    public void testDontExpecttoBeatRugbyPoints() { assertFalse(Eagles.expectToBeat(Falcons)); }  // Rugby Case where input team has better win to loss ratio and intimidation ritual isn't deciding factor
    @Test
    public void testRugbyNoExpectation() { assertFalse(Falcons.expectToBeat(Dads)); } // Rugby Case where no expected winner
    @Test
    public void testRoboticsExpecttoBeat() { assertTrue(Bruhs.expectToBeat(Gizmos)); } // Case where inputted team has lower previous score
    @Test
    public void testRoboticsDontExpecttoBeat() { assertFalse(Gizmos.expectToBeat(Bruhs)); } // Case where inputted team has higher previous score
    @Test
    public void testRoboticsNoExpectation() { assertFalse(Gizmos.expectToBeat(Doodads)); } // Case where no expected winner

    //getNumWinnersTests
    @Test
    public void testNumWinnerInitRug() {
        assertEquals(Round1ARugby.getNumWinners(), 1); } // Case where the round if an Initial (Rugby)
    @Test
    public void testNumWinnerInitRob() {
        assertEquals(Round1ARobotics.getNumWinners(), 1); } // Case where the round if an Initial (Robotics)
    @Test
    public void testNumWinnerAdvancedRug() {
        assertEquals(Round1BRugby.getNumWinners(), 1); } // Case where the round is Advanced (Rugby)
    @Test
    public void testNumWinnerAdvancedRob() {
        assertEquals(Round1BRobotics.getNumWinners(), 1); } // Case where the round is Advanced (Robotics)

    //getMatchWinners Tests
    @Test
    public void testgetMatchWinnersInitRug() {
        expectedList.add(Falcons);
        assertEquals(Round1ARugby.getMatchWinners(), expectedList); } // Case where round is Initial (Rugby)
    @Test
    public void testgetMatchWinnersInitRob() {
        expectedList.add(Gizmos);
        assertEquals(Round1ARobotics.getMatchWinners(), expectedList); } // Case where round is Initial (Robotics)
    @Test
    public void testgetMatchWinnersAdvancedRug() {
        expectedList.add(Squirrels);
        assertEquals(Round1BRugby.getMatchWinners(), expectedList); } // Case where round is Advanced (Rugby)
    @Test
    public void testgetMatchWinnersAdvancedRob() {
        expectedList.add(Bruhs);
        assertEquals(Round1BRobotics.getMatchWinners(), expectedList); } // Case where round is Advanced (Robotics)

    //isWinnerAdvanced Tests
    @Test
    public void testisWinnerTrueRugAdvanced() {
        assertTrue(Round1BRugby.isWinner(Falcons)); } // Case where passed IContestant is a winner (Rugby)
    @Test
    public void testisWinnerTrueRobAdvanced() {
        assertTrue(Round1BRobotics.isWinner(Gizmos)); } // Case where passed IContestant is a winner (Robotics)
    @Test
    public void testisWinnerFalseRugAdvanced() {
        assertFalse(Round1BRugby.isWinner(Dads)); } // Case where passed IContestant isn't a winner (Rugby)
    @Test
    public void testisWinnerFalseRobAdvanced() {
        assertFalse(Round1BRobotics.isWinner(Doodads)); } // Case where passed IContestant isn't a winner (Robotics)

    //isWinnerInit Tests
    @Test
    public void testisWinnerTrueRugInit() {
        assertTrue(Round1ARugby.isWinner(Falcons)); }
    @Test
    public void testisWinnerFalseRugInit() {
        assertFalse(Round1ARugby.isWinner(Squirrels)); }
    @Test
    public void testisWinnerTrueRobInit() {
        assertTrue(Round1ARobotics.isWinner(Gizmos)); }
    @Test
    public void testisWinnerFalseRobInit() {
        assertFalse(Round1ARobotics.isWinner(Bruhs)); }

    //finalWinnerIsValid tests
    @Test
    public void testfinalWinnerTrueRug() {
        assertTrue(RugbyTourney.finalWinnerIsValid(Falcons)); //Case where passed IContestant is a final winner (rugby)
    }
    @Test
    public void testfinalWinnerFalseRug() {
        assertFalse(RugbyTourney.finalWinnerIsValid(Squirrels)); //Case where passed IContestant isn't a final winner (rugby)
    }
    @Test
    public void testfinalWinnerEdgeRug() {
        assertTrue(RugbyTourney.finalWinnerIsValid(Dads)); //Case where passed IContestant is a final winner by having EXACTLY half the rounds won (edge case) (rugby)
    }
    @Test
    public void testfinalWinnerTrueRob() {
        assertTrue(RoboticsTourney.finalWinnerIsValid(Gizmos)); //Case where passed IContestant is a final winner (robotics)
    }
    @Test
    public void testfinalWinnerFalseRob() {
        assertFalse(RoboticsTourney.finalWinnerIsValid(Jaguars)); //Case where passed IContestant isn't a final winner (robotics)
    }
    @Test
    public void testfinalWinnerEdgeRob() {
        assertTrue(RoboticsTourney.finalWinnerIsValid(Doodads)); //Case where passed IContestant is a final winner by having EXACTLY half the rounds won (edge case) (robotics)
    }
}

