package fr.insa.room.entities;

public class Light {

	private boolean state;

	public Light() {
	super();
	}

	public Light(boolean state) {
	super();
	this.state = state;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	public void openLights(){
		this.state=true;
	}
	
	public void closeLights(){
		this.state=false;
	}
	
	public void printState(){
		System.out.println("Light state is " + this.state);
	}
	
}
