import java.util.Random;
import java.util.stream.IntStream;

public class Main
{

    public static void main(String[] args)
    {
        int[] allowList = IntStream.generate( () ->
                new Random().nextInt(10)).limit(10).toArray();
        Integer[] realInput = IntStream.of( allowList ).boxed().toArray( Integer[]::new );
        MinPQ minPQ = new MinPQ<Integer>(10);
        for(int i = 0; i < 10; i++)
        {
            minPQ.insert(realInput[i]);
        }
        System.out.println("Part 4a:");
        System.out.println("Array representation of random integer MinPQ heap:");
        minPQ.printArray();

        minPQ.delMin();
        System.out.println("Same array after removing minimum:");
        minPQ.printArray();


        Student[] studentList = new Student[10];
        studentList[0] = new Student(120, "Keith", "Sophomore");
        studentList[1] = new Student(100, "Sam", "Sophomore");
        studentList[2] = new Student(130, "Ryan", "Sophomore");
        studentList[3] = new Student(110, "Brandon", "Sophomore");
        studentList[4] = new Student(90, "Katie", "Freshman");
        studentList[5] = new Student(140, "Liz", "Freshman");
        studentList[6] = new Student(150, "Kush", "Sophomore");
        studentList[7] = new Student(160, "Talia", "Sophomore");
        studentList[8] = new Student(170, "George", "Senior");
        studentList[9] = new Student(18231, "Larry", "Junior");
        MinPQ minPQStd = new MinPQ(10);
        for(int i = 0; i < 10; i++)
        {
            minPQStd.insert(studentList[i]);
        }
        System.out.println("Part 4b:");
        System.out.println("Array representation of custom Student data type (showing ID Number) MinPQ heap:");
        minPQStd.printArray2();

        minPQStd.delMin();
        System.out.println("Same array after removing minimum:");
        minPQStd.printArray2();







    }
}
