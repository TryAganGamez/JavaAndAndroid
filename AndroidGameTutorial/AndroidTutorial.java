package com.android.tutorial;


import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;

public class AndroidTutorial extends Activity {
	AnimatedSprite animation = new AnimatedSprite();

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new AndroidTutorialPanel(this));
    }
    
    class AndroidTutorialPanel extends DrawablePanel {

		public AndroidTutorialPanel(Context context) {
			super(context);
		}

		@Override
		public void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			AndroidTutorial.this.animation.draw(canvas);
		}
		
		@Override
		public void onInitalize() {
			AndroidTutorial.this.animation.Initialize(
					BitmapFactory.decodeResource(
							getResources(), 
							R.drawable.explosion), 
							32, 32, 14, 7);
		}

		@Override
		public void onUpdate(long gameTime) {
			AndroidTutorial.this.animation.Update(gameTime);
		}
    }
    
}