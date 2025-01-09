#include <stdio.h>
#include <stdlib.h>

int main (int argc, char **argv) {

	if (argc != 2) {
		fprintf(stderr, "Exactly one argument required, namely the filename\n");
		exit (1);
	}
	
	FILE *p_file;
	p_file = fopen (argv[1], "r");
	if (p_file == NULL) {
		fprintf(stderr, "%s: Couldn't open file %s!\n", argv[0], argv[1]);
		exit (1);
	}
	
	printf("Reading file: %s \n", argv[1]);
	
	char *line = NULL;
	size_t len;
	int line_no = 0;
	
	while (getline (&line, &len, p_file) != -1) {
		printf ("Line %d is: %s", line_no, line);
		free (line);
		line = NULL;
		line_no++;
	}
	
	fclose(p_file);
	
	return 0;
}
