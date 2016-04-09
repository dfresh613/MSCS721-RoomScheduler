#!/bin/bash
#Test case which will attempt to create 10 rooms, and then the user must verify that listing 10 rooms are listed. Basis for other tests
x=0
while [[ $x -lt 10 ]];do
    echo "1"
    echo "room$x"
    echo "12"
    echo "Marist$x"
    echo "HC$x"
    ((x=x+1))
    echo "room$x added!"
done

#list the rooms grep to make sure at least one exists
echo "5" 
