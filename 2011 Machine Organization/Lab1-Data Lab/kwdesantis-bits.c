/* 
 * CS:APP Data Lab 
 * 
 * Keith DeSantis kwdesantis@wpi.edu
 * 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *
 * WARNING: Do not include the <stdio.h> header; it confuses the dlc
 * compiler. You can still use printf for debugging without including
 * <stdio.h>, although you might get a compiler warning. In general,
 * it's not good practice to ignore compiler warnings, but in this
 * case it's OK.  
 */

#if 0
/*
 * Instructions to Students:
 *
 * STEP 1: Read the following instructions carefully.
 */

You will provide your solution to the Data Lab by
editing the collection of functions in this source file.

INTEGER CODING RULES:
 
  Replace the "return" statement in each function with one
  or more lines of C code that implements the function. Your code 
  must conform to the following style:
 
  int Funct(arg1, arg2, ...) {
      /* brief description of how your implementation works */
      int var1 = Expr1;
      ...
      int varM = ExprM;

      varJ = ExprJ;
      ...
      varN = ExprN;
      return ExprR;
  }

  Each "Expr" is an expression using ONLY the following:
  1. Integer constants 0 through 255 (0xFF), inclusive. You are
      not allowed to use big constants such as 0xffffffff.
  2. Function arguments and local variables (no global variables).
  3. Unary integer operations ! ~
  4. Binary integer operations & ^ | + << >>
    
  Some of the problems restrict the set of allowed operators even further.
  Each "Expr" may consist of multiple operators. You are not restricted to
  one operator per line.

  You are expressly forbidden to:
  1. Use any control constructs such as if, do, while, for, switch, etc.
  2. Define or use any macros.
  3. Define any additional functions in this file.
  4. Call any functions.
  5. Use any other operations, such as &&, ||, -, or ?:
  6. Use any form of casting.
  7. Use any data type other than int.  This implies that you
     cannot use arrays, structs, or unions.

 
  You may assume that your machine:
  1. Uses 2s complement, 32-bit representations of integers.
  2. Performs right shifts arithmetically.
  3. Has unpredictable behavior when shifting an integer by more
     than the word size.

EXAMPLES OF ACCEPTABLE CODING STYLE:
  /*
   * pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
   */
  int pow2plus1(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     return (1 << x) + 1;
  }

  /*
   * pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
   */
  int pow2plus4(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     int result = (1 << x);
     result += 4;
     return result;
  }

FLOATING POINT CODING RULES

For the problems that require you to implent floating-point operations,
the coding rules are less strict.  You are allowed to use looping and
conditional control.  You are allowed to use both ints and unsigneds.
You can use arbitrary integer and unsigned constants.

You are expressly forbidden to:
  1. Define or use any macros.
  2. Define any additional functions in this file.
  3. Call any functions.
  4. Use any form of casting.
  5. Use any data type other than int or unsigned.  This means that you
     cannot use arrays, structs, or unions.
  6. Use any floating point data types, operations, or constants.


NOTES:
  1. Use the dlc (data lab checker) compiler (described in the handout) to 
     check the legality of your solutions.
  2. Each function has a maximum number of operators (! ~ & ^ | + << >>)
     that you are allowed to use for your implementation of the function. 
     The max operator count is checked by dlc. Note that '=' is not 
     counted; you may use as many of these as you want without penalty.
  3. Use the btest test harness to check your functions for correctness.
  4. Use the BDD checker to formally verify your functions
  5. The maximum number of ops for each function is given in the
     header comment for each function. If there are any inconsistencies 
     between the maximum ops in the writeup and in this file, consider
     this file the authoritative source.

/*
 * STEP 2: Modify the following functions according the coding rules.
 * 
 *   IMPORTANT. TO AVOID GRADING SURPRISES:
 *   1. Use the dlc compiler to check that your solutions conform
 *      to the coding rules.
 *   2. Use the BDD checker to formally verify that your solutions produce 
 *      the correct answers.
 */


#endif
/* Copyright (C) 1991-2018 Free Software Foundation, Inc.
   This file is part of the GNU C Library.

   The GNU C Library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.

   The GNU C Library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.

   You should have received a copy of the GNU Lesser General Public
   License along with the GNU C Library; if not, see
   <http://www.gnu.org/licenses/>.  */
/* This header is separate from features.h so that the compiler can
   include it implicitly at the start of every compilation.  It must
   not itself include <features.h> or any other header that includes
   <features.h> because the implicit include comes before any feature
   test macros that may be defined in a source file before it first
   explicitly includes a system header.  GCC knows the name of this
   header in order to preinclude it.  */
/* glibc's intent is to support the IEC 559 math functionality, real
   and complex.  If the GCC (4.9 and later) predefined macros
   specifying compiler intent are available, use them to determine
   whether the overall intent is to support these features; otherwise,
   presume an older compiler has intent to support these features and
   define these macros by default.  */
/* wchar_t uses Unicode 10.0.0.  Version 10.0 of the Unicode Standard is
   synchronized with ISO/IEC 10646:2017, fifth edition, plus
   the following additions from Amendment 1 to the fifth edition:
   - 56 emoji characters
   - 285 hentaigana
   - 3 additional Zanabazar Square characters */
/* We do not support C11 <threads.h>.  */
/* 
 * oddBits - return word with all odd-numbered bits set to 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 8
 *   Rating: 2
 */
int oddBits(void) {
    int word = 0;
    int bitPiece = 170;
    word = word | bitPiece;
    word = word << 8;
    word = word | bitPiece;
    word = word << 8;
    word = word | bitPiece;
    word = word << 8;
    word = word | bitPiece;
  return word;
}
/*
 * isTmin - returns 1 if x is the minimum, two's complement number,
 *     and 0 otherwise 
 *   Legal ops: ! ~ & ^ | +
 *   Max ops: 10
 *   Rating: 1
 */
int isTmin(int x) {
    int inputChanged = x;
    inputChanged = inputChanged + inputChanged;
    int isZero = !x;
    int answer = inputChanged | isZero;
    return !answer;
}
/* 
 * bitXor - x^y using only ~ and & 
 *   Example: bitXor(4, 5) = 1
 *   Legal ops: ~ &
 *   Max ops: 14
 *   Rating: 1
 */
int bitXor(int x, int y) {

    int partAOfOr = (x&~y);
    int partBOfOr = (~x&y);
    int answer = ~(~partAOfOr & ~partBOfOr);
    return answer;
}
/* 
 * conditional - same as x ? y : z 
 *   Example: conditional(2,4,5) = 4
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 16
 *   Rating: 3
 */
int conditional(int x, int y, int z) {
    //if x is not 0 return y
    //if x is 0 return z
    int boolean = !!x; //So now we have 1 or 0 for x
    int allZerosOrOnes = ~boolean + 1; // This converts to 111...1 if true and 000...0 if false
    int truePart = allZerosOrOnes & y;
    int falsePart = ~allZerosOrOnes & z;
    int answer = truePart + falsePart; // Effectively "multiplying" by 0 or 1
    // based on if we want true or false to
    // return a given value
    return answer;
}
/* 
 * greatestBitPos - return a mask that marks the position of the
 *               most significant 1 bit. If x == 0, return 0
 *   Example: greatestBitPos(96) = 0x40
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 70
 *   Rating: 4 
 */
int greatestBitPos(int x) {

    //TODO
    // NOTE: *********************************************************************************************************************************************************************************
    // I had lots of trouble fitting this one into the operator limit.
    // NOTE: *********************************************************************************************************************************************************************************

    int TMin = 1 << 31;
    int isNeg = x>>31; //Returns -1 if negative, 0 otherwise
    int inputZero = !x;
    inputZero = (inputZero<<31)>>31; // returns -1 if x is 0.  0 if not.
    int isZero;
    int counter = 0;

    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;
    x = x >> 1; // If 000000... we've found our mask
    isZero = !!x; // Returns 0 if x is 0 or 1 if x is not zero
    counter = counter + isZero;

    return ((1 << counter) & ~inputZero & ~isNeg) + (isNeg & TMin);

}
/* 
 * divpwr2 - Compute x/(2^n), for 0 <= n <= 30
 *  Round toward zero
 *   Examples: divpwr2(15,1) = 7, divpwr2(-33,4) = -2
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 2
 */
int divpwr2(int x, int n) {

    int isNeg = x>>31; //-1 if negative, 0 if not

    return (isNeg & ((x + (1<<n)+ (~0))>>n)) + ((~isNeg) & (x>>n));
}
/* 
 * isNonNegative - return 1 if x >= 0, return 0 otherwise 
 *   Example: isNonNegative(-1) = 0.  isNonNegative(0) = 1.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 6
 *   Rating: 3
 */
int isNonNegative(int x) {
    return !(x >> 31); // Woo for one liners!
}
/*
 * satMul2 - multiplies by 2, saturating to Tmin or Tmax if overflow
 *   Examples: satMul2(0x30000000) = 0x60000000
 *             satMul2(0x40000000) = 0x7FFFFFFF (saturate to TMax)
 *             satMul2(0x60000000) = 0x80000000 (saturate to TMin)
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 3
 */
int satMul2(int x) {
    /*
     * Check if input is positive and output would be negative (saturate to TMax)
     * Check if input is negative and output would be positive (saturate to TMin)
     *
     * x>>31 returns -1 if x is negative and 0 if x is positive or 0
     *
     * Otherwise, do x<<1
     */
    int TMin = 1<<31; // Defining TMax and TMin for bit manipulation
    int TMax = ~TMin;
    int answerIfNotSaturated = x<<1;
    int inputNegativeAndOutputNot = (x>>31) & ~(answerIfNotSaturated>>31); // Returns -1 if input is negative and output is positive or 0
    int inputPositiveAndOutputNot = ~(x>>31) & (answerIfNotSaturated>>31); // Returns -1 if input is positive and output is negative
    return (inputNegativeAndOutputNot & TMin) + (inputPositiveAndOutputNot & TMax) +
        (answerIfNotSaturated & ~(inputPositiveAndOutputNot | inputNegativeAndOutputNot));

}
/* 
 * isLess - if x < y  then return 1, else return 0 
 *   Example: isLess(4,5) = 1.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 24
 *   Rating: 3
 */
int isLess(int x, int y) {

    int regular = x+(~y+1); // Case where there is no funkiness and the two inputs have the same sign
    int YMove = !(y>>31);
    int XMove = !(x>>31);

    // Have to protect against all overflow cases, if overflow happens the sign will flip:
    int XPYN = ((XMove|!x)) & (y>>31); // For when X is positive and Y is negative (therefore X>Y)
    int YPXN = ((YMove|!y)) & (x>>31); // For when Y is positive and X is negative (therefore Y>X)

    return ((regular>>31) & !(XPYN)) | YPXN; // If YPXN the solution is easy, but otherwise we try to run a regular calculation, but make sure they still have the same sign

}
/* 
 * isAsciiDigit - return 1 if 0x30 <= x <= 0x39 (ASCII codes for characters '0' to '9')
 *   Example: isAsciiDigit(0x35) = 1.
 *            isAsciiDigit(0x3a) = 0.
 *            isAsciiDigit(0x05) = 0.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 3
 */
int isAsciiDigit(int x) {
    int TMin = 1 << 31; //Defining TMin and TMax for bit manipulation
    int TMax = TMin + ~1 + 1;

    // Found numbers that would cause overflow or sign changes if the input is outside of our range (either lower or higher)

    int numberThatWhenAddedToWillBePositiveIfTooBig = 70 << 25;
    numberThatWhenAddedToWillBePositiveIfTooBig = numberThatWhenAddedToWillBePositiveIfTooBig >> 25;
    numberThatWhenAddedToWillBePositiveIfTooBig = numberThatWhenAddedToWillBePositiveIfTooBig & TMax;
    // This is the number that will positively overflow if 58 (0x3A) or more are added, flipping signs to TMin

    numberThatWhenAddedToWillBePositiveIfTooBig = (numberThatWhenAddedToWillBePositiveIfTooBig + x) >> 31;
    // If the number overflowed (aka the input is bigger than the desired range, this shift will turn the number to -1

    int numberThatWhenAddedToWillBeNegativeIfTooBig = ~48+1;
    // This is the number (-48) that, if 47 (0x2F) or less is added too will remain negative, but any larger number will
    // flip the sign to positive

    numberThatWhenAddedToWillBeNegativeIfTooBig = (numberThatWhenAddedToWillBeNegativeIfTooBig+x) >> 31;
    // If the number didn't flip signs (aka is smaller than the desired range) this shift will turn the number to -1

    return !(numberThatWhenAddedToWillBePositiveIfTooBig | numberThatWhenAddedToWillBeNegativeIfTooBig);
    // As long as both of our conditional variables are zero (meaning we aren't above or below the desired range)
    // then this will return 1

}
/*
 * trueThreeFourths - multiplies by 3/4 rounding toward 0,
 *   avoiding errors due to overflow
 *   Examples: trueThreeFourths(11) = 8
 *             trueThreeFourths(-9) = -6
 *             trueThreeFourths(1073741824) = 805306368 (no overflow)
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 4
 */
int trueThreeFourths(int x)
{

    //TODO
    // NOTE: ************************************************************************************************************************************************************************************************************
    // I solved this problem but I was above the operator max.
    // NOTE: ************************************************************************************************************************************************************************************************************

    // (x<<2) - (x<<0) = x * 3 OR (x << 1) + x = x * 3
    // x >> 2 = x / 4 (if x is negative this rounds away from zero, something we will take into account
    // The two lost bits when x >> 2 division is done could be 00, 01, 10, or 11.

    // If our number is positive:
    // 00 means there is no lost remainder in the division (no need to worry about rounding)
    // 01 (1) means the lost remainder is 0.25, and our calculated value will be 0 less than what we want
    // 10 (2) means the lost remainder is 0.50 and our calculated value will be 1 less than what we want
    // 11 (3) means the lost remainder is 0.75 and our calculated value will be 2 less than what we want
    // If our number is negative:
    // 00 means there is not lost remainder in the division (no need to worry about rounding)
    // 01 (1) means the lost remainder is 0.75 and our calculated value will be 3 more than what we want
    // 10 (2) means the lost remainder is 0.50 and our calculated value will be 2 more than what we want
    // 11 (3) means the lost remainder is 0.25 and our calculated value will be 1 more than what we want

    /*
     * Check if x is positive or negative
     * If positive: x >> 2, then << 2 - << 0
     * If negative: (x + (1<<2)-1) >> 2 then << 2 - << 0
     */

    int isNeg = x>>31; // Returns -1 if x is negative, 0 if x is positive or 0
    int divideByFourNegative = (x+(1<<2)+(~0))>>2; //losing rightmost two bits here, can you save them?
    int divideByFourPositive = x>>2;

    int lostBits = x & 3;
    int lostBitsAre00 = ~((!(x&3)<<31)>>31);

    int NegativeSavedBits = lostBits + (~(3 & lostBitsAre00)+1);
    int PositiveSavedBits = lostBits + (~(1 & lostBitsAre00)+1);

    int normalAnswer = ( (~isNeg & ((divideByFourPositive << 2) + (~divideByFourPositive+1) + PositiveSavedBits)) + (isNeg & ((divideByFourNegative << 2) + (~divideByFourNegative+1) + NegativeSavedBits)) );

    return normalAnswer;

}
/*
 * ilog2 - return floor(log base 2 of x), where x > 0
 *   Example: ilog2(16) = 4
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 90
 *   Rating: 4
 */
int ilog2(int x) {

    //TODO
    // NOTE: *************************************************************************************************************************************************************************************************************
    // I couldn't figure out how to do this one with my logic without loops or breaking the operator limit.
    // NOTE: *************************************************************************************************************************************************************************************************************

    // Just looking for the index of the highest true bit

    int mask = x;

    int logCounter;

    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    mask = mask>>1; // if zero we found our spot
    logCounter = logCounter + !!mask;
    return logCounter;
}
/* 
 * float_neg - Return bit-level equivalent of expression -f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   When argument is NaN, return argument.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 10
 *   Rating: 2
 */
unsigned float_neg(unsigned uf) {
    unsigned num = 1<<23; //number needed to multiply 2* NaN with to overflow UMax
    unsigned isNaN = uf*2+num; //If NaN this will return 0
    isNaN =  !isNaN; // Returns 1 if is NaN
    return ((uf^(1<<31)) * !isNaN) + (uf * isNaN);
}
/* 
 * float_i2f - Return bit-level equivalent of expression (float) x
 *   Result is returned as unsigned int, but
 *   it is to be interpreted as the bit-level representation of a
 *   single-precision floating point values.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned float_i2f(int x) {

    //TODO
    // NOTE: ******************************************************************************************************************************************
    // TA helped extensively with this problem during office hours.  Stepped through the logic with everyone in office hours and helped us figure it out.  I have tried to edit it as best as I can.
    // If that disqualifies this question for credit I understand.
    // NOTE: ******************************************************************************************************************************************

    /*
     * What sign is needed (sign bit)
     *
     * This is entirely dependent on the sign of the number
     *
     * What power of 2 is needed (exp part)
     *
     * This is dependent on the log of the number and the bias value, 127 (i.e. the index of the highest 1)
     *
     * What decimal is needed (fractional part)
     *
     * This is dependent on the xxxxxx in the "scientific notation" form of the number:
     * 0.xxxxxx
     * or
     * 1.xxxxxx
     *
     * Add all three parts (making sure they are in the right locations)
     */


    int TMin = (1 << 31);
    int firstSignBit = (x & TMin);
    int exponent = 158; // Accounts for 31 bits that make up the word
    if(x==0)        // Edge case where x is zero
    { return 0; }
    if(x==TMin)                          // Edge case where x is TMin
    { return (exponent<<23) | TMin; }

    if(x < 0)  // If x is negative we can shift it to its positive form to make calculations easier (adding the original sign bit later will fix this loss of information)
    { x = -x; }

    while(!(x & TMin)) // Gets the proper exp by dividing by 2 repeatedly until we reach the proper placement.  The proper placement occurs when x & TMin (1000...)
                      // equals something other than zero, because it means the leading 1 has reached the 31st index
    { x = x << 1; // Divide by 2 (move the decimal over 1)
      exponent = exponent - 1; } // Change the exponent by 1 for every division by 2

    // Now our number is in the form 1xxxxxxx which was can treat as 1.xxxxxxx for purposes of getting xxxxxxx

    int  caseOne = (x & 128); // Cases to handle the lost information from the upcoming right shift.
    int caseTwo = (x & 127); // Bits are going to be lost from the shift

    int SignToBeCutOff = (x & (~TMin)); // Gets the xxxx part of 1.xxxxxxx

    int fractionalPart;
    fractionalPart = (SignToBeCutOff >> 8); // This shifts the fraction into the correct positioning, loses some info to the right shift
    if (caseOne && (caseTwo > 0 || fractionalPart & 1)) // Testing the cases
    { fractionalPart = fractionalPart + 1; }// Correct the loss of info from the shift

    return (firstSignBit + (exponent << 23) + fractionalPart);
}
/* 
 * float_twice - Return bit-level equivalent of expression 2*f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representation of
 *   single-precision floating point values.
 *   When argument is NaN, return argument
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned float_twice(unsigned uf) {

    /*
     * If we can increase the exponent section by 1 it will multiply the value by 2 if normalized
     * If denormalized, we can multiply by two by moving the M section forward one
     * Conditions:
     * Check if Normalized or Denormalized
     * If +inf (return +inf)
     * If -inf (return -inf)
     * If +0 (return +0)
     * If -0 (return -0)
     * If NaN
     */

    unsigned posInf = (255<<23);
    unsigned ufShifted = uf<<1;
    unsigned TMin = 1<<31;

    if(uf == TMin || uf == 0) // Argument is -0 or +0
    { return uf; }

    if( (uf & posInf) == posInf ) // Argument is +/-inf or +/-NaN
    { return uf; }

    if(ufShifted>>24 == 0) // is denormalized
    {
        if((uf>>31) == 1) // sign bit is 1
        { return ufShifted | TMin; } // make sure to keep the sign bit
        else // sign bit is 0 (aka who cares?)
        { return ufShifted; }
    }

    //is normalized
    int toAddOneToExponentNormalized = 1 << 23;
    unsigned doubledIfNoIssuesNormalized = uf + toAddOneToExponentNormalized;
    return doubledIfNoIssuesNormalized;
}
