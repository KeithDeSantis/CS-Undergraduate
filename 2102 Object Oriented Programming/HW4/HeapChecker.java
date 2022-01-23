import java.util.LinkedList;

/**
 * A class used to hold and perform methods to check if a given BT is a valid answer to performing the addElt and
 * remMinElt methods on a heap.
 */
public class HeapChecker {

    public HeapChecker() {};

    /**
     * Checks to see if the given Binary tree is a valid answer to adding the given integer to the given heap.
     * @param hOrig The original heap.
     * @param elt The integer being added.
     * @param hAdded The possible answer.
     * @return True if it is a valid answer.
     */
    public boolean addEltTester(IHeap hOrig, int elt, IBinTree hAdded) {
        boolean valid = hAdded.isValidHeap();
        boolean hasAddition = hAdded.hasElt(elt);
        boolean sameElements = this.elementComparator(hOrig, hAdded, elt);
        return (valid && hasAddition && sameElements);
    }

    /**
     * Check to see if the given binary tree is a valid answer to removing an occurrence of the minimum value of the given heap.
     * @param hOrig The original heap.
     * @param hRemoved The possible answer.
     * @return True if the given binary tree is a valid answer.
     **/
    public boolean remMinEltTester(IHeap hOrig, IBinTree hRemoved) {
        boolean valid = hRemoved.isValidHeap();
        boolean properElements = this.elementComparatorRemoval(hOrig, hRemoved);
        return (valid && properElements);
    }

    /**
     * Helper for element comparator.  Counts occurrences of an integer in a list.
     * @param intList List being looked through.
     * @param desired Int being counted.
     * @return Number of occurrences.
     */
    public int counter(LinkedList<Integer> intList, int desired) {
        int count = 0;
        for(int i = 0; i < intList.size(); i++) {
            if(intList.get(i) == desired) {
                count++;
            }
        }
        return count;
    }

    /**
     * Check if two Binary Trees have the same elements, regardless of order and excluding the added element (same number of occurences).
     * @param tree1 The first binary tree.
     * @param tree2 The second binary tree.
     * @return True if they have the same elements.
     */
    public boolean elementComparator(IBinTree tree1, IBinTree tree2, int added) {
        LinkedList<Integer> list1 = tree1.listElements();
        LinkedList<Integer> list2 = tree2.listElements();
        if(list1.contains(added)) { // If the added element is a duplicate this removes the duplicate from the second list for counting purposes
            list2.remove(list2.indexOf(added));
        }
        for(int i = 0; i < list1.size(); i++) { // For each original element
            if(list2.contains(list1.get(i))) { // Make sure it still exists
                if(! (this.counter(list1, list1.get(i)) == this.counter(list2, list1.get(i)))) { // Makes sure it has the same number of occurrences
                    return false;
                }
            } else {
                return false;
            }
        }
        if(list2.size() > list1.size() + 1) { // Make sure no extraneous elements have been added.
            return false;
        }
        return true;
    }

    /**
     * Check if two Binary Trees have the same elements, regardless of order and ignoring the removed element (same number of occurences).
     * @param tree1 The original tree.
     * @param tree2 The tree after having an element removed.
     * @return True if the trees have the same elements except for the one that's been removed.
     */
    public boolean elementComparatorRemoval(IBinTree tree1, IBinTree tree2) {
        LinkedList<Integer> list1 = tree1.listElements();
        if(!(list1.size() == 0)) { // For the case where you are trying to remove a minimum element from an empty heap
            list1.remove(list1.indexOf(this.minimumGrabber(list1))); // Removes the desired element from the original list to then compare properly
            LinkedList<Integer> list2 = tree2.listElements();
            for (int i = 0; i < list1.size(); i++) { // For each element in the original list (except the removed one)
                if (list2.contains(list1.get(i))) { // Makes sure it still exists
                    if (!(this.counter(list1, list1.get(i)) == this.counter(list2, list1.get(i)))) { // Make sure it has the same number of occurrences
                        return false;
                    }
                } else {
                    return false;
                }
            }
            if (!(list2.size() == list1.size())) { // Make sure no extraneous elements have been added or removed.
                return false;
            }
            return true;
        }
        else {
            LinkedList<Integer> list2 = tree2.listElements();
            if(list2.size()==0) { return true; } // When removing a min element from an empty heap you should produce another empty heap
            else { return false; }
        }
    }

    /**
     * Helper that gets the minimum value from a list of integers.
     * @param list The list.
     * @return The minimum integer from the list.
     */
    public int minimumGrabber(LinkedList<Integer> list) {
        int min = 1000000000;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i)<min){
                min = list.get(i);
            }
        }
        return min;
    }
}
