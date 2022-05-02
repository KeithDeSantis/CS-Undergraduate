import os

os.environ["GREENIE"] = 'a' * 92 + "\r\n\r\n" 
print(os.getenv("GREENIE"))

