import requests

url = "http://localhost:8888"
data = {"SSN": "764982686", "canidate": "KushShah"}

x = requests.post(url, data=data)
print(x.text)