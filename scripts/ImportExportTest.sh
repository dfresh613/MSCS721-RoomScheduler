#!/bin/bash
#Test which will import 100 rooms then attempt to schedule a meeting in one,
#and attempt to export the data again
#dir to reference other scripts/related files
currDir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

#first import the room data
echo "7"
echo "$currDir/json100Rooms"

#now try to schedule one of those rooms (room#78)
echo "3"
echo "n"
echo "n"
echo "room78"
echo "2017-11-12"
echo "12:50"
echo "2017-11-12"
echo "13:45"
echo "subjectNew"

#try to export the newly added data
echo "6"
echo "/tmp"