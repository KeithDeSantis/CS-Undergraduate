import java.util.LinkedList;
import java.util.HashMap;

class ElectionData {
    LinkedList<String> ballot = new LinkedList<String>();
    private HashMap<Integer, int[]> hashPlacements = new HashMap<Integer, int[]>();

    ElectionData() {}

    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : ballot) {
            System.out.println(s);
        }
    }

    /**
     * Makes sure the votes are valid before adding them to the data Structure
     * @param vote1 String first choice candidate
     * @param vote2 String second choice candidate
     * @param vote3 String third choice candidate
     * @throws DuplicateVotesException is thrown if a candidate is voting for more than once
     * @throws UnknownCandidateException is thrown if a candidate is not on the ballot and therefor doesn't exist
     */
    public void processVote(String vote1, String vote2, String vote3) throws DuplicateVotesException, UnknownCandidateException {

        LinkedList<String> personalVotes = new LinkedList<String>();
        personalVotes.add(vote1);
        personalVotes.add(vote2);
        personalVotes.add(vote3);
        for (String vote : personalVotes) {
            if (!ballot.contains(vote)) {
                throw new UnknownCandidateException(vote);
            }
        }

        if (vote1.equals(vote2) || vote1.equals(vote3)) {
            throw new DuplicateVotesException(vote1);
        } else if (vote2.equals(vote3)) {
            throw new DuplicateVotesException(vote2);
        }


        for (int i = 0; i < personalVotes.size(); i++) {
            int[] ranks = hashPlacements.get(personalVotes.get(i).hashCode());
            ranks[i]++;
            hashPlacements.replace(personalVotes.get(i).hashCode(), ranks);
        }



    }

    /**
     * Add the given candidate to the ballot
     * @param name String candidate to be added
     * @throws CandidateExistsException is thrown if the given candidate is already in the ballot
     */
    public void addCandidate(String name) throws CandidateExistsException {
        if (ballot.contains(name)) {
            throw new CandidateExistsException(name);
        } else {
            ballot.add(name);
            hashPlacements.put(name.hashCode(),new int[3]);
        }
    }

    /**
     * Computes the winner using only first place votes
     * @return String candidate that won
     */
    public String findWinnerMostFirstVotes(){
        int totalVotes = 0;
        int mostFirst = -1;
        String winner = "Runoff required";
        for (String c: ballot){
            totalVotes += hashPlacements.get(c.hashCode())[0];
        }
        for(String c: ballot){
            if(hashPlacements.get(c.hashCode())[0] > (totalVotes/2)){
                return c;
            }
        }

        return winner;
    }

    /**
     * Computes winner by assigning points to different placements first place 3 points second place 2 points third place 1 point
     * @return  String candidate that won
     */
    public String findWinnerMostPoints(){
        int total = -1;
        String winner = "";
        for (String c : ballot){
            int subTotal = 0;
            for (int i = 0; i < hashPlacements.get(c.hashCode()).length; i++) {
                subTotal+=(hashPlacements.get(c.hashCode())[i])*(3-i);
            }
            if(subTotal>total){
                total=subTotal;
                winner = c;
            }
        }
        return winner;
    }


}