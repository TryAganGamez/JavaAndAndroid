import java.util.Random;

public class RollSix {
	
	static int dieValue;
	
public static void main(String[] args){
	
	rollDie();
}

static void rollDie(){
	Random rand=new Random();
	//Assign a random number between 1 and 6 as dieValue
	dieValue = rand.nextInt(6)+1;
	System.out.println("You rolled a " + dieValue);
	//Why add1?Think about it.
	testDieValue(dieValue);
}

static void testDieValue(int dieValue){
	if(dieValue<=2){
		System.out.println("You Suck.");
		}
	
	else if(dieValue==3||dieValue==4||dieValue==
			5){
		System.out.println();
		System.out.println("Rerolling");
		System.out.println();
		rollDie();
	}	
		else if (dieValue==6){
		System.out.println("You win muthaFucka!");
			
	}
	}
}
