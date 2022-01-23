package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    private final String number;

    private final int arabicValue;
    private final String elbonianValue;


    public static final int MAX_ELBONIAN = 3999; //FIXME: CHECK
    public static final int MIN_ELBONIAN = 1;

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

      if(number.trim().matches("\\d*"))
      {
          //Number in Arabic
          final int value = Integer.parseInt(number);
          if(value < MIN_ELBONIAN || value > MAX_ELBONIAN)
              throw new ValueOutOfBoundsException("Arabic number provided ( " + value + ") exceeds Elbonian bounds (" + MIN_ELBONIAN + " to " + MAX_ELBONIAN + ")");

          this.arabicValue = value;
          this.elbonianValue = toElbonian(this.arabicValue);
      }
      else //Need to verify number is in Elbonian
      {
          if(isElbonian(number.trim()))
          {
              this.arabicValue = parseElbonian(number);
              this.elbonianValue = number.trim();
          }
          else
          {
              throw new MalformedNumberException(number.trim() + " is not a valid number in either Arabic or Elbonian.");
          }
      }


        // TODO check to see if the number is valid, then set it equal to the string
        this.number = number;
    }

    private static boolean isElbonian(String str) throws MalformedNumberException //Basically b/c parsing is going to be a decent amount of code, I figure I can make a method to help
    {
        //Okay that would ve fun but would take too much time probably

        if(str.trim().length() == 0)
            throw new MalformedNumberException("String cannot be empty!");


        int counter = 0;
        char lastChar = (char) 0; //null character


        //this pains me ... this is why we parse lol
        boolean usesF = false;
        boolean usesN = false;
        boolean usesY = false;

        for(int i = 0; i < str.length(); i++)
        {
            //reference to current character
            final char curr = str.charAt(i); //It means that I cant redefine it within the loop

            final String regexTestString = "" + curr;
            final boolean isTripleUseType = regexTestString.matches("[MCXI]");

            //final = const = readonly (java c c#). Same idea, diff keyword
            if(!isTripleUseType && !("" + curr).matches("[DFLNVY]"))
                throw new MalformedNumberException("Unexpected token: " + curr);

            //Valid token, need to verify semantics
            if(lastChar == curr)
            {
                counter++;

                if(isTripleUseType && counter > 3)
                    throw new MalformedNumberException(curr + " is used more than 3 times!"); //Error checking
                else if(!isTripleUseType && counter > 1)
                    throw new MalformedNumberException(curr + " is used more than once!");
            }
            else
            {
                counter = 1;
            }

            switch (curr){

                case 'M':
                    if(lastChar != 'M' && lastChar != (char)0)
                        throw new MalformedNumberException("Tokens are not in decreasing order!");
                    break;
                case 'D':
                    if(lastChar != 'D' && lastChar != 'M' && lastChar != (char) 0)
                        throw new MalformedNumberException("Tokens are not in decreasing order!");
                    break;

                case 'L':
                    if(lastChar == 'N' || lastChar == 'X' || lastChar == 'V' || lastChar == 'Y' || lastChar == 'I')
                        throw new MalformedNumberException("Tokens are not in decreasing order!");
                    break;

                //Track if we've used these tokens
                case 'F':
                    usesF = true;

                    if(lastChar != 'F' && lastChar != 'D' && lastChar != 'M' && lastChar != (char)0)
                        throw new MalformedNumberException("Tokens are not in decreasing order!");
                    break;
                case 'N':
                    usesN = true;

                    if(lastChar == 'X' || lastChar == 'V' || lastChar == 'Y' || lastChar == 'I')
                        throw new MalformedNumberException("Tokens are not in decreasing order!");
                    break;
                case 'Y':
                    usesY = true;
                    if(lastChar == 'I')
                        throw new MalformedNumberException("Tokens are not in decreasing order!");
                    break;

                    //Check to see that we can't use conflicting tokens
                case 'C':
                    if(usesF)
                        throw new MalformedNumberException("Number cannot use both F and C!");
                    if(lastChar != 'C' && lastChar != 'F' && lastChar != 'D' && lastChar != 'M' && lastChar != (char) 0)
                        throw new MalformedNumberException("Tokens are not in decreasing order!");
                    break;
                case 'X':
                    if(usesN)
                        throw new MalformedNumberException("Number cannot use both N and X!");

                    if(lastChar == 'V' || lastChar == 'Y' || lastChar == 'I')
                        throw new MalformedNumberException("Tokens are not in decreasing order!");
                    break;
                case 'I':
                    if(usesY)
                        throw new MalformedNumberException("Number cannot use both Y and I!");
                    break;
            }

            lastChar = curr;
            //Check decreasing
            //trying to think how to do this efficiently hmm....
        }

        return true;
    }

    private static int parseElbonian(String number) throws MalformedNumberException {
        int value = 0;

        for(char c : number.toCharArray())
        {
            switch (c){
                case 'M':
                    value += 1000;
                    break;
                case 'D':
                    value += 500;
                    break;
                case 'F':
                    value += 400;
                    break;
                case 'C':
                    value += 100;
                    break;
                case 'L':
                    value += 50;
                    break;
                case 'N':
                    value += 40;
                    break;
                case 'X':
                    value += 10;
                    break;
                case 'V':
                    value += 5;
                    break;
                case 'Y':
                    value += 4;
                    break;
                case 'I':
                    value += 1;
                    break;

                default:
                    throw new MalformedNumberException("Unexpected token: " + c);
            }
        }
        return  value;
    }

    private static String toElbonian(int arabicValue)
    {
        String elbonian = "";
        int numM = 0;

        while(arabicValue >= 1000 && numM < 3)
        {
            arabicValue -= 1000;
            elbonian += "M";
            numM++;
        }

        if(arabicValue >= 500)
        {
            elbonian += "D";
            arabicValue -= 500;
        }

        if(arabicValue >= 400)
        {
            elbonian += "F";
            arabicValue -= 400;
        }
        else
        {
            int numC = 0;
            while(arabicValue >= 100  && numC < 3)
            {
                elbonian += "C";
                arabicValue -= 100;
                numC++;
            }
        }

        if(arabicValue >= 50)
        {
            elbonian += "L";
            arabicValue -= 50;
        }

        if(arabicValue >= 40)
        {
            elbonian += "N";
            arabicValue -= 40;
        }
        else
        {
            int numX = 0;

            while(arabicValue >= 10  && numX < 3)
            {
                elbonian += "X";
                arabicValue -= 10;
                numX++;
            }
        }

        if(arabicValue >= 5)
        {
            elbonian += "V";
            arabicValue -= 5;
        }

        if(arabicValue >= 4)
        {
          elbonian += "Y";
          arabicValue -= 4;
        }
        else
        {
            int numI = 0;
            while (arabicValue > 0 && numI < 3)
            {
                elbonian += "I";
                arabicValue -= 1;

                numI++;
            }
        }

        return elbonian;
    }


    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() { return this.arabicValue; }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */
    public String toElbonian() { return this.elbonianValue; }

}
