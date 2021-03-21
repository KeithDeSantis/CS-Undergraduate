import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String ONE = "ElephantsChild.txt";
        String TWO = "MOBY-DICK_Gutenberg-GROOMED.txt";
        String chosenOne = "ERROR";
        Scanner sc = new Scanner(System.in);
        System.out.println("How big would you like the table to be?");
        int size = sc.nextInt();
        while (chosenOne.equals("ERROR")){
            System.out.println("Pick a text file to work with. Say 'one' for 'ElephantsChild.txt' or say 'two' for 'MOBY-DICK_Gutenberg-GROOMED.txt");
            String choice = sc.next().toUpperCase();
            if(choice.equals("ONE")){
                chosenOne = ONE;
            }else if(choice.equals("TWO")){
                chosenOne = TWO;
            }else{
                chosenOne = "ERROR";
                System.out.println("ERROR");
            }
        }
        boolean head = false;
        System.out.println("Type yes if you want the insertKey to insert at the head of the bucket\nType no if you do not.");
        String headBool = sc.next().toUpperCase();
        if(headBool.equals("YES")){
            head = true;
        }
        File file = new File(chosenOne);
        Table t = new Table(size,file,head);
        t.printTable();
        System.out.println("\n\n\n");

        File Elephant = new File(ONE);
        File Moby = new File(TWO);
        Scanner ElephantReader = null;
        Scanner MobyReader = null;
        try {
            ElephantReader = new Scanner(Elephant);
            MobyReader = new Scanner(Moby);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LinkedList<String> elephantWords = new LinkedList<String>();
        LinkedList<String> mobyWords = new LinkedList<String>();
        if(ElephantReader!=null){
            while (ElephantReader.hasNext()) {
                String next = ElephantReader.next().replaceAll("[[^'a-zA-Z]]", "");
                if (!elephantWords.contains(next)) {
                    elephantWords.add(next);
                }
            }
        }
        if(MobyReader!=null){
            while (MobyReader.hasNext()) {
                String next = MobyReader.next().replaceAll("[[^'a-zA-Z]]", "");
                if (!mobyWords.contains(next)) {
                    mobyWords.add(next);
                }
            }
        }

        Table elephant = new Table(1000,Elephant,false);
        LinkedList<String> notInElephant = new LinkedList<String>();
        for (String s :
                mobyWords) {
            if (!elephant.removeFromTable(s)) {
                notInElephant.add(s);
            }
            }
        String temp = Arrays.toString(listToArray(notInElephant));
        System.out.printf("There are %d words in 'MOBY-DICK_Gutenberg-GROOMED.txt' but aren't in 'ElephantsChild.txt' and they are \n\t%s\n\n", temp.length(),temp);
        Table moby = new Table(1000,Moby,false);
        LinkedList<String> notInMoby = new LinkedList<String>();
        for (String s :
                elephantWords) {
            if (!moby.removeFromTable(s)) {
                notInMoby.add(s);
            }
        }
        temp = Arrays.toString(listToArray(notInMoby));
        System.out.printf("There are %d words in'ElephantsChild.txt' but aren't in 'MOBY-DICK_Gutenberg-GROOMED.txt' are \n\t%s\n\n", temp.length() ,temp);


    }//end of main function


    public static String[] listToArray(LinkedList<String> l){
        String[] returned = new String[l.size()];
        for (int i = 0; i < l.size(); i++) {
            returned[i] = l.get(i);
        }
        return returned;
    }


















}//end of Main class
