public class UnknownCandidateException extends Exception {

    private String name;

    public UnknownCandidateException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
