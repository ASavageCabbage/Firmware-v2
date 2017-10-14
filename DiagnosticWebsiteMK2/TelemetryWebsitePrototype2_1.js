var PeakMainCurrent = 0;							//GLOBAL BECAUSE I AM TOO LAZY TO USE REAL FUNCTIONS
var PeakMainTemperature = 0;
var PeakMainTemperatureTime;
var PeakMainCurrentTime;
var StartTime = Date.now();
			


		function Placeholder() {
				var Rand;
				Rand = Math.floor(Math.random()*100);
				//Rand = "OK";
				return Rand;
			}
			
		function DisplayBlockA() {					//DISPLAY BLOCK FOR KEY TEMPERATURE
				document.getElementById('CANMessage').innerHTML = "Last CAN Message: " + Placeholder();
				document.getElementById('LeftSignal').innerHTML = "Left Signal: " + Placeholder();
				document.getElementById('RightSignal').innerHTML = "Right Signal: " + Placeholder();
				document.getElementById('Acceleration').innerHTML = "Acceleration: " + Placeholder();
				document.getElementById('Regeneration').innerHTML = "Regeneration: " + Placeholder();
				document.getElementById('Direction').innerHTML = "Direction: " + Placeholder();
				document.getElementById('Status').innerHTML = "Status: " + Placeholder();
				document.getElementById('ErrorMessage').innerHTML = "Error: " + Placeholder();
				var t = setTimeout(DisplayBlockA, 1000);	
		}
			
		function DisplayBlockB() {					//DISPLAY BLOCK FOR STATES
				document.getElementById('StateOfCharge').innerHTML = "Charge: " + Placeholder();
				document.getElementById('MainVoltage').innerHTML = "Voltage: " + Placeholder();
				document.getElementById('AuxVoltage').innerHTML = "AuxVoltage: " + Placeholder();
				document.getElementById('Brake').innerHTML = "Brake: " + Placeholder();
				document.getElementById('Hazard').innerHTML = "Hazard: " + Placeholder();
				var t = setTimeout(DisplayBlockB, 1000);	
		}
			
		function DisplayBlockC() {					//DISPLAY BLOCK FOR MAIN CURRENT
		
				var X = Placeholder();
				//var PeakMainCurrentTime;				
				
				document.getElementById('MainCurrent').innerHTML = "Current: " + X;
				if (X > PeakMainCurrent){
					PeakMainCurrent = X;
					PeakMainCurrentTime = Date.now() - StartTime;
					PeakMainCurrentTime = Math.round(PeakMainCurrentTime/1000);
					
				}
				document.getElementById('PeakCurrent').innerHTML = "Peak: " + PeakMainCurrent;
				document.getElementById('PeakCurrentTime').innerHTML = "Peak Time: " + PeakMainCurrentTime + 's';
				
				var t = setTimeout(DisplayBlockC, 1000);	
				
				return PeakMainCurrent;
		}
		
		function DisplayBlockD() {					//DISPLAY BLOCK FOR MAIN TEMPERATURE
		
				var X = Placeholder();
				//var PeakMainTemperatureTime;




				document.getElementById('MainTemperature').innerHTML = "Temperature: " + X;
				if (X > PeakMainTemperature){
					PeakMainTemperature = X;
					PeakMainTemperatureTime = Date.now() - StartTime;
					PeakMainTemperatureTime = Math.round(PeakMainTemperatureTime/1000);
				}
				document.getElementById('PeakTemperature').innerHTML = "Peak: " + PeakMainTemperature;

				document.getElementById('PeakTemperatureTime').innerHTML = "Peak Time: " + PeakMainTemperatureTime + 's';
				
				var t = setTimeout(DisplayBlockD, 1000);	
		}	

		function ResetMainCurrent() {				//RESET PEAKMAINCURRENT AND PEAKMAINCURRENTTIME TO 0
			PeakMainCurrent = 0;
			PeakMainCurrentTime = 0;
			document.getElementById('PeakCurrent').innerHTML = "Peak: " + PeakMainCurrent;
			document.getElementById('PeakCurrentTime').innerHTML = "Peak Time: " + PeakMainCurrentTime + 's';
			StartTime = Date.now();
		}
		
		function ResetMainTemperature() {			//RESET PEAKMAINTEMPERATURE AND PEAKMAINTEMPERATURETIME TO 0
			PeakMainTemperature = 0;		
			PeakMainTemperatureTime = 0;
			document.getElementById('PeakTemperature').innerHTML = "Peak: " + PeakMainTemperature;
			document.getElementById('PeakTemperatureTime').innerHTML= "Peak Time: " + PeakMainTemperatureTime + 's';
			StartTime = Date.now();
		}
		

function MainUpdater() {
		
		DisplayBlockA();
		DisplayBlockB();
		DisplayBlockC();
		DisplayBlockD();
}