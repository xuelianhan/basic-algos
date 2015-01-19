#include <stdio.h>
#include <stdlib.h>

int insert_sort(int t_array[], int n) {
  int i,j;
  for (i = 1; i < n; i++) {
    j = i;
    while (j > 0 && t_array[j] < t_array[j-1]) {
      swap(&t_array[j], &t_array[j-1]);
      j--;
    }
  }



}
