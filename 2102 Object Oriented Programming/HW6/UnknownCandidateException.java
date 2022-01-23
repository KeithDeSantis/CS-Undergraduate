public class UnknownCandidateException extends Exception{

    private String name;

    public UnknownCandidateException(String name) { this.name = name; }

    /**
     * A getter for the name.
     * @return The name of the unknown candidate.
     */
    public String getName() { return this.name; }
}
