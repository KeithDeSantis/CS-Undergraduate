public class CandidateExistsException extends Exception{

    private String name;

    public CandidateExistsException(String  name) { this.name = name; }

    /**
     * A getter for the name.
     * @return The name of the existing candidate.
     */
    public String getName() { return this.name; }
}
