//Sample Character Select Screen


public class CharacterSelect {
	
	public static void main(String[] args) {
		
		String currentCharacter = "Mommy";
		
		int maxLife;
		int maxJump;
		int maxSpeed;
		
		switch (currentCharacter){
		
		case "Mommy":
			System.out.println("You have selected Mommy");
			maxLife = 70;
			maxJump = 50;
			maxSpeed = 25;
		break;
		
		case "Daddy":
			System.out.println("You have selected Daddy");
			maxLife = 40;
			maxJump = 70;
			maxSpeed = 30;
		break;
		
		case "Baby":
			System.out.println("You have selected Baby");
			maxLife = 50;
			maxJump = 30;
			maxSpeed = 40;
		break;
		
		
		}
	}

}
