#include <stdio.h>
#include <stdlib.h>

int main () {
	
	FILE *p_file;
	p_file = fopen ("Data2", "w");
	if (p_file == NULL) {
		fprintf(stderr, "Couldn't open file!\n");
		exit (1);
	}
	
	fprintf(p_file,"%d %s \n",1,"Haneen");
	fclose(p_file);
	
	p_file = fopen ("Data2", "r");
	if (p_file == NULL) {
		fprintf(stderr,"Couldn't open file!\n");
		exit (1);
	}
	
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
