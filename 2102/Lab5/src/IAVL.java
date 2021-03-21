public interface IAVL extends ISet {
    // returns AVL containing all existing elements and the given element
    IAVL addElt (String elt);

    // returns the number of distinct elements in the AVL
    int size ();

    // determines whether given element is in the AVL
    boolean hasElt (String elt);
}
