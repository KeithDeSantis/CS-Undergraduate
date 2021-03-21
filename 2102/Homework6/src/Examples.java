import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;

public class Examples {


    public Examples() {
    }


    ElectionData Setup1 () {
        ElectionData ed1 = new ElectionData();


        // put candidates on the ballot
        try {

            ed1.addCandidate("gompei");
            ed1.addCandidate("husky");
            ed1.addCandidate("ziggy");

        } catch (Exception ignored) {}

        // cast votes

        try {

            ed1.processVote("gompei", "husky", "ziggy");
            ed1.processVote("gompei", "ziggy", "husky");
            ed1.processVote("husky", "gompei", "ziggy");

        } catch (Exception ignored) {}

        return(ed1);

    }
    ElectionData Setup2 () {
        ElectionData ed2 = new ElectionData();


        // put candidates on the ballot
        try {

            ed2.addCandidate("gompei");
            ed2.addCandidate("husky");
            ed2.addCandidate("ziggy");

        } catch (Exception ignored) {}

        // cast votes

        try {

            ed2.processVote("gompei", "husky", "ziggy");
            ed2.processVote("husky", "ziggy", "gompei");
            ed2.processVote("husky", "gompei", "ziggy");

        } catch (Exception ignored) {}

        return(ed2);

    }
    ElectionData Setup3 () {
        ElectionData ed3 = new ElectionData();


        // put candidates on the ballot
        try {

            ed3.addCandidate("gompei");
            ed3.addCandidate("husky");
            ed3.addCandidate("ziggy");

        } catch (Exception ignored) {}

        // cast votes

        try {

            ed3.processVote("gompei", "husky", "ziggy");
            ed3.processVote("ziggy", "husky", "gompei");
            ed3.processVote("husky", "gompei", "ziggy");

        } catch (Exception ignored) {}

        return(ed3);

    }





    @Test
    public void testMostFirstWinner () {
        assertEquals ("gompei", Setup1().findWinnerMostFirstVotes());
        assertEquals ("husky", Setup2().findWinnerMostFirstVotes());
        assertEquals ("Runoff required", Setup3().findWinnerMostFirstVotes());
    }

    @Test
    public void testFindWinnerMostPoints(){
        assertEquals("gompei",Setup1().findWinnerMostPoints());
        assertEquals("husky",Setup2().findWinnerMostPoints());
        assertEquals("husky",Setup3().findWinnerMostPoints());

    }

    @Test(expected=UnknownCandidateException.class)
    public void testUnknownCandidateException() throws UnknownCandidateException
    {
        try {
            Setup1().processVote("tiger", "gompei","husky");
        } catch (DuplicateVotesException ignored) {}

        try {
            Setup2().processVote("Kush", "gompei","husky");
        } catch (DuplicateVotesException ignored) {}

        try {
            Setup1().processVote("Sam", "gompei","husky");
        } catch (DuplicateVotesException ignored) {}

    }

    @Test(expected=CandidateExistsException.class)
    public void testCandidateExistsException() throws CandidateExistsException
    {
            Setup2().addCandidate("husky");
            Setup1().addCandidate("gompei");
            Setup3().addCandidate("ziggy");

    }

    @Test(expected=DuplicateVotesException.class)
    public void testDuplicateVotesException() throws DuplicateVotesException
    {
        try{
            Setup3().processVote("husky","husky","husky");
    }catch (UnknownCandidateException ignored){}

        try{
            Setup3().processVote("gompei","husky","husky");
        }catch (UnknownCandidateException ignored){}

        try{
            Setup3().processVote("ziggy","gompei","gompei");
        }catch (UnknownCandidateException ignored){}

    }

}
