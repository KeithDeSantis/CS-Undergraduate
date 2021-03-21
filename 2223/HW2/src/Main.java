import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
   //     lucas();
     //   waitForConfirmation();
       // clearConsole();
        subirachs();
    }

    public static void lucas() {

        Lucas l = new Lucas();

        Scanner s = new Scanner(System.in);
        System.out.println("Give me a integer value > -1");
        l.printLucasNumber(0); //TODO: change to System.in for submission
        clearConsole();
        System.out.println("       2A");
        System.out.println("----------------");
        waitForConfirmation();
        for (int i = 0; i < 41; i++) {
            l.printGetTime(i);
        }


        System.out.println("       2B");
        System.out.println("----------------");
        waitForConfirmation();
        clearConsole();
        int n = 41;
        for (int i = 0; i < n; i++) {
            System.out.printf("\nStarting computation of n = %d\n", i);
            System.out.println(l.getPercentDiffTime(i));

        }
        System.out.println("As n increases the growth rate approaches 1.6 which is the golden ratio");


    }

    public static void subirachs() {
        Scanner scanner = new Scanner(System.in);
        Subirachs s = new Subirachs();
        System.out.println("Getting all the 4-element combinations that have the same sum as the rows/columns");
        waitForConfirmation();
        int total = s.getNumCombinations(4, 33);
        System.out.printf("The number of combinations is %d\n", total);
        waitForConfirmation();
        clearConsole();
        System.out.println("All combinations with sum 33 with n number of elements (0<=n<=16)");
        total = 0;
        for (int i = 0; i < 17; i++) {
            total += s.getNumCombinations(i, 33);
        }
        System.out.printf("The number of combinations is %d\n", total);
        waitForConfirmation();
        clearConsole();
        System.out.println("Counting the number of ways every possible sum can be formed");
        //if verbose is true then all possible sums will be displayed otherwise just combinations > 0
        System.out.println("If you would like to do the next part in verbose mode please type 'y' otherwise type 'n'");
        String input = scanner.nextLine();
        boolean verbose = false;
        if (input.toUpperCase().equals("Y")) verbose = true;
        s.getAll(verbose);
        waitForConfirmation();
        clearConsole();
        System.out.println("Getting most popular sum (the sum with the most possible combinations) ");
        s.getMostPopularSum();
    }

    public static void clearConsole() {
        for (int i = 0; i < 101; i++) {
            System.out.println("");
        }
    }

    public static void waitForConfirmation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press anything to continue to next step");
        scanner.nextLine();
    }
}
