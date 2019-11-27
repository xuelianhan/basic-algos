#!/bin/bash
#------------------------------------------------------------------------------------------------
#
# The following shell count the download-times of each script by analyzing files in accesslog.
# It's effects as same as executing the command:
# grep "script" dfuse_ex140324.log | cut -d ' ' -f4 | cut -d '/' -f4 | sed '/^$/d' | sort | uniq -c | sort -rn | awk '{print $2,$1}' >20140324-download.log
#
# Author:xuelian.han<xueliansniper@gmail.com>
# Tue Mar 25,2014
#------------------------------------------------------------------------------------------------
ACCESS_LOG_DIR=./accesslog
MID_RS_LOG_DIR=./downloads-log
if [ ! -d "$MID_RS_LOG_DIR" ] ; then
 mkdir $MID_RS_LOG_DIR
fi
for aclog in `ls $ACCESS_LOG_DIR`
do
 echo "Start to process file:"$aclog
 prefix_name=${aclog%.*}
 num_prefix=`echo $prefix_name | sed -e s/[^0-9]//g`
 grep "script" "$ACCESS_LOG_DIR"/"$aclog" | cut -d ' ' -f4 | cut -d '/' -f4 | sed '/^$/d' | sort | uniq -c | sort -rn | awk '{print $2,$1}' >"$MID_RS_LOG_DIR"/"20""$num_prefix""-download.log"
 echo "Finished processing file:"$aclog
done
echo "Statistic jobs for all accessing logs finished! The results have been store into directory "$MID_RS_LOG_DIR
