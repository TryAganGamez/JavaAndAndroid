package com.bbpz.soulsurvivors;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.util.Random;

import android.opengl.GLSurfaceView.Renderer;


public class SSGameRenderer implements Renderer {
	private SSBackground background = new SSBackground();
	private SSBackground background2 = new SSBackground();
	private SSGoodGuy player1=new SSGoodGuy();	
	private SSEnemy[] enemies=new SSEnemy[SSEngine.TOTAL_INTERCEPTORS+
SSEngine.TOTAL_SCOUTS+SSEngine.TOTAL_WARSHIPS-1];
	private SSTextures textureLoader;
	private int[] spriteSheets=new int[2];
	private SSWeapon[]playerFire=new SSWeapon[4];
		
	private int goodGuyBankFrames=0;
	private long loopStart=0;
	private long loopEnd=0;
	private long loopRunTime=0;
	
	private float bgScroll1;
	private float bgScroll2;
	
	@Override
	public void onDrawFrame(GL10 gl){
		//TODO Auto-generated method stub
		
		loopStart=System.currentTimeMillis();
		try {
				if(loopRunTime<SSEngine.GAME_THREAD_FPS_SLEEP){
			Thread.sleep(SSEngine.GAME_THREAD_FPS_SLEEP -
	loopRunTime);
				}
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
	
	scrollBackground1(gl);
	scrollBackground2(gl);
	
	movePlayer1(gl);
	moveEnemy(gl);
	detectCollisions();
	firePlayerWeapon(gl);	
		
	
	gl.glEnable(GL10.GL_BLEND);
	gl.glBlendFunc(GL10.GL_ONE,  GL10.GL_ONE_MINUS_SRC_ALPHA);
	loopEnd=System.currentTimeMillis();
	loopRunTime=((loopEnd-loopStart));
	}
	private void initializeInterceptors(){
		for(int x=0;x<SSEngine.TOTAL_INTERCEPTORS-1; x++){
			SSEnemy interceptor = new SSEnemy(SSEngine.TYPE_INTERCEPTOR,
					
SSEngine.ATTACK_RANDOM);
						enemies[x]=interceptor;
			}
		}
private void initializeScouts(){
	
		for (int x=SSEngine.TOTAL_INTERCEPTORS-1;
x<SSEngine.TOTAL_INTERCEPTORS+SSEngine.TOTAL_SCOUTS -1; x++){
		SSEnemy interceptor;
		if (x>=(SSEngine.TOTAL_INTERCEPTORS +SSEngine.TOTAL_SCOUTS) /2){
			
			interceptor = new SSEnemy(SSEngine.TYPE_SCOUT,
SSEngine.ATTACK_RIGHT);
		}else{
				interceptor = new SSEnemy(SSEngine.TYPE_SCOUT,
SSEngine.ATTACK_LEFT);
					}
					enemies[x]=interceptor;
			}
		}
	

private void initializeWarships(){
	
	for (int x=SSEngine.TOTAL_INTERCEPTORS-1;
x<SSEngine.TOTAL_INTERCEPTORS+SSEngine.TOTAL_SCOUTS -1; x++){
				SSEnemy interceptor=new SSEnemy(SSEngine.TYPE_WARSHIP,
SSEngine.ATTACK_RANDOM);
				enemies[x]=interceptor;
	}
}
private void initializePlayerWeapons(){
		for(int x=0;x<4;x++){
			SSWeapon weapon=new SSWeapon();
			playerFire[x]=weapon;
		}
		playerFire[0].shotFired=true;
		playerFire[0].posX=SSEngine.playerBankPosX;
		playerFire[0].posY=1.25f;
	}
private void firePlayerWeapon(GL10 gl){
	for(int x=0;x<4;x++){
		if(playerFire[x].shotFired){
				int nextShot=0;
if(playerFire[x].posY>4.25){
						playerFire[x].shotFired=false;
				}else{
						if (playerFire[x].posY>2){
								if(x==3){
									nextShot=0;
								}else{
									nextShot=x+1;				
								}
								if (playerFire[nextShot].shotFired==
false){
										playerFire[nextShot].shotFired=
true;
										playerFire[nextShot].posX=
SSEngine.playerBankPosX;
										playerFire[nextShot].posY=
1.25f;
								}
						}
playerFire[x].posY+=
SSEngine.PLAYER_BULLET_SPEED;
gl.glMatrixMode(GL10.GL_MODELVIEW);
										gl.glLoadIdentity();
										gl.glPushMatrix();
										gl.glScalef(.25f, .25f, 0f);
gl.glTranslatef(playerFire[x].posX, playerFire[x].posY, 0f);

gl.glMatrixMode(GL10.GL_TEXTURE);
gl.glLoadIdentity();
						gl.glTranslatef(0.0f, 0.0f, 0.0f);
						playerFire[x].draw(gl, spriteSheets);
						gl.glPopMatrix();
						gl.glLoadIdentity();{
							
						
						}
				}}}
		
	}
private void detectCollisions(){
		for (int y=0;y<3;y++){
if(playerFire[y].shotFired){
								for (int x=0;x<SSEngine.TOTAL_INTERCEPTORS+
SSEngine.TOTAL_SCOUTS+SSEngine.TOTAL_WARSHIPS-1;x++){
									if(!enemies[x].isDestroyed && enemies[x].posY<
4.25){
										if((playerFire[y].posY>=
enemies[x].posY-1
&& playerFire[y].posY<=enemies[x].posY)
&& (playerFire[y].posX<=enemies[x].posX+1
&& playerFire[y].posX>=enemies[x].posX-1)){
											int nextShot=0;
enemies[x].applyDamage();
											playerFire[y].shotFired=false;
											if(y==3){
													nextShot=0;
											}else{
													nextShot=y+1;
											}
											if
(playerFire[nextShot].shotFired==false){
playerFire[nextShot].shotFired=true;
playerFire[nextShot].posX=SSEngine.playerBankPosX;
playerFire[nextShot].posY=1.25f;
											}
										}}}}}
											
											
									
									
								}
	private void moveEnemy(GL10 gl){
			for (int x=0; x < SSEngine.TOTAL_INTERCEPTORS+SSEngine.TOTAL_SCOUTS
+SSEngine.TOTAL_WARSHIPS-1;x++){
						if (!enemies[x].isDestroyed){
								Random randomPos=new Random();
								switch (enemies[x].enemyType){
								case SSEngine.TYPE_INTERCEPTOR:
										if(enemies[x].posY<=0){
												enemies[x].posY=(randomPos.nextFloat()
*4)+4;
												enemies[x].posX=randomPos.nextFloat()
*3;
												enemies[x].isLockedOn=false;
														enemies[x].lockOnPosX=0;
										}
										gl.glMatrixMode(GL10.GL_MODELVIEW);
										gl.glLoadIdentity();
										gl.glPushMatrix();
										gl.glScalef(.25f, .25f, 1f);
										if (enemies[x].posY>=3){
												enemies[x].posY -=
SSEngine.INTERCEPTOR_SPEED;
											
										}else{
												if (!enemies[x].isLockedOn){
														enemies[x].lockOnPosX=
SSEngine.playerBankPosX;
														enemies[x].isLockedOn=true;
														enemies[x].incrementXToTarget=
(float)((enemies[x].lockOnPosX-enemies[x].posX)/(enemies[x].posY/
(SSEngine.INTERCEPTOR_SPEED*4)));
												}
												enemies[x].posY -=
(SSEngine.INTERCEPTOR_SPEED*4);
												enemies[x].posX +=
enemies[x].incrementXToTarget;
										
										}
										
										break;
								case SSEngine.TYPE_SCOUT:
																			
									break;
								case SSEngine.TYPE_WARSHIP:
								
									break;
							}
							}
						}
		}	
		
	private void movePlayer1(GL10 gl){
	switch(SSEngine.playerFlightAction){
	case SSEngine.PLAYER_BANK_LEFT_1:
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(.25f, .25f, 1f);
		if (goodGuyBankFrames<SSEngine.PLAYER_FRAMES_BETWEEN_ANI&&
SSEngine.playerBankPosX>0){
							SSEngine.playerBankPosX-=SSEngine.PLAYER_BANK_SPEED;
							gl.glTranslatef(SSEngine.playerBankPosX, 0f, 0f);
							gl.glMatrixMode(GL10.GL_TEXTURE);
							gl.glLoadIdentity();
							gl.glTranslatef(0.75f, 0.0f, 0.0f);
							goodGuyBankFrames+=1;
						}else if (goodGuyBankFrames >=
SSEngine.PLAYER_FRAMES_BETWEEN_ANI && SSEngine.playerBankPosX>0){
								SSEngine.playerBankPosX-=SSEngine.PLAYER_BANK_SPEED;
			gl.glTranslatef(SSEngine.playerBankPosX, 0f, 0f);
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();
			gl.glTranslatef(0.0f, 0.25f, 0.0f);
		}else{
			gl.glTranslatef(SSEngine.playerBankPosX, 0f, 0f);
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();
			gl.glTranslatef(0.0f, 0.0f, 0.0f);
		}			
		player1.draw(gl,spriteSheets);
		gl.glPopMatrix();
		gl.glLoadIdentity();
		
			break;
	case SSEngine.PLAYER_BANK_RIGHT_1:
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(.25f, .25f, 1f);
		if(goodGuyBankFrames < SSEngine.PLAYER_FRAMES_BETWEEN_ANI &&
	SSEngine.playerBankPosX<3){
			
		}else if (goodGuyBankFrames>=
	SSEngine.PLAYER_FRAMES_BETWEEN_ANI && 
	SSEngine.playerBankPosX <3){
			
		}else{
				
		}
	
			break;
	case SSEngine.PLAYER_RELEASE:
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(.25f, .25f, 1f);
		gl.glTranslatef(SSEngine.playerBankPosX, 0f, 0f);
		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glTranslatef(0.0f,0.0f,0.0f);
		
		player1.draw(gl, spriteSheets);
		gl.glPopMatrix();
		gl.glLoadIdentity();
		

		
		break;
	default:
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(.25f,  .25f,  1f);
			gl.glTranslatef(SSEngine.playerBankPosX, 0f, 0f);
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glTranslatef(0.0f,0.0f,0.0f);
	}
	
			player1.draw(gl, spriteSheets);
			gl.glPopMatrix();
			gl.glLoadIdentity();
			goodGuyBankFrames+=1;
			
		
		
	}

	private void scrollBackground1(GL10 gl){
		if (bgScroll1==Float.MAX_VALUE){
				bgScroll1=0f;
		}
		/*This code just resets the scale and translate of the 
			Model matrix mode, we are not moving it */
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		 gl.glLoadIdentity();
		 gl.glPushMatrix();
		 gl.glScalef(1f, 1f, 1f);
		 gl.glTranslatef(0f,0f,0f);	
		 
		 gl.glMatrixMode(GL10.GL_TEXTURE);
		 gl.glLoadIdentity();
		 gl.glTranslatef(0.0f, bgScroll1,  0.0f); 
		 
		 background.draw(gl);
		 gl.glPopMatrix();
		 bgScroll1+= SSEngine.SCROLL_BACKGROUND_1;
		 gl.glLoadIdentity();		
	
	}
	
	private void scrollBackground2(GL10 gl){
		if(bgScroll2==Float.MAX_VALUE){
			bgScroll2=0f;
		}
	
	gl.glMatrixMode(GL10.GL_MODELVIEW);
	gl.glLoadIdentity();
	gl.glPushMatrix();
	gl.glScalef(.5f, 1f, 1f);
	gl.glTranslatef(1.5f, 0f, 0f);
	
	gl.glMatrixMode(GL10.GL_TEXTURE);
	gl.glLoadIdentity();
	gl.glTranslatef(0.0f, bgScroll2, 0.0f);
	
	background2.draw(gl);
	gl.glPopMatrix();
	bgScroll2+=SSEngine.SCROLL_BACKGROUND_2;
	gl.glLoadIdentity();
	}
	
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height){
		
		gl.glViewport(0, 0, width,height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0f,1f,0f,1f,-1f,1f);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config){
		initializeInterceptors();
		initializeScouts();
		initializeWarships();
		textureLoader=new SSTextures(gl);
		spriteSheets=textureLoader.loadTexture(gl,SSEngine.CHARACTER_SHEET,
SSEngine.context,1);
spriteSheets=textureLoader.loadTexture(gl, SSEngine.WEAPONS_SHEET, SSEngine.context,2);
				
		gl.glEnable(GL10.GL_TEXTURE_2D);
		 gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_ONE,  GL10.GL_ONE);
		
		//TODO Add texture loading for background image
	
	 	 background.loadTexture(gl,SSEngine.BACKGROUND_LAYER_ONE, 
SSEngine.context);
	 	 background2.loadTexture(gl, SSEngine.BACKGROUND_LAYER_TWO,
SSEngine.context);
	 	 
	 	 player1.loadTexture(gl, SSEngine.PLAYER_SHIP, SSEngine.context);
	 	 
	}
	
}