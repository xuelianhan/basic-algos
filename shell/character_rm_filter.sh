#!/bin/bash

#------------------------------------------------------------------------------------
# This shell implements the same function as cat *-download.log  | grep -E '[^0-9]'.
# Lines not starting with numbers will be removed of each file.
#
# Author:xuelian.han<xueliansniper@gmail.com>
# Wed Mar 26, 2014
#-----------------------------------------------------------------------------------

ipt_one=./downloads-log/*-download.log

for ipt1 in `ls $ipt_one`
do
  echo "Prepare to process "$ipt1
  sed -ni '/^[0-9]/p'  "$ipt1" 
done
echo "Filter job all done!"
