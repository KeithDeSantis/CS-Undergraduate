import java.util.Random;

class TestHeap5 extends DataHeap {
    IHeap left;
    IHeap right;
    TestHeap5(int data, IHeap left, IHeap right) {
        super (data, left, right);
        this.left = left;
        this.right = right;
    }
    @Override
    public IHeap addElt(int e) {
        Random newElt = new Random();
        return new TestHeap5 (newElt.nextInt(), this.right, this.left);
    }
}