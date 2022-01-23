public class Heapsort {

    public void sort(Comparable[] a)
    {
        Stopwatch timer = new Stopwatch();
        Comparable[] sorted = new Comparable[a.length];
        sorted[0] = 0;

        int N = a.length;
        for ( int k = N/2; k >= 1; k--) //WORKING
            sink(a, k, N);

        while(N > 1)
        {
            exch(a, 1, --N);
            sorted[N] = a[N];
            a[N] = 0;
            sink(a, 1, N);
        }

        for(int i = 1; i < sorted.length; i++)
            a[i] = sorted[i];

        double time = timer.elapsedTime();
        System.out.println("Time elapsed for Heap Sort:");
        System.out.println(time);
        return;
    }

    public void sink(Comparable[] a, int k , int N)
    {
        while(2*k < N)
        {
            int j = 2*k;
            if (j+1 < N)
                if (j < N && less(a[j], a[j+1])) j++;
            if (less(a[j], a[k])) break;
            exch(a, k, j);
            k = j;
        }
    }

    public void exch(Comparable[] a, int first, int second)
    {
        Comparable temp = a[first];
        a[first] = a[second];
        a[second] = temp;
    }

    public boolean less(Comparable a, Comparable b)
    { return a.compareTo(b) < 0; }
}
