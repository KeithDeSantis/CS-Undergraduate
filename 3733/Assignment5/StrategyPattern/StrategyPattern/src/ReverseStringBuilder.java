public class ReverseStringBuilder implements IReverseString {

    public String reverse(String str) {
        // Not safe for use by multiple threads
        StringBuilder reverseString = new StringBuilder();
        char[] strChars = str.toCharArray();

        for (int i = strChars.length - 1; i >= 0; i--) {
            reverseString.append(strChars[i]);
        }
        return "String Builder: " + reverseString.toString();
    }
}
