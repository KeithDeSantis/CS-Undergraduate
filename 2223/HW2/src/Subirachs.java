import java.util.Arrays;
import java.util.LinkedList;

public class Subirachs {

    LinkedList<Integer> combo = new LinkedList<Integer>();

    int[] square = {1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15};


    public Subirachs() {
    }


    void combinationUtil(int[] data, int start, int end, int pointer, int numOfEle) {
        if (pointer == numOfEle) {
            for (int j = 0; j < numOfEle; j++) {
                combo.add(data[j]);
            }
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= numOfEle - pointer; i++) {
            data[pointer] = square[i];
            combinationUtil(data, i + 1, end, pointer + 1, numOfEle);
        }

    }

    int getNumCombinations(int numOfEle, int toEqual) {
        if (toEqual == 0 && numOfEle == 0) {
            System.out.println("[0]");
            return 1;
        }

        combinationUtil(new int[numOfEle], 0, 15, 0, numOfEle);
        int count = 0;
        while (!combo.isEmpty()) {
            int[] temp = new int[numOfEle];
            for (int i = 0; i < numOfEle; i++) {
                temp[i] = combo.removeFirst();
            }
            int sum = 0;
            for (int i :
                    temp) {
                sum += i;
            }
            if (sum == toEqual) {
                count++;
                System.out.println(Arrays.toString(temp));
            }

        }
        return count;
    }

    void getAll(boolean verbose) {
        int numOfE = 0;
        int sum = 0;

        for (; numOfE < 17; numOfE++) {
            sum = 0;
            for (; sum < 133; sum++) {

                int total_combo = getNumCombinations(numOfE, sum);
                if (total_combo != 0 && !verbose) {
                    System.out.printf("For %d elements and sum: %d there are %d combinations\n", numOfE, sum, total_combo);
                    System.out.println("---------------------");

                } else if (verbose) {
                    System.out.printf("For %d elements and sum: %d there are %d combinations\n", numOfE, sum, total_combo);
                    System.out.println("---------------------");
                }


            }
        }
    }

    int getAllForSum(int sum) {
        int totalCombo = 0;

        for (int i = 0; i < 17; i++) {
            totalCombo += getNumCombinations(i, sum);
        }
        return totalCombo;
    }

    void getMostPopularSum() {
        int max = -1;
        int mostPop = -1;
        int[] totalCombos = new int[132];
        for (int i = 0; i < totalCombos.length; i++) {
            int temp = 0;
            for (int j = 0; j < 17; j++) {
                temp += getNumCombinations(j, i);
            }
            totalCombos[i] = temp;
        }

        for (int i = 0; i < totalCombos.length; i++) {
            if (totalCombos[i] > max) {
                max = totalCombos[i];
                mostPop = i;
            }
        }
        System.out.printf("The most popular sum is %d with %d possible combinations", mostPop, max);
    }


    int[] getOccur(String str, char c) {
        int count = 0;
        char[] cArr = str.toCharArray();
        Integer[] temp = new Integer[str.length()];
        Arrays.fill(temp, -1);
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] == c) {
                temp[i] = i;
                count++;
            }
        }
        LinkedList<Integer> rem = new LinkedList<Integer>(Arrays.asList(temp));
        rem.addFirst(count);
        while (rem.contains(-1)) {
            rem.removeFirstOccurrence(-1);
        }
        int[] occ = new int[rem.size()];
        for (int i = 0; i < occ.length; i++) {
            occ[i] = rem.get(i);
        }

        return occ;
    }

    LinkedList<String> BRGC(int n) {
        if (n == 1) {
            return new LinkedList<String>(Arrays.asList("0", "1"));
        }
        LinkedList<String> L1 = BRGC(n - 1);
        int count = L1.size();
        String[] temp = new String[count];
        for (int i = 1; i <= count; i++) {
            temp[i - 1] = "1" + L1.get(count - i);
        }
        LinkedList<String> L2 = new LinkedList<String>(Arrays.asList(temp));
        for (String s :
                L1) {
            L1.set(L1.indexOf(s), "0" + s);
        }

        L1.addAll(L2);
        return L1;
    }


}