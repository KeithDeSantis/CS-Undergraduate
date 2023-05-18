import os
import sys
import matplotlib.pyplot as plt

# Relative frequency of English language from Wikipedia
eng_letter_freq = {
    "A": 8.2,
    "B": 1.5,
    "C": 2.8,
    "D": 4.3,
    "E": 13,
    "F": 2.2,
    "G": 2,
    "H": 6.1,
    "I": 7,
    "J": 0.15,
    "K": 0.77,
    "L": 4,
    "M": 2.4,
    "N": 6.7,
    "O": 7.5,
    "P": 1.9,
    "Q": 0.095,
    "R": 6,
    "S": 6.3,
    "T": 9.1,
    "U": 2.8,
    "V": 0.98,
    "W": 2.4,
    "X": 0.15,
    "Y": 2,
    "Z": 0.074,
}

# Helper to return the closest frequency letter from the wikipedia list using a given frequency in the cipher text
def get_closest_frequency(cipher_freq):

    # Letter, Freq Diff
    closest_freq = {"Letter": None, "Frequency": 1000}

    for letter in eng_letter_freq:
        if abs(cipher_freq - eng_letter_freq[letter]) < closest_freq["Frequency"]:
            closest_freq["Letter"] = letter
            closest_freq["Frequency"] = abs(cipher_freq - eng_letter_freq[letter])
    
    return closest_freq

#Ciphertext
ciphertext = """CKCLBAELDK DGJ LFNSMBCA CGQEGCCAI JCUCKFS DGJ LACDBC SAFJMLBI BHDB LHDGQC BHC 
OFAKJ DGJ NDVC FMA KEUCI CDIECA BHC LCKK SHFGCI OC JCSCGJ FG BHC LFNSMBCAI MICJ EG 
GDBEFGDK ICLMAEBR DGJ BHC CKCLBAELDK IRIBCNI BHDB NDVC FMA LDAI FSCADBC OCAC DKK 
LACDBCJ TR CKCLBAELDK DGJ LFNSMBCA CGQEGCCAI DB OSE OC VCCS BHDB SAFQACII 
NFUEGQ PFAODAJ OEBH FMA EGGFUDBEUC ACICDALH DGJ FMB-FP-BHC TFY DSSAFDLHCI BHC 
JCSDABNCGB FP CKCLBAELDK DGJ LFNSMBCA CGQEGCCAEGQ DB OSE LHDKKCGQCI IBMJCGBI 
BF SMIH BHCNICKUCI BF MGJCAIBDGJ IFLECBRI DGJ BCLHGFKFQRI LFNSKCY EIIMCI EG D 
TAFDJCA LFGBCYB BHDG OHDBI EG PAFGB FP BHCN OC ODGB FMA IBMJCGBI OHCBHCA BHCR 
DAC CDAGEGQ DG MGJCAQADJMDBC NEGFA FA D JFLBFADBC BF BDLVKC IFLECBRI NFIB 
SACIIEGQ SAFTKCNI DGJ MGLFUCA GCO ODRI FP IFKUEGQ BHCN OHCBHCA EBI JCUCKFSEGQ 
IRIBCNI BHDB LDG KFLDBC PEACPEQHBCAI EG BHC NEJJKC FP D TMAGEGQ TMEKJEGQ FA 
LACDBEGQ GCMAFSAFIBHCBELI BHDB KFFV DGJ PMGLBEFG KEVC GDBMADK KENTI FMA 
PDLMKBR DGJ IBMJCGBI DAC DB BHC PAFGB CJQC FP ACNDAVDTKC EGGFUDBEFG OHEKC 
DJUDGLEGQ BCLHGFKFQECI EI DB FMA LFAC OC DKIF BDVC HMNDG LFGGCLBEFGI UCAR 
ICAEFMIKR EG CLC OC SAEJC FMAICKUCI FG BHC PDNEKR-KEVC DBNFISHCAC OC LMKBEUDBC; 
PDLMKBR IBMJCGBI DGJ IBDPP CGLFMADQC CDLH FBHCAI CUCAR IMLLCII DGJ DAC BHCAC PFA 
BHC LHDKKCGQCI TFBH EG BHC LKDIIAFFN DGJ EG KEPC"""

# Get list of alphabet
alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
alphabet = [*alphabet]

# Set up dictionary to store relative frequencies
total = 0
rel_freqs = {}
for letter in alphabet:
    # LETTER: (NUMBER_OF_INSTANCES, FREQUENCY)
    rel_freqs.setdefault(letter, [0,0])

# Get counts of letters in ciphertext
for character in [*ciphertext]:
    if character in alphabet:
        total += 1
        rel_freqs[character][0] += 1

# Calculate relative frequencies
for letter in alphabet:
    rel_freqs[letter][1] = (rel_freqs[letter][0]/total) * 100

for ltr in list(rel_freqs.keys()):
    print(f"{ltr} : {rel_freqs[ltr][1]}")


# Organize the ciphertext and known english language letters be frequency in descending order and plot them
names = []
freqs = []
for letter in list(rel_freqs.keys()):
    insertedFlag = False
    for index in range(len(freqs)):
        if freqs[index] < rel_freqs[letter][1]:
            freqs.insert(index, rel_freqs[letter][1])
            names.insert(index, letter)
            insertedFlag = True
            break
    if not insertedFlag:
        freqs.append(rel_freqs[letter][1])
        names.append(letter)
plt.bar(range(len(rel_freqs)), freqs, tick_label=names, color='red')
plt.savefig("cipher.png")

names2 = []
freqs2 = []
for letter in list(eng_letter_freq.keys()):
    insertedFlag = False
    for index in range(len(freqs2)):
        if freqs2[index] < eng_letter_freq[letter]:
            freqs2.insert(index, eng_letter_freq[letter])
            names2.insert(index, letter)
            insertedFlag = True
            break
    if not insertedFlag:
        freqs2.append(eng_letter_freq[letter])
        names2.append(letter)

plt.cla()
plt.bar(range(len(eng_letter_freq)), freqs2, tick_label=names2, color='blue')
plt.savefig("real.png")

# Ciphertext letter: Real letter Key now that the lists are lined up with one another
key = {}

for ind in range(len(names)):
    key.setdefault(names[ind], names2[ind])

# Manually changing key by making educated guesses and looking at the decrypted plaintext each time

key["H"] = "H"
key["F"] = "O"
key["M"] = "I"
key["K"] = "L"
key["U"] = "V"
key["L"] = "C"
key["A"] = "R"
key["E"] = "I"
key["G"] = "N"
key["Q"] = "G"
key["S"] = "P"
key["M"] = "U"
key["J"] = "D"
key["O"] = "W"
key["R"] = "Y"
key["T"] = "B"
key["P"] = "F"
key["Y"] = "X"
key["W"] = "J"

# Print key
for ltr in list(key.keys()):
    print(f"{ltr} : {key[ltr]}")

# Decrypt and print plaintext
plaintext = [*ciphertext]

for index in range(len(plaintext)):
    if plaintext[index] in alphabet:
        plaintext[index] = key[plaintext[index]]

tmp = ""
print(tmp.join(plaintext))