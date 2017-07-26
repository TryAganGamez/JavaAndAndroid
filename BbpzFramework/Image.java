package com.bbpz.Framework;

import com.bbpz.Framework.Graphics.ImageFormat;

public interface Image {
	public int getWidth();
	public int getHeight();
	public ImageFormat getFormat();
	public void dispose();
}
