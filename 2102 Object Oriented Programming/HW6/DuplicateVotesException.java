public class DuplicateVotesException extends Exception {

    private String name;

    public DuplicateVotesException(String name) { this.name = name; }

    /**
     * A getter for the name.
     * @return The name of the duplicate candidate.
     */
    public String getName() { return this.name; }
}
