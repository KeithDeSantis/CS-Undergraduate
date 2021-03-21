public interface IBST extends ISet{
    // returns BST containing all existing elements and the given element
    IBST addElt (String elt);

    // returns the number of distinct elements in the BST
    int size ();

    // determines whether given element is in the BST
    boolean hasElt (String elt);
}
