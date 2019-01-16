package fr.insa.room.services;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insa.room.entities.Room;


@Path("Light")
public class LightWS {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public boolean getLightState(){
		return Room.getInstance().getLight().getState();
	}
	
	@GET
	@Path("/Open")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean openLight(){
		Room.getInstance().getLight().setState(true);
		return Room.getInstance().getLight().getState();
	}
	
	@GET
	@Path("/Close")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean closeLight(){
		Room.getInstance().getLight().setState(false);
		return Room.getInstance().getLight().getState();
	}
	
	@PUT
	@Path("/{LightState}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean changeLightState(@PathParam("LightState") boolean state){
		//Here we have to compare the system time, if it is 22:00 ~ 8:00, we will make the light off
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(hour>=22||hour<8){
			Room.getInstance().getLight().setState(false);
			System.out.println("Out of time, you can't modify the light state.");
		}else{
			Room.getInstance().getLight().setState(state);
		}
		
		return Room.getInstance().getLight().getState();
	}
	
}
