#include <stdio.h>
#include <string.h>
#define MAX_LENGTH 65536

#define NOT_IN_COMMENT 0
#define SINGLE_COMMENT 1
#define MULTI_COMMENT  2
int status = NOT_IN_COMMENT;  /* Are we in a comment? What type? */
int in_string = 0;            /* Are we inside of a string constant? */
char* stripcomments(char* stripped,char* code)
{
  int ndx;                      /* index for code[] */
  int ondx;                     /* index for output[] */
  char prevch;                  /* Value of the previous character */
  char ch;                      /* Character to input into */

  /* Remove all comments from the code and display results to user */
  for (ndx=ondx=0; ndx < strlen(code); ndx++)
  {
    char current = code[ndx];

    if (in_string) {
      if (current == '"') in_string = 0;
      stripped[ondx++] = current;
    }
    else {
      if (status == NOT_IN_COMMENT) {
        if (current == '"') {
          stripped[ondx++] = current;
          in_string = 1;
          continue;
        }

        if (current == '/' && prevch == '/') status = SINGLE_COMMENT;
        else if (current == '*' && prevch == '/') status = MULTI_COMMENT;
        else if (current != '/' || (current == '/' && ndx < strlen(code)-1 && !(code[ndx+1] == '/' || code[ndx+1] == '*'))) stripped[ondx++] = current;
      }

      else if (status == SINGLE_COMMENT) {
        if (current == '\n') {
          status = NOT_IN_COMMENT;
          stripped[ondx++] = '\n';
        }
      }

      else if (status == MULTI_COMMENT) {
        if (current == '/' && prevch == '*') status = NOT_IN_COMMENT;
      }
    }
    prevch = current;
  }
  stripped[ondx] = '\0';
  return(stripped);
}

int main(void)
{
  char code[MAX_LENGTH];        /* Buffer that stores the inputted code */
  char stripped[MAX_LENGTH];

  while( fgets(code,sizeof(code),stdin) )
  {
    //printf("%s\n",code);
    //        //strip comments...
    stripcomments(stripped,code);
    if( strlen(stripped) > 0 ) printf("%s",stripped);
  }
}
