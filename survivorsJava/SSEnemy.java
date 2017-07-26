package com.bbpz.soulsurvivors;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;
import javax.microedition.khronos.opengles.GL10;

public class SSEnemy {
	public float posY; //the x position of the enemy
	public float posX=0f; //the y position of the enemy
	public float posT=0f; //the t used in calculating a Beizer curve
	public float incrementXToTarget=0f; //the y increment to reach a target
	public float incrementYToTarget=0f;//the y increment to reach a target
	public int attackDirection=0;//the attack direction of the ship
	public boolean isDestroyed=false;//has this ship been destroyed?
	private int damage=0;
	public int enemyType=0;//what type of enemy is this?
	
	public boolean isLockedOn=false;//had the enemy locked onto a target?
	public float lockOnPosX=0f;//x position of the target
	public float lockOnPosY=0f;//y position of the target
	
	private Random randomPos=new Random();
	
	public void applyDamage(){
		damage++;
		switch(enemyType){
		case SSEngine.TYPE_INTERCEPTOR:;
		if(damage==SSEngine.INTERCEPTOR_SHIELDS){
			isDestroyed=true;
		}
		break;
		case SSEngine.TYPE_SCOUT:
			if(damage==SSEngine.SCOUT_SHIELDS){
				isDestroyed=true;
			}
			break;
		}
	}
	
	private FloatBuffer vertexBuffer;
	private FloatBuffer textureBuffer;
	private ByteBuffer indexBuffer;
	
	private float vertices[]={
			0.0f,0.0f,0.0f,
			1.0f,0.0f,0.0f,
			1.0f,1.0f,0.0f,
			0.0f,1.0f,0.0f,
	};
	
	private float texture[]={
			0.0f,0.0f,
			0.25f,0.0f,
			0.25f,0.025f,
			0.0f,0.25f,
	};
	
	private byte indices[]={
			0,1,2,
			0,2,3,
	};
	
	public SSEnemy(int type, int direction){
			enemyType=type;
			attackDirection=direction;
			posY=(randomPos.nextFloat()*4)+4;
			switch(attackDirection){
			case SSEngine.ATTACK_LEFT:
					posX=0;
					break;
			case SSEngine.ATTACK_RANDOM:
					posX=randomPos.nextFloat()*3;
					break;
			case SSEngine.ATTACK_RIGHT:
					posX=3;
					break;
			}
			posT=SSEngine.SCOUT_SPEED;
			
			ByteBuffer byteBuf=ByteBuffer.allocateDirect(vertices.length*4);
			byteBuf.order(ByteOrder.nativeOrder());
			vertexBuffer=byteBuf.asFloatBuffer();
			vertexBuffer.put(vertices);
			vertexBuffer.position(0);		
			byteBuf=ByteBuffer.allocateDirect(texture.length*4);
			byteBuf.order(ByteOrder.nativeOrder());
			textureBuffer=byteBuf.asFloatBuffer();
			textureBuffer.put(texture);
			textureBuffer.position(0);
			
			indexBuffer=ByteBuffer.allocateDirect(indices.length);
			indexBuffer.put(indices);
			indexBuffer.position(0);
		}
	
	public float getNextScoutX(){
		if(attackDirection==SSEngine.ATTACK_LEFT){
			return (float)((SSEngine.BEIZER_X_4*(posT*posT*posT))+
(SSEngine.BEIZER_X_3*3*(posT*posT)*(1-posT))+(SSEngine.BEIZER_X_2*3*posT*
((1-posT)*(1-posT)))+(SSEngine.BEIZER_X_1*((1-posT)*(1-posT)*(1-posT))));
		}else{
				return (float)((SSEngine.BEIZER_X_1*(posT*posT*posT))+
SSEngine.BEIZER_X_2*3*(posT*posT)*(1-posT))+(SSEngine.BEIZER_X_3*3*posT*
((1-posT)*(1-posT)))+(SSEngine.BEIZER_X_4*((1-posT)*(1-posT)*(1-posT)));
		}
	
	}
	public float getNextScoutY(){
			return (float)((SSEngine.BEIZER_Y_1*(posT*posT*posT))+
(SSEngine.BEIZER_Y_2*3*(posT*posT)*(1-posT)+(SSEngine.BEIZER_Y_3*3*posT*
((1-posT)*(posT)))+(SSEngine.BEIZER_Y_4*((1-posT)*(1-posT)*(1-posT)))));
	}
	
public void draw(GL10 gl,int[] spriteSheet){
	gl.glBindTexture(GL10.GL_TEXTURE_2D,spriteSheet[0]);
	gl.glFrontFace(GL10.GL_CCW);
	gl.glEnable(GL10.GL_CULL_FACE);
	gl.glCullFace(GL10.GL_BACK);
	
	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	
	gl.glVertexPointer(3, GL10.GL_FLOAT, 0,vertexBuffer);
	gl.glTexCoordPointer(2,GL10.GL_FLOAT,0,textureBuffer);
	
	gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE,
			indexBuffer);
	
	gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	gl.glDisable(GL10.GL_CULL_FACE);
	}
}
