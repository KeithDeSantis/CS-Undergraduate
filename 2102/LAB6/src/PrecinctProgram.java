import java.util.Scanner;
public class PrecinctProgram {


    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Precinct worcester12;
        worcester12 = new Precinct("Worcester12", "130 Winter Street", 1673);
        System.out.println(worcester12);
        System.out.println("Please input amount");
        worcester12 = worcester12.grow(keyboard.nextInt());
        System.out.println(worcester12);


        System.out.println("Please input amount for population");
        int pop = keyboard.nextInt();
        System.out.println("Please input String for name");
        String preName = keyboard.next();
        System.out.println("Please input String for address");
        String preAddr = keyboard.next();

        Precinct newPrecinct = new Precinct(preName,preAddr,pop);

        System.out.println(newPrecinct);

    }
}
