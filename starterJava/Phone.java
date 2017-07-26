//This is a superclass.

class Phone {
	
	static int weight= 10;
	static int numberofProcessingCores=8;
	static int price=200;
	
	static boolean turnedOn=false;
	
	static void togglePower(){
		turnedOn=!turnedOn;
		//The above line of code toggles the value of a boolean.
		//It basically says: turnedOn is now the opposite of what it used to be.
	}
	}


