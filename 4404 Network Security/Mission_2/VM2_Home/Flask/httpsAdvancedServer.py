from flask import Flask, flash, redirect, request, url_for, render_template
from passwordLocal import check_password, check_2fa, sendCodeToApp
app = Flask(__name__)


@app.route('/', methods=['GET', 'POST'])
def display():
    if request.method == 'POST':
        entered_user = request.form['username']
        entered_pass = request.form['password']
        print(f"User: {entered_user} \nPassword: {entered_pass}")
        if check_password(entered_user, entered_pass):
            print("Login Successful")
            return redirect(url_for('auth', username=entered_user))
        else:
            print("Login Failed")
    return render_template('index.html')


@app.route('/auth', methods=['GET', 'POST'])
def auth():
    try:
        username = request.query_string.decode('utf-8').split('=')[1]
    except IndexError:
        return redirect(url_for('display'))
    if request.method == 'GET':
        sendCodeToApp(username)
    if request.method == 'POST':
        entered_2fa = request.form['code']
        print(f"2FA: {entered_2fa}")
        if check_2fa(username, entered_2fa):
            print("2FA Successful")
            return redirect(url_for('dashboard', username=username))
        else:
            print("2FA Failed")
            return redirect(url_for('display'))
    return render_template('auth.html')


@app.route('/dashboard')
def dashboard():
    try:
        username = request.query_string.decode('utf-8').split('=')[1]
    except IndexError:
        return redirect(url_for('display'))

    accounts = {"admin": ["Name: admin", "SSN: 348-30-2385", "BALANCE: $10000000000000"],
                "user": ["Name: user", "SSN: 364-19-4543", "BALANCE: $2500"],
                "user2": ["Name: user2", "SSN: 649-23-2936", "BALANCE: -$1000000000"]}
    return render_template('dashboard.html', data=accounts[username])


if __name__ == '__main__':
    app.run(host='10.64.6.2', port=8888, ssl_context=('server.crt', 'server.key'))
