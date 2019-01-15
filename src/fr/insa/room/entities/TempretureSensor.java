package fr.insa.room.entities;

public class TempretureSensor {

	private Float tempreture;
	
	public TempretureSensor() {
		super();
	}

	public TempretureSensor(Float tempreture) {
		super();
		this.tempreture = tempreture;
	}

	public Float getTempreture() {
		return tempreture;
	}

	public void setTempreture(Float tempreture) {
		this.tempreture = tempreture;
	}
	
	public void printTempreture(){
		System.out.println("Now the tempreture is "+this.tempreture);
	}
	
}
