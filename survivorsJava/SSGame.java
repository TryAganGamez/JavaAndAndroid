package com.bbpz.soulsurvivors;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;


public class SSGame extends Activity {
	
		private SSGameView gameView;	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		gameView= new SSGameView(this);
		setContentView(gameView);
		}
	@Override
   		protected void onResume() {
	   	super.onResume();
	   	gameView.onResume();
   }
	   
   @Override
   protected void onPause(){
	   super.onPause();
	   gameView.onPause();
   }		
		@Override
		public boolean onTouchEvent(MotionEvent event){
			float x = event.getX();
			float y = event.getY();
			int height = SSEngine.display.getHeight()/4;
			int playableArea=SSEngine.display.getHeight()-height;
			if (y > playableArea){
				switch (event.getAction()){
				case MotionEvent.ACTION_DOWN:
					  if(x < SSEngine.display.getWidth() / 2){
			  SSEngine.playerFlightAction=
		SSEngine.PLAYER_BANK_LEFT_1;
					  }else{
						  SSEngine.playerFlightAction=
		SSEngine.PLAYER_BANK_RIGHT_1;
					  }					
					break;
				case MotionEvent.ACTION_UP:
						SSEngine.playerFlightAction=
		SSEngine.PLAYER_RELEASE;
											
					break;
				}
			}
			return false;
		}
		
		
	   
	   
	
	}
	 

	

