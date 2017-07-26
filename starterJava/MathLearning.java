
public class MathLearning {
	
// These are class wide variables;
	
	static double a= 10;
	static double b= 15;
	static double c = 22;

	//Setup a counter.
	static int counter = 0;
	
public static void main(String[] args){
		
//The following are local variables
//They belong to the main method only.
		
		double result;
		double resultTwo;
		double resultThree;
		double resultFour;
		double resultFive;
		
//These assign values to the five "result variables".
//(You could have assigned when you declared them above).
		
		result = a+b;
		resultTwo = a - b;
		resultThree = a * c;
		resultFour = c / b;
		resultFive = c % b;
		
//The following statements
//invoke the blahBlahBlah method
//by "inputting" the required
//integer parameter.
		
		blahBlahBlah(result);
		blahBlahBlah(resultTwo);
		blahBlahBlah(resultThree);
		blahBlahBlah(resultFour);
		blahBlahBlah(resultFive);
		
}
	
public static void blahBlahBlah(double output){
	
	//Add one to counter.
	counter++;
	
	//this method takes the parameter
	//and creates a variable called output.
	//This variable is then displayed
	//in the below statement.
	
	System.out.println("Results" + counter + "is" + output);
	
	}
}
