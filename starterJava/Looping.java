
public class Looping {
	public static void main(String[] args){
		
		boolean earthIsSpinning = true;
		int counter = 0;
		
		while (earthIsSpinning){
			System.out.println("The earth is spinning");
			System.out.println(counter);
			counter++;
			
			if (counter >=10){
				earthIsSpinning = false;
			}
		}
	}

}
