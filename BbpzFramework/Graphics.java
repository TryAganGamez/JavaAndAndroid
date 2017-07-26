package com.bbpz.Framework;

import android.graphics.Paint;

public interface Graphics {
	public static enum ImageFormat{
		ARGB8888, ARGB4444, RGB565
	}
	
//The graphics interface contains many methods what will be used to draw images.
//ARGB stands for Alpha Red Green Blue and by using these four parameters you can
//specify an RGB color and an Alpha value(opacity).
	
	public Image newImage(String fileName, ImageFormat format);
	public void clearScreen(int color);
	public void drawLine(int x, int y, int x2, int y2, int color);
	public void drawRect(int x, int y, int width, int height, int color);
	public void drawImage(Image image, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight);
	public void drawImage(Image image, int x, int y, Paint paint);
	public int getWidth();
	public int getHeight();
	public void drawARGB(int i, int j, int k, int l);
	
}
