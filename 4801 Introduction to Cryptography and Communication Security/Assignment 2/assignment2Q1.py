import sys

##### INITIAL VALUES #####

# Input
inputBitsString = "10110011000011111100100010100110"

# Split Input into Array of Bits and Make ints
inputBits = [*inputBitsString]
inputBits = [int(x) for x in inputBits]

# Round key
roundKeyString = "001101110001101011011111000001011100000100111110"

# Split Round Key into Array of Bits and Make ints
roundKeyBits = [*roundKeyString]
roundKeyBits = [int(x) for x in roundKeyBits]

# Permutation Table
permutationTable = [16,7,20,21,29,12,28,17,1,15,23,26,5,18,31,10,2,8,24,14,32,27,3,9,19,13,30,6,22,11,4,25]

# Expansion Table
DESexpansionTable = [32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,1]


print("\nThis is the script written by Keith DeSantis for Question 1 on Assignment 2.\nFor a more verbose output use command line argument 'v' when running.\n")

verbose = False
if len(sys.argv) > 1:
    verbose = sys.argv[1] == 'v'

########################################## Part a ##########################################

print("Part a:\n")
# Convert DES Expansion Table to 0-indexed indices
DESExpansionTable = [x-1 for x in DESexpansionTable]

# Array for the expanded input
expandedBits = [-1]*48

# Use the table to expand the input
for index in range(len(DESExpansionTable)):

    # Verbose mode printing
    if verbose:
        print(f"Index {index} will be set to {inputBits[DESExpansionTable[index]]}")

    # Set the bit accordingly
    expandedBits[index] = inputBits[DESExpansionTable[index]]

print(f"\nInput after applying DES Expansion function: {''.join([str(x) for x in expandedBits])}\n\n")

########################################## Part b ##########################################

print("Part b:\n")

# Result of xor
xoredBits = []

# XOR the bits
for index in range(len(expandedBits)):
    xoredBits.append(expandedBits[index] ^ roundKeyBits[index])

print(f"XOR'd bits of round key and expanded input: {''.join([str(x) for x in xoredBits])}\n\n")

########################################## Part c ##########################################

print("Part c:\n")

# The eight sboxes stored as 2D arrays
sboxes = [

[
[14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7],
[0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8],
[4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0],
[15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13],
],
[
[15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10],
[3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5],
[0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15],
[13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9],
],
[
[10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8],
[13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1],
[13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7],
[1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12],
],
[
[7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15],
[13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9],
[10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4],
[3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14],
],
[
[2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9],
[14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6],
[4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14],
[11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3],
],
[
[12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11],
[10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8],
[9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6],
[4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13],
],
[
[4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1],
[13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6],
[1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2],
[6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12],
],
[
[13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7],
[1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2],
[7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8],
[2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11],
]

]

# Dictionary to translate bits into the numbers they denote
bitsToIndex = {
    "00": 0,
    "01": 1,
    "10": 2,
    "11": 3,
    "0000": 0,
    "0001": 1,
    "0010": 2,
    "0011": 3,
    "0100": 4,
    "0101": 5,
    "0110": 6,
    "0111": 7,
    "1000": 8,
    "1001": 9,
    "1010": 10,
    "1011": 11,
    "1100": 12,
    "1101": 13,
    "1110": 14,
    "1111": 15
}

# Dictionary to translate number to 4 bits of binary
numberToBitList = {
    0 : [0,0,0,0],
    1 : [0,0,0,1],
    2 : [0,0,1,0],
    3 : [0,0,1,1],
    4 : [0,1,0,0],
    5 : [0,1,0,1],
    6 : [0,1,1,0],
    7 : [0,1,1,1],
    8 : [1,0,0,0],
    9 : [1,0,0,1],
    10 : [1,0,1,0],
    11 : [1,0,1,1],
    12 : [1,1,0,0],
    13 : [1,1,0,1],
    14 : [1,1,1,0],
    15 : [1,1,1,1]
}

# List of subsets of length 6
sBoxInputs = []

# Separating xor'd bits into groups of 6 bits
for i in range(0,47,6):
    sBoxInputs.append(xoredBits[i:i+6])

    # Verbose Printing
    if verbose:
        print(f"New subsection of bits of length 6: {sBoxInputs[-1]}")

# The outputs of the 8 S-Boxes (at ints)
sBoxOutputBits = []

# Counter for the number sbox we are on
SboxNumber = 0

# Calculate each sBoxOutput
for input in sBoxInputs:

    # Get the row bits (first and last) and convert to int using our dictionary
    row = bitsToIndex[''.join([str(x) for x in [input[0],input[5]]])]
    # Do the same for the column bits (middle 4)
    column = bitsToIndex[''.join([str(x) for x in input[1:5]])]

    # Verbose Printouts
    if verbose:
        print(f"Sbox # {SboxNumber}")
        print(f"Subsection of bits: {input}")
        print(f"Row: {row}")
        print(f"Row Bits: {[input[0],input[5]]}")
        print(f"Column: {column}")
        print(f"Column Bits: {input[1:5]}")
        print(f"SBox Entry: {sboxes[SboxNumber][row][column]}")
        print(f"SBox Entry Bits: {numberToBitList[sboxes[SboxNumber][row][column]]}\n")
    
    # Use list comprhension to turn the int from the sbox back to a list of bits and pad
    sBoxOutputBits = sBoxOutputBits + numberToBitList[sboxes[SboxNumber][row][column]]

    # Increment Sbox number
    SboxNumber += 1

print(f"32-bit output of substitution step: {''.join([str(x) for x in sBoxOutputBits])}\n\n")

########################################## Part d ##########################################

print("Part d\n")

# Convert Permutation Table to 0-indexed indices
permutationTable = [x-1 for x in permutationTable]

# Array for the permuted input
permutedBits = [-1]*32

# Use the table to permute the input
for index in range(len(permutationTable)):
    permutedBits[index] = sBoxOutputBits[permutationTable[index]]

print(f"Permuted 32-bit output: {''.join([str(x) for x in permutedBits])}\n\n")