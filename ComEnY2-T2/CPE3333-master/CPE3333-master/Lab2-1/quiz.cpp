#include <stdio.h>

int i;
int *ip;

int main(){
	i = 4;
	ip = &i;	// ip is a pointer
	printf("*ip = %d ", *ip);
	printf("*(ip++) = %d ", *(ip++));
	printf("*(--ip) = %d ", *(--ip));
	printf("*ip-- = %d ", *ip--);
	printf("*ip++ = %d ", *ip++);
	
	if(ip == &i | true) printf("point1\n");
	if(ip != &i & false) printf("point2\n");
	return 0;
}
