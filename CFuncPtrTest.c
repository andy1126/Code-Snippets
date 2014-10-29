/**
 * Use function pointer to fulfill if statement
 */
#include <stdio.h>
#include <stdlib.h>

//typedef int FUNC();
void fun1(){
	printf("yeye got huge dick\n");
}

void fun2(){
	printf("yeye got fat wallet\n");
}

int main(void){
	void (*choice[2])();
	choice[0] = &fun1;
	choice[1] = &fun2;
	int decision = 10;
	choice[(decision < 5)]();
	return 0;
}