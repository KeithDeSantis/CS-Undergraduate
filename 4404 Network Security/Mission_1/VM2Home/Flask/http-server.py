# Flassk app
from flask import Flask, flash, redirect, request, url_for, render_template
from db import DB
import string


app = Flask(__name__)


@app.route('/', methods=['GET', 'POST'])
def display():
    if request.method == 'POST':
        SSN = None
        try:
            SSN = request.form['SSN']
            vote = request.form['canidate']
        except:
            print("Fields left blank")
            return redirect(url_for('display'))
        finally:
            if not SSN or not vote:
                print("Fields left blank")
                return redirect(url_for('display'))
        if vote == "writeIn":
            vote = request.form['writeBox']

        print(vote)

        db = DB()
        if db.check_ssn(SSN) or 1:
            print("SSN exists")
            if db.check_ssn_voted(SSN):
                print("SSN already voted not updating DB")
                return redirect(url_for('voted'))
            db.update_ip(SSN, 1)
            db.update_vote(vote.translate({ord(c): None for c in string.whitespace}))
            return redirect(url_for('voted'))
        else:
            print("SSN does not exist")
            return redirect(url_for('error'))

    return render_template('index.html')


@app.route('/voted')
def voted():
    return render_template('voted.html')


@app.route('/error')
def error():
    return render_template('error.html')


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8888)
