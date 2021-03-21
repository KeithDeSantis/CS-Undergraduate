import os

files = []

for file in os.listdir(os.getcwd()):
	files.append(file if (file[-4:] == '.mp4') else 'XXX')
	#print(file[-4:])
files = [i for i in files if i != 'XXX']

count = 0
min = 0
try:
	min = int(files[0][0])
	for i in files:
		if int(i[0]) < min:
			min = int(i[0])

	count = min

	for i in files:
		if int(i[0]) > count:
			count+=1
	print('Files read:')
	for file in files:
		print('\t'+file)
except:
    pass

#print(count) 

for i in range(min,count+1):
	dir = 'Class-' + str(i)
	try:
		if i != 0:
			print(f'Making Directory called {dir}')
			os.mkdir(dir)
	except:
		print('Directory Already Made')
	for file in files:
		if int(file[0]) == i:
			mv = 'mv '+ file + ' ' + dir
			print(f'Calling: \n\t{mv}')
			try:
				os.system(mv)
			except:
				pass
   
print('Cleaning out temporary files...')
try:
	os.system('rm -r tmp_files')
	os.system('mkdir tmp_files')
except:
	pass
