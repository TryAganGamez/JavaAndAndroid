package com.android.tutorial;

import android.graphics.Canvas;

public interface ISurface {
	void onInitalize();
	void onDraw(Canvas canvas);
	void onUpdate(long gameTime);
}
