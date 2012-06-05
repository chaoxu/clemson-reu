#!/bin/bash

echo -e "k\tsg\tsg-k"
echo -e "---\t---\t----"
cat $1 | awk '{ print NR-1, "\t", $NF, "\t", $NF-(NR-1)}'