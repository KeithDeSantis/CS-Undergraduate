public class BinarySearch {

    public static int steps = 0;

    /**
     * Searches through an array of integers and returns the key integer if it is there, if not, it returns -1.
     * @param key The desired integers.
     * @param a The array of integers.
     * @return The desired integer or -1 if that integer isn't present.
     */
    public static int rank(int key, int[] a)
    {
        int lo = 0;
        int hi = a.length-1;
        while(lo<=hi)
        {
            steps++;
            int mid = lo + (hi - lo) / 2;
            if(key < a[mid])
            { hi = mid - 1; }
            if(key > a[mid])
            { lo = mid + 1; }
            else
            { return mid; }
        }
        return -1;
    }

}
