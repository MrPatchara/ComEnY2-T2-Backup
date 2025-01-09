#include <stdio.h>
#include <stdlib.h>

int main() {
	FILE *p_file;
	p_file = fopen("data", "r");
	
	if (p_file == NULL) {
		perror("Couldn't open file! Check it exists and that you have read access.\n");
		exit(1);
	}
	
	int value_1;
	int value_2;
	int result = fscanf(p_file, "%d,%d", &value_1, &value_2);
	if(result == 0) {	// like scanf, fscanf will return zero on fail.
		perror("Couldn't read data!\n");
		exit (1);
	}

printf ("value_1 -> %d\n", value_1);
printf ("value_2 -> %d\n", value_2);
return 0;
}
