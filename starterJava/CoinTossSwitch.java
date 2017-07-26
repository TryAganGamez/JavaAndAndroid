
import java.util.Random;

public class CoinTossSwitch {
	
public static void main(String[] args) {
	
	Random rand = new Random();
	
	int randInt = rand.nextInt(2);
	
	System.out.println(randInt);
	
	switch (randInt){
	case 1: System.out.println("Heads!");
	break;
	case 0: System.out.println("Tails!");
	break;

	
	
			}
		}

}
