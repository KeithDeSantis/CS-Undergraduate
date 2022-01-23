public class BubbleSort {

    public static long steps = 0; // a step is a comparison

    public void sort(Comparable[] a)
    {
        int n = a.length;
        boolean hasDoneASwap = true;
        while(hasDoneASwap)
        {
            hasDoneASwap = false;
            for(int i = 0; i < n-1; i++)
            {
                if(less(a[i+1], a[i]))
                {
                    hasDoneASwap = true;
                    Comparable temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                }
            }
        }
        return;
    }

    public boolean less(Comparable x, Comparable y)
    {
        steps++;
        boolean answer = x.compareTo(y) < 0;
        return answer;
    }

}
