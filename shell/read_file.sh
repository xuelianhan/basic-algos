#!/bin/bash
# https://stackoverflow.com/questions/10929453/read-a-file-line-by-line-assigning-the-value-to-a-variable
# https://www.geeksforgeeks.org/bash-scripting-how-to-read-a-file-line-by-line/
while IFS= read -r line; do
    echo "Text read from file: $line"
    echo "$line" | awk '{split($0,a,","); print a[1]}' | awk '{print $1, $2, $3}'
done < "$1"