import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class ElephantsChild {

    int c;
    int m;
    Scanner sc;
    LinkedList<String> words = new LinkedList<String>();
    String[] hashedWords = new String[1000];
    int[] frequency = new int[1000];

    public ElephantsChild(int c, int m, File file) {
        this.c = c;
        this.m = m;
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
        Arrays.fill(hashedWords, "NULL");
        Arrays.fill(frequency, 0);
        fillFrequency();
    }

    void buildMap() {
        for (String word :
                words) {
            int hash = (int) hashWord(word);
            if (!hashedWords[hash].equals("NULL")) {
                while (!hashedWords[hash].equals("NULL")) {
                    if (hash+1 >= hashedWords.length) {
                        hash = 0;
                    } else {
                        hash++;
                    }
                }
            }
            hashedWords[hash] = word;
        }
    }

    void printMap() {
        buildMap();
        for (int i = 0; i < hashedWords.length; i++) {
            System.out.printf("Hash Address: %d Hashed Word: %s Hashed Value: %s\n", i, hashedWords[i], hashWord(hashedWords[i]));
        }
        System.out.printf("\nThere are %d non-empty addresses in the table. The load factor is %.03f\n",
                hashedWords.length - countNull(), (float)(hashedWords.length - countNull())/hashedWords.length);
        System.out.printf("The longest streak of empty area is %d which begins at %d\n",
                countStreak("NULL")[0],countStreak("NULL")[1]-countStreak("NULL")[0]+1);
        System.out.printf("The longest streak of non-empty area is %d which begins at %d\n",
                countStreak("ANYTHING123456")[0],countStreak("ANYTHING123456")[1]-countStreak("ANYTHING123456")[0]+1);
        System.out.printf("The hash address that has the most words is %d with %d words\n",
                getMaxWordCount()[1], getMaxWordCount()[0]);
        System.out.println("\t-The words are: " + Arrays.toString(getWordsWithHash( getMaxWordCount()[1])));
        System.out.printf("The word that is most displaced is '%s' with hash value %d and is placed at %d a distance of %d away from its original hash value\n",
                getMaxDistance(), hashWord(getMaxDistance()),getIndexOf(getMaxDistance()),getIndexOf(getMaxDistance())- hashWord(getMaxDistance()));
    }

    int countNull() {
        int count = 0;
        for (String word :
                hashedWords) {
            if (word.equals("NULL")) {
                count++;
            }
        }
        return count;
    }

    int[] countStreak(String lookingFor) {
        int count = 0;
        int tempCount = 0;
        int index = 0;
        for (int i = 0; i< hashedWords.length;i++) {
                String word = hashedWords[i];
            if (lookingFor.equals("ANYTHING123456")) {
                if (!word.equals("NULL")) {
                    tempCount++;
                    if (tempCount > count) {
                        count = tempCount;
                        index = i;
                    }
                } else {
                    tempCount = 0;
                }
            } else if (word.equals(lookingFor)) {
                tempCount++;
                if (tempCount > count) {
                    count = tempCount;
                    index = i;
                }
            } else {
                tempCount = 0;
            }

        }
        return new int[]{count, index};
    }

    void fillFrequency() {
        for (String word :
                words) {
            frequency[(int) hashWord(word)]++;
        }
    }

    int[] getMaxWordCount() {
        int max = 0;
        int pos = 0;
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > max) {
                max = frequency[i];
                pos = i;
            }
        }
        return new int[]{max, pos};
    }

    int getDistance(String word) {
        int hash = hashWord(word);
        LinkedList<String> hashTable = new LinkedList<String>(Arrays.asList(hashedWords));
        int hashPos = hashTable.indexOf(word);
        if (hashPos < hash) {
            hashPos += hashedWords.length;
        }

        return hashPos - hash;
    }

    String getMaxDistance() {
        int max = 0;
        String theWord = "";
        for (String word :
                words) {
            if (getDistance(word) > max) {
                max = getDistance(word);
                theWord = word;
            }

        }
        return theWord;
    }

    String[] getWordsWithHash(int hash){
        LinkedList<String> listOfWords = new LinkedList<String>();
        for (String word :
                words) {
            if (hashWord(word) == hash) {
                listOfWords.add(word);
            }
            }
        String[] arrayOfWords = new String[listOfWords.size()];
        for (int i = 0; i < arrayOfWords.length; i++) {
            arrayOfWords[i] = listOfWords.get(i);
        }
        return arrayOfWords;
    }

    int getIndexOf(String word){
        for (int i = 0; i < hashedWords.length; i++) {
            if(hashedWords[i].equals(word)){
                return i;
            }
        }
        return -1;
    }

    int hashWord(String word) {
        if (word.equals("NULL")) {
            return -1;
        }
        long hash = 0;
        char[] letters = word.toCharArray();
        for (char letter : letters) {
            hash = ((hash * c) + letter) % m;

        }
        return (int) hash;
    }
}



