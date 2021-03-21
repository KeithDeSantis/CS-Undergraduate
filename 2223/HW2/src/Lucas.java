public class Lucas {

    public Lucas() {

    }

    void printLucasNumber(int n) {
        System.out.println("Starting computations");
        while (n > -1) {
            System.out.println(getNums(n));
            n--;
        }
    }

    void lucasNumber(int n) {
        while (n > -1) {
            getNums(n);
            n--;
        }
    }

    long getNums(int num) {
        if (num <= 0) {
            return 2;
        } else if (num == 1) {
            return 1;
        }
        return getNums(num - 1) + getNums(num - 2);
    }

    double getTime(int n) {
        int start = (int) System.currentTimeMillis();
        long lucas = getNums(n);
        int end = (int) System.currentTimeMillis();
        return ((end - start) / 1000.0);
    }

    void printGetTime(int n) {
        int start = (int) System.currentTimeMillis();
        long lucas = getNums(n);
        int end = (int) System.currentTimeMillis();
        System.out.printf("n: %d = %d; Time to compute =  %f seconds\n", n, lucas, ((end - start) / 1000.0));
    }

    double getDiffTime(int n) {
        return getTime(n) - getTime(n - 1);
    }

    double getPercentDiffTime(int n) {
        return getTime(n + 1) / getTime(n);
    }
}
