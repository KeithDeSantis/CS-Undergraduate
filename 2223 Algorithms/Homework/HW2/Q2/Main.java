import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args)
    {

        System.out.println("Input Size: 10,000 elements from 0 to 100.");

        int[] allowList = IntStream.generate( () ->
                new Random().nextInt(100)).limit(10000).toArray();
        Integer[] realInput = IntStream.of( allowList ).boxed().toArray( Integer[]::new );
        Integer[] realInputSelection = IntStream.of( allowList ).boxed().toArray( Integer[]::new );

        Heapsort hpSrt = new Heapsort();
        Selectionsort slcSort = new Selectionsort();

        hpSrt.sort(realInput);
        slcSort.sort(realInputSelection);

        /* TODO If you'd like to see the sorted array, uncomment this block
        System.out.println("Sorted Array:");
        for(int i = 1; i < realInput.length; i++)
        { System.out.println(realInput[i]); }
         */


    }
}
