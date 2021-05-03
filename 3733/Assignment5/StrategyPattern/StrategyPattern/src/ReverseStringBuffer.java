import java.util.*;
public class ReverseStringBuffer implements IReverseString{
    public String reverse(String str) {
        String reverseString = (String) new StringBuffer(str).reverse().toString();
        return "String Buffer: " + reverseString;
    }
}
