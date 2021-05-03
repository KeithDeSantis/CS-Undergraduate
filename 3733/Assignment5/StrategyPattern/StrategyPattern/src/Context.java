public class Context {
    IReverseString reverseAlgorithm;

    public Context(IReverseString reverseAlgorithm) {
        this.reverseAlgorithm = reverseAlgorithm;
    }

    public IReverseString getReverseAlgorithm() {
        return reverseAlgorithm;
    }

    public void setReverseAlgorithm(IReverseString reverseAlgorithm) {
        this.reverseAlgorithm = reverseAlgorithm;
    }
    // Delegation

}
