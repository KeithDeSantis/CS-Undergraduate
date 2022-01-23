import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;

/**
 * A class to hold data and processes on an election.
 */
public class ElectionData {
    private LinkedList<String> ballot = new LinkedList<String>();
    private HashMap<String, LinkedList<Integer>> votes = new HashMap<String, LinkedList<Integer>>();
    private Scanner keyboard = new Scanner(System.in);

    public ElectionData() { }

    /**
     * A helper to set up my ballot how I want it for tests.
     */
    public void setupBallot() {
        if(ballot.size() == 0) {
            LinkedList<Integer> blankVotes = new LinkedList<Integer>();
            for (int i = 0; i < 3; i++) {
                blankVotes.add(0);
            }
            this.ballot.add("Gompei");
            votes.put("Gompei", blankVotes);
            this.ballot.add("Husky");
            votes.put("Husky", blankVotes);
            this.ballot.add("George");
            votes.put("George", blankVotes);
            this.ballot.add("Henry");
            votes.put("Henry", blankVotes);
        }
    }

    /**
     * A getter for the hashmap for testing purposes.
     * @return The hashmap.
     */
    public HashMap<String, LinkedList<Integer>> getVotes() { return this.votes; }
    /**
     * A getter for the ballot.
     */
    public LinkedList<String> getBallot() { return this.ballot; }

    /**
     * Prints out a list of the possible candidates.
     */
    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : ballot) {
            System.out.println(s);
        }
    }

    /**
     * Processes a vote and updates the hashmap.
     * @param first The first choice vote.
     * @param second The second choice vote.
     * @param third The third choice vote.
     * @throws DuplicateVotesException An exception in case the voter tries to vote for the same candidate
     * multiple times.
     * @throws UnknownCandidateException An exception in case the voter votes for a candidate that isn't running.
     */
    public void processVote(String first, String second, String third) throws DuplicateVotesException, UnknownCandidateException {
        if (this.isntValidVoteSameCandidate(first, second, third)) {throw new DuplicateVotesException(this.duplicateCandidate(first, second, third)); }
        if (this.isntValidVotesExist(first, second, third)) {throw new UnknownCandidateException(this.fakeCandidate(first, second, third)); }
        else {
            LinkedList<Integer> firstNew = new LinkedList<Integer>();
            LinkedList<Integer> secondNew = new LinkedList<Integer>();
            LinkedList<Integer> thirdNew = new LinkedList<Integer>();

            firstNew.add(this.votes.get(first).get(0) + 1);
            firstNew.add(this.votes.get(first).get(1));
            firstNew.add(this.votes.get(first).get(2));

            secondNew.add(this.votes.get(second).get(0));
            secondNew.add(this.votes.get(second).get(1) + 1);
            secondNew.add(this.votes.get(second).get(2));

            thirdNew.add(this.votes.get(third).get(0));
            thirdNew.add(this.votes.get(third).get(1));
            thirdNew.add(this.votes.get(third).get(2) + 1);

            this.votes.put(first, firstNew);
            this.votes.put(second, secondNew);
            this.votes.put(third, thirdNew);
        }
    }

    /**
     * Adds a candidate to the ballot.
     * @param newCand The new candidate.
     * @throws CandidateExistsException In case the candidate is already running.
     */
    public void addCandidate(String newCand) throws CandidateExistsException{
        if(this.votes.containsKey(newCand)) { throw new CandidateExistsException(newCand); }
        else {
            this.ballot.add(newCand);
            LinkedList<Integer> blankVotes = new LinkedList<Integer>();
            for(int i = 0; i < 3; i++) {blankVotes.add(0);}
            this.votes.put(newCand, blankVotes);
        }
    }

    /**
     * A helper to check if a vote has duplicate votes.
     * @param first The first choice.
     * @param second The second choice.
     * @param third The third choice.
     * @return True if some votes are for the same person.
     */
    public boolean isntValidVoteSameCandidate(String first, String second, String third) {
        if( first.equals(second) || first.equals(third) || second.equals(third)) {
            return true;
        }
        return false;
    }
    /**
     * A helper that finds the candidate who was voted for multiple times (assumes duplicates exist).
     * @param first The first choice.
     * @param second The second choice.
     * @param third The third choice.
     * @return The duplicated candidate's name.
     */
    public String duplicateCandidate(String first, String second, String third) {
        if(first.equals(second) || first.equals(third)) {return first;}
        return second;

    }
    /**
     * A helper to check if a vote has valid candidates.
     * @param first The first choice.
     * @param second The second choice.
     * @param third The third choice.
     * @return True if any candidate doesn't exist in the hashmap.
     */
    public boolean isntValidVotesExist(String first, String second, String third) {
        if( !this.votes.containsKey(first) || !this.votes.containsKey(second) || !this.votes.containsKey(third)) {
            return true;
        }
        return false;
    }
    /**
     * A helper that finds the candidate who wasn't on the ballot (assumes there is one).
     * @param first The first choice.
     * @param second The second choice.
     * @param third The third choice.
     * @return The nonexistent candidate's name.
     */
    public String fakeCandidate(String first, String second, String third) {
        if(this.votes.containsKey(first)) {
            if(this.votes.containsKey(second)) {
                return third;
            } else { return second; }
        } else { return first; }
    }

    /**
     * Determines which candidate won > 50% of the first choice votes.
     * @return The winning candidate.
     */
    public String findWinnerMostFirstVotes() {
        for (String cand : ballot) {
            if (this.percentOfVotes(cand, 0) > .50) {return cand;}
        }
        return "Runoff required.";
    }

    /**
     * Takes a cnadidate and a number representing which kind of vote and finds out
     * what percentage of those kinds of votes that candidate got.
     * @param candidate The candidate in question.
     * @param index A number to represent the type of vote, corresponding to the index in the value,
     * 0 for First Choice, 1 for Second Choice, 2 for Third Choice.
     * @return The percentage of votes of the specified type that the candidate won.
     */
    public double percentOfVotes(String candidate, int index) {
        int count = 0;
        int sum = 0;
        for(String cand : this.ballot) {
            count = count + this.votes.get(cand).get(index);
            if (cand.equals(candidate)) {sum = sum + this.votes.get(cand).get(index);}
        }
        return sum/count;
    }

    /**
     * Finds the candidate with the most total points.
     * @return The winning candidate.
     */
    public String findWinnerMostPoints() {
        String winner = "";
        int maxPoints = 0;
        for(String cand : this.ballot) {
            if (this.pointTally(cand)>maxPoints) {
                winner = cand;
                maxPoints = this.pointTally(cand);
            }
        }
        return winner;
    }

    /**
     * A helper for point tally that gets th total points of a candidate.
     * @param cand The candidate in question.
     * @return The candidate's points.
     */
    public int pointTally(String cand) {
        LinkedList<Integer> voteList = this.votes.get(cand);
        return 3*voteList.get(0) + 2*voteList.get(1) + voteList.get(2);
    }
}