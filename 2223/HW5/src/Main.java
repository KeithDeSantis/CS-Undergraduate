import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
    ElephantsChild();
    //dijkstraAlgo();
    }

    public static void ElephantsChild(){
        System.out.println("Elephants Child");
        System.out.println("---------------");
        waitForConfirmation();
        clearConsole();
        File file = new File("ElephantsChild.txt");
        ElephantsChild sh = new ElephantsChild(123, 1000, file);
        sh.printMap();
        waitForConfirmation();
        clearConsole();
    }

    public static void dijkstraAlgo(){
        System.out.println("Dijkstra's Algorithm");
        System.out.println("--------------------");
        waitForConfirmation();
        clearConsole();
        int[][] matrix = {{0, 50, 7, 10, 0, 0, 0, 0, 0, 0},
                          {50, 0, 30, 0, 3, 0, 99, 0, 0, 0},
                          {7, 30, 0, 6, 27, 15, 0, 0, 0, 0},
                          {10, 0, 6, 0, 0, 11, 0, 0, 4, 0},
                          {0, 3, 27, 0, 0, 12, 120, 105, 0, 0},
                          {0, 0, 15, 11, 12, 0, 0, 119, 5, 0},
                          {0, 99, 0, 0, 120, 0, 0, 2, 0, 67},
                          {0, 0, 0, 0, 105, 119, 2, 0, 122, 66},
                          {0, 0, 0, 4, 0, 5, 0, 122, 0, 190},
                          {0, 0, 0, 0, 0, 0, 67, 66, 190, 0}};
        Dijkstra d = new Dijkstra(matrix);
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
