package com.bbpz.Framework;

public abstract class Screen {
	protected final Game game;
	
	public Screen(Game game){
		this.game=game;
	}
//This is an abstract class(Not really an interface,which you cannot use
//to create objects.However,you can subclass it).It contains abstract
//methods which state the parameters but are not implemented.
	
	public abstract void update(float deltaTime);
	public abstract void paint(float deltaTime);
	public abstract void pause();
	public abstract void resume();
	public abstract void dispose();
	public abstract void backButton();

}

