import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BoardReader {
    Coordinate start;
    Coordinate goal;

    public BoardReader() {
    }

    public int[][] read(String fileName, Coordinate s, Coordinate g) throws IOException {

        int numLines = 0;

        File text = new File(fileName);

        // Get number of rows
        Scanner sc = new Scanner(text);
        while(sc.hasNextLine()) {
            sc.nextLine();
            numLines++;
        }

        Scanner scanner = new Scanner(text);

        String line;

        // Look at first line to get number of columns
        String[] firstLine = scanner.nextLine().split("\t");

        int numColumns = firstLine.length;

        String[][] board = new String[numLines][numColumns];
        board[0] = firstLine; //Does this work?

        int boardRowNum = 1;

        // Load each other line into the board
        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            board[boardRowNum] = line.split("\t");
            boardRowNum++;

        }

        /*
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
         */


        findStartAndGoal(board, s, g);

        return convertToInt(board);

    }

    public Coordinate[][] translateToCoord(int[][] board) {

        int numRows = board.length;
        int numColumns = board[0].length;

        Coordinate[][] coordBoard = new Coordinate[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {

            for (int j = 0; j < numColumns; j++) {

                coordBoard[i][j] = new Coordinate(i, j);

            }

        }

        return coordBoard;

    }

    public void findStartAndGoal(String[][] board, Coordinate start, Coordinate goal) {

        int numRows = board.length;
        int numColumns = board[0].length;

        for (int i = 0; i < numRows; i++) {

            for (int j = 0; j < numColumns; j++) {

                switch (board[i][j]) {
                    case "S":
                        start.setI(i);
                        start.setJ(j);
                        board[i][j] = "1";
                        break;
                    case "G":
                        goal.setI(i);
                        goal.setJ(j);
                        board[i][j] = "1";
                        break;
                    default:
                        break;
                }
            }

        }

    }

    public int[][] convertToInt(String[][] board) {

        int[][] intBoard = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                intBoard[i][j] = Integer.parseInt(board[i][j]);
            }
        }

        return intBoard;

    }


}
