package fr.insa.room.middleservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("PreHeating")
public class PreHeating {
	
	String baseURL = "http://localhost:8080/INSARooms/Resources/";
	float innerTemp;
	Client client = ClientBuilder.newClient();
	
	// in this WS, we will close the window, light, door, heater
	// and then open the alarm
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String preHeating(){
		
		String toreturn = "Inside tempreture is perfect, no need to heating!";
		
		Response responseInner = client.target(baseURL+"Tempreture/InnerTempreture").request().get();
		String inner = responseInner.readEntity(String.class);
		System.out.println(inner);
		innerTemp = Float.parseFloat(inner);
		responseInner.close();
		
		if(innerTemp < 18.0){
		
		Response responseHeater = client.target(baseURL+"Heater/Open").request().get();
		responseHeater.close();
		System.out.println("Heater opened...");
		System.out.println("Heater is working. PreHeating... ");

		toreturn = "Heater is working. PreHeating! ";
		}
		
		return toreturn;
	}

}
