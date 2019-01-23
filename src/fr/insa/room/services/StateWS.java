package fr.insa.room.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insa.room.entities.Room;

@Path("State")
public class StateWS {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getState(){
		String state="The Door state is "+Room.getInstance().getDoor().getState();
		state += ";\nThe Window state is "+Room.getInstance().getWindow().getState();
		state += ";\nThe Light state is "+Room.getInstance().getLight().getState();
		state += ";\nThe Heat state is "+Room.getInstance().getHeater().getState();
		state += ";\nThe Alarm state is "+Room.getInstance().getAlarm().getState();
		state += ";\nThe Open time is "+Room.getInstance().getOpenTime();
		state += ";\nThe Close time is "+Room.getInstance().getCloseTime();
		return state;
	}
	
}
