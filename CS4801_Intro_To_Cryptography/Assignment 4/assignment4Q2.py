import math

print("\nThis is the script written by Keith DeSantis for Question 2 on Assignment 4.\n")

##### INITIAL VALUES #####

p = 971
g = 314
a = 23
k = 21

##### EUCLIDEAN ALGORITHM FUNCTION USED TO FIND THE MULTIPLICATIVE INVERSE IN A MODULUS WITH EXTENDED EUCLIDEAN ALGORITHM

def EuclideanAlgorithm(num1,num2, leadingChars):

    equations = []

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

    print(f"{leadingChars}{left} = {right} * {coefficient} + {addition}")
    equations.append([left,right,coefficient,addition])

    # Repeat the steps until there is no remainder left

    while addition != 0:

        previousAddition = addition

        left = right
        right = addition
        coefficient = math.floor(left/right)
        addition = left - (coefficient*right)
        print(f"{leadingChars}{left} = {right} * {coefficient} + {addition}")
        equations.append([left,right,coefficient,addition])

    return equations

############################## Part a ##############################

print("Part a:\n")

m_1 = 49

print("Encryption:\n")

h = (g**a)%p
print(f"\tGenerator = {a}")
print(f"\tPrivate key = {a}")
print(f"\tModulus = {p}")
print(f"\th value = {g} ^ {a} mod {p}")
print(f"\tThis exponentiation can be solved using the SQUARE & MULTIPLY algorithm, but in this case the number is small enough to solve outright.")
print(f"\th value = {g**a} mod {p}")
print(f"\th value {h}\n\n")

c_1 = (g**k)%p
c_2 = (m_1*((h**k))%p)%p

print(f"\tEphemeral key chosen: {k}\n")

print(f"\tComputed c_1 = {g} ^ {k} mod {p}")
print(f"\tThis exponentiation can be solved using the SQUARE & MULTIPLY algorithm, but in this case the number is small enough to solve outright.")
print(f"\tComputed c_1 = {(g**k)} mod {p}")
print(f"\tComputed c_1 = {c_1}\n")

print(f"\tComputed c_2 = {m_1} * {h} ^ {k} mod {p}")
print(f"\tThis exponentiation can be solved using the SQUARE & MULTIPLY algorithm, but in this case the number is small enough to solve outright.")
print(f"\tComputed c_2 = {m_1} * {(h**k)} mod {p}")
print(f"\tComputed c_2 = {m_1} * {(h**k)%p} mod {p}")
print(f"\tComputed c_2 = {m_1 * ((h**k)%p)} mod {p}")
print(f"\tComputed c_2 = {c_2}\n")

print(f"\tEncrypted ciphertext pair c = ({c_1},{c_2})\n")

print("Decryption:\n")

print(f"\tThe decrypted message is computed as {c_2} * ({c_1} ^ {a})^-1 mod {p}")
print(f"\tThis exponentiation can be solved using the SQUARE & MULTIPLY algorithm, but in this case the number is small enough to solve outright.")
print(f"\tMessage = {c_2} * ({(c_1**a)})^-1 mod {p}")
print(f"\tMessage = {c_2} * ({(c_1**a)%p})^-1 mod {p}")
print(f"\tThe multiplicative inverse of {(c_1**a)%p} mod {p} can be found using the extended euclidean algorithm.\n")
print(f"\t\tThe euclidean algorithm for {(c_1**a)%p} and {p} gives the following equations:\n")
eAlgoEquations = EuclideanAlgorithm(p, (c_1**a)%p,"\t\t")
print("\n\t\tFrom this I performed the extended euclidean algorithm by hand, a picture of the calculations are included on the assignment writeup document.")
print("\n")
print(f"\tMultiplicative inverse in modulo {p} of {(c_1**a)%p}: 525")
print(f"\tMessage = {c_2} * 525 mod {p}")
print(f"\tMessage = {c_2 * 525} mod {p}")


print(f"\tMessage = {(c_2 * 525)%p}\n")

############################## Part b ##############################

print("Part b:\n")

c_3 = 285
c_4 = 849


print("\tSubpart i:\n")

print("\tUsing homomorphic property of ElGamal Encryption to determine m2 based on c3, c4, c1, c2, and m1\n")

