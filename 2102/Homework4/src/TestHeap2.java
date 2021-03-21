class TestHeap2 extends DataHeap {
    IHeap left;
    IHeap right;
    TestHeap2 (int data, IHeap left, IHeap right) {
        super (data, left, right);
        this.left = left;
        this.right = right;
    }
    @Override
    public IHeap remMinElt() {
        if (this.left.height() == 0 || this.right.height() == 0) {
            return super.remMinElt();
        } else {
            return new TestHeap2 (data, this.left, this.right.remMinElt());
        }
    }
}