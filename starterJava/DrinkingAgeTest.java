
public class DrinkingAgeTest {
	
	//Class-wide integer age
	static int age = 18;
	
	public static void main(String[] args) {
		
		if (age>=21){
			System.out.println("You may drink.");
		}
		
		else{
			
			System.out.println("You are too young to drink.");
			System.out.println("Come back in" +(21-age)+"years.");
		}
	}

}
