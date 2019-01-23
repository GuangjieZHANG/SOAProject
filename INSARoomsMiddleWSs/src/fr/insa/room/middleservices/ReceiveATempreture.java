package fr.insa.room.middleservices;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ReceiveATempreture")
public class ReceiveATempreture {

	String baseURL = "http://localhost:8080/INSARooms/Resources/";
	Client client = ClientBuilder.newClient();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void receiveATempreture(){
		
		float innerTemp;
		float outerTemp;	
		
		double openTime;
		double closeTime;

		//get the room open time and close time via ChangeTimeWS
		Response responseOpenTime = client.target(baseURL+"ChangeTime/OpenTime").request().get();
		String open = responseOpenTime.readEntity(String.class);
		System.out.println(open);
		openTime = Double.parseDouble(open);
		responseOpenTime.close(); 

		Response responseCloseTime = client.target(baseURL+"ChangeTime/CloseTime").request().get();
		String close = responseCloseTime.readEntity(String.class);
		System.out.println(close);
		closeTime = Double.parseDouble(close);
		responseCloseTime.close(); 
		
		Response responseInner = client.target(baseURL+"Tempreture/InnerTempreture").request().get();
		String inner = responseInner.readEntity(String.class);
		System.out.println(inner);
		innerTemp = Float.parseFloat(inner);
		responseInner.close();
		
		Response responseOuter = client.target(baseURL+"Tempreture/OuterTempreture").request().get();
		String outer = responseOuter.readEntity(String.class);
		System.out.println(outer);
		outerTemp = Float.parseFloat(outer);
		responseOuter.close();
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		
		int openHour = (int) Math.floor(openTime);
		int openMinute = (int)((openTime-openHour)*100);
		int closeHour = (int)Math.floor(closeTime);
		int closeMinute = (int)((closeTime-closeHour)*100);
		
		if((hour==openHour&&minute>openMinute)||(hour==closeHour&&minute<closeMinute)||(hour>openHour&&hour<closeHour)){
		// logic part
		// from opentime, we start to monitor
			if(outerTemp > 18.0 && outerTemp < 27.0 && innerTemp > outerTemp){
				Response responseWindow = client.target(baseURL+"Window/Open").request().get();
				responseWindow.close();
				System.out.println("Window opened...");
			}
			
			if(innerTemp < 18.0){
				Client client = ClientBuilder.newClient();
				Response responseHeater = client.target(baseURL+"Heater/Open").request().get();
				responseHeater.close();
			}	
		}else{
			// sinon il ne fait rien
		}

		
	}
	
	
}
