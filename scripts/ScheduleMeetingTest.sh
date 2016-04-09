#!/bin/bash
#Test script which will first create 10 rooms (by calling the roomcreation test script)
#and then attempt to adding 1 meeting to each room and list the meetings.
#The user must look at the list to verify all meetings are scheduled

#directory the script is running in, to reference the other scripts
currDir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

#run the first test script to generate the rooms
$currDir/RoomCreationTest.sh

x=0
while [[ $x -lt 10 ]];do
    echo "3"
    echo "n"
    echo "n"
    echo "room$x"
    #different start date/time for each room
   ((startNum=x+1))
    echo "2016-02-$startNum"
    echo "12:$startNum"
    echo "2016-02-$startNum"
    echo "12:30"
    echo "subject$x"
    ((x=x+1))
done

#list the schedule for each and tell whether there's a meeting scheduled
x=0
while [[ $x -lt 10 ]];do
    echo "4"
    echo "room$x"
    ((x=x+1))
done
