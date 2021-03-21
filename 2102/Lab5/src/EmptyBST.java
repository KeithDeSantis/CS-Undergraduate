class EmptyBST implements IBST  {
    EmptyBST() {}

    // returns the number of distinct elements in the BST
    public int size () { return 0; }

    // returns BST containing all existing elements and the given element
    public IBST addElt (String elt) {
        return new DataBST(elt, new EmptyBST(), new EmptyBST());
    }

    // determines whether the given element is in the BST
    public boolean hasElt (String elt) { return false; }
}