;--------------------------------------------------------
; File Created by C51
; Version 1.0.0 #1069 (Apr 23 2015) (MSVC)
; This file was generated Wed Mar 15 01:44:10 2017
;--------------------------------------------------------
$name main
$optc51 --model-small
	R_DSEG    segment data
	R_CSEG    segment code
	R_BSEG    segment bit
	R_XSEG    segment xdata
	R_PSEG    segment xdata
	R_ISEG    segment idata
	R_OSEG    segment data overlay
	BIT_BANK  segment data overlay
	R_HOME    segment code
	R_GSINIT  segment code
	R_IXSEG   segment xdata
	R_CONST   segment code
	R_XINIT   segment code
	R_DINIT   segment code

;--------------------------------------------------------
; Public variables in this module
;--------------------------------------------------------
	public _main
	public _delay
;--------------------------------------------------------
; Special Function Registers
;--------------------------------------------------------
;--------------------------------------------------------
; special function bits
;--------------------------------------------------------
;--------------------------------------------------------
; overlayable register banks
;--------------------------------------------------------
	rbank0 segment data overlay
;--------------------------------------------------------
; internal ram data
;--------------------------------------------------------
	rseg R_DSEG
;--------------------------------------------------------
; overlayable items in internal ram 
;--------------------------------------------------------
	rseg	R_OSEG
;--------------------------------------------------------
; indirectly addressable internal ram data
;--------------------------------------------------------
	rseg R_ISEG
;--------------------------------------------------------
; absolute internal ram data
;--------------------------------------------------------
	DSEG
;--------------------------------------------------------
; bit data
;--------------------------------------------------------
	rseg R_BSEG
;--------------------------------------------------------
; paged external ram data
;--------------------------------------------------------
	rseg R_PSEG
;--------------------------------------------------------
; external ram data
;--------------------------------------------------------
	rseg R_XSEG
;--------------------------------------------------------
; absolute external ram data
;--------------------------------------------------------
	XSEG
;--------------------------------------------------------
; external initialized ram data
;--------------------------------------------------------
	rseg R_IXSEG
	rseg R_HOME
	rseg R_GSINIT
	rseg R_CSEG
;--------------------------------------------------------
; Reset entry point and interrupt vectors
;--------------------------------------------------------
	CSEG at 0x0000
	ljmp	_crt0
;--------------------------------------------------------
; global & static initialisations
;--------------------------------------------------------
	rseg R_HOME
	rseg R_GSINIT
	rseg R_GSINIT
;--------------------------------------------------------
; data variables initialization
;--------------------------------------------------------
	rseg R_DINIT
	; The linker places a 'ret' at the end of segment R_DINIT.
;--------------------------------------------------------
; code
;--------------------------------------------------------
	rseg R_CSEG
;------------------------------------------------------------
;Allocation info for local variables in function 'delay'
;------------------------------------------------------------
;dly                       Allocated to registers r2 r3 
;------------------------------------------------------------
;	C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.c:3: void delay(int dly)
;	-----------------------------------------
;	 function delay
;	-----------------------------------------
_delay:
	using	0
	mov	r2,dpl
	mov	r3,dph
;	C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.c:5: while( dly--);
L002001?:
	mov	ar4,r2
	mov	ar5,r3
	dec	r2
	cjne	r2,#0xff,L002008?
	dec	r3
L002008?:
	mov	a,r4
	orl	a,r5
	jnz	L002001?
	ret
;------------------------------------------------------------
;Allocation info for local variables in function 'main'
;------------------------------------------------------------
;------------------------------------------------------------
;	C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.c:8: void main(void)
;	-----------------------------------------
;	 function main
;	-----------------------------------------
_main:
;	C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.c:10: RCC_AHBENR |= 0x00020000; // peripheral clock enable for port A
	mov	dptr,#0x1014
	mov	b,#0x02
	lcall	__gptrget
	mov	r2,a
	inc	dptr
	lcall	__gptrget
	mov	r3,a
	mov	r4,#0x00
	mov	r5,#0x00
	orl	ar4,#0x02
	mov	dptr,#0x1014
	mov	b,#0x02
	mov	a,r2
	lcall	__gptrput
	inc	dptr
	mov	a,r3
	lcall	__gptrput
;	C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.c:11: GPIOA_MODER |= 0x00000001; // Make pin PA0 output
	mov	dptr,#0x0000
	mov	b,#0x00
	lcall	__gptrget
	mov	r2,a
	inc	dptr
	lcall	__gptrget
	mov	r3,a
	orl	ar2,#0x01
	mov	dptr,#(0x00&0x00ff)
	clr	a
	mov	b,a
	mov	a,r2
	lcall	__gptrput
	inc	dptr
	mov	a,r3
	lcall	__gptrput
;	C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.c:12: while(1)
L003002?:
;	C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.c:14: GPIOA_ODR |= 0x00000001; // PA0=1
	mov	dptr,#0x0014
	mov	b,#0x00
	lcall	__gptrget
	mov	r2,a
	inc	dptr
	lcall	__gptrget
	mov	r3,a
	orl	ar2,#0x01
	mov	dptr,#(0x14&0x00ff)
	clr	a
	mov	b,a
	mov	a,r2
	lcall	__gptrput
	inc	dptr
	mov	a,r3
	lcall	__gptrput
;	C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.c:15: delay(500000);
	mov	dptr,#0xA120
	lcall	_delay
;	C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.c:16: GPIOA_ODR &= ~(0x00000001); // PA0=0
	mov	dptr,#0x0014
	mov	b,#0x00
	lcall	__gptrget
	mov	r2,a
	inc	dptr
	lcall	__gptrget
	mov	r3,a
	anl	ar2,#0xFE
	mov	dptr,#(0x14&0x00ff)
	clr	a
	mov	b,a
	mov	a,r2
	lcall	__gptrput
	inc	dptr
	mov	a,r3
	lcall	__gptrput
;	C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.c:17: delay(500000);
	mov	dptr,#0xA120
	lcall	_delay
	sjmp	L003002?
	rseg R_CSEG

	rseg R_XINIT

	rseg R_CONST

	CSEG

end
