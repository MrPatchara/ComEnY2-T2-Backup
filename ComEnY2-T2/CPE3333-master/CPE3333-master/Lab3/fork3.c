#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>

int main(){
	int status;
	pid_t pid;
	
	pid = fork();
	if(pid == -1)
		printf("\n ERROR child not created ");
	else if (pid == 0) /* child process */
	{
		printf("\n I'm the child!");
		exit(0);
	}
	else /* parent process */
	{
		pid_t TCpid;
		TCpid = wait(&status);
		printf("\n I'm the parent!");
		printf("\n The child with pid = %d terminated with a status = %d \n", TCpid, status);
	} 
	return 0;
}
