import org.junit.Test;

public class StrategyPatternTest {

    @Test
    public void testRecursion() {
        ReverseRecursion reverseRecursion = new ReverseRecursion();
        Context context = new Context(reverseRecursion);
        System.out.println(context.reverse("tseT s'htieK"));
    }

    @Test
    public void testStringBuffer() {
        ReverseStringBuffer reverseStringBuffer = new ReverseStringBuffer();
        Context context = new Context(reverseStringBuffer);
        System.out.println(context.reverse("91-DIVOC"));
    }

    @Test
    public void testStringBuilder() {
        ReverseStringBuilder reverseStringBuilder = new ReverseStringBuilder();
        Context context = new Context(reverseStringBuilder);
        System.out.println(context.reverse("tsniagA esiR yb yawA efiL gniwS"));
    }

}
