import math

def output_to_string(output):
    string = ""
    for bit in output:
        string += str(bit)
    return string

def encrypt(msg, keystream):
    c = []
    for ind in range(len(msg)):
        c.append(int(msg[ind] ^ keystream[ind]))
    return c

msg_to_encrypt = [1,1,0,0,1,1,0,0,0,1,1,1,1,0,1,1,0,0,1,1,0,1,0,0,1,1,1,1,1,0]

output = []
lsfr = [0,0,1,0,1,1,0,0,0]

num_states = 0
previous_states = []
current_state = lsfr.copy()

while current_state not in previous_states:
    
    # Update num states
    num_states += 1

    # Update previous states
    previous_states.append(current_state)

    # Add to output
    output.append(lsfr[8])

    # Calculate new bit
    newbit = lsfr[8] ^ lsfr[6] ^ lsfr[1] ^ lsfr[0]

    # Move elements (except first element)
    for index in range(len(lsfr)-1, 0, -1):
        lsfr[index] = lsfr[index-1]
    
    # Update first element with new bit
    lsfr[0] = newbit
    
    current_state = lsfr.copy()

print("LFSR #1: ")
print(f"Maximum Sequence of LFSR 1: {math.pow(2,len(lsfr))-1}")
print(f"Period of LFSR 1: {num_states}")
print(f"First 30 bits of LFSR 1: {output_to_string(output[0:30])}")
print(f"Using keystream to encrypt: {output_to_string(encrypt(msg_to_encrypt, output[0:30]))}\n\n")

print(f"LFSR #2: ")

output = []
lsfr = [0,0,1,0,1,1,0,0,0]

num_states = 0
previous_states = []
current_state = lsfr.copy()

while current_state not in previous_states:
    
    # Update num states
    num_states += 1

    # Update previous states
    previous_states.append(current_state)

    # Add to output
    output.append(lsfr[8])

    # Calculate new bit
    newbit = lsfr[6] ^ lsfr[1] ^ lsfr[0]

    # Move elements (except first element)
    for index in range(len(lsfr)-1, 0, -1):
        lsfr[index] = lsfr[index-1]
    
    # Update first element with new bit
    lsfr[0] = newbit
    
    current_state = lsfr.copy()

print(f"Maximum Sequence of LFSR 2: {math.pow(2,len(lsfr))-1}")
print(f"Period of LFSR 2: {num_states}")
print(f"First 30 bits of LFSR 2: {output_to_string(output[0:30])}")
print(f"Using keystream to encrypt: {output_to_string(encrypt(msg_to_encrypt, output[0:30]))}\n\n")