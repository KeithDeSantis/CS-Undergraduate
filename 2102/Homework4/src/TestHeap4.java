class TestHeap4 extends DataHeap {
    IHeap left;
    IHeap right;
    TestHeap4(int data, IHeap left, IHeap right) {
        super (data, left, right);
        this.left = left;
        this.right = right;
    }
    @Override
    public IHeap remMinElt() {
        return new TestHeap4 (0, this.right, this.left);
    }
}