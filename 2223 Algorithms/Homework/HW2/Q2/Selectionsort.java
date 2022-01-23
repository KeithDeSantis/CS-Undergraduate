public class Selectionsort {

    public void sort(Comparable[] a)
    {
        Stopwatch timer = new Stopwatch();
        int N = a.length;
        for(int i = 0; i < N; i++)
        {
            int min = i;
            for(int j = i+1; j < N; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
        }
        double time = timer.elapsedTime();
        System.out.println("Time elapsed for Selection Sort:");
        System.out.println(time);
        return;
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
