from matplotlib import pyplot as plt

#################################### TCPPING GRAPH ################################################
"""
with open('/Users/keithdesantis/PycharmProjects/CS4516-Team17/Phase4Submission/tcppingData.txt', 'r') as f:
    lines = f.readlines()
data = [float(i.split(' ')[-2]) for i in lines]
print(data)
trial = []

trialNum = 0
for point in data:
    trialNum += 1
    trial.append(trialNum)

rtt_trad = []
time_trad = []
rtt_OPEN = []
time_OPEN = []
with open('/Users/keithdesantis/PycharmProjects/CS4516-Team17/Data/host1_host3_rtt.txt', 'r') as f:
    lines = f.readlines()
    rtt = []
    time = []
    for line in lines:
        rtt_trad.append(float(line.split(":")[0]))
        time_trad.append(float(line.split(":")[1]))
with open('/Users/keithdesantis/PycharmProjects/CS4516-Team17/Data/host1_host3_rtt_OPENFLOW.txt', 'r') as f:
    lines = f.readlines()
    for line in lines:
        rtt_OPEN.append(float(line.split(":")[0]))
        time_OPEN.append(float(line.split(":")[1]))


plt.plot(trial, rtt_trad[:-2], color='r', label='Traditional')
plt.plot(trial, rtt_OPEN[:-2], color='b', label='Simple OpenFlow')

plt.plot(trial, data, color='orange', label='SDN Network')
plt.legend()
plt.grid()
plt.xlabel('Trial')
plt.ylabel('RTT (ms)')
plt.title('RTTs with tcpping')
plt.show()

"""
#################################### PING GRAPH ################################################
with open('/Users/keithdesantis/PycharmProjects/CS4516-Team17/Phase4Submission/pingData.txt', 'r') as f:
    lines = f.readlines()
data = [float(i.split(' ')[-2].split("=")[1]) for i in lines[1:-10]]
print(data)
trial = []

trialNum = 0
for point in data:
    trialNum += 1
    trial.append(trialNum)

rtt_trad = []
time_trad = []
rtt_OPEN = []
time_OPEN = []
with open('/Users/keithdesantis/PycharmProjects/CS4516-Team17/Data/host1_host3_rtt.txt', 'r') as f:
    lines = f.readlines()
    rtt = []
    time = []
    for line in lines:
        rtt_trad.append(float(line.split(":")[0]))
        time_trad.append(float(line.split(":")[1]))
with open('/Users/keithdesantis/PycharmProjects/CS4516-Team17/Data/host1_host3_rtt_OPENFLOW.txt', 'r') as f:
    lines = f.readlines()
    for line in lines:
        rtt_OPEN.append(float(line.split(":")[0]))
        time_OPEN.append(float(line.split(":")[1]))


plt.plot(trial, rtt_trad[1:], color='r', label='Traditional')
plt.plot(trial, rtt_OPEN[1:], color='b', label='Simple OpenFlow')

plt.plot(trial, data, color='orange', label='SDN Network')
plt.legend()
plt.grid()
plt.xlabel('Trial')
plt.ylabel('RTT (ms)')
plt.title('RTTs with ping (excluding initial DNS resolution)')
plt.show()

if __name__ == "__main__":
    pass
