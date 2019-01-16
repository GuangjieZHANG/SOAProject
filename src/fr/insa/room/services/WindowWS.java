package fr.insa.room.services;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insa.room.entities.Room;

@Path("Window")
public class WindowWS {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public boolean getWindowState(){
		return Room.getInstance().getWindow().getState();
	}
	
	@GET
	@Path("/Open")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean openWindow(){
		Room.getInstance().getWindow().setState(true);
		return Room.getInstance().getWindow().getState();
	}
	
	@GET
	@Path("/Close")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean closeWindow(){
		Room.getInstance().getWindow().setState(false);
		return Room.getInstance().getWindow().getState();
	}
	
	@PUT
	@Path("/{WindowState}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean changeWindowState(@PathParam("WindowState") boolean state){
		//Here we have to compare the system time, if it is 22:00 ~ 8:00, we will make the window closed
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(hour>=22||hour<8){
			Room.getInstance().getWindow().setState(false);
			System.out.println("Out of time, you can't modify the window state.");
		}else{
			Room.getInstance().getWindow().setState(state);
		}
		
		return Room.getInstance().getWindow().getState();
	}

}
