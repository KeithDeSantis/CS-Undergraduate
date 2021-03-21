import java.util.Arrays;
import java.util.LinkedList;

public class BinaryReflectiveGrayCode {


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

    int[] findChange(String code1, String code2){
        char[] code1Arr = code1.toCharArray();
        char[] code2Arr = code2.toCharArray();
        int[] answer = new int[2];
        for (int i = 0; i < code1Arr.length; i++) {
            if(code1Arr[i]!=code2Arr[i]){
                answer[0]=i;
                if(code2Arr[i]=='1'){
                    answer[1]=1;
                }
                return answer;
            }
        }
        return null;
    }

    LinkedList<String> getAction(LinkedList<String> code){
       int numChanges = code.size()-1;
       int[][] changes = new int[numChanges][2];
        for (int i = 0; i <numChanges; i++) {
            changes[i] = findChange(code.get(i), code.get(i+1));
        }
        LinkedList<String> names = new LinkedList<String>();
        if(getOccur(code.getFirst(), '1') == 0){
            names.add("");
        }
        for (int[] change :
                changes) {
            String action = "";
            if(change[1]==1){
                action= " In";
            }else{
                action=" Out";
            }
            if (change[0] == 3) {
                names.add("Alice" + action);
            }else if(change[0]==2){
                names.add("Bob" + action);
            }else if(change[0]==1){
                names.add("Chris" + action);
            }else if (change[0]==0){
                names.add("Dylan" + action);
            }else{
                System.out.println("I think something went wrong");
            }
            }

        return names ;
    }

    String whoIsInPicture(String code){
            String answer = "";
            char[] codeArr = code.toCharArray();
        for (int i = 0; i < code.length(); i++) {
            if(codeArr[i]=='1'){
                answer+="& ";
                if(i==0){
                    answer+= "Dylan ";
                }if(i==1){
                    answer+="Chris ";
                }if(i==2){
                    answer+="Bob ";
                }if(i==3){
                    answer+="Alice ";
                }
            }
        }
        if(answer.equals("")){
            return "A lovely landscape";
        }else return answer.substring(2);
    }

    void printTable(int n){
        String[] titles = {" Index "," Gray Code ", " Child(ren) in Photo "," Action "};

        LinkedList<String> codes = BRGC(n);
        int totalRows = codes.size();
        String[] index = new String[totalRows];
        for (int i = 0; i < codes.size(); i++) {
            index[i]="" + i;
        }
        String[] gc = new String[totalRows];
        for (int i = 0; i < totalRows; i++) {
            gc[i]= BRGC(n).get(i);
        }
        String[] inPhoto = new String[totalRows];
        for (int i = 0; i < totalRows; i++) {
            inPhoto[i]=whoIsInPicture(gc[i]);
        }

        LinkedList<String> actionList = getAction(codes);
        String[] action = new String[actionList.size()];
        for (int i = 0; i < actionList.size(); i++) {
            action[i]= actionList.get(i);
        }

        int rowCount = 0;
        for(int i = 0; i < 4; i++){
            System.out.print(titles[i] + "|");
        }
        System.out.println("");
        System.out.println("--------------------------------------------");
        while (rowCount<codes.size()){
            System.out.println(index[rowCount]  + "      " + "|" + " " +
                                  gc[rowCount]  + "      " + "|" + " " +
                             inPhoto[rowCount]  + "  " + "|" + " " +
                              action[rowCount]);
            rowCount++;
            System.out.println("");
        }


    }

    int getOccur(String str, char c) {
        int count = 0;
        char[] cArr = str.toCharArray();
        for (char value : cArr) {
            if (value == c) {
                count++;
            }
        }



        return count;
    }
}
