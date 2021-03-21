import static org.junit.Assert.*;

import org.junit.Test;

public class Examples {

    public Examples() {
    }
    Fish fish1 = new Fish(4, 2);
    Shark shark1 = new Shark(15, 5);
    Shark shark2 = new Shark(4, 0);
    Shark shark3 = new Shark(8, 0);
    Boa boa1 = new Boa("Larry", 15, "rats");
    Boa boa2 = new Boa("Gwen", 20, "people");
    Dillo dillo1 = new Dillo(2, false);


    @Test
    public void testForIsNormalSize() {
        assertTrue(fish1.isNormalSize());
        assertTrue(shark1.isNormalSize());
        assertFalse(shark2.isNormalSize());
        assertTrue(shark3.isNormalSize());
    }

    @Test
    public void testForIsDangerToPeople(){
        assertTrue(shark1.isDangerToPeople());
        assertFalse(shark2.isDangerToPeople());
        assertFalse(boa1.isDangerToPeople());
        assertTrue(boa2.isDangerToPeople());
    }
}