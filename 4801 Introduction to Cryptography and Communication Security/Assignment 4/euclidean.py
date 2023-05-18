import math
import sys

# Keith DeSantis
# Assignment 3 Question 2.d

print("This is a script written by Keith DeSantis for Assignment 3 of Intro to Cryptography.")

# Perform the Euclidean Algorithm and Return True if the numbers are relatively prime
# (gcd is 1)
def EuclideanAlgorithm(num1,num2):

    # Variable to track last addition, used when algorithm ends to check gcd
    previousAddition = None

    # Find the larger number and start setting values to equation:

    # left = right * coefficient + addition

    if num1 > num2:
        left = num1
        right = num2
    else:
        left = num2
        right = num1

    coefficient = math.floor(left/right)
    addition = left - (coefficient*right)

    print(f"{left} = {right} * {coefficient} + {addition}")

    # Repeat the steps until there is no remainder left

    while addition != 0:

        previousAddition = addition

        left = right
        right = addition
        coefficient = math.floor(left/right)
        addition = left - (coefficient*right)
        print(f"{left} = {right} * {coefficient} + {addition}")

    if previousAddition == 1:
        return True

    return False

EuclideanAlgorithm(int(sys.argv[1]), int(sys.argv[2]))
