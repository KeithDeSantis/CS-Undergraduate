public class Context {
    IReverseString reverseAlgorithm;

    public Context(IReverseString reverseAlgorithm) {
        this.reverseAlgorithm = reverseAlgorithm;
    }

    // Delegation

    public String reverse(String str) {
        return this.reverseAlgorithm.reverse(str);
    }

}
