package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    private final String number;

    /**
     * Constructor for the ElbonianArabic class that takes a string. The string should contain a valid
     * Elbonian or Arabic numeral. The String can have leading or trailing spaces. But there should be no
     * spaces within the actual number (ie. "9 9" is not ok, but " 99 " is ok). If the String is an Arabic
     * number it should be checked to make sure it is within the Elbonian number systems bounds. If the
     * number is Elbonian, it must be a valid Elbonian representation of a number.
     *
     * @param number A string that represents either a Elbonian or Arabic number.
     * @throws MalformedNumberException Thrown if the value is an Elbonian number that does not conform
     * to the rules of the Elbonian number system. Leading and trailing spaces should not throw an error.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic number that cannot be represented
     * in the Elbonian number system.
     */
    public ElbonianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {

        // TODO check to see if the number is valid, then set it equal to the string



        this.number = number.trim();
    }

    public boolean validElbonian(String number) throws ValueOutOfBoundsException, MalformedNumberException {
        boolean rightOrder = checkElbonianOrder(number);
        boolean properContents = containsProper(number);
        return rightOrder && properContents;
    }

    public boolean containsProper(String number){
        Object[] array;

        List<Character> chars = new LinkedList();
        for (int i = 0; i < number.length(); i++) {
            chars.add(number.charAt(i));
        }
        List<Character> singleNums = new LinkedList(Arrays.asList('F','N','Y','D','L','V'));
        List<Character> multiNums = new LinkedList<>(Arrays.asList('M','C','X','I'));
        for (Character c : singleNums) {
            if(getOccur(number,c)> 1){
                return false;
            }
            if(c.equals('F') && getOccur(number,c) > 0 && chars.contains('C')){
                return false;
            }
            if(c.equals('N') && getOccur(number,c) > 0 && chars.contains('X')){
                return false;
            }
            if(c.equals('Y') && getOccur(number,c) > 0 && chars.contains('I')){
                return false;
            }
        }
        for (Character c : multiNums) {
            int lastIndex =0;
            int inRow = 0;
            while (chars.contains(c)){
                if(chars.indexOf(c) == lastIndex){
                    lastIndex = chars.indexOf(c);
                    inRow++;
                }else{
                    inRow = 0;
                }
                chars.remove(chars.indexOf(c));
            }
            if(inRow > 3){
                return false;
            }
        }

        return true;
    }

    public int getOccur(String values, char value){
        int count =0;

        for (int i = 0; i < values.length(); i++) {
            if(values.charAt(i) == value){
                count++;
            }
        }

        return count;
    }

    public boolean checkElbonianOrder(String number) throws MalformedNumberException {
        char[] nums = number.toCharArray();
        for (int i = 0; i < number.length(); i++) {
            for (int j = i; j < number.length(); j++) {
                if(toArabic(nums[i])<toArabic(nums[j])){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() throws MalformedNumberException, ValueOutOfBoundsException{
        try{
            if(Integer.parseInt(number) == Integer.parseInt(number)){
                return Integer.parseInt(number);
            }
        }catch (NumberFormatException ignored){}
        if(!validElbonian(number) || !arabicForm(number)){
            throw  new MalformedNumberException(number);
        }
        int answer = 0;

        for(char num: number.toCharArray()){
            answer = getAnswer(answer, num);
        }
        // TODO Fill in the method's body
        return answer;
    }

    private int getAnswer(int answer, char num) throws MalformedNumberException {
        switch (num){
            case('M'):
                answer+=1000;
                break;
            case ('D'):
                answer+=500;
                break;
            case('F'):
                answer+=400;
                break;
            case('C'):
                answer+=100;
                break;
            case('L'):
                answer+=50;
                break;
            case('N'):
                answer+=40;
                break;
            case('X'):
                answer+=10;
                break;
            case('V'):
                answer+=5;
                break;
            case('Y'):
                answer+=4;
                break;
            case('I'):
                answer+=1;
                break;
            default:
                throw new MalformedNumberException(Character.toString(num));
        }
        return answer;
    }

    public int toArabic(char num) throws MalformedNumberException {
        int answer = 0;
        answer = getAnswer(answer, num);
        // TODO Fill in the method's body
        return answer;
    }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */
    public String toElbonian() throws ValueOutOfBoundsException, MalformedNumberException {
        try{
            if(validElbonian(number)){
                return number;
            }
        }catch (MalformedNumberException ignored){}
        try {
            if(Integer.parseInt(number) > 3999 || Integer.parseInt(number) < 0){
                throw new ValueOutOfBoundsException(number);
            }
        }catch (NumberFormatException ex){
            throw new MalformedNumberException(number);
        }

        StringBuilder answer = new StringBuilder();

        int numberInInt = Integer.parseInt(number);

        while(numberInInt > 0){
            if(numberInInt >= 1000){
               numberInInt =  numberInInt - 1000;
               answer.append("M");
           }
           else if(numberInInt >= 500){
               numberInInt =  numberInInt - 500;
               answer.append("D");
           }
           else if(numberInInt >= 400){
               numberInInt =  numberInInt - 400;
               answer.append("F");
           }else if(numberInInt >= 100){
                numberInInt =  numberInInt - 100;
                answer.append("C");
            }
            else if(numberInInt >= 50){
                numberInInt =  numberInInt - 50;
                answer.append("L");
            }
            else if(numberInInt >= 40){
                numberInInt =  numberInInt - 40;
                answer.append("N");
            }
            else if(numberInInt >= 10){
                numberInInt =  numberInInt - 10;
                answer.append("X");
            }
            else if(numberInInt >= 5){
                numberInInt =  numberInInt - 5;
                answer.append("V");
            }
            else if(numberInInt >= 4){
                numberInInt =  numberInInt - 4;
                answer.append("Y");
            }
            else {
                numberInInt =  numberInInt - 1;
                answer.append("I");
            }
        }

        return answer.toString();
    }

    public boolean arabicForm(String number){
        if(number.contains(" ")){
            return false;
        }
        return true;
    }

}
