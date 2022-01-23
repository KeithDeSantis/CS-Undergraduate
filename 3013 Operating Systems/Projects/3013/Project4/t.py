l = [100000,1,1,1,1,1,1,1,1,1]

fifo = []
total = 0

for i in range(len(l)):
	turn = 0
	for j in range(i+1):
		turn+= l[j]
	total+=turn
print(f'FIFO: {total/len(l)}')

sjf = 0

l.sort()

for i in range(len(l)):
	turn = 0
	for j in range(i+1):
		sjf +=l[j]
	sjf+=turn
print(f'SJF {sjf/len(l)}')

print(f'{total/sjf}')
