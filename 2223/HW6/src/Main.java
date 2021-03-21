import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Board b = new Board();

      for (int i = 1; i< 17 ;i++ ) {
        System.out.println("n="+i+":\n\t"+Arrays.toString(b.getSolution(new int[i], i)));
      }

        for (int i = 1; i< 17 ;i++ ) {
            System.out.println(b.getNumSolutions(i,false));
        }
    }

}
