package fr.insa.room.services;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insa.room.entities.Room;

@Path("Door")
public class DoorWS {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public boolean getDoorState(){
		return Room.getInstance().getDoor().getState();
	}
	
	@PUT
	@Path("/{DoorState}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean changeAlarmState(@PathParam("DoorState") boolean state){
		//Here we have to compare the system time, if it is 22:00 ~ 8:00, we will make the light off
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(hour>=22||hour<8){
			Room.getInstance().getDoor().setState(false);
			System.out.println("Out of time, you can't modify the door state.");
		}else{
			Room.getInstance().getDoor().setState(state);
		}
		
		return Room.getInstance().getDoor().getState();
	}
	
}
