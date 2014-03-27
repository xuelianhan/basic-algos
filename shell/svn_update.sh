#!/bin/bash
i=1;
SDIR_1=/home/dir1;
SDIR_2=/home/dir2
cat sm-log.txt | (while read line; 
do
	arr=(${line});
	a1=${arr[0]};
	a2=${arr[1]};
	echo "start to process line "$i;
	echo "a1:"$a1;
	echo "a2:"$a2;
	if [ "$a1" = "A" ] ; then 
		echo "svn add "$a2;
		echo "copy "$SDIR_1/$a2" to "$SDIR_2/`dirname $a2`;
		cp "$SDIR_1/$a2" $SDIR_2/`dirname $a2`; 
	elif [ "$a1" = "D" ]; then
		echo "svn delete "$a2;
		echo "delete -f "$SDIR_2/$a2;
		rm -f $SDIR_2/$a2; 
	elif [ "$a1" = "U" ]; then
		echo "svn update "$a2;
		echo "copy "$SDIR_1/$a2" to "$SDIR_2/`dirname $a2`;
		cp "$SDIR_1/$a2"  $SDIR_2/`dirname $a2`;
	else 
		echo "no matched options for"+$a1;
	fi	
	i=$(($i+1));
done)



   


