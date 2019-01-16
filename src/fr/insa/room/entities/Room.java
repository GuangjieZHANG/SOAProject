package fr.insa.room.entities;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Room {

	private Alarm alarm;
	private Door door;
	private Heater heater;
	private Light light;
	private Window window;
	
	private double openTime;
	private double closeTime; 
	
	private MovementSensor movementSensor;
	private TempretureSensor innerTempretureSensor;
	private TempretureSensor outerTempretureSensor;
	
	private static Room room;
	
	//by default, we have the room with one instance for each device
	public Room() {
		
		alarm = new Alarm(false,false);
		door = new Door(false);
		heater = new Heater(false);
		light = new Light(false);
		window = new Window(false);
		openTime = (double) 8.0;
		closeTime = (double) 22.0;
		
		movementSensor = new MovementSensor();
		innerTempretureSensor = new TempretureSensor();
		outerTempretureSensor = new TempretureSensor();
	}
	
	// using singleton instance, we have only one room disponible
	public static Room getInstance(){
		
		if(room == null){
			room = new Room();
		}
	
		return room;
	}
	
	public Alarm getAlarm() {
		return alarm;
	}
	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}
	public Door getDoor() {
		return door;
	}
	public void setDoor(Door door) {
		this.door = door;
	}
	public Heater getHeater() {
		return heater;
	}
	public void setHeater(Heater heater) {
		this.heater = heater;
	}
	public Light getLight() {
		return light;
	}
	public void setLight(Light light) {
		this.light = light;
	}
	public Window getWindow() {
		return window;
	}
	public void setWindow(Window window) {
		this.window = window;
	}

	public double getOpenTime() {
		return openTime;
	}

	public void setOpenTime(double openTime) {
		this.openTime = openTime;
	}

	public double getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(double closeTime) {
		this.closeTime = closeTime;
	}

	public MovementSensor getMovementSensor() {
		return movementSensor;
	}
	public void setMovementSensor(MovementSensor movementSensor) {
		this.movementSensor = movementSensor;
	}

	public TempretureSensor getInnerTempretureSensor() {
		return innerTempretureSensor;
	}

	public void setInnerTempretureSensor(TempretureSensor innerTempretureSensor) {
		this.innerTempretureSensor = innerTempretureSensor;
	}

	public TempretureSensor getOuterTempretureSensor() {
		return outerTempretureSensor;
	}

	public void setOuterTempretureSensor(TempretureSensor outerTempretureSensor) {
		this.outerTempretureSensor = outerTempretureSensor;
	}
	
	/**
	 * This function describes what will happen at seven o'clock
	 */
/*	public void oneHourBeforeStart(){
		
		Room.getInstance().getHeater().openHeater();
	
	}*/
	
	/**
	 * This function describes what will happen at eight o'clock
	 */
	/*public void start(){
		
		Room.getInstance().getAlarm().closeAlarm();
		
		Room.getInstance().getDoor().openDoor();
		Room.getInstance().getWindow().openWindow();
		//because here we allow it to open but it depends on the movement
		//Room.getInstance().getLight().openLights();
	}*/
	
	/**
	 *  This function describes what will happen at twenty two o'clock
	 */
/*	public void close(){
		
		Room.getInstance().getAlarm().openAlarm();
		
		Room.getInstance().getDoor().closeDoor();
		Room.getInstance().getWindow().closeWindow();
		Room.getInstance().getLight().closeLights();
		Room.getInstance().getHeater().closeHeater();
	}*/
	
	/**
	 * This function will be invoked when it receives a movement from the movement sensor
	 */
	/*public void receiveAMovement(){
		
		// Alarm is on, it's during the night time
		if(Room.getInstance().getAlarm().getState()){
			Room.getInstance().getAlarm().setRinging(true);
			System.out.println("--------------------ALARM-------------------");
			System.out.println("Some one enters the room during the night!!!");
			System.out.println("--------------------ALARM-------------------");
		}else{
			// This is the day time, we will turn on the light
			Room.getInstance().getLight().openLights();
			Timer timer = new Timer();
			TurnOffLightTask task = new TurnOffLightTask();
			timer.schedule(task, 600000);
		}
		
	}*/
	
	/**
	 * This function will be invoked when it receives a tempreture signal
	 * inner tempreture & outer tempreture
	 */
/*	public void receiveATempreture(Float inner, Float outer){
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int openHour = (int) Math.floor(Room.getInstance().getOpenTime());
		int openMinute = (int)((Room.getInstance().getOpenTime()-openHour)*100);
		int closeHour = (int) Math.floor(Room.getInstance().getCloseTime());
		int closeMinute = (int)((Room.getInstance().getCloseTime()-closeHour)*100);
		// 7:00 ~ 8:00
		if(hour==openHour-1 && minute>openMinute){
			if(inner<18.0){
				Room.getInstance().getHeater().openHeater();
			}
		}else if(hour>openHour&&hour<=closeHour){
			// 8:00 ~ 22:00
			if(outer>18.0&&outer<27.0&&inner>outer){
				Room.getInstance().getWindow().openWindow();
			}
		}else{
				
			}
	}
	
	public class TurnOffLightTask extends TimerTask{

		public TurnOffLightTask(){
			
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Room.getInstance().getLight().closeLights();
		}
		
	}*/
}


