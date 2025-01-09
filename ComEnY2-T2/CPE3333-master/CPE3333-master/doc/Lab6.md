# Lab 6

> [!NOTE]
> This document doesn't yet finish.

---

## FCFS.c

```c
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
```

![FCFS.c](https://github.com/zorone/CPE3333/blob/master/res/Screenshot%20from%202024-10-24%2011-36-54.png?raw=true "FCFS.c")

### explaination

At line 10 - 16:

```c
    printf("Enter the number of processes: ");
    scanf("%d", &n);
    for(i = 0; i < n; i++)
    {
        printf("Enter the Process Name, Arrival Time & Burst Time: ");
        scanf("%s%d%d", pn[i], &arr[i], &bur[i]);
    }
```

> This section is about getting the inputs from the user

At line 17 - 33:

```c
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
```

> This section is about calculating the CPU time of each process.
>
> Let's explain the variable first.
>
> - `arr` contains the `arrival time` of each process
> - `star` contains the `starting time` of each process
> - `wt` contains the `waiting time` of each process
> - `finish` contains the `finishing time` of each process
> - `bur` contains the `bursting time` (Or, the `CPU time`) of each process
> - `tat` contains the `Turn Around time` of each process
>
> This part of the code assume that calculation has to be in 2 stage.
>
> - First Stage is when the calculation data is the first one. In this case, we'll set the process to start at `t = 0`
>   - It'll set `starting time` as the same as `arriving time`.
>   - It'll set `waiting time` as `0`
>   - It'll set `finishing time` as `arriving time` + `bursting time` (time from the starting point until done, with the length of `bursting time`.)
>   - It'll set `Turn Around time` as the `bursting time` (In this case, `Turn Around time` is the same as the length of `bursting time`, as there aren't any process beforehand, and the process doesn't have to wait any.)
>
> - The latter one is the normal case.
>
>   - `starting time` of the next process, is the same as the `finishing time` of the last process.
>   - `waiting time` is the length from `arriving time` to `starting time`
>   - `finishing time` is the point after `starting time`, with the length of `bursting time`
>   - `Turn Around time`, in this code, is the time from `arriving time` to `finishing time`. which is the same length as the time from `arriving time`, to `waiting time`, and then to `bursting time`

At line 34 - 42:

```c
    printf("\n%-8s%-8s%-8s%-8s%-8s%-8s", 
            "PName", "Arrtime", "Burtime", "Start", "TAT", "Finish");
    for(i=0;i<n;i++)
    {
        printf("%-8s%-8d%-8d%-8d%-8d%-8d", 
                pn[i], arr[i], bur[i], star[i], tat[i], finish[i]);
    }
    printf("\nAverage Waiting time: %lf", totwt/n);
    printf("\nAverage Turn Around Time: %lf", tottat/n);
```

> Show the result, iterate over each and every process the user give.

SJF.c
![SJF.c](https://github.com/zorone/CPE3333/blob/master/res/Screenshot%20from%202024-10-25%2020-29-11.png?raw=true "SJF.c")

RR.c
![RR.c](https://github.com/zorone/CPE3333/blob/master/res/Screenshot%20from%202024-10-25%2021-10-05.png?raw=true "RR.c")
