public class BinarySearchRecursive {

    public static int steps = 0;

    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
    }

    public static int rank(int key, int[] a, int lo, int hi) {
        steps++;
        if(lo > hi) return -1;
        int mid = lo + (hi-lo) / 2;
        if (key < a[mid]) {
            return rank(key, a, lo, mid - 1);
        } else if (key > a[mid]) {
            return rank(key, a, mid + 1, hi);
        }
        return mid;
    }
}
