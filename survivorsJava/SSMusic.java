package com.bbpz.soulsurvivors;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.media.MediaPlayer;

public class SSMusic extends Service{
	public static boolean isRunning=false;
	MediaPlayer player;
	
	@Override
	public IBinder onBind(Intent arg0){
		return null;
		}
	@Override
	public void onCreate(){
		super.onCreate();
		
	setMusicOptions(this,SSEngine.LOOP_BACKGROUND_MUSIC,SSEngine.R_VOLUME,SSEngine.L_VOLUME,
	SSEngine.SPLASH_SCREEN_MUSIC);
								}
	public void setMusicOptions(Context context, boolean isLooped,int rVolume,
			int lVolume, int soundFile){
		player=MediaPlayer.create(context, soundFile);
		player.setLooping(isLooped);
		player.setVolume(rVolume, lVolume);
	}
	public int onStartCommand(Intent intent, int flags, int startId){
		try
		{
			player.start();
			isRunning=true;
						}catch(Exception e){
			isRunning=false;
			player.stop();
						  }
		
						   return 1;
	}
	public void onStart(Intent intent, int startId){

				}
	public IBinder onUnBind(Intent arg0){
		//TODO Auto-generated method stub
		return null;
	}
	public void onStop(){
						isRunning=false;
		}
	public void onPause(){		}
	@Override
	public void onDestroy(){
							player.stop();
							player.release();
	}
	@Override
	public void onLowMemory(){
						player.stop();
		
		}
}
