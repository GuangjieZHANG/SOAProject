package fr.insa.room.entities;

public class Door {
	
	private boolean state;
	
	
	
	public Door() {
		super();
	}

	public Door(boolean state) {
		super();
		this.state = state;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	public void openDoor(){
		this.state=true;
	}
	
	public void closeDoor(){
		this.state=false;
	}
	
	public void printState(){
		System.out.println("Door state is " + this.state);
	}
	
}
