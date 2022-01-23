public class Main {

    public static void main(String[] args)
    {
        System.out.println("Q1 Part 2 Section a:");
        System.out.println("First expression: ( 2 * 54 )");
        StackCalculator.evaluate(new String[]{"(", "2", "*", "54", ")"});
        System.out.println("Second expression: ( 2 * ( 54 /5 ) )");
        StackCalculator.evaluate(new String[]{"(", "2", "*", "(", "54", "/", "5", ")", ")"});
        System.out.println("Third expression: ( ( 6 / 2 ) - ( 7 / 5 ) )");
        StackCalculator.evaluate(new String[]{"(", "(", "6", "/", "2", ")", "-", "(", "7", "/", "5", ")", ")"});

        System.out.println("\n\n\nQ1 Part 2 Section b:");
        System.out.println("Expression: ( 2 * 54 / 5 )\nBy order of operations, this should evaluate to 21.6.");
        StackCalculator.evaluate(new String[]{"(", "2", "*", "54", "/", "5", ")"});
        System.out.println("But it did not.");


        System.out.println("\n\n\nQ1 Part 3 Section a:");
        System.out.println("The added operators were ^ (exponential) and % (modulo).");
        System.out.println("Exponential expression: ( 2 ^ 5 )");
        StackCalculator.evaluate(new String[]{"(", "2", "^", "5", ")"});
        System.out.println("Modulo expression: ( 5 % 2 )");
        StackCalculator.evaluate(new String[]{"(", "5", "%", "2", ")"});

    }
}
