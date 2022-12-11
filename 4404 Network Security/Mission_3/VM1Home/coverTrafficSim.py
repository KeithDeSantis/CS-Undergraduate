import threading
import os
import time
import random


trafficCommands = [
    "ping 10.64.6.1 -c 2 >> trafficOutput.txt", "ping 10.64.6.2 -c 2 >> trafficOutput.txt", "ping 10.64.6.3 -c 2 >> trafficOutput.txt", "ping 10.64.6.4 -c 2 >> trafficOutput.txt",
    "curl http://10.64.6.1:8000 >> trafficOutput.txt", "curl http://10.64.6.2:8000 >> trafficOutput.txt", "curl http://10.64.6.3:8000 >> trafficOutput.txt", "curl http://10.64.6.4:8080 >> trafficOutput.txt"
]

# python3 -m http.server

def runHTTPServer():
    os.system("python3 -m http.server >> trafficOutput.txt")

serverThread = threading.Thread(target=runHTTPServer)
serverThread.start()

while True:
    time.sleep(1)
    traffic = random.choice(trafficCommands)
    os.system(traffic)
