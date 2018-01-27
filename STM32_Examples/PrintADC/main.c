#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "stm32f05xxx.h"
#include "adc.h"

void delay(int dly)
{
	while( dly--);
}

void main(void)
{
	float a;
	int j;
	
	printf("ADC/SERIAL/CLOCK test.\r\n");
	
	initADC();
	
	RCC_AHBENR |= 0x00020000; // peripheral clock enable for port A
	GPIOA_MODER |= 0x00000001; // Make pin PA0 output
	while(1)
	{
		j=readADC();
		a=(j*3.3)/0x1000;
		printf("ADC[9]=0x%04x V=%fV\r", j, a);
		fflush(stdout);
		GPIOA_ODR |= BIT0; // PA0=1
		delay(500000);
		GPIOA_ODR &= ~(BIT0); // PA0=0
		delay(500000);
	}
}
