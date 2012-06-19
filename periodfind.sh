#!/bin/bash

file=$1

maxstar=`cat $file | tail -n1 | awk '{print $1}'`

for (( i=1; i<=maxstar; i++ )); do
    echo "For a $i-star:"
    start=`cat $file | head -n $(($i-1)) | tail -n1 | java period | head -n1 | awk '{print $1}'`
    if [ "$start" == "No" ]; then
	echo "No period found"
	echo " "
    else
	echo "Period found at position $start"
	length=`cat $file | head -n $(($i-1)) | tail -n1 | java period | head -n1 | awk '{print $2}'`
	echo "Length of the period is $length"
	period=`cat $file | head -n $(($i-1)) | tail -n1 | java period | sed -n 2p`
	echo "The period is $period"
	echo " "
    fi
done
