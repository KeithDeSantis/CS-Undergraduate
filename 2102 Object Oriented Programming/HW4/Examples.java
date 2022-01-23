import org.junit.*;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class Examples {

    //HeapChecker
    HeapChecker heapChecker = new HeapChecker();

    //Empty Heap
    MtHeap empty = new MtHeap();
    //Valid Heap without duplicates
    DataHeap heap10 = new DataHeap(10);
    DataHeap heap13 = new DataHeap(13);
    DataHeap heap23 = new DataHeap(23);
    DataHeap heap19 = new DataHeap(19, heap23, new MtHeap());
    DataHeap heap9 = new DataHeap(9, heap13, heap10);
    DataHeap heap2 = new DataHeap(2, heap19, heap9);
    //Valid Heap with duplicates
    DataHeap twoheap10 = new DataHeap(10);
    DataHeap twoheap13 = new DataHeap(13);
    DataHeap twoheap23 = new DataHeap(23);
    DataHeap twoheap19 = new DataHeap(19);
    DataHeap twoheap2two = new DataHeap(2, twoheap23, twoheap19);
    DataHeap twoheap9 = new DataHeap(9, twoheap10, twoheap13);
    DataHeap twoheap2 = new DataHeap(2, twoheap2two, twoheap9);

    // isValidHeap Tests
    @Test
    public void testIsValidHeapNoDuplicateValidHeap() {
        assertTrue(heap2.isValidHeap());
    }
    @Test
    public void testIsValidHeapWithDuplicateValidHeap() {
        assertTrue(twoheap2.isValidHeap());
    }
    @Test
    public void testIsValidHeapEmptyHeap() {
        MtHeap empty = new MtHeap();
        assertTrue(empty.isValidHeap());
    }
    @Test
    public void testIsValidHeapInvalid() {
        DataHeap badSubtree = new DataHeap(4);
        DataHeap badRoot = new DataHeap(32, badSubtree, new MtHeap());
        assertFalse(badRoot.isValidHeap());
    }
    // List Elements Tests
    @Test
    public void testListElementsWithDuplicates() {
        LinkedList<Integer> right = new LinkedList<Integer>();
        right.add(2);
        right.add(2);
        right.add(23);
        right.add(19);
        right.add(9);
        right.add(10);
        right.add(13);
        assertEquals(twoheap2.listElements(), right);
    }
    @Test
    public void testListElementsNoDuplicates() {
        LinkedList<Integer> right = new LinkedList<Integer>();
        right.add(2);
        right.add(19);
        right.add(23);
        right.add(9);
        right.add(13);
        right.add(10);
        assertEquals(heap2.listElements(), right);
    }
    @Test
    public void testListElementsEmpty() {
        MtHeap emptyHeap = new MtHeap();
        assertEquals(emptyHeap.listElements(), new LinkedList<Integer>());
    }


    // addEltTester Tests
    @Test
    public void testAddEltTesterCorrect() {
        assertTrue(heapChecker.addEltTester(heap2, 2, twoheap2));
    }
    @Test
    public void testAddEltTesterNotAHeap() {
        DataHeap badHeap = new DataHeap(10, heap2, heap9);
        assertFalse(heapChecker.addEltTester(heap2, 3, badHeap));
    }
    @Test
    public void testAddEltTesterDifferentNumberOfOccurrencesOfAnElement() {
        assertFalse(heapChecker.addEltTester(heap2, 2, heap2.addElt(2).addElt(2)));
    }
    @Test
    public void testAddEltTesterNewElementNotAdded() {
        assertFalse(heapChecker.addEltTester(heap2, 5, heap2));
    }
    @Test
    public void testAddEltTesterRandomExtraElement() {
        assertFalse(heapChecker.addEltTester(heap2, 21, heap2.addElt(21).addElt(32)));
    }
    @Test
    public void testAddEltTesterEmptyAddition() {
        assertTrue(heapChecker.addEltTester(empty, 1, new DataHeap(1)));
    }
    // remMinElt Tests
    @Test
    public void testRemMinEltCorrectDuplicateMin() {
        assertTrue(heapChecker.remMinEltTester(twoheap2, twoheap2.remMinElt()));
    }
    @Test
    public void testRemMinEltCorrectNotDuplicateMin() {
        assertTrue(heapChecker.remMinEltTester(heap2, heap2.remMinElt()));
    }
    @Test
    public void testRemMinEltNotAHeap(){
        DataHeap badHeap = new DataHeap(10, heap2, heap9);
        assertFalse(heapChecker.remMinEltTester(heap2, badHeap));
    }
    @Test
    public void testRemMinEltDifferentNumberOfOccurrencesOfAnElement() {
        assertFalse(heapChecker.remMinEltTester(heap2, heap2.remMinElt().remMinElt()));
    }
    @Test
    public void testRemMinEltElementNotRemoved() {
        assertFalse(heapChecker.remMinEltTester(heap2, heap2));
    }
    @Test
    public void testRemMinEltRandomExtraElement() {
        assertFalse(heapChecker.remMinEltTester(heap2, heap2.remMinElt().addElt(34)));
    }
    @Test
    public void testRemMinEltEmptyRemoval() {
        assertTrue(heapChecker.remMinEltTester(empty, empty));
    }
    @Test
    public void testRemMinEltRemovalCreatesEmptyHeap() {
        assertTrue(heapChecker.remMinEltTester(heap10, empty));
    }
}
