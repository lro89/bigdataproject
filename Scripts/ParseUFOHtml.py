#!/usr/bin/env python
import re
import sys 
import subprocess
from subprocess import Popen, PIPE, STDOUT

# Definition der lokalen Ergebnisdatei
# outputFile = '/user/cloudera/Staging/UFO/ufostaging.txt' 

content = ""

# fileWriter = open(outputFile, 'w')

# Auslesen der entfernten HDFS-Datei
cat = subprocess.Popen(["hadoop", "fs", "-cat", "/user/cloudera/Datasources/UFO/ndxlCA.html"], stdout=subprocess.PIPE)
content = ""
result = ""

for line in cat.stdout:
	content += line + "\n"

filteredContent = re.findall('<TD><FONT[^>]*>(.*?)</TD>', content, 0)

#fileWriter = open(outputFile, 'w')

tmpCounter = 0
for line in filteredContent:
	HREFtest = re.search('<A[^>]*>(.*?)</A>', line)
	if HREFtest is not None:
		currentValue = HREFtest.group(1)
	else:
		currentValue = line
	
	if '\"' in currentValue:
		currentValue = currentValue.replace('\"','\'')

	result += '\"' + currentValue #fileWriter.write('\"' + currentValue)
	tmpCounter += 1
	
	if tmpCounter > 6:
		result += '\"\n' #fileWriter.write('\"\n')
		tmpCounter = 0
	else:
		result += '\";' #fileWriter.write('\";')


# Workaround, um die lokale Datei zu HDFS zu putten
# cat = subprocess.Popen(["cat", "/tmp/Staging/ufo.txt"], stdout=subprocess.PIPE)
cat = subprocess.Popen(["hadoop", "fs", "-put", "-f", "-", "/user/cloudera/Staging/UFO/UFOExtract.txt"], stdout=PIPE, stdin=PIPE, stderr=PIPE) #stdin=cat.stdout)
stdout_data = cat.communicate(input=result)




#put = subprocess.Popen(["hadoop", "fs", "-copyFromLocal","-f",outputFile , "/user/cloudera/Staging/UFO/UFOExtract.txt"],  stdin=cat.stdout)
#put = subprocess.Popen(["hadoop", "fs", "-put", "-f", "-", "/user/cloudera/Staging/UFO/UFOExtract.txt"], stdin=cat.stdout)
#put.communicate()
