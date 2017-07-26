package com.bbpz.Framework;

import java.util.List;

//The input interface keeps track of touch events,with variables x,y,type
//(touch down,touch up,etc) and pointer,(each point of contact on screen-
//Android supports Multi-Touch.

public interface Input {
	
	public static class TouchEvent{
		public static final int TOUCH_DOWN=0;
		public static final int TOUCH_UP=1;
		public static final int TOUCH_DRAGGED=2;
		public static final int TOUCH_HOLD=3;
		
		public int type;
		public int x, y;
		public int pointer;
		
	}
public boolean isTouchedDown(int pointer);

public int getTouchX(int pointer);
public int getTouchY(int pointer);
public List<TouchEvent> getTouchEvent();

}
