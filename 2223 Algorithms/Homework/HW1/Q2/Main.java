import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args)
    {
        int[] allowList = IntStream.generate( () ->
                new Random().nextInt(1000)).limit(1000).toArray(); // Making a random stream of 1000 integers and putting them into an array
                                                        //Num of points
        Arrays.sort(allowList);

        int[] candidates = IntStream.generate( () ->
                new Random().nextInt(1000)).limit(100).toArray(); // Check is 100 random numbers are in the allowList

        for(int cand : candidates) {
            if(BinarySearchRecursive.rank(cand, allowList) == -1)
                System.out.format("int %d not found\n", cand);
            else
                System.out.format("int %d found!!\n", cand);
        }
        System.out.println("Steps:");
        System.out.print(BinarySearchRecursive.steps);
    }
}
