#!/bin/bash
i=1;
cat file.txt | (while read line;
do
  if [ "$i" = "10" ] ; then
    	echo $line;
    	break;
  fi
  i=$(($i+1));
done)