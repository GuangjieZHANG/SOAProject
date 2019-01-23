package fr.insa.room.middleservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("Close")
public class CloseWS {
	
	String baseURL = "http://localhost:8080/INSARooms/Resources/";
	
	// in this WS, we will close the window, light, door, heater
	// and then open the alarm
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String closeClassroom(){
		
		Client client = ClientBuilder.newClient();
		Response responseDoor = client.target(baseURL+"Door/Close").request().get();
		responseDoor.close();
		System.out.println("Door closed...");
		
		Response responseHeater = client.target(baseURL+"Heater/Close").request().get();
		responseHeater.close();
		System.out.println("Heater closed...");
		
		Response responseLight = client.target(baseURL+"Light/Close").request().get();
		responseLight.close();
		System.out.println("Light closed...");
		
		Response responseWindow = client.target(baseURL+"Window/Close").request().get();
		responseWindow.close();
		System.out.println("Window closed...");
		
		Response responseAlarm = client.target(baseURL+"Alarm/Open").request().get();
		responseAlarm.close();
		System.out.println("Alarm opened...");
		
		System.out.println("Everythig is ready. You can leave your room now!");
	
		return "Everythig is ready. You can leave your room now!";
	}

}
