public class Functions {

    public int sum = 0;

    public void Linear()
    {
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < 100000; i++);
        double time = timer.elapsedTime();
        System.out.println("Linear Time: ");
        System.out.print(time);
        System.out.println("\n");
    }

    public void Quadratic()
    {
        Stopwatch timer2 = new Stopwatch();
        for (int i = 0; i < 100000; i++)
        {
            for (int j = 0; j < 100000; j++);
        }
        double time2 = timer2.elapsedTime();
        System.out.println("Quadratic Time: ");
        System.out.print(time2);
        System.out.println("\n");
    }

    public void Cubic()
    {
        Stopwatch timer3 = new Stopwatch();
        for (int i = 0; i < 100000; i++)
        {
            for (int j = 0; j < 100000; j++)
            {
                for(int k = 0; k < 100000; k++);
            }
        }
        double time3 = timer3.elapsedTime();
        System.out.println("Cubic Time: ");
        System.out.print(time3);
        System.out.println("\n");
    }

}
