import java.util.Scanner;

/**
 * A machine interface to handle input and output of an election.
 */
public class VotingMachine {

    private ElectionData e;
    private Scanner keyboard = new Scanner(System.in);

    public VotingMachine() {
        this.e = new ElectionData();
    }

    /**
     * Launches the screen interface for the user to vote.
     */
    public void screen() {
        e.setupBallot();
        this.e.printBallot();
        System.out.println("Who do you want to vote for, please list three names in order of preference.");
        String candidate = this.keyboard.next();
        String candidateTwo = this.keyboard.next();
        String candidateThree = this.keyboard.next();
        try {
            this.e.processVote(candidate, candidateTwo, candidateThree);
        }
        catch(UnknownCandidateException d) {
            System.out.println("The candidate " + d.getName() + " is not running.  Would you like to add them to the ballot?  Write Y or y for yes.  If not type anything else.");
            String addOrNot = keyboard.next();
            if (addOrNot.equals("Y") || addOrNot.equals("y")) {
                System.out.println(this.addWriteIn(d.getName()));
            }
            this.screen();
            return;
        }
        catch(DuplicateVotesException e) {
            System.out.println("You cannot vote for the same person twice.  Please vote again.");
            this.screen();
            return;
        }
        System.out.println("You voted for " + candidate + ", " + candidateTwo + ", and " + candidateThree);
    }

    /**
     * Adds a candidate to the ballot that a person tried to vote for and wasn't found.
     * @param cand The write in candidate.
     * @return A string explaining the outcome of the attempt to add.
     */
    public String addWriteIn(String cand) {
        try { this.e.addCandidate(cand);
              return "Candidate added."; }
        catch (CandidateExistsException e) { return "That candidate is already on the ballot."; }
    }
}
