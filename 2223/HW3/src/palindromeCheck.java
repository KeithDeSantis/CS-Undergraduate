import java.util.LinkedList;

public class palindromeCheck {

    public palindromeCheck() {
    }

    boolean isPalindrome(String strToCheck, String acc) {
        if(strToCheck.length()<1){
            return true;
        }
        if(strToCheck.substring(0,1).equals(acc.substring(0,1))){
            return isPalindrome(strToCheck.substring(1), acc.substring(1));
        }else{
            return false;
        }
    }
}
