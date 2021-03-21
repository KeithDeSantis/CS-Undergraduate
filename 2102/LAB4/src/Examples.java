import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Examples {

    VoteData voteD = new VoteData();

    public Examples() {}

    @Before
    public void setup(){
        voteD.addCandidate("Gompei");
        voteD.addCandidate("Husky");

        for (int i = 0; i < 5; i++) voteD.votes.add("Husky");
        for (int i = 0; i < 15; i++) voteD.votes.add("Gompei");
        for (int i = 0; i < 25; i++) voteD.votes.add("Husky");
    }

    @Test
    public void checkCountVotes(){
        assertEquals(voteD.countVotes("Husky"),30);
        assertEquals(voteD.countVotes("Gompei"),15);
    }

    @Test
    public void checkWinner(){
        assertEquals(voteD.winner(),"Husky");
        for (int i = 0; i < 155; i++) voteD.votes.add("Gompei");
        assertEquals(voteD.winner(),"Gompei");
    }

    @Test
    public void checkStreak(){
        assertEquals(voteD.longestStreak("Gompei"),15);
        assertEquals(voteD.longestStreak("Husky"),25);
        assertEquals(voteD.longestStreak(""),0);
    }


}
