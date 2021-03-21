import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Homework 3!");
        System.out.println("-----------------------");
        clearConsole();
        palindrome();
        waitForConfirmation();
        clearConsole();
        inversions();
        waitForConfirmation();
        clearConsole();
        BRGC();

    }

    public static void palindrome() {
        System.out.println("Palindrome");
        System.out.println("----------");
        Scanner s = new Scanner(System.in);
        System.out.println("Which string would you like to check?");
        String strToCheck = s.nextLine();
        palindromeCheck p = new palindromeCheck();
        String check = "";
        for (int i = strToCheck.length(); i > 0; i--) {
            check+=strToCheck.toCharArray()[i-1];
        }

        if (p.isPalindrome(strToCheck.replaceAll("\\s+","").replaceAll("\\p{Punct}","").toUpperCase(),check.replaceAll("\\s+","").replaceAll("\\p{Punct}","").toUpperCase())){
            System.out.printf("%s is a palindrome%n", strToCheck);
        } else {
            System.out.printf("%s isn't a palindrome%n", strToCheck);
        }

    }

    public static void inversions() {
        System.out.println("Inversions");
        System.out.println("----------");
        Inversions i = new Inversions();
        int[][] arrays = {new int[]{3, 2, 1}, new int[]{4, 3, 2, 1}, new int[]{4, 3, 2, 1, 8, 7, 6, 5}};
        int[][] FastArrays = {new int[]{3, 2, 1}, new int[]{4, 3, 2, 1}, new int[]{4, 3, 2, 1, 8, 7, 6, 5}};


        for (int j = 0; j < 3; j++) {

            System.out.printf("Getting inversions for %s%n", Arrays.toString(arrays[j]));
            i.easyInversionCount(arrays[j]);
            i.fastInversionCount(FastArrays[j]);


        }


    }

    public static void BRGC(){
        System.out.println("Binary Reflective Gray Code");
        System.out.println("----------");
        System.out.println("Only works for 4 children");
        BinaryReflectiveGrayCode b = new BinaryReflectiveGrayCode();
        b.printTable(4);
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
