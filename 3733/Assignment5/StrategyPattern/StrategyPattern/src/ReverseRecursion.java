public class ReverseRecursion implements IReverseString{
    public String reverse(String str) {
        if (str.length() <= 1) {
            return str;
        } else {
            return reverse(str.substring(1)) + str.charAt(0);
        }
    }
}
