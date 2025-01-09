#include  <stdio.h>
#include  <stdlib.h>

typedef struct NODE{
	int num;
	struct NODE* next;
}NODE;


typedef struct LIST{
	NODE* head;
	NODE* tail;
	int size;
}LIST;

typedef char boolean;
#define TRUE '1'
#define FALSE '0'

LIST* createQ();
LIST* enqueue(LIST*, const int);
int dequeue(LIST*);
int size(LIST*);
boolean isEmpty(LIST*);

int main(){
	LIST*  Q=createQ();
	printf("Empty/No:  %c\n",isEmpty(Q));
	enqueue(Q,10);
	enqueue(Q,20);
	enqueue(Q,30);
	enqueue(Q,40);
	printf("The size of the Queue:\t%d\n",size(Q));
	printf("Empty/No:  %c\n",isEmpty(Q));
	printf("The first element in the Queue:\t%d\n",front(Q));
	printf("%d removed from the Queue\n",dequeue(Q));
	printf("The first element in the Queue:\t%d\n",front(Q));
	printf("The size of the Queue is: \t%d\n",size(Q));

return 0;
}

LIST* createQ(){
	LIST* L = malloc(sizeof(LIST));
	if( L==NULL){
		perror("malloc() failed\n");
		exit(1);
	}
	L->head = L-> tail = NULL;
	L->size=0;
	return L;
}

LIST* enqueue(LIST* L, const int i){
	if(L==NULL){	  //"Unintialized list
		fprintf(stderr,"Queue not initialized\n");
		exit(1);
	}
	
	NODE* N = malloc(sizeof(NODE));
	if( N==NULL){
		perror("malloc() failed\n");
		exit(1);
	}
	N->num = i;
	N->next = NULL;
	
	if(L->head==NULL && L->tail==NULL){    //"Empty list"	
		L->head = L->tail = N;
		L->size++;
	}
	else{
		L->tail->next = N;
		L->tail = N;
		L->size++;
	}
return L;
}

int dequeue(LIST* L){
	NODE* N1 = NULL;
	NODE* N2 = NULL;
	int data;
	
	if(L==NULL){
		fprintf(stderr,"Queue not initialized");
		exit(1);
  	  }
  	else if(L->head==NULL && L->tail==NULL){
  		fprintf(stderr,"List is empty\n");
  		exit(1);
   	  }
   	N1 = L->head;
   	N2 = N1->next;
   	data=N1->num;
   	free(N1);
   	L->size--;
   	L->head =N2;
   	if(L->head==NULL)
	L->tail = L->head;
	return data;
}

int front(LIST* L){
	if(L==NULL){
		fprintf(stderr,"Queue not initialized");
		exit(1);
	}
	else if(L->head==NULL&&L->tail==NULL)
		printf("Empty Queue");
	else
		return L->head->num;
}

int size(LIST* L){
	if(L==NULL){
		fprintf(stderr,"Queue not initialized");
		exit(1);
	}
	else
		return L->size;
}

boolean isEmpty(LIST* L){
	boolean flag;
	if(L==NULL){
		fprintf(stderr,"Queue not initialized");
		exit(1);
	}
	else if(L->size==0)
		flag=TRUE;
	else
		flag=FALSE;
return flag;
}

