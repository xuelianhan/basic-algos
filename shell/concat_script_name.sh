#!/bin/bash

#-----------------------------------------------------------------------
# This shell join two files by fields.It reads files line by line,and 
# concat two lines of input files if field $1 of file1 equals field $1
# of file2.
#
# Author:xuelian.han<xueliansniper@gmail.com>
# Wed Mar 26,2014
#
#------------------------------------------------------------------------

ipt_one=./downloads-log/*-download.log
ipt_two=./id_name.log
STA_RS_LOG_DIR=./statistic-log
if [ ! -d "$STA_RS_LOG_DIR" ] ; then
  mkdir $STA_RS_LOG_DIR
fi

rs_f="statistic.log"

for ipt1 in `ls $ipt_one`
do
  echo "Prepare to process "$ipt1
  prefix_name=${ipt1%.*}
  num_prefix=`echo $prefix_name | sed -e s/[^0-9]//g`""
  while read line;
  do
    script_id_f1=$(echo "$line" | awk -F " " '{ print $1 }')
    while read kv;
    do
      script_id_f2=$(echo "$kv" | awk -F "\t" '{ print $1 }')
      script_name_f2=$(echo "$kv" | awk -F "\t" '{ print $2 }')
      if [ $script_id_f1 -eq $script_id_f2 ]; then
        a=$(echo "$line" | awk -F " " '{ print $1,$2 }')
        b=$script_name_f2
        echo "$a"" ""$b">>$STA_RS_LOG_DIR"/"$num_prefix"-"$rs_f
      fi
    done < "$ipt_two"
  done < "$ipt1"
  echo "End processing "$ipt1
done
echo "All the results have been written into directory "$STA_RS_LOG_DIR
