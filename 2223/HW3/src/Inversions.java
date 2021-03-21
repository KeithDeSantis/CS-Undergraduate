import java.util.Arrays;
import java.util.LinkedList;

public class Inversions {

    int counterForFast = 0;

    public Inversions() {
    }

    int easyInversionCount(int[] nums) {
        int count = 0;
        int temp;
        for (int j = 0; j < nums.length; j++) {


            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[i - 1]) {
                    temp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = temp;
                    count++;
                }
            }
        }
        System.out.printf("Easy Inversion Count: %d%n",count);
        return count;
    }

    int fastInversionCount(int[] nums) {
        counterForFast =0;
        mergeSort(nums);
        System.out.printf("Fast Inversion Count: %d%n", counterForFast);
        return counterForFast;
    }

    int[] mergeSort(int[] A) {
        if (A.length < 2) {
            return A;
        }

        int mid = A.length / 2;
        int[] left = new int[mid];
        int[] right = new int[A.length - mid];

        for (int i = 0; i < A.length; i++) {
            if (i < mid) left[i] = A[i];
            else right[i - mid] = A[i];
        }

        return merge(A, mergeSort(left), mergeSort(right));

    }

    int[] merge(int[] A, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                A[k++] = left[i++];
            } else {
                A[k++] = right[j++];
                counterForFast += left.length - i;
            }
        }
        while (i < left.length) {
            A[k++] = left[i++];
        }

        while (j < right.length) {
            A[k++] = right[j++];
        }
        return A;
    }


}

