package fr.insa.room.entities;

public class TempretureSensor {

	private Float tempreture;
	
	public TempretureSensor() {
		this.tempreture = (float)Math.random()*20+5;
	}

	public TempretureSensor(Float tempreture) {
		super();
		this.tempreture = tempreture;
	}

	//each time it returns a float between 5 and 25
	public Float getTempreture() {
		this.tempreture = (float)Math.random()*20+5;
		return tempreture;
	}

	public void setTempreture(Float tempreture) {
		this.tempreture = tempreture;
	}
	
	public void printTempreture(){
		System.out.println("Now the tempreture is "+this.tempreture);
	}
	
}
