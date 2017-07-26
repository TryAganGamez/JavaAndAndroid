package com.bbpz.soulsurvivors;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

public class SoulzActivity extends Activity {
	/**Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*display the splash screen image from a layout*/
		setContentView(R.layout.splashscreen);
	
	/*start up the splash screen and main menu in a time delayed thread*/
	new Handler().postDelayed(new Thread(){
		@Override
		public void run(){
			Intent mainMenu=new Intent(SoulzActivity.this,
	SSMainMenu.class);
				SoulzActivity.this.startActivity(mainMenu);
				SoulzActivity.this.finish();
				overridePendingTransition(R.layout.fadein,R.layout.fadeout);
			}
	}, SSEngine.GAME_THREAD_DELAY);
	
			}
		
	}
	



