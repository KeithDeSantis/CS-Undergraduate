import java.util.LinkedList;

class VoteData {
    // a list of the candidates
    LinkedList<String> ballot = new LinkedList<String>();
    // stores the number of votes for each candidate
    LinkedList<String> votes = new LinkedList<String>();

    VoteData(){}

    // record a vote for a candidate
    void castVote(String forCandidate) {
        votes.add(forCandidate);
    }

    // add candidate to the ballot
    void addCandidate(String name) {
        ballot.add(name);
    }

    // return list of all the candidates
    LinkedList<String> candidateList() {
        return this.ballot;
    }

    // count votes for a given candidate
    public int countVotes(String forcand) {
        int numvotes = 0;
        for (String cand :
                votes) {
            if (cand.equals(forcand)) {
                numvotes++;
            }
            }

        return numvotes;
    }

    // produce the name of the candidate with the most votes
    public String winner() {
        String currWinner = ballot.get(0);
        for (String candidate : ballot) {
            if (countVotes(currWinner) < countVotes(candidate)) {
                currWinner = candidate;
            }
        }
        return currWinner;
    }

    // produce the largest number of consecutive votes for the candidate
    // within the list of votes
    public int longestStreak(String forCandidate) {
        int maxStreak = 0;  // longest sequence of votes for this candidate
        int currStreak = 0; // current consecutive votes for this candidate
        String lastVote = "";

        for (String vote : votes) {
            lastVote = vote;

            if (vote.equals(forCandidate) && lastVote.equals(forCandidate)) {
                currStreak++;
            } else {
                    currStreak = 0;
                }
            if (currStreak > maxStreak) {
                maxStreak = currStreak;
            }
        }
        return maxStreak;
    }
}


