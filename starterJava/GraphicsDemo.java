import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;

public class GraphicsDemo extends JFrame {
	
//The constructor follows:
	public GraphicsDemo(){
		setTitle("KiloBolt");
		setSize(800,480);
		setVisible(true);
		
setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,800,480);
		g.setColor(Color.GREEN);
		g.drawRect(60, 200, 100, 250);
		g.setColor(Color.BLACK);
		g.drawString("Kindly and Please...Go Fuck yourself",200,400);
	}

//All classes need a main method so we create that here too...
public static void main(String args[]){

//We will create a GraphicsDemo object in the main method
//the same as we would create random objects or threads
	
	GraphicsDemo demo= new GraphicsDemo(){
		
};
}
}
	
