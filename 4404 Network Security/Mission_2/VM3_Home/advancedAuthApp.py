import sys
import math
import time
import hashlib
import hmac
import secrets

key = secrets.token_hex(16)
d = 6


def generateCode():
    byteKey = bytes(key, encoding="utf-8")
    byteCounter = counter().to_bytes(length=8, byteorder="big")
    hash = hmac.new(byteKey, byteCounter, hashlib.sha256)
    return truncate(hash, d)


def truncate(hash, d):
    bits = bin(int(hash.hexdigest(), base=16))
    lastfour = bits[-4]
    offset = int(lastfour, base=2)
    choose32 = bits[offset * 8 : offset * 8 + 32]
    totp = str(int(choose32, base=2))
    return totp[-d:]


def counter():
    T = math.floor(time.time())
    T0 = 0
    TX = 30
    return math.floor((T - T0) / TX)


if __name__ == '__main__':
    arguments = len(sys.argv)

    for i in range(1, arguments, 2):
        arg = sys.argv[i]
        if "--key" in arg:
            #print("Found Key")
            key = sys.argv[i + 1]
            #print(key)
        elif "--generate" in arg:
            print("Found Generate")
            key = secrets.token_hex(16)
            print(type(key))
            print(key)

    print(generateCode())
