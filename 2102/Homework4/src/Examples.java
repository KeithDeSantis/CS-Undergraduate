import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import static org.junit.Assert.*;


public class Examples {

    HeapChecker heapChecker = new HeapChecker();

    DataHeap heap;
    DataHeap subHeap8;
    DataHeap subHeap9;
    DataHeap subHeap7;
    DataHeap subHeap5;
    DataHeap subHeap3;
    DataHeap subHeap2;



    //not real heap
    DataHeap fakeHeap;
    DataHeap subHeap8Fake;
    DataHeap subHeap7Fake;
    DataHeap subHeap5Fake;
    DataHeap subHeap3Fake;
    DataHeap subHeap2Fake;





    @Before
    public void setup(){
        //real heap
        subHeap8 = new DataHeap(8, new MtHeap(), new MtHeap());
        subHeap9 = new DataHeap(9, new MtHeap(), new MtHeap());

        subHeap7 = new DataHeap(7, subHeap8, subHeap9);
        subHeap5 = new DataHeap(5, subHeap8, subHeap9);

        subHeap3 = new DataHeap(3, subHeap5, subHeap7);

        subHeap2 = new DataHeap(2, subHeap3, subHeap7);

        heap = new DataHeap(1, subHeap9, subHeap2);


        //fake heap
        subHeap2Fake = new DataHeap(2, new MtHeap(), new MtHeap());

        subHeap5Fake = new DataHeap(5, subHeap2Fake, new MtHeap());
        subHeap7Fake = new DataHeap(7, new MtHeap(), subHeap5Fake);


        subHeap3Fake = new DataHeap(3, subHeap5Fake, subHeap7Fake);
        subHeap8Fake = new DataHeap(8, new MtHeap(), new MtHeap());

        fakeHeap = new DataHeap(9,subHeap8Fake, subHeap3Fake);

    }

    @Test
    public void isHeapTests(){
        assertTrue(heapChecker.isHeap(heap));
        assertTrue(heapChecker.isHeap(subHeap8));
        assertTrue(heapChecker.isHeap(subHeap7));
        assertTrue(heapChecker.isHeap(subHeap3));
        assertTrue(heapChecker.isHeap(subHeap2));
        assertFalse(heapChecker.isHeap(fakeHeap));
        assertFalse(heapChecker.isHeap(subHeap3Fake));
        assertFalse(heapChecker.isHeap(subHeap7Fake));

    }

    @Test
    public void heapToListTest(){
        LinkedList<Integer> heapList = heapChecker.heapToList(heap);
        LinkedList<Integer> heapList2 = heapChecker.heapToList(subHeap2);
        LinkedList<Integer> heapList3 = heapChecker.heapToList(subHeap3);
        LinkedList<Integer> heapList5 = heapChecker.heapToList(subHeap5);
        LinkedList<Integer> heapList8 = heapChecker.heapToList(subHeap8);

        LinkedList<Integer> heapListTest = new LinkedList<Integer>(Arrays.asList(1,9,2,3,5,8,9,7,8,9,7,8,9));
        LinkedList<Integer> heapList2Test = new LinkedList<Integer>(Arrays.asList(2,3,5,8,9,7,8,9,7,8,9));
        LinkedList<Integer> heapList3Test = new LinkedList<Integer>(Arrays.asList(3,5,8,9,7,8,9));
        LinkedList<Integer> heapList5Test = new LinkedList<Integer>(Arrays.asList(5,8,9));
        LinkedList<Integer> heapList8Test = new LinkedList<Integer>(Arrays.asList(8));

        assertEquals(heapList, heapListTest);
        assertEquals(heapList2, heapList2Test);
        assertEquals(heapList3, heapList3Test);
        assertEquals(heapList5, heapList5Test);
        assertEquals(heapList8, heapList8Test);

    }

    @Test
    public void occursListTest(){
        LinkedList<Integer> heapList = heapChecker.heapToList(heap);
        assertEquals(heapChecker.occursList(heapList, 9), 4);
        assertEquals(heapChecker.occursList(heapList, 1), 1);
        assertEquals(heapChecker.occursList(heapList, 5), 1);
        assertEquals(heapChecker.occursList(heapList, 2), 1);
        assertEquals(heapChecker.occursList(heapList, 8), 3);
        assertEquals(heapChecker.occursList(heapList, 45), 0);
        assertEquals(heapChecker.occursList(heapList, -6), 0);


    }

    @Test
    public void legitAddTest() {
        assertTrue(heapChecker.legitAdd(subHeap8, new DataHeap(8, subHeap9, new MtHeap()), 9));
        assertFalse(heapChecker.legitAdd(subHeap8, subHeap8, 9));
    }

    @Test
    public void testAddEltTester(){
        assertTrue(heapChecker.addEltTester(subHeap8,9,new DataHeap(8, subHeap9, new MtHeap())));
        assertTrue(heapChecker.addEltTester(subHeap8,9,new DataHeap(8, new MtHeap(), subHeap9)));
        assertFalse(heapChecker.addEltTester(subHeap9, 8, new DataHeap(9, subHeap8, new MtHeap())));
    }

    @Test
    public void remMinEltTester(){
        assertTrue(heapChecker.remMinEltTester(new DataHeap(8, subHeap9, new MtHeap()), subHeap9));
        assertTrue(heapChecker.remMinEltTester(new DataHeap(8, new MtHeap(), subHeap9 ), subHeap9));
    }

}
