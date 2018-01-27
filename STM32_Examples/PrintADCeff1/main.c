#include "stm32f05xxx.h"
#include "adc.h"
#include "serial.h"
#include <stdio.h>

void delay(int dly)
{
	while( dly--);
}

void main(void)
{
	int j, a, b;
	
	printf("ADC/SERIAL/CLOCK test.\r\n");
	initADC();
	
	RCC_AHBENR |= 0x00020000; // peripheral clock enable for port A
	GPIOA_MODER |= 0x00000001; // Make pin PA0 output
	while(1)
	{
		j=readADC();
		a=(j*330000)/0x1000;
		printf("ADC[9]=0x%04x V=%d.%dV\r", j, a/100000, a%100000);
		fflush(stdout);
		GPIOA_ODR |= BIT0; // PA0=1
		delay(500000);
		GPIOA_ODR &= ~(BIT0); // PA0=0
		delay(500000);
	}
}
