import pymysql.cursors


"""
Check if SSN exists:
"select SSN from SSNs where SSN = '" + ssn + "'"

Update Vote for candidate:
"Update Votes Set NumVotes = NumVotes + 1 where CanidateName = '" + canidate + "'"

Get number of votes:
"select NumVotes from Votes where CanidateName = '" + candidate + "'"

Update IP (needs to be changed):
"update SSNs set IPAddr = '" + IPAddr + "' where SSN = '" + ssn + "'"
"""


class DB:
    def __init__(self):
        self.connection = pymysql.connect(user='netsec', password='password',
                                          host='localhost',
                                          database='netsec')

    def get_connection(self):
        return self.connection

    def check_ssn(self, ssn):
        with self.connection.cursor() as cursor:
            sql = f"select SSN from SSNs where SSN = '{ssn}'"
            cursor.execute(sql)
            result = cursor.fetchone()
            if result:
                return True
            else:
                return False

    def check_canidate(self, canidate):
        with self.connection.cursor() as cursor:
            sql = f"select CanidateName from Votes where CanidateName = '{canidate}'"
            cursor.execute(sql)
            result = cursor.fetchone()
            return True if result else False

    def update_vote(self, candidate):
        if self.check_canidate(candidate):
            with self.connection.cursor() as cursor:
                sql = f"Update Votes Set NumVotes = NumVotes + 1 where CanidateName = '{candidate}'"
                cursor.execute(sql)
                self.connection.commit()
        else:
            self.write_in_vote(candidate)
    
    def write_in_vote(self, candidate):
        with self.connection.cursor() as cursor:
            sql = f"insert into Votes (CanidateName, NumVotes) values ('{candidate}', 1)"
            cursor.execute(sql)
            self.connection.commit()

    def get_votes(self, candidate):
        with self.connection.cursor() as cursor:
            sql = f"select NumVotes from Votes where CanidateName = '{candidate}'"
            cursor.execute(sql)
            result = cursor.fetchone()
            return result[0]

    def update_ip(self, ssn, ip):
        with self.connection.cursor() as cursor:
            sql = f"update SSNs set IPAddr = '{ip}' where SSN = '{ssn}'"
            cursor.execute(sql)
            self.connection.commit()

    def check_ssn_voted(self, ssn):
        with self.connection.cursor() as cursor:
            sql = f"select IPAddr from SSNs where SSN = '{ssn}'"
            cursor.execute(sql)
            result = cursor.fetchone()
            return True if result[0] == 1 else False
