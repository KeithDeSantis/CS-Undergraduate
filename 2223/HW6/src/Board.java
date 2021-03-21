import java.util.Arrays;
import java.util.LinkedList;

public class Board {
    public Board() {
    }


    boolean isLegalPosition(int[] cols, int n){
        Queen[] queens = new Queen[n];
        Arrays.fill(queens, null);
        int marker = 0;
        for (int i = 0; i < cols.length; i++) {
            if(cols[i]!=0){
                queens[i] = new Queen(i,cols[i]);
                marker = i;
            }

        }
        return isLegalPositionHelper(queens, marker);
    }
    boolean isLegalPositionHelper(Queen[] queens, int marker){
        for (int i = 0; i < marker; i++) {
                Queen top = queens[i];
                Queen check = queens[marker];
                if(top != null && check != null){
                    //Re orients the top queen as (0,0) and if the check Queens row and col are the same it is on the diagonal
                    int tempRow = Math.abs(top.row - check.row);
                    int tempCol = Math.abs(top.col - check.col);
                   if(top.col == check.col || top.row == check.row || tempRow==tempCol){
                       return false;
                   }
                }
        }
        return true;
    }
    int[] nextLegalPosition(int[] cols, int n){
        int marker = 0;
        while(cols[marker]!=0){
            marker++;
        }
        int[] temp = Arrays.copyOf(cols,n);
        for (int i = 1; i <= n; i++) {
           temp[marker] = i;
           if(isLegalPosition(temp,n)){
               return temp;
           }
        }
        return cols;
    }

    int[] getSolution(int[] board, int n){
        int placedQueens = countNonZeros(board);
        int[] cols = board;
        while (placedQueens<n){
            int[] temp = nextLegalPosition(cols,n);
            if(Arrays.equals(cols, temp)){
                cols = prune(cols,placedQueens-1);
                if(cols[0]==-1){
                    return null;
                }
                placedQueens=countNonZeros(cols);
            }else {
                cols = temp;
                placedQueens++;
            }
        }
        if(!isLegalPosition(cols,n)){
            if(cols[0]==-1){
                return null;
            }
            cols = prune(cols,placedQueens-1);
            cols = getSolution(cols,n);
        }
        return cols;
    }

    int countNonZeros(int[] a){
        int count = 0;
        for (int i :
                a) {
            if (i != 0) {
                count++;
            }
            }
        return count;
    }

    int[] prune(int[] board,int index){
        if(board[index]<board.length){
            board[index]++;
        }else{
            board[index]=0;
            if(index-1>=0){
                index--;
            }else{
                Arrays.fill(board,-1);
                return board;
            }
        }

        if(index==1 && board[0]==board.length){
            Arrays.fill(board,-1);
            return board;
        }

        boolean pruned = false;
        while(!pruned){
            if(index<0){
                Arrays.fill(board,-1);
                return board;
            }
            if(board[index]+1>board.length){
                board[index]=0;
                index--;
            }else{
                board[index]++;
                if(isLegalPosition(board,board.length)){
                    pruned = true;
                }
            }
        }
        return board;
    }

    String getNumSolutions(int n, boolean printCols){
      int count = 0;
        if(n==1){
            return "There is one solution for 1-Queen Problem";
        }
        int[] board = new int[n];
        int[] prev = getSolution(board,n);
        if(printCols && prev != null){
          System.out.println(Arrays.toString(prev));
        }
        while(prev != null){
            int[] temp = Arrays.copyOf(prev,prev.length);
            temp[n-1]++;
            temp = prune(temp,n-1);
            temp = getSolution(temp,n);
            prev = temp;
            if(printCols && temp != null){
              System.out.println(Arrays.toString(temp));
            }
            count++;
        }

        StringBuilder solutionString = new StringBuilder();

        return "There are " + count+ " solutions to the " +n+"-Queens Problem";

    }
}
