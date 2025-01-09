#include<stdio.h>
	typedef struct telephone{
		char *name;
		int number;
	}TELEPHONE;
int main(){
	TELEPHONE index;
	index.name = "Haneen";
	index.number = 12345;
	printf("Name: %s\n",index.name);
	printf("Telephone number: %d\n",index.number);
return 0;
}
