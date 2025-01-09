#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>

int count(char* buffer){			// Count the number of arguments in user command
	int count = 0;
	char * argument;
	argument = strtok(buffer, " \n");
	while(argument != NULL){
		count++;
		argument = strtok(NULL, " \n");
	}
	return count;
}

int main(){
	char buffer[512];				//buffer is to hold the commands that the user will type in
	char* path = "/bin/";			// /bin/program_name is the first argument to pass to execv
	
	while(1){
		printf("myShell>");						//print the prompt
		fgets(buffer, 512, stdin);				//get input from the user
		
		int pid = fork();
		if(pid != 0){
			wait(NULL);
		}
		else{
			int no_of_args = count(buffer);
			char** array_of_strings = malloc((sizeof(char*)*(no_of_args+1)));		//+1 so that we can make the last element NULL
			// Find the Arguments and store them in array_of_strings
			int i = 0;
			char* ptr;
			ptr = strtok(buffer, " \n");
			while(ptr != NULL){
				array_of_strings[i] = (char*)malloc((sizeof(char)*strlen(ptr)));
				strcpy(array_of_strings[i], ptr);
				ptr = strtok(NULL, " \n");
				i++;
			}
			
			 //The first element in the array will be the program name so the path will be path+first element
			 char* prog = malloc(sizeof(char)*(strlen(array_of_strings[0]+strlen(path))));
			 prog = strcat(strcpy(prog, path), array_of_strings[0]);
			 
			 //pass the prepared arguments to execv and we're done!
			 int rv = execv(prog, array_of_strings);
		}
	}
	return 0;
}
