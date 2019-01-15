package fr.insa.room.services;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insa.room.entities.Room;

@Path("Alarm")
public class AlarmWS {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public boolean getAlarmState(){
		return Room.getInstance().getAlarm().getState();
	}
	
	@PUT
	@Path("/{AlarmState}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean changeAlarmState(@PathParam("AlarmState") boolean state){
		
		Room.getInstance().getAlarm().setState(state);
		return Room.getInstance().getAlarm().getState();
	}
	
}
