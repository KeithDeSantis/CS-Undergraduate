import sys
import os
if len(sys.argv) < 2:
	print("Usage python3 main.py [http, https]")
	exit()
if sys.argv[1] == "https":
	os.system("python3 https-server.py")
elif sys.argv[1] == "http":
	os.system("python3 http-server.py")
else:
	print("Invalid args\nUsage python3 main.py [http, https]")
	exit()
