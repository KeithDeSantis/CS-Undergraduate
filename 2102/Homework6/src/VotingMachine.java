import java.util.Scanner;

public class VotingMachine {
    private ElectionData ed;
    Scanner keyboard = new Scanner(System.in);

    public VotingMachine(ElectionData ed) {
        this.ed = ed;
    }

    public void screen(){
        ed.printBallot();
        System.out.println("Who do you want to vote for?");
        String candidate1 = keyboard.next();
        String candidate2 = keyboard.next();
        String candidate3 = keyboard.next();

        try {
            ed.processVote(candidate1,candidate2,candidate3);
            System.out.printf("You have voted for 1)%s 2)%s 3)%s%n",candidate1,candidate2,candidate3);
        } catch (UnknownCandidateException e) {
           System.out.printf("Would you liked to add %s to the ballot%n", e.getName());
           if(keyboard.next().toUpperCase().equals("Y")){
               addWriteIn(e.getName());
               System.out.printf("%s has been added%n", e.getName());
               screen();
           }
           System.out.printf("Please vote again%n");
           screen();
        } catch (DuplicateVotesException e) {
            System.out.printf("You cannot vote for %s more than once%n", e.getName());
            screen();
        }




    }

    public void addWriteIn(String name){
        try {
            ed.addCandidate(name);
        } catch (CandidateExistsException e) {
            System.out.printf("The candidate %s all ready exists%n", e.getName());
        }

    }
}
