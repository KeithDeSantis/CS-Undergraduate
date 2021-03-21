import java.util.LinkedList;

public class HeapChecker {

    /**
     * Checks to see if the input is a a valid heap (smallest element on top)
     *
     * @param heap of type IBinTree
     * @return boolean if @param heap is valid heap or not
     */
    public boolean isHeap(IBinTree heap) {
        if (!heap.hasData()) {
            return false;
        }
        boolean leftNull = !heap.getLeft().hasData();

        boolean rightNull = !heap.getRight().hasData();

        if (!rightNull) {

            if (heap.getData() <= heap.getRight().getData()) {
                if (!isHeap(heap.getRight())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        if (!leftNull) {

            if (heap.getData() <= heap.getLeft().getData()) {
                if (!isHeap(heap.getLeft())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts the input heap to a LinkedList of Integers
     *
     * @param heap type IBinTree
     * @return LinkedList<Integer> containing all the values in the heap (goes down left most branch first)
     */
    public LinkedList<Integer> heapToList(IBinTree heap) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        if (!heap.hasData()) {
            return list;
        }
        list.add(heap.getData());
        if (heap.getLeft().hasData()) {
            list.addAll(heapToList(heap.getLeft()));
        }
        if (heap.getRight().hasData()) {
            list.addAll(heapToList(heap.getRight()));
        }
        return list;
    }

    /**
     * Gets the number of occurrences for the given element in the input heap
     *
     * @param heap type IBinTree containing all data
     * @param e    the element that we are looking for
     * @return int number of times @param e appears in @param heap
     */
    public int occursTree(IBinTree heap, int e) {
        LinkedList<Integer> list = heapToList(heap);

        return occursList(list, e);
    }

    /**
     * Gets the number of occurrences for the given element in the input list
     *
     * @param list type LinkedList<Integer> containing all data
     * @param e    the element that we are looking for
     * @return int number of times @param e appears in @param list
     */
    public int occursList(LinkedList<Integer> list, int e) {
        int occurrences = 0;
        for (Integer i : list) {
            if (i == e) {
                occurrences++;
            }
        }
        return occurrences;
    }

    /**
     * Test to see if the addition of an element was done correctly
     *
     * @param hOrig  type IHeap original data
     * @param elt    type int the data to be added to @param hOrig
     * @param hAdded type IBinTree should be equivalent to hOrig with the exception of @param e
     * @return boolean true if the addition of @param e to @param hOrig results in @param hAdded
     */
    public boolean addEltTester(IHeap hOrig, int elt, IBinTree hAdded) {

        if (!isHeap(hAdded) || !hAdded.hasElt(elt) || !legitAdd(hOrig, hAdded, elt)) {
            return false;
        }
        return occursTree(hOrig, elt) < occursTree(hAdded, elt);

    }

    /**
     * Test to see if the removal of the smallest element was done correctly
     *
     * @param hOrig    type IHeap original data
     * @param hRemoved type IBinTree should be equivalent to @param hOrig with the exception of the smallest item in @param hOrig
     * @return boolean true if the removal of the smallest item from @param hOrig results in @param hAdded
     */
    public boolean remMinEltTester(IHeap hOrig, IBinTree hRemoved) {

        if (!isHeap(hRemoved) || !hOrig.hasData() || hRemoved.getData() == hOrig.getData() || !legitRemove(hOrig, hRemoved, hOrig.getData())) {
            return false;
        }

        return occursTree(hOrig, hOrig.getData()) > occursTree(hRemoved, hOrig.getData());

    }

    /**
     * Checks to see that no changes were made to the heap except for the addition of the given element
     *
     * @param original type IBinTree tree without the added element
     * @param changed  type IBinTree with the added element
     * @param e        type int the element to be added to @param original which should then be equivalent to @param changed
     * @return boolean true if no changes were made other than the addition of @param e
     */
    public boolean legitAdd(IBinTree original, IBinTree changed, int e) {
        LinkedList<Integer> origList = heapToList(original);
        LinkedList<Integer> changedList = heapToList(changed);

        if (occursList(origList, e) + 1 != occursList(changedList, e)) {
            return false;
        }

        for (Integer i : origList) {
            if (e != i) {
                if (occursList(origList, i) != occursList(changedList, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks to see that no changes were made to the heap except for the removal of the smallest element
     *
     * @param original type IBinTree tree with the smallest element
     * @param changed  type IBinTree tree without the smallest element
     * @param e        type int the element to be removed (the smallest) from @param original which should then be equivalent to @param changed
     * @return boolean true if no changes were made other than the removal of @param e from @param original
     */
    public boolean legitRemove(IBinTree original, IBinTree changed, int e) {
        LinkedList<Integer> origList = heapToList(original);
        LinkedList<Integer> changedList = heapToList(changed);

        if (occursList(origList, e) - 1 != occursList(changedList, e)) {
            return false;
        }

        for (Integer i : origList) {
            if (e != i) {
                if (occursList(origList, i) != occursList(changedList, i)) {
                    return false;
                }
            }
        }
        return true;

    }
}
