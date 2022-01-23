// Keith DeSantis

import org.junit.*;

import static org.junit.Assert.*;

public class Examples {

    RugbyTeam Falcons = new RugbyTeam("Austria", "Red", true, 14, 3);
    RugbyTeam Squirrels = new RugbyTeam("Canada", "Blue", false, 5, 10);
    RugbyTeam Dads = new RugbyTeam("America", "White", true, 14, 3);
    RugbyTeam Eagles = new RugbyTeam("England", "Orange",true, 10, 5);

    RoboticsTeam Gizmos = new RoboticsTeam("WPI", "Hook", 0);
    RoboticsTeam Bruhs = new RoboticsTeam("RIT", "Gun", 5);
    RoboticsTeam Doodads = new RoboticsTeam("MIT", "Fire", 0);

    RugbyResult FvSr = new RugbyResult(Falcons, Squirrels, 43, 2);
    RugbyResult FvSr2 = new RugbyResult(Falcons, Squirrels, 10, 11);
    RugbyResult InvalidRugby = new RugbyResult(Falcons, Squirrels, 2435442, 1);
    RugbyResult EdgeCaseRugby = new RugbyResult(Falcons, Squirrels, 150, 9);

    RoboticsResult GvBr = new RoboticsResult(Gizmos, Bruhs, 7, 3, false, 2, 1, true);
    RoboticsResult GvBr2 = new RoboticsResult(Gizmos, Bruhs, 0, 4, true, 5, 5, true);
    RoboticsResult TooManyPoints = new RoboticsResult(Gizmos, Bruhs, 111110, 4, true, 5, 5, false);
    RoboticsResult EdgePoints = new RoboticsResult(Gizmos, Bruhs, 16, 4, true, 5, 5, true);
    RoboticsResult TooManyTasks = new RoboticsResult(Gizmos, Bruhs, 0, 442342, true, 5, 5, true);
    RoboticsResult EdgeTasks = new RoboticsResult(Gizmos, Bruhs, 0, 8, false, 5, 5, false);

    Match FvS = new Match(Falcons, Squirrels, FvSr);
    Match FvS2 = new Match(Falcons, Squirrels, FvSr2);
    Match FvSIn = new Match (Falcons, Squirrels, InvalidRugby);
    Match GvB = new Match(Gizmos, Bruhs, GvBr);
    Match GvB2 = new Match(Gizmos, Bruhs, GvBr2);
    Match GvBIn = new Match(Gizmos, Bruhs, TooManyPoints);

    // isValid Tests
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

    // getScore Tests
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


}

