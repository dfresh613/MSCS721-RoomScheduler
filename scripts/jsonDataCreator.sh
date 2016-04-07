#!/bin/bash
#this script will use the dataFile to create x amount of rooms, each with a single meeting.
if [[ $# -ne 2 ]];then
	echo "Usage: jsonDataCreator <templateFile> <numberOfRooms>"
fi
dataFile=$1
numberOfRooms=$2
cat /dev/null > generatedOutputFile
echo "[" > generatedOutputFile
x=0
#take the contents of the templateFile and replace the %x with the number of the room it is.
while [[ $x -lt $numberOfRooms ]];do
	#only add commas to subsequent rooms, when it's not the first room.
	if [[ $x -ne 0 ]];then
		echo "," >> generatedOutputFile
	fi
	sed "s/%x/$x/g" $dataFile >> generatedOutputFile
	((x=x+1))
done 
echo "]" >> generatedOutputFile

