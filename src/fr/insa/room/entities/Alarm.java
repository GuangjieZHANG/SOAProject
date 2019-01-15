package fr.insa.room.entities;

public class Alarm {

	private boolean state;
	private boolean ringing;
	

	public Alarm() {
	}

	
	public Alarm(boolean state, boolean ringing) {
		super();
		this.state = state;
		this.ringing = ringing;
	}

	public boolean isRinging() {
		return ringing;
	}


	public void setRinging(boolean ringing) {
		this.ringing = ringing;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	public void openAlarm(){
		this.state=true;
	}
	
	public void closeAlarm(){
		this.state=false;
	}
	
	public void printState(){
		System.out.println("Alarm state is " + this.state);
	}
	
	
}
