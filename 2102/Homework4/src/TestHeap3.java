class TestHeap3 extends DataHeap {
    IHeap left;
    IHeap right;
    TestHeap3(int data, IHeap left, IHeap right) {
        super (data, left, right);
        this.left = left;
        this.right = right;
    }
    @Override
    public IHeap addElt(int e) {
        return this.merge(new DataHeap(e, new DataHeap(e, new MtHeap(), new
                MtHeap()), new MtHeap()));
    }
}