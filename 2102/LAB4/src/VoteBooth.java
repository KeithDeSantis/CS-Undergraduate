// The user interface for a voting booth

import java.util.Scanner;

class VoteBooth {
    Scanner keyboard = new Scanner(System.in);
    VoteData voteD = new VoteData();

    VoteBooth() {
        this.voteD.addCandidate("Gompei");
        this.voteD.addCandidate("Husky");
    }

    // display all the candidate names
    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : voteD.candidateList()) {
            System.out.println(s);
        }
    }

    // interact with the user to cast votes and view results
    public void screen() {
        this.printBallot();
        System.out.println("Who do you want to vote for?");
        System.out.println("Enter either a name, *W for the winner, or *R for the tally per candidate");
        String candidate = keyboard.next();
        if (candidate.equals("*R")) {
            for (String s : voteD.candidateList()) {
                System.out.println(s + " got " + voteD.countVotes(s) + " votes");
            }
        } else if (candidate.equals("*W")) {
            System.out.println("The winner is " + voteD.winner());
        } else {
            voteD.castVote(candidate);
            System.out.println("You voted for " + candidate);
            screen();
        }
    }
}