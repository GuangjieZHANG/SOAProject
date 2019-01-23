package fr.insa.room.middleservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("Open")
public class OpenWS {

	String baseURL = "http://localhost:8080/INSARooms/Resources/";
	
	// in this WS, we will open the window, light, door
	// and then close the alarm
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String openClassroom(){
		
		Client client = ClientBuilder.newClient();
		Response responseDoor = client.target(baseURL+"Door/Open").request().get();
		responseDoor.close();
		System.out.println("Door opened...");
		
/*		Response responseLight = client.target(baseURL+"Light/Open").request().get();
		responseLight.close();
		System.out.println("Light opened...");*/
		
/*		Response responseWindow = client.target(baseURL+"Window/Open").request().get();
		responseWindow.close();
		System.out.println("Window opened...");*/
		
		Response responseAlarm = client.target(baseURL+"Alarm/Close").request().get();
		responseAlarm.close();
		System.out.println("Alarm closed...");
		
		System.out.println("Everythig is ready. You can use your room now!");
	
		return "Everythig is ready. You can use your room now!";
	}
	
}
