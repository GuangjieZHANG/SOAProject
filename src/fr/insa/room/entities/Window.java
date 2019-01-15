package fr.insa.room.entities;

public class Window {
	
	private boolean state;
	
	public Window() {
		super();
	}

	public Window(boolean state) {
		super();
		this.state = state;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	public void openWindow(){
		this.state=true;
	}
	
	public void closeWindow(){
		this.state=false;
	}
	
	public void printState(){
		System.out.println("Window state is " + this.state);
	}
}
