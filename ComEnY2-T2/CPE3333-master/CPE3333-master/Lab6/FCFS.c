/* A program to simulate the FCFS CPU scheduling algorithm */
#include<stdio.h>
#include<string.h>

int main()
{
	char pn[10][10];
	int arr[10], bur[10], star[10], finish[10], tat[10], wt[10], i, n;
	double totwt = 0, tottat = 0;
	printf("Enter the number of processes: ");
	scanf("%d", &n);
	for(i = 0; i < n; i++)
	{
		printf("Enter the Process Name, Arrival Time & Burst Time: ");
		scanf("%s%d%d", pn[i], &arr[i], &bur[i]);
	}
	for(i = 0; i < n; i++)
	{
		if(i == 0)
		{
			star[i] = arr[i];
			wt[i] = star[i] - arr[i];
			finish[i] = star[i] + bur[i];
			tat[i] = finish[i] - arr[i];
		}
		else
		{
			star[i] = finish[i-1];
			wt[i] = star[i] - arr[i];
			finish[i] = star[i] + bur[i];
			tat[i] = finish[i] - arr[i];
		}
	}
	printf("\n%-8s%-8s%-8s%-8s%-8s%-8s", 
			"PName", "Arrtime", "Burtime", "Start", "TAT", "Finish");
	for(i=0;i<n;i++)
	{
		printf("%-8s%-8d%-8d%-8d%-8d%-8d", 
				pn[i], arr[i], bur[i], star[i], tat[i], finish[i]);
	}
	printf("\nAverage Waiting time: %lf", totwt/n);
	printf("\nAverage Turn Around Time: %lf", tottat/n);
	return 0;
}
