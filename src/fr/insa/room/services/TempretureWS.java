package fr.insa.room.services;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insa.room.entities.Room;

@Path("Tempreture")
public class TempretureWS {

	@GET
	@Path("/InnerTempreture")
	@Produces(MediaType.TEXT_PLAIN)
	public float getInnerTempreture(){
		return Room.getInstance().getInnerTempretureSensor().getTempreture();
	}
	
	@GET
	@Path("/OuterTempreture")
	@Produces(MediaType.TEXT_PLAIN)
	public float getOuterTempreture(){
		return Room.getInstance().getOuterTempretureSensor().getTempreture();
	}
	
	@PUT
	@Path("/InnerTempreture/{temp}")
	@Produces(MediaType.TEXT_PLAIN)
	public double changeInnerTempreture(@PathParam("temp") float inner){
		
		Room.getInstance().getInnerTempretureSensor().setTempreture(inner);
		return Room.getInstance().getInnerTempretureSensor().getTempreture();
	}
	
	@PUT
	@Path("/OuterTempreture/{temp}")
	@Produces(MediaType.TEXT_PLAIN)
	public double changeOuterTempreture(@PathParam("temp") float outer){
		
		Room.getInstance().getOuterTempretureSensor().setTempreture(outer);
		return Room.getInstance().getOuterTempretureSensor().getTempreture();
	}
	
}
