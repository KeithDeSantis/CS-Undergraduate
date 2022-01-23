import java.util.LinkedList;
import org.junit.*;
import static org.junit.Assert.*;

public class Examples {

    ElectionData e = new ElectionData();

    @Before
    public void setup() { e.setupBallot(); }
    //Process Vote Tests
    @Test
    public void testProcessVote() {
        LinkedList<Integer> voteGompei = new LinkedList<Integer>();
        voteGompei.add(1);
        voteGompei.add(0);
        voteGompei.add(0);
        LinkedList<Integer> voteHusky = new LinkedList<Integer>();
        voteHusky.add(0);
        voteHusky.add(1);
        voteHusky.add(0);
        LinkedList<Integer> voteGeorge = new LinkedList<Integer>();
        voteGeorge.add(0);
        voteGeorge.add(0);
        voteGeorge.add(1);
        try { e.processVote("Gompei", "Husky", "George"); }
        catch (UnknownCandidateException e) {}
        catch (DuplicateVotesException e) {}
        assertEquals(e.getVotes().get("Gompei"), voteGompei);
        assertEquals(e.getVotes().get("Husky"), voteHusky);
        assertEquals(e.getVotes().get("George"), voteGeorge);
    }
    @Test(expected=UnknownCandidateException.class)
    public void testUnknownCandidateException() throws UnknownCandidateException
    {
        try { e.processVote("Bruh", "Gompei", "Henry"); }
        catch (DuplicateVotesException e) {}
    }
    @Test(expected=DuplicateVotesException.class)
    public void testDuplicateVotesException() throws DuplicateVotesException
    {
        try { e.processVote("Gompei", "Gompei", "Henry"); }
        catch (UnknownCandidateException e) {}
    }

    //Add candidate test
    @Test
    public void testAddCandidate() {
        try { e.addCandidate("Bill"); }
        catch (CandidateExistsException e) {}
        LinkedList<String> testList = new LinkedList<String>();
        testList.add("Gompei");
        testList.add("Husky");
        testList.add("George");
        testList.add("Henry");
        testList.add("Bill");
        assertEquals(e.getBallot(), testList);
    }
    @Test(expected=CandidateExistsException.class)
    public void testCandidateExistsException() throws CandidateExistsException
    {
        e.addCandidate("Gompei");
    }

    // Isn't Valid Vote Same Candidate and Duplicate Candidate Tests
    @Test
    public void testIsntValidVoteSameCandidateTrue() {
        assertTrue(e.isntValidVoteSameCandidate("Gompei", "Gompei", "Bruh"));
    }
    @Test
    public void testIsntValidVoteSameCandidateFalse() {
        assertFalse(e.isntValidVoteSameCandidate("Gompei", "Gofsafei", "Bruh"));
    }
    @Test
    public void testDuplicateCandidate() {
        assertEquals(e.duplicateCandidate("Gompei", "Gompei", "Gompei"), "Gompei");
    }

    // Isn't Valid Votes Exist and Fake Candidate Tests
    @Test
    public void testIsntValidVotesExistTrue() {
        assertTrue(e.isntValidVotesExist("DFSA", "FasfA", "FaSF"));
    }
    @Test
    public void testIsntValidVotesExistFalse() {
        assertFalse(e.isntValidVotesExist("Gompei", "Husky", "Henry"));
    }
    @Test
    public void testFakeCandidate() {
        assertEquals(e.fakeCandidate("Gompei", "Husky", "fbijsadf"), "fbijsadf");
    }

    // Find Winner Most First Votes and Percent of Vote Tests
    @Test
    public void testFindWinnerMostFirst() {
        try { e.processVote("Gompei", "Husky", "George"); }
        catch (UnknownCandidateException e) {}
        catch (DuplicateVotesException e) {}
        assertEquals(e.findWinnerMostFirstVotes(), "Gompei");
        assertEquals(e.percentOfVotes("Gompei", 0), 1, 0.1);
        assertEquals(e.percentOfVotes("Husky", 0), 0, 0.1);
    }
    @Test
    public void testFindWinnerMostFirstTie() {
        try { e.processVote("Gompei", "Husky", "George"); }
        catch (UnknownCandidateException e) {}
        catch (DuplicateVotesException e) {}
        try { e.processVote("Henry", "Husky", "George"); }
        catch (UnknownCandidateException e) {}
        catch (DuplicateVotesException e) {}
        assertEquals(e.findWinnerMostFirstVotes(), "Runoff required.");
    }

    // Find Winner Most Points Tests
    @Test
    public void testFindWinnerMostPointsNoTie() {
        try { e.processVote("Gompei", "Husky", "George"); }
        catch (UnknownCandidateException e) {}
        catch (DuplicateVotesException e) {}
        try { e.processVote("George", "Gompei", "Husky"); }
        catch (UnknownCandidateException e) {}
        catch (DuplicateVotesException e) {}
        assertEquals(e.findWinnerMostPoints(), "Gompei");
    }
    @Test
    public void testFindWinnerMostPointsTie() {
        try { e.processVote("Gompei", "Husky", "George"); }
        catch (UnknownCandidateException e) {}
        catch (DuplicateVotesException e) {}
        try { e.processVote("Henry", "George", "Husky"); }
        catch (UnknownCandidateException e) {}
        catch (DuplicateVotesException e) {}
        assertEquals(e.findWinnerMostPoints(), "Gompei");
    }
}
