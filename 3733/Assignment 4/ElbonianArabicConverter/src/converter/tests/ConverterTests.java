package converter.tests;

import converter.ElbonianArabicConverter;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the ElbonianArabicConverter class.
 */
public class ConverterTests {

    @Test
    public void ElbonianToArabicSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("1");
        assertEquals(converter.toElbonian(), "I");
    }

    @Test
    public void testing40ToElbonian() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("40");
        assertEquals(converter.toElbonian(), "N");
    }

    @Test
    public void testing43ToElbonian() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("43");
        assertEquals(converter.toElbonian(), "NIII");
    }

    @Test(expected = MalformedNumberException.class)
    public void testingExceptionForToElbonian() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMMM");
        converter.toArabic();
    }

    @Test(expected = MalformedNumberException.class)
    public void testingExceptionForToElbonian1() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("FCNXYIDNN");
        converter.toArabic();
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void testingExceptionForOutOfBounds() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("15000");
        converter.toElbonian();
    }

    @Test
    public void ArabicToElbonian41() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter(" 41 ");//41
        assertEquals(converter.toElbonian(), "NI");
    }

    @Test
    public void ArabicToElbonian2745() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("2745");//41
        assertEquals(converter.toElbonian(), "MMDCCNV");
    }

    @Test
    public void ElbonianToArabicDX() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("DX");
        assertEquals(converter.toArabic(),510);
    }

   @Test
   public void ElbonianToArabicVI() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter =  new ElbonianArabicConverter("MMDCCNV");
        assertEquals(converter.toArabic(), 2745);
   }

    @Test
    public void ElbonianToArabicMDLI() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter =  new ElbonianArabicConverter("MDLI");
        assertEquals(converter.toArabic(), 1551);
    }

    @Test
    public void ElbonianToElbonian() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMMDCCXXXIII");
        assertEquals(converter.toElbonian(),"MMMDCCXXXIII");
    }
    @Test
    public void ArabicToArabic() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("100");
        assertEquals(converter.toArabic(),100);
    }

    @Test
    public void ArabicToElbonian9() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("9");
        assertEquals(converter.toElbonian(), "VY");
    }

    @Test
    public void ArabicToElbonian500() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("500");
        assertEquals(converter.toElbonian(), "D");
    }

    @Test(expected = MalformedNumberException.class)
    public void malformedNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new MalformedNumberException("TEST");
    }



    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new ValueOutOfBoundsException("0");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest2() throws ValueOutOfBoundsException, MalformedNumberException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("-100");
        converter.toElbonian();
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest3() throws ValueOutOfBoundsException, MalformedNumberException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("10000");
        converter.toElbonian();
    }



    @Test(expected = MalformedNumberException.class)
    public void malformedNumberTest2() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("XCC");
        converter.toArabic();
    }

    @Test(expected = MalformedNumberException.class)
    public void malformedNumberTest3() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("VME");
        converter.toArabic();
    }

    // TODO Add more test cases
}
