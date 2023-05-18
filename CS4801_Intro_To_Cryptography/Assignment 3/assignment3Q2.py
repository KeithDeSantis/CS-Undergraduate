import math

# Keith DeSantis
# Assignment 3 Question 2.d

print("This is a script written by Keith DeSantis for Assignment 3 of Intro to Cryptography.")

# Perform the Euclidean Algorithm and Return True if the numbers are relatively prime
# (gcd is 1)
def isRelPrime(num1,num2):

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

    # Repeat the steps until there is no remainder left

    while addition != 0:

        previousAddition = addition

        left = right
        right = addition
        coefficient = math.floor(left/right)
        addition = left - (coefficient*right)

    if previousAddition == 1:
        return True

    return False

relPrimes = []

# Check each number to see if it is relatively prime

for i in range(2,127):
    if not isRelPrime(i, 126):
        relPrimes.append(i)

print(relPrimes)