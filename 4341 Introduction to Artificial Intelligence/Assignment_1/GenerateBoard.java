import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateBoard {



    public GenerateBoard() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        GenerateBoard generateBoard = new GenerateBoard();
        for (int i = 0; i < 10; i++) {
            generateBoard.generateBoard(6,6, i);
        }
    }

    public void generateBoard(int boundI, int boundJ, int index) throws IOException {
        String fileName = "random"+index+".txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        Random random = new Random();
        int startI = random.nextInt(boundI);
        int startJ = random.nextInt(boundI);
        int goalI = random.nextInt(boundI);
        int goalJ = random.nextInt(boundI);
        String currentString;

        for (int i = 0; i < boundI; i++) {
            for (int j = 0; j < boundJ; j++) {
                if(i == startI && j == startJ){
                    currentString = "S";
                }
                else if(i == goalI && j == goalJ){
                    currentString = "G";
                }
                else {
                    int cur = random.nextInt(9) + 1;
                    currentString = "" + cur;
                }
//                if(j == boundJ -1){
//                    fileWriter.write(cur);
//                }else{
//                    fileWriter.write(cur + "\t");
//
//                }
//                fileWriter.write(cur + "\t");
                if ((j == boundJ -1) && (i == boundI -1) ) {
                    fileWriter.write(currentString);
                }
                else if (j == boundJ -1) {
                    fileWriter.write(currentString + "\n");
                }
                else {
                    fileWriter.write(currentString + "\t");
                }

                // print a tab if its not boundJ - 1


            }
        }
        fileWriter.flush();
        fileWriter.close();

    }
}