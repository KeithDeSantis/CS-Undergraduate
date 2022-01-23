public class MergeSort{

    public static int steps = 0; // A step is a merge function call or a sort function call

    private Comparable[] aux;

    public void merge(Comparable[] a, int lo, int mid, int hi)
    {

        // Merge a[lo...mid] with a[mid+1...hi] where each of the two individual sub-arrays is ordered
        int i = lo, j = mid+1;

        for(int k = lo; k<=hi; k++) // Copy a[lo...hi] to aux[lo...hi]
        { aux[k] = a[k]; }

        for(int k = lo; k <= hi; k++) // Merge back to a[lo...hi] but in order
        {
            if (i>mid)                      a[k] = aux[j++];
            else if (j>hi)                  a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }
    }

    public void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];
        sort(a, 0, a.length-1);
    }

    public void sort(Comparable[] a, int lo, int hi)
    {
        if(hi<=lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid); //Sort left half
        sort(a, mid+1, hi); //Sort right half
        merge(a, lo, mid, hi);
    }

    public boolean less(Comparable x, Comparable y)
    {
        steps++;
        return x.compareTo(y) < 0;
    }
}
