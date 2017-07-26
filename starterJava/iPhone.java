
class iPhone extends Phone {
	
	boolean hasAppleLogo=true;
	String color = "black";
	
	void adjustPrice(){
		if (hasAppleLogo==true){
			
			price +=450;
		}
	}

}
