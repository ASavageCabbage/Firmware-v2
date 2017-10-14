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
				//var Largest = 0;
				var LargestTime;
				document.getElementById('MainCurrent').innerHTML = "Current: " + X;
				if (X > PeakMainCurrent){
					PeakMainCurrent = X;
				}
				document.getElementById('PeakCurrent').innerHTML = "Peak: " + PeakMainCurrent;
				//Also get LargestTime, but I haven't fiqure out how to do that yet.
				document.getElementById('PeakCurrentTime').innerHTML = "Peak Time: " + 0;
				var t = setTimeout(DisplayBlockC, 1000);	
				
				return PeakMainCurrent;
		}
		
		function DisplayBlockD() {					//DISPLAY BLOCK FOR MAIN TEMPERATURE
		
				var X = Placeholder();
				//var Largest = 0;
				var LargestTime;
				document.getElementById('MainTemperature').innerHTML = "Temperature: " + X;
				if (X > PeakMainTemperature){
					PeakMainTemperature = X;
				}
				document.getElementById('PeakTemperature').innerHTML = "Peak: " + PeakMainTemperature;
				//Also get LargestTime, but I haven't fiqure out how to do that yet.
				document.getElementById('PeakTemperatureTime').innerHTML = "Peak Time: " + 0;
				
				var t = setTimeout(DisplayBlockD, 1000);	
		}	

		function ResetMainCurrent() {				//RESET PEAKMAINCURRENT AND PEAKMAINCURRENTTIME TO 0
			PeakMainCurrent = 0;
			document.getElementById('PeakCurrent').innerHTML = "Peak: " + PeakMainCurrent;
		}
		
		function ResetMainTemperature() {			//RESET PEAKMAINTEMPERATURE AND PEAKMAINTEMPERATURETIME TO 0
			PeakMainTemperature = 0;		
			document.getElementById('PeakTemperature').innerHTML = "Peak: " + PeakMainTemperature;
		}
		
var PeakMainCurrent = 0;							//GLOBAL BECAUSE I AM TOO LAZY TO USE REAL FUNCTIONS
var PeakMainTemperature = 0;
			
function MainUpdater() {
		
		DisplayBlockA();
		DisplayBlockB();
		DisplayBlockC();
		DisplayBlockD();
}