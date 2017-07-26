package com.bbpz.soulsurvivors;



import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.view.View;


public class SSMainMenu extends Activity{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		/**Fire up background music*/
		SSEngine.musicThread=new Thread(){
public void run(){
	Intent bgmusic=new
	Intent(getApplicationContext(),SSMusic.class);
				startService(bgmusic);
				SSEngine.context=getApplicationContext();
				}
	};
SSEngine.musicThread.start();

		
final SSEngine engine=new SSEngine();
		
	/** Set menu button options*/
ImageButton start=(ImageButton)findViewById(R.id.btnStart);
ImageButton exit=(ImageButton)findViewById(R.id.btnExit);
		
start.getBackground().setAlpha(SSEngine.MENU_BUTTON_ALPHA);
start.setHapticFeedbackEnabled(SSEngine.HAPTIC_BUTTON_FEEDBACK);
		
exit.getBackground().setAlpha(SSEngine.MENU_BUTTON_ALPHA);
exit.setHapticFeedbackEnabled(SSEngine.HAPTIC_BUTTON_FEEDBACK);
		
start.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
				/**Start Game!!!*/
		Intent game=new Intent(getApplicationContext(),SSGame.class);
		SSMainMenu.this.startActivity(game);
				
				}
		});
		
exit.setOnClickListener(new OnClickListener(){
@Override
public void onClick(View v){
					boolean clean=false;
					
															clean =
engine.onExit(v);
															if (clean)
															{
				int pid = android.os.Process.myPid();
				
				android.os.Process.killProcess(pid);
															}
}
});
	}
}
													

	