package fr.insa.room.middleservices;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ReceiveAMovement")
public class ReceiveAMovement {
	
	String baseURL = "http://localhost:8080/INSARooms/Resources/";
	Client client = ClientBuilder.newClient();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void receiveAMovement(){
		
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
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		
		int openHour = (int) Math.floor(openTime);
		int openMinute = (int)((openTime-openHour)*100);
		int closeHour = (int)Math.floor(closeTime);
		int closeMinute = (int)((closeTime-closeHour)*100);
		
		// if the room is open
		// turn on the light and turn it off after 10 minutes
		if((hour==openHour&&minute>openMinute)||(hour==closeHour&&minute<closeMinute)||(hour>openHour&&hour<closeHour)){
			
			Response responseLight = client.target(baseURL+"Light/Open").request().get();
			responseLight.close();
			System.out.println("Light opened...");
			Timer timer = new Timer();
			TurnOffLightTask task = new TurnOffLightTask();
			timer.schedule(task, 600000);
			
		}else{
		// if the room is closed
		// it means someone enters the room, we ringing the bells 
			Response responseAlarm = client.target(baseURL+"Alarm/Start").request().get();
			responseAlarm.close();
			System.out.println("Alarm Ringing...");
			System.out.println("--------------------ALARM-------------------");
			System.out.println("Some one enters the room during the night!!!");
			System.out.println("--------------------ALARM-------------------");
		}
	}
	
	public class TurnOffLightTask extends TimerTask{

		public TurnOffLightTask(){
			
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Response responseLight = client.target(baseURL+"Light/Close").request().get();
			responseLight.close();
			System.out.println("Light closed...");
		}
		
	}

}
