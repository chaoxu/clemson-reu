#!/bin/bash

file=$1
num=$2
numones=$3

query=''

for (( i=0; i<$numones; i++ )); do
    query=$query"1, "
done

query=$query$num

cat "$file" | grep "$query" | head -n1