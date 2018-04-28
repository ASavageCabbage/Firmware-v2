/* UBC Solar Motor/Motor Controller Code
	Date: 1/13/18
	Purpose:
	- To monitor and/or control the speed, torque, input voltage, etc., of the motor via the pins on the motor controller and other hardware
	- To implement the PID controller
	- To communicate data collected to the driver, pit crew, and the rest of the electrical system in the car via CAN Bus
	Inputs:
	- w_sp = Speed set point value from the driver
	- B+, B- = Input terminals from battery
	- Kp, Ki, Kd = PID controller constants
	Outputs:
	- VA, VB, VC = Set of 3-phase voltages applied to the terminals of the motor
	- ___ = CAN messages
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <ubcsolar_can_ids.h>
#include <SPI.h>
#include <mcp_can.h>
#include <mcp_can_dfs.h>
#include <stm32f10x.h>

#define CAN_SS 9			// Replace with actual value later
#define BUS_SPEED CAN_125KBPS

#define RPM2MPS 2 * pi / 60
#define GND 0
#define VCC 3.3

#define Kp				// PID Controller constants
#define Ki
#define Kd

MCP_CAN CAN(CAN_SS);

int canSSOffset = 0;

volatile double w_sp = 0;		// Speed set point given by the driver
volatile double D = 0;
volatile int clk = 0;			// Clock generated using w_sp

// Define and initialize user interface variables
volatile int dir;			// Forward = 1, Reverse = 0
volatile int brake;			// Pressed = 1, Not pressed = 0
volatile float posedge;			// Rising edge of clk whose freq is determined by w_sp
volatile int enInv = 0;			// Inverter enabled = 1, disabled = 0
volatile int enRegen = 0;		// Regen enabled = 1, disabled = 0

// Define a structure for containing the state of the MOSFET gates (GND = ON, VCC = OFF)
// For a diagram of the elements' corresponding MOSFETs, see Confluence page
typedef struct{
	float HB1;
	float HB2;
	float HB3;
	float HB4;
	float HB5;
	float HB6;
}SpeedCtrl;

// 3-phase MOSFET bridge control variables
SpeedCtrl initBridge = {VCC, VCC, VCC, VCC, VCC, VCC};		// Initial condition
SpeedCtrl stateBridge;						// Struct for current states
float *Qn1 = (float *)malloc(sizeof(float));			// Pointers to the two MOSFETs that are on
float *Qn2 = (float *)malloc(sizeof(float));
Qn1 = &stateBridge;						// Initialize pointers to point to addresses of first 2 elements in stateBridge
Qn2 = &stateBridge + 1;

// Initialize MOSFET bridge
stateBridge = initBridge;

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

// Purpose: To convert encoder values to desired speed
// Inputs:
// Outputs:
double rpm2volts( float w_sp, float vmin, float vmax ){
	double rpm = 0;
	
	G_sp = 1300 * RPM2MPS / (vmax - vmin);
	rpm = G_sp * w_sp;

	return rpm;
}

// Purpose: To control the 3-phase MOSFET bridge
// Inputs:
// 	-dir = Direction to turn motor
// 	-brake = If brake is pressed or not
// 	-posedge = Pulse when posedge clk where clk freq changes according to w_sp
// Outputs: stateBridge = ON or OFF signal to MOSFETs
void bridgeCtrl( int dir, int brake, int posedge ){
	if( ~brake && posedge ){
		enRegen = 0;
		enInv = 1;
		if( dir ){
			*Qn1 = VCC;
			Qn1 = Qn2;
			Qn2++;
			*Qn2 = GND;
		}
		else{
			*Qn2 = VCC;
			Qn2 = Qn1;
			Qn1--;
			*Qn1 = GND;
		}
	}

	else{
		stateBridge = initBridge;
		enRegen = 1;
		enInv = 0;
	}
}

// Purpose: To change the frequency of the clk to match the set point speed
// Inputs: w_sp = Set point speed given by the driver
// Outputs: clk = Clock of desired frequency
// SPECIAL NOTE!!! PID controller constants used here!!!
int vfd( double w_sp ){
	return clk;
}

void Timer2ISR(void) 
{
	TIM2_SR &= ~BIT0; // clear update interrupt flag
	if (D * 4000 > TIM2_CNT){
		TIM2_CH1 ~= TIM2_CH1;
	}
}

// void Timer3ISR(void){}

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

// Write to pins on the STM32F103RB microcontroller and implement above functions
void main( void ){
	SysInit();
	CAN_setup();
	rpm_sp = rpm2volts( w_sp, vmin, vmax );
}

