import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Table {
    int size;
    Scanner sc;
    LinkedList<String> words = new LinkedList<String>();
    ArrayList<LinkedList<String>> table;
    public Table(int size, File file, boolean head) {
        System.out.println("Working with " + file.toString());
        this.size = getNextPrime(size);
        table = new ArrayList<LinkedList<String>>(size);
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            String next = sc.next().replaceAll("[[^'a-zA-Z]]", "");
                if (!words.contains(next)) {
                    words.add(next);
                }
        }

        if(head){
            buildTableHead();
        }else {
            buildTableTail();
        }

    }

    int getNextPrime(int num){
        if(prime(num)){
            return num;
        }else {
            while(!prime(num)){
                num++;
            }
        }
        return num;
    }

    boolean prime(int num){
        for(int i = 2; i <= num/2; ++i)
        {
            if(num % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    int hashWord(String word) {
        if (word.equals("NULL")) {
            return -1;
        }
        long hash = 0;
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            hash = ((hash * 123) + letters[i]) % size;
        }

        return (int) hash;
    }

    void buildTableTail(){
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<String>());
        }
        for (String word :
                words) {
            table.get(hashWord(word)).add(word);
        }
    }
    void buildTableHead(){
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<String>());
        }
        for (String word :
                words) {
            table.get(hashWord(word)).add(0, word);
        }
    }
    boolean removeFromTable(String word){
        int hash = hashWord(word);
        return table.get(hash).remove(word);

    }

    int getNonEmpty(){
        int count = 0;
        for (LinkedList<String> s :
                table) {
            if(!s.isEmpty()){
                count++;
            }
        }
        return count;
    }
    int[] getLongestStreak(boolean empty){
        int count = 0;
        int temp = 0;
        int location = 0;
        if(empty){
            for (int i = 0; i < size; i++) {
                if(table.get(i).isEmpty()){
                    temp++;
                }else {
                    if(temp>=count){
                        count = temp;
                        location = i-count;
                    }
                    temp = 0;
                }
            }
        }else{
            for (int i = 0; i < size; i++) {
                if(!table.get(i).isEmpty()){
                    temp++;
                }else {
                    if(temp>=count){
                        count = temp;
                        location = i-count;
                    }
                    temp = 0;
                }
            }
        }
        return new int[]{count,location};
    }
    int[] biggestBucket(){
        int max = 0;
        int location = 0;
        for (int i = 0; i < size; i++) {
            if(table.get(i).size()>max){
                max = table.get(i).size();
                location = i;
            }
        }
        return new int[]{location, max};
    }

    void printStats(){
        System.out.printf("\nThere are %d non-empty addresses in the table created, so the load factor is %f\n", getNonEmpty(), (float) getNonEmpty()/size);
        int[] temp = getLongestStreak(true);
        System.out.printf("The longest streak of empty is %d at location %d \n\t(There may be multiple streaks of this length)\n",temp[0],temp[1]);
        temp = getLongestStreak(false);
        System.out.printf("The longest streak of non-empty is %d at location %d \n\t(There may be multiple streaks of this length)\n",temp[0],temp[1]);
        temp = biggestBucket();
        System.out.printf("The hash address with the most words is %d with %d words \n\t(There may be multiple addresses with this number of elements)\n",temp[0],temp[1]);
    }
    void printTable(){
        for (int i = 0; i < size; i++) {
            System.out.printf("Hash Location: %d Word(s): %s\n", i,Arrays.toString(table.get(i).toArray()));
        }
        printStats();
    }











}
