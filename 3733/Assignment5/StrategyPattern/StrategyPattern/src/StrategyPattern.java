public class StrategyPattern {
    public static void main(String[] args) {
        String str = "This String Will Be Reversed";
        Context c = new Context(new ReverseRecursion());
        System.out.println(str+ " | " +c.getReverseAlgorithm().reverse(str));
        c.setReverseAlgorithm(new ReverseStringBuffer());
        System.out.println(str+ " | " +c.getReverseAlgorithm().reverse(str));
        c.setReverseAlgorithm(new ReverseStringBuilder());
        System.out.println(str+ " | " +c.getReverseAlgorithm().reverse(str));
        // Example code demonstrating switching between the algorithms.

    }
}
