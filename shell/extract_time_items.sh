#!/bin/bash
#------------------------------------------------------------------------------------------------
#
# The following shell extract date of downloading for each script by analyzing files in accesslog.
# It's effects as same as executing the command:
# grep "script" dfuse_ex140324.log | cut -d ' ' -f1,4 | cut -d '/' -f3,5,7 | tr '/' ' ' | awk -F ':' '{ print $1"-00-00",substr($2,0,2)":"substr($3,1,3)":"$4}'|sed "s/-00-00/-03-24/" 
#
# Author:xuelian.han<xueliansniper@gmail.com>
# Tue Mar 26,2014
# 2014-00-00 00: 41: 42  20 58bf6488-948b-4be9-992a-1ecdab3f0f0e.se
#
#------------------------------------------------------------------------------------------------
ACCESS_LOG_DIR=./accesslog
MID_RS_LOG_DIR=./timeitems-log
if [ ! -d "$MID_RS_LOG_DIR" ] ; then
   mkdir $MID_RS_LOG_DIR
fi
zero_str="-00-00"

for aclog in `ls $ACCESS_LOG_DIR`
do
  echo "Start to process file:"$aclog
  prefix_name=${aclog%.*}
  num_prefix=`echo $prefix_name | sed -e s/[^0-9]//g`""
  pnum="20""$num_prefix"
  target_f="$MID_RS_LOG_DIR"/"20""$num_prefix""-timeitems.log"
  y=${pnum:0:4}
  m=${pnum:4:2}
  d=${pnum:6:2}
  md="-"$m"-"$d
  ymd=$y"-"$m"-"$d
  echo "Extracting log on "$ymd
  while read line;
  do
    if [[ $line == *script* ]];then
      echo "$line" | cut -d ' ' -f1,4 | cut -d '/' -f3,5,7 | tr '/' ' ' | awk -F ':' '{ print $1"-00-00",substr($2,0,2)":"substr($3,1,3)":"$4}'\
      | sed "s/$zero_str/$md/">>$target_f
    fi
  done < "$ACCESS_LOG_DIR"/"$aclog"
  echo "Finished processing file:"$aclog
done
echo "Final results have been written into directory "$MID_RS_LOG_DIR