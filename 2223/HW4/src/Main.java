import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to HW4!");
        System.out.println("----------------");
        waitForConfirmation();
        clearConsole();
        GE();
        waitForConfirmation();
        clearConsole();
        bilbo();

    }

    public static void GE(){
        System.out.println("Gaussian Elimination");
        System.out.println("--------------------");
        waitForConfirmation();
        Elimination e = new Elimination();
        int[][] a ={{1, 1, 1, 1, 1,  1, 1, 1},
                    {1, 2, 1, 1, 1,  1, 2, 1},
                    {1, 1, 3, 1, 1,  3, 1, 1},
                    {1, 1, 1, 4, 4,  1, 1, 1},
                    {11,1, 1, 1, 1,  1, 1, 1},
                    {1, 1, 1, 1,-1, -1,-1,-1},
                    {1, 2, 3, 4, 5,  6, 7, 8},
                    {1,-1, 1,-1, 1, -1, 1,-1}};
        int[][] l = {{1, -2}, {2,3}};
        int[] z = {-15,26};
        int[][] board1 = {{1, -2, -15},{2, 3, 26}};

        int[] b =   {0, 0, 0, 0,20, 34,-51,-6};

        float[] answer = e.betterForwardElimination(l,z);
        String[] toDisplay = new String[answer.length];
        for (int i = 0; i < answer.length; i++) {
            toDisplay[i] = String.format("%.2f",answer[i]);
        }
        for (int[] i :
                l) {
            System.out.println(Arrays.toString(i));
        }

        System.out.println("\nSolution:\n"+Arrays.toString(toDisplay));
    }

    public static void bilbo(){
        System.out.println("Unlock the Dwarf Kings Treasure");
        System.out.println("-------------------------------");
        waitForConfirmation();
        Vaults v = new Vaults();
        int[][] board = {   {98,70,73,83,97,33,44,99},
                {46,23,60,76,10,42,1,53},
                {66,52,27,5,91,94,82,30},
                {22,92,68,12,56,63,47,67},
                {13,71,48,14,78,11,89,95},
                {31,4,64,25,32,41,17,16},
                {79,38,24,49,15,6,40,74},
                {81,96,19,20,34,51,93,65} };


        for (int[] i :
                board) {
            System.out.println(Arrays.toString(i)+"");
        }
        System.out.println();
        v.getMostPreciousPath(board);
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
