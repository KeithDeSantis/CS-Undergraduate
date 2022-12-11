import socket

# Socket that auth app will listen on
port = 10123
host = ''
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((host, port))
#print(f"Bound {host} on {port}")

# Listen on the connection
s.listen(1)
#print(f"Connected by {addr}")

# Everytime we are sent a 2FA code, display it for the end users
while True:
    conn, addr = s.accept()
    try:
        data = conn.recv(20)

        if not data: continue

        print(f"{data.decode('utf-8')}\n")

    except socket.error:
        print("Error Occurred. Please rerun the auth app and try to login again.")
        break

conn.close()
