import socket
from key import generateCode, generateKey
import time
accounts = {"admin": ["password", "179c86980b18cc8ba6ddd4e28b6a4cb8", None],
            "user": ["1234", "32c5b6f1c079f0bd8bbf790639642627", None],
            "user2": ["4321", "0242a09063d3600c8057fe30582936d1", None]}


def check_password(username, password):
    if username in accounts:
        if accounts[username][0] == password:
            return True
    return False


def sendCodeToApp(username):
    port = 10123
    host = '10.64.6.3'
    code = generateCode(accounts[username][1])
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    while True:
        try:
            s.connect((host, port))
            s.sendall(bytes(code, encoding="utf-8"))
            s.close()
            break
        except:
            continue
    accounts[username][2] = (code, time.time())
    print(f"Code, time_sent {accounts[username][2]}")
    return code


def check_2fa(username, code):
    if username in accounts:
        code, time_sent = accounts[username][2]
        if code == code and time.time() - time_sent < 30:
            return True
    return False
