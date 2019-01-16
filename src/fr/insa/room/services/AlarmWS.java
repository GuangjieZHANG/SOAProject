package fr.insa.room.services;

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
	
	@GET
	@Path("/Open")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean openAlarm(){
		Room.getInstance().getAlarm().openAlarm();
		return Room.getInstance().getAlarm().getState();
	}
	
	@GET
	@Path("/Close")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean closeAlarm(){
		Room.getInstance().getAlarm().setRinging(false);
		return Room.getInstance().getAlarm().getState();
	}
	
	//Start Ringing
	@GET
	@Path("/Start")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean startAlarm(){
		Room.getInstance().getAlarm().setRinging(true);
		return Room.getInstance().getAlarm().getState();
	}
	
	//Stop Ringing
	@GET
	@Path("/Stop")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean stopAlarm(){
		Room.getInstance().getAlarm().closeAlarm();
		return Room.getInstance().getAlarm().getState();
	}
	
	// detect if it is ringing
	@GET
	@Path("/Ringing")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean ringing(){
		return Room.getInstance().getAlarm().isRinging();
	}
	
	
	@PUT
	@Path("/{AlarmState}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean changeAlarmState(@PathParam("AlarmState") boolean state){
		
		Room.getInstance().getAlarm().setState(state);
		return Room.getInstance().getAlarm().getState();
	}
	
}