print("\tBelow is the equation used to solve for the multiplication of the two messages, solved step by step.")
print(f"\tMultiplication of Messages = ({c_2} * {c_4}) * ({c_1} * {c_3}) ^ -{a} mod {p}")
print(f"\tMultiplication of Messages = ({c_2*c_4}) * ({c_1} * {c_3}) ^ -{a} mod {p}")
print(f"\tMultiplication of Messages = ({(c_2*c_4)%p}) * ({c_1} * {c_3}) ^ -{a} mod {p}")
print(f"\tMultiplication of Messages = ({(c_2*c_4)%p}) * ({c_1*c_3}) ^ -{a} mod {p}")
print(f"\tMultiplication of Messages = ({(c_2*c_4)%p}) * ({(c_1*c_3)%p}) ^ -{a} mod {p}")
print(f"\tMultiplication of Messages = ({(c_2*c_4)%p}) * ({((c_1*c_3)%p)**a}) ^ -1 mod {p}")
print(f"\tMultiplication of Messages = ({(c_2*c_4)%p}) * ({(((c_1*c_3)%p)**a)%p}) ^ -1 mod {p}")
print(f"\tThe multiplicative inverse of {(((c_1*c_3)%p)**a)%p} mod {p} can be found using the extended euclidean algorithm.\n")
print(f"\t\tThe euclidean algorithm for {(((c_1*c_3)%p)**a)%p} and {p} gives the following equations:\n")
eAlgoEquations = EuclideanAlgorithm(p, (((c_1*c_3)%p)**a)%p,"\t\t")
print(f"\n\t\tFrom this the extended euclidean algorithm produces the multiplicative inverse in modulo {p} of {(((c_1*c_3)%p)**a)%p}: 963\n")
# print(f"\tMultiplication of Messages = ({(c_2*c_4)%p}) * (13) ^ {a} mod {p}")
# print(f"\tMultiplication of Messages = {(c_2*c_4)%p} * {13**a} mod {p}")
# print(f"\tMultiplication of Messages = {(c_2*c_4)%p} * {(13**a)%p} mod {p}")
# print(f"\tMultiplication of Messages = {((c_2*c_4)%p)*((13**a)%p)} mod {p}")
# print(f"\tMultiplication of Messages = {(((c_2*c_4)%p)*((13**a)%p))%p}")
print(f"\tMultiplication of Messages = {(c_2*c_4)%p} * 963 mod {p}")
print(f"\tMultiplication of Messages = {((c_2*c_4)%p * 963)} mod {p}")
print(f"\tMultiplication of Messages = {((c_2*c_4)%p * 963)%p}")
# print(f"\tm2 = {(((c_2*c_4)%p * 963)%p)} / {m_1}")
# print(f"\tm2 = {(((c_2*c_4)%p * 963)%p)/m_1}")

print("\n\n\tSubpart ii:\n")

print("\tDecrypt c3 and c4 to get m2 and confirm it is consitent with the m1 * m2 found before.\n")

print(f"\tThe decrypted message is computed as {c_4} * ({c_3} ^ {a})^-1 mod {p}")
print(f"\tThis exponentiation can be solved using the SQUARE & MULTIPLY algorithm, but in this case the number is small enough to solve outright.")
print(f"\tMessage = {c_4} * ({(c_3**a)})^-1 mod {p}")
print(f"\tMessage = {c_4} * ({(c_3**a)%p})^-1 mod {p}")
print(f"\tThe multiplicative inverse of {(c_3**a)%p} mod {p} can be found using the extended euclidean algorithm.\n")
print(f"\t\tThe euclidean algorithm for {(c_3**a)%p} and {p} gives the following equations:\n")
eAlgoEquations = EuclideanAlgorithm(p, (c_3**a)%p,"\t\t")
print(f"\n\t\tFrom this the extended euclidean algorithm produces the multiplicative inverse in modulo {p} of {(c_3**a)%p}: 135\n")
print(f"\tMessage = {c_4} * 135 mod {p}")
print(f"\tMessage = {c_4 * 135} mod {p}")
print(f"\tMessage = {(c_4 * 135)%p}")
print(f"\tm1 * m2 = {(c_4 * 135)%p} * {m_1} mod {p}")
print(f"\tm1 * m2 = {((c_4 * 135)%p) * m_1} mod {p}")
print(f"\tm1 * m2 = {(((c_4 * 135)%p) * m_1)%p}")
print(f"\tSince {(((c_4 * 135)%p) * m_1)%p} = {((c_2*c_4)%p * 963)%p}, we see that the homomorphic property held and we did our calculations correctly.\n")


