import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {


    public static void main(String[] args) {

        MergeSort mrgSrt = new MergeSort();
        BubbleSort bblSrt = new BubbleSort();
        QuickSort qckSrt = new QuickSort();

        int[] input = IntStream.generate( () -> new Random().nextInt(1000)).limit(100).toArray();

        Integer[] realInput = IntStream.of( input ).boxed().toArray( Integer[]::new );

        bblSrt.sort(realInput);

        System.out.println("Bubblesort steps:");
        System.out.print(bblSrt.steps);
        System.out.println("\n");



        int[] inputMrg = IntStream.generate( () -> new Random().nextInt(1000)).limit(100).toArray();

        Integer[] realInputMrg = IntStream.of( input ).boxed().toArray( Integer[]::new );

        mrgSrt.sort(realInputMrg);

        System.out.println("Mergesort steps:");
        System.out.print(mrgSrt.steps);
        System.out.println("\n");



        int[] inputQck = IntStream.generate( () -> new Random().nextInt(1000)).limit(100).toArray();

        Integer[] realInputQck = IntStream.of( input ).boxed().toArray( Integer[]::new );

        qckSrt.sort(realInputQck);

        System.out.println("Quicksort steps:");
        System.out.print(qckSrt.steps);
        System.out.println("\n");

    }

}
