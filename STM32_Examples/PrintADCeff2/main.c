#include "stm32f05xxx.h"
#include "adc.h"
#include "serial.h"

void delay(int dly)
{
	while( dly--);
}

void PrintNumber(int N, int Base, int digits)
{ 
	char HexDigit[]="0123456789ABCDEF";
	int j;
	#define NBITS sizeof(int)
	char buff[NBITS+1];
	buff[NBITS]=0;

	j=NBITS-1;
	while ( (N>0) | (digits>0) )
	{
		buff[j--]=HexDigit[N%Base];
		N/=Base;
		if(digits!=0) digits--;
	}
	eputs(&buff[j+1]);
}

void main(void)
{
	int j, v;
	eputs("ADC/SERIAL/CLOCK test.\r\n");
	initADC();
	
	RCC_AHBENR |= 0x00020000; // peripheral clock enable for port A
	GPIOA_MODER |= 0x00000001; // Make pin PA0 output
	while(1)
	{
		j=readADC();
		v=(j*33000)/0x1000;
		eputs("ADC[9]=0x");
		PrintNumber(j, 16, 4);
		eputs(", ");
		PrintNumber(v/10000, 10, 1);
		eputc('.');
		PrintNumber(v%10000, 10, 4);
		eputs("V       \r");;

		GPIOA_ODR ^= BIT0; // complement PA0
		delay(500000);
	}
}
