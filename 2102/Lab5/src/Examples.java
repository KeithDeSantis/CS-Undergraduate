import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;

public class Examples {

    public Examples() {}
    EventGuests guestsList = new EventGuests(new AbsLinkedList(new LinkedList<String>()));
    EventGuests guestsBST = new EventGuests(new EmptyBST());
    EventGuests guestsAVL = new EventGuests(new EmptyBST());

    @Before
    public void setup(){
        guestsBST.addGuest("Kush Shah");
        guestsBST.addGuest("John Doe");
        guestsList.addGuest("Kush Shah");
        guestsAVL.addGuest("John Doe");
    }


    @Test
    public void sizeTest(){
       assertEquals(guestsBST.numGuests(), 2);
        guestsBST.addGuest("Jane Doe");
        assertEquals(guestsBST.numGuests(), 3);
        assertEquals(guestsList.numGuests(), 1);
        assertEquals(guestsAVL.numGuests(), 1);
    }

    @Test
    public void isComingTest(){
        assertTrue(guestsBST.isComing("Kush Shah"));
        assertTrue(guestsBST.isComing("John Doe"));
        assertFalse(guestsBST.isComing("Jane Doe"));
        assertTrue(guestsList.isComing("Kush Shah"));
        assertFalse(guestsList.isComing("John Doe"));
        assertTrue(guestsAVL.isComing("John Doe"));
        assertFalse(guestsAVL.isComing("Kush Shah"));

    }



}
