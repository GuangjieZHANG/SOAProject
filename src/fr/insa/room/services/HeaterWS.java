package fr.insa.room.services;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insa.room.entities.Room;

@Path("Heater")
public class HeaterWS {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public boolean getHeaterState(){
		return Room.getInstance().getHeater().getState();
	}
	
	@GET
	@Path("/Open")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean openHeater(){
		Room.getInstance().getHeater().setState(true);
		return Room.getInstance().getHeater().getState();
	}
	
	@GET
	@Path("/Close")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean closeHeater(){
		Room.getInstance().getHeater().setState(false);
		return Room.getInstance().getHeater().getState();
	}
	
	
	
	@PUT
	@Path("/{HeaterState}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean changeHeaterState(@PathParam("HeaterState") boolean state){
		//Here we have to compare the system time, if it is 22:00 ~ 8:00, we will make the heater off
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(hour>=22||hour<7){
			Room.getInstance().getHeater().setState(false);
			System.out.println("Out of time, you can't modify the heater state.");
		}else{
			Room.getInstance().getHeater().setState(state);
		}
		
		return Room.getInstance().getHeater().getState();
	}
	
}
