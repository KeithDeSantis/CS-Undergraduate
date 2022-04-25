import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Real main method

        BoardReader boardReader = new BoardReader();

        int[][] board = null;

        Coordinate start = new Coordinate(0,0);
        Coordinate goal = new Coordinate(0,0);


        String filename = args[0];
        int heuristic = 0;
        try {
            heuristic = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println("Second argument must be an integer 1-6.");
            return;
        }

        try {

            board = boardReader.read(filename, start, goal);

        } catch (IOException e) { System.out.println("Could not find file.");}

        long startTimer = System.currentTimeMillis();

        AStar test = AStarFactory.produceAstarWithSpecificHeuristics(heuristic);
        Result result = test.findPath(board, start, goal);

        System.out.println(result);

        long endTimer = System.currentTimeMillis();

        long timeElapsed = (endTimer - startTimer);

        System.out.println("Program took " + timeElapsed + " milliseconds\n");

        long mem2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("End Main Memory Used: " + mem2 + "\n"); // To print out memory use for analysis


        // Alternate main method used for data collection
        /*
        BoardReader boardReader = new BoardReader();
        int[][] board = null;
        Coordinate start = new Coordinate(0,0);
        Coordinate goal = new Coordinate(0,0);
        int[] heuristics = { 1, 2, 3, 4, 5, 6 };
        String[] files = { "board1.txt", "board2.txt", "board3.txt", "board4.txt", "board5.txt", "board6.txt", "board7.txt",
                "board8.txt", "board9.txt", "board10.txt"};

        for (String i : files) {

            System.out.println(i + " tests:\n\n");

            for (int j : heuristics) {
                System.gc();

                System.out.println("Heuristic " + j + "-------------------\n");

                try {
                    board = boardReader.read(i, start, goal);
                } catch (Exception e) {
                    System.out.println("Could not find file.");
                    return;
                }

                AStar test = AStarFactory.produceAstarWithSpecificHeuristics(j);

                long startTimer = System.currentTimeMillis();
                Result result = test.findPath(board, start, goal);
                long endTimer = System.currentTimeMillis();

                System.out.println(result);

                long timeElapsed = (endTimer - startTimer);

                System.out.println("Program took " + timeElapsed + " milliseconds\n");

                long mem2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                System.out.println("End Main Memory Used: " + mem2 + "\n"); // To print out memory use for analysis
                System.gc();

                if(j == 4) System.out.println("Optimal Path " + result.printActions() + "\n");
                else if (j ==6) System.out.println("H6 Path " + result.printActions() + "\n");

            }

            System.out.println("\n\n\n\n");

        }
        */



        // Board time testing
        /*
        long timeTaken = 0;
        int dimention = 2;
        while( timeTaken < 25.0 || timeTaken > 45.0){

            System.out.println("Dimension: " + dimention + "--------------------\n");
            BoardReader boardReader = new BoardReader();
            Coordinate start = new Coordinate(0,0);
            Coordinate goal = new Coordinate(0,0);

            GenerateBoard generateBoard = new GenerateBoard();
            generateBoard.generateBoard(dimention,dimention,1);
            int[][] board = boardReader.read("random1.txt", start, goal);

            long startTimer = System.currentTimeMillis();

            AStar test = AStarFactory.produceAstarWithSpecificHeuristics(1);
            Result result = test.findPath(board, start, goal);
            System.out.println(result);

            long endTimer = System.currentTimeMillis();

            timeTaken = (endTimer - startTimer)/1000;

            System.out.println(timeTaken + "\n");

            //dimention++;
        }
         */


    }

}
