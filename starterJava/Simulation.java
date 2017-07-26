import java.util.Random;

//Lets simulate a coin toss!

public class Simulation {
	public static void main(String[] args){
		
		Random rand = new Random();
		
		int result = rand.nextInt(2);
		
		if (result == 0){
			System.out.println("heads");
		}
		
		else if(result == 1){
			System.out.println("tails");
		}
		
		else if(result == 3){
			System.out.println("side. fix your random number generator");
		}
		
	}

}
