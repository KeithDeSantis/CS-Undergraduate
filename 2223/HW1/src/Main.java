import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] comebacks = {"HAHA! I BEAT YOU AND I WILL ALWAYS BEAT YOU", "YOU FOOL YOU THOUGHT YOU COULD BEAT ME? THINK AGAIN! HAHAHAHA!","I COULD DO THIS IN MY SLEEP! (well not actually but you know what I mean)","Try your luck again and see if you can beat me and my wonderful brain coded by non other than the wonderful Kush Shah!"};
        String[] lost = {"H...How did you do that? I better get back to training!","You might have beat me this time but don't get used to it!","I let you win so that you wouldn't feel bad about yourself"};
        splashScreen();
        Board b = new Board();
        while(!b.gameOver()){
            b.play();
        }
        for(int i = 0; i < 100; i++){
            System.out.println("\n");
        }
        if(b.turn){
            System.out.println("Sorry but I guess the computer is better than you! Keep practicing! There is a message incoming from the computer! Press 'E' to accept.");
            if(s.nextLine().toUpperCase().equals("E")) {


                int picker = (int) (Math.random() * 3);
                char[] message = comebacks[picker].toCharArray();

                try {
                    for (char c : message) {
                        System.out.print(c);
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e) {
                    System.out.println("I guess the message didn't make it...");
                }
            }
        }else{
            System.out.println("Woah! You beat the computer! Good Job, you deserve a soda! There is a message incoming from the computer! Press 'E' to accept.");
            if(s.nextLine().toUpperCase().equals("E")) {


                int picker = (int) (Math.random() * 3);
                char[] message = lost[picker].toCharArray();

                try {
                    for (char c : message) {
                        System.out.print(c);
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e) {
                    System.out.println("I guess the message didn't make it...");
                }
            }
        }
    }

    public static void splashScreen(){

        System.out.println("Welcome to Double Trouble!");
        System.out.println("--------------------------\n\n\n");
        System.out.println("Press 'I' for the instructions!\n");
        System.out.println("Or press any other key to continue.\n");
        Scanner s = new Scanner(System.in);
        if(s.nextLine().toUpperCase().equals("I")){
            System.out.println("\n\n\n");
            System.out.println("Instructions");
            System.out.println("------------");
            System.out.println("There are 15 stones.");
            System.out.println("--3 green\n--7 yellow\n--5 orange");
            System.out.println("They are arranged like so:\n");
            Board instruction = new Board();
            System.out.println("Two players take turns removing as many markers of a single color as they wish.");
            System.out.println("The player who removes the last marker wins.");
            System.out.println("Press any key to begin!");
            s.nextLine();
            for(int i = 0; i < 100; i++){
                System.out.println("\n");
            }
        }else{
            for(int i = 0; i < 100; i++){
                System.out.println("\n");
            }
        }

    }


}
