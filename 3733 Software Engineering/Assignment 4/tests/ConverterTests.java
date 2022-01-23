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
    public void ArabicToElbonianSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("I");
        assertEquals(converter.toArabic(), 1);
    }

    @Test(expected = MalformedNumberException.class)
    public void malformedNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new MalformedNumberException("TEST");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new ValueOutOfBoundsException("0");
    }

    // TODO Add more test cases

    @Test
    public void ArabicToElbonianMiddleNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("742");
        assertEquals(converter.toElbonian(), "DCCNII");
    }
    @Test
    public void ArabicToElbonianMaxNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("3999");
        assertEquals("MMMDFLNVY",converter.toElbonian());
    }
    @Test(expected = ValueOutOfBoundsException.class)
    public void ArabicToElbonianOutOfBoundsBelow()  throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("0");
    }
    @Test(expected = ValueOutOfBoundsException.class)
    public void ArabicToElbonianOutOfBoundsAbove()  throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("4000");
    }
    @Test
    public void ElbonianToArabicMiddleNumberTest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("DCI");
        assertEquals(601,converter.toArabic());
    }
    @Test
    public void ElbonianToArabicMaxNumberTest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMMDFLNVY");
        assertEquals(3999,converter.toArabic());
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicRepeatMTest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMMM");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicRepeatCTest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("CCCC");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicRepeatXTest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("XXXX");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicRepeatITest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("IIII");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicRepeatFTest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("FF");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicRepeatNTest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("NN");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicRepeatYTest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("YY");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicRepeatDTest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("DD");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicRepeatLTest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("LL");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicRepeatVTest() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("VV");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicNoFAndC() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("FC");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicNoNAndX() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("NX");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicNoYAndI() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("YI");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicMLast() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("DFLNVYM");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicDFirst() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("DMFLNVY");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicFFirst() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("FMDLNVY");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicLFirst() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("LMDFNVY");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicNFirst() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("NMDFLVY");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicVFirst() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("VMDFLNY");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicYFirst() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("YMDFLNV");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicDFirstOther() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("DMCLXVI");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicCFirst() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("CMDLXVI");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicLFirstOther() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("LMDCXVI");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicXFirst() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("XMDLCVI");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicVFirstOther() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("VMDLCXI");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicIFirst() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("IMDLCXV");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicRandomOutOfOrder() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("LCIXVM");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicWrongCharactersCaps() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("CAIHFEI");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicWrongCharactersLower() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("CafewaE");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicWrongCharacters() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("CHW&*#GI");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicWrongCharactersSpace() throws MalformedNumberException, ValueOutOfBoundsException{
        ElbonianArabicConverter converter = new ElbonianArabicConverter("M D");
    }


}
