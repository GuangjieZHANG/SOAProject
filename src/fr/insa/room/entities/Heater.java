package fr.insa.room.entities;

public class Heater {

	private boolean state;
	
	public Heater() {
		super();
	}

	public Heater(boolean state) {
		super();
		this.state = state;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	public void openHeater(){
		this.state=true;
	}
	
	public void closeHeater(){
		this.state=false;
	}
	
	public void printState(){
		System.out.println("Heater state is " + this.state);
	}
}
