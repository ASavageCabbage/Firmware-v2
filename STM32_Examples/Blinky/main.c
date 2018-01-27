#include "stm32f05xxx.h"

void delay(int dly)
{
	while( dly--);
}

void main(void)
{
	RCC_AHBENR |= 0x00020000; // peripheral clock enable for port A
	GPIOA_MODER |= 0x00000001; // Make pin PA0 output
	while(1)
	{
		GPIOA_ODR |= 0x00000001; // PA0=1
		delay(500000);
		GPIOA_ODR &= ~(0x00000001); // PA0=0
		delay(500000);
	}
}
