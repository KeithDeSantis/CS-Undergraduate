import requests
from time import sleep

SSNs = [399066968,191405466,206064582,776158968,168662764,854496395,279459600,764982686,451367415,855593403]

for SSN in SSNs:
    url = "http://10.64.6.2:8888"
    data = {"SSN": SSN, "canidate": "KushShah"}

    x = requests.post(url, data=data)
    print(x.text)
    print("\n\n")
    sleep(1)
