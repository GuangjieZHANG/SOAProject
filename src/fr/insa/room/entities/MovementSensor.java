package fr.insa.room.entities;

import java.sql.Timestamp;

public class MovementSensor {

	Timestamp time;
	

	public MovementSensor() {
		super();
	}

	public MovementSensor(Timestamp time) {
		super();
		this.time = time;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public void printTime(){
		System.out.println(this.time);
	}
	
	
}
