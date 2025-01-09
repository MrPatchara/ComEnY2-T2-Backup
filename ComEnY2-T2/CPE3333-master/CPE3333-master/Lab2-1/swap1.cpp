/*		exchange values */

#include <stdio.h>

void swap(float *x, float *y);

main()
{
	float x, y;
	
	printf("Please input 1st value: ");
	scanf("%f", &x);
	printf("Please input 2nd value: ");
	scanf("%f", &y);
	printf("Values BEFORE 'swap' %f, %f\n", x, y);
	swap(&x, &y);	/*		address of x, y */
	printf("Values AFTER  'swap' %f, %f\n", x, y);
	return 0;
}

/*	exchange values within function */

void swap(float *x, float *y)
{
	float t;
	
	t = *x; /*		*x is value pointed to by x 	*/
	*x = *y;
	*y = t;
	printf("Values WITHIN 'swap' %f, %f\n", *x, *y);
}
