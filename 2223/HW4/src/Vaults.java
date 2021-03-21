import java.util.Arrays;

public class Vaults {


    public Vaults() {
    }

    void getMostPreciousPath(int[][] board) {
        printCave(getPathTraveled(board, findValues(board)));
    }

    int[][] findValues(int[][] grid) {
        int[][] paths = new int[grid.length][grid[0].length];
        paths[0] = grid[0];
        for (int row = 1; row < paths.length; row++) {
            for (int col = 0; col < paths[0].length; col++) {
                paths[row][col] = grid[row][col] + findMax(paths, row, col);
            }
        }
        return paths;
    }

    private int findMax(int[][] paths, int row, int col) {
        int left;
        int mid;
        int right;
        try {
            left = paths[row - 1][col - 1];
        } catch (Exception e) {
            left = 0;
        }
        try {
            right = paths[row - 1][col + 1];
        } catch (Exception e) {
            right = 0;
        }
        mid = paths[row - 1][col];
        return Math.max(Math.max(left, mid), right);
    }

    char[][] getPathTraveled(int[][] grid, int[][] obtained) {
        int lastRow = obtained.length - 1;
        int maxTreas = -1;
        int startCol = -1;
        for (int col = 0; col < obtained[lastRow].length; col++) {
            if (obtained[lastRow][col] > maxTreas) {
                maxTreas = obtained[lastRow][col];
                startCol = col;
            }
        }
        char[][] path = new char[grid.length][grid[0].length];
        initialize(path);
        int row = lastRow;
        int endCol = startCol;
        int currentTotal = maxTreas;
        path[row][endCol] = 'S';
        int step = 1;
        while (row > 0) {
            int prevTotal = currentTotal - grid[row][endCol];
            if (prevTotal == obtained[row - 1][endCol]) {
                row = row - 1;
                path[row][endCol] = (char) ((char) step + '0');
            } else if (endCol != 0 && prevTotal == obtained[row - 1][endCol - 1]) {
                row = row - 1;
                endCol = endCol - 1;
                path[row][endCol] = (char) ((char) step + '0');
            } else if (endCol != lastRow && prevTotal == obtained[row - 1][endCol + 1]) {
                row = row - 1;
                endCol = endCol + 1;
                path[row][endCol] = (char) ((char) step + '0');
            }
            step++;
            currentTotal = prevTotal;
        }
        path[0][endCol] = 'E';
        System.out.println("Bilbo has "
                            + maxTreas +
                            " gems, by starting at column "
                            + (startCol + 1) +
                            ", and ending at vault "
                            + (endCol + 1) +
                            " by following this path:");
        return path;
    }

    void initialize(char[][] a) {
        for (char[] c : a) {
            Arrays.fill(c, '.');
        }
    }

    void printCave(char[][] a) {
        int n = a.length;
        for (char[] c : a) {
            for (int col = 0; col < n; col++) {
                System.out.print(" " + c[col] + " ");
            }
            System.out.println("");
        }
    }
}

