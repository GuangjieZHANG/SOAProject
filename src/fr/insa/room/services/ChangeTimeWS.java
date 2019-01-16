package fr.insa.room.services;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insa.room.entities.Room;

@Path("ChangeTime")
public class ChangeTimeWS {

	@GET
	@Path("/OpenTime")
	@Produces(MediaType.TEXT_PLAIN)
	public double getOpenTime(){
		return Room.getInstance().getOpenTime();
	}
	
	@GET
	@Path("/CloseTime")
	@Produces(MediaType.TEXT_PLAIN)
	public double getCloseTime(){
		return Room.getInstance().getCloseTime();
	}
	
	@PUT
	@Path("/OpenTime/{opentime}")
	@Produces(MediaType.TEXT_PLAIN)
	public double changeOpenTime(@PathParam("opentime") double time){
		Room.getInstance().setOpenTime(time);
		return Room.getInstance().getOpenTime();
	}
	
	@PUT
	@Path("/CloseTime/{closetime}")
	@Produces(MediaType.TEXT_PLAIN)
	public double changeCloseTime(@PathParam("closetime") double time){
		
		Room.getInstance().setCloseTime(time);;
		return Room.getInstance().getCloseTime();
	}
	
}
