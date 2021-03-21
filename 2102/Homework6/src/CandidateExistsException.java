public class CandidateExistsException extends Exception {
    private String name;

    public CandidateExistsException(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
