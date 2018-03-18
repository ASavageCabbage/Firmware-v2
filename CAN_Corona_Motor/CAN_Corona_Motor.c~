/* UBC Solar Corona Motor/Motor Controller Code
	Date: 1/13/18
	Purpose:
	- To monitor and/or control the speed, torque, input voltage, etc., of the M2096D-III motor via the pins on hte M2096C motor controller and other hardware
	- To implement the PID controller computations
	- To communicate data collected to the driver, pit crew, and the rest of the electrical system in the car via CAN Bus
	Inputs:
	-
	Outputs:
	-
*/

#include <stdio.h>
#include <stdlib.h>
#include <ubcsolar_can_ids.h>
#include <SPI.h>
#include <mcp_can.h>
#include <mcp_can_dfs.h>
#include <stm32f10x.h>

#define CAN_SS 9			// Replace with actual value later
#define BUS_SPEED CAN_125KBPS

#define RPM2MPS 2 * pi / 60

MCP_CAN CAN(CAN_SS);

int canSSOffset = 0;
volatile float rpm_sp = 0;
volatile float v_sp;
volatile float D = 0;

void CAN_setup( void ){
	// Initialize CAN bus serial communications
	CAN_INIT: 
		if (CAN_OK == CAN.begin(BUS_SPEED)) {
			printf("CAN BUS Shield init okay\n");
		}
		else {
			printf("CAN BUS Shield init fail\n");
			printf("Init CAN BUS Shield again with SS pin\n");
			printf(CAN_SS + canSSOffset);
		
			delay(100);

			canSSOffset ^= 1;
			CAN = MCP_CAN(CAN_SS + canSSOffset);
			ljmp CAN_INIT;
		}
}

double rpm2volts( float v_sp, float vmin, float vmax ){
	double rpm = 0;
	
	G_sp = 1300 * RPM2MPS / (vmax - vmin);
	rpm = G_sp * v_sp;

	return rpm;
}

void Timer1ISR(void) 
{
	TIM1_SR &= ~BIT0; // clear update interrupt flag
	if (D * 40 > TIM1_CNT){
		rpm_sp = rpm2volts( v_sp, vmin, vmax );
		TIM2_CH1 ~= TIM_CH1;
	}
}

void SysInit(void)
{
	// Set up output port bit for blinking LED
	RCC_AHBENR |= 0x00020000;  // peripheral clock enable for port A
	GPIOA_MODER |= 0x00000001; // Make pin PA0 output
	
	// Set up timer
	RCC_APB2ENR |= BIT11; // turn on clock for timer1
	// TIM1_CNT = ;
	// TIM1_PSC = 0x3E8;	// Pulse clk every 1ms
	TIM1_ARR = 8000;	// reload counter with 8000 at each overflow (equiv to 1ms)

	ISER |= BIT13;        // enable timer interrupts in the NVIC
	TIM1_CR1 |= BIT4;     // Downcounting    
	TIM1_CR1 |= BIT0;     // enable counting    
	TIM1_DIER |= BIT0;    // enable update event (reload event) interrupt  
	enable_interrupts();
}

void main( void ){
	SysInit();
	CAN_setup();
}

