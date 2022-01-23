public class StrategyPattern {
    public static void main(String[] args) {
        // Example code demonstrating switching between the algorithms.

        ReverseRecursion reverseRecursion = new ReverseRecursion();
        Context recursionContext = new Context(reverseRecursion);

        System.out.println(recursionContext.reverse("olleH"));
        System.out.println(recursionContext.reverse("dlroW"));
        System.out.println("\n");

        ReverseStringBuffer reverseStringBuffer = new ReverseStringBuffer();
        Context stringBufferContext = new Context(reverseStringBuffer);

        System.out.println(stringBufferContext.reverse("nosliW"));
        System.out.println(stringBufferContext.reverse("gnoW"));
        System.out.println("\n");

        ReverseStringBuilder reverseStringBuilder = new ReverseStringBuilder();
        Context stringBuilderContext = new Context(reverseStringBuilder);

        System.out.println(stringBuilderContext.reverse("erawtfoS"));
        System.out.println(stringBuilderContext.reverse("gnirreenignE"));
        System.out.println("\n");
    }
}
