// getpid.c

#include <stdio.h>
#include <unistd.h>

int main(void)
{
	printf("Hello, this is process ID %d\n", getpid());
	return 0;
}
