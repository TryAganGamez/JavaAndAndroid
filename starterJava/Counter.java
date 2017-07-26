
public class Counter {
	
	//Create a class-wide integer called counter.
	static int counter = 0;
	
	public static void main(String[] args)	{
	
		//This is a loop. It will keep
		//repeating everything contained
		//inside the loop while the
		//conditions are met!
	
		while (counter < 10) {
			
			//This post-increment operator adds 1.
			counter++;
			//Display the new counter value.
			System.out.println(counter);
			
		}
		
		}

}
