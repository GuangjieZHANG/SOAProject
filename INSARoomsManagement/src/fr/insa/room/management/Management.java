package fr.insa.room.management;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("Management")
public class Management {

	public String baseURL = "http://localhost:8080/INSARooms/Resources/";
	public String middleURL = "http://localhost:8080/INSARoomsMiddleWSs/MiddleServices";
	public Client client = ClientBuilder.newClient();
	public static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	public static final long PERIOD_20 = 20 * 60 * 1000;
	
	//this method is to manage the classroom, as well as sensors
	@GET
	@Path("/Start")
	@Produces(MediaType.TEXT_PLAIN)
	public String startSystem(){
		// get the system first
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
		
		int openHour = (int) Math.floor(openTime);
		int openMinute = (int)((openTime-openHour)*100);
		int closeHour = (int)Math.floor(closeTime);
		int closeMinute = (int)((closeTime-closeHour)*100);
		
		Calendar openCalendar = Calendar.getInstance();
		openCalendar.set(Calendar.HOUR_OF_DAY,openHour);
		openCalendar.set(Calendar.MINUTE,openMinute);
		openCalendar.set(Calendar.SECOND,0);
		
		Calendar closeCalendar = Calendar.getInstance();
		closeCalendar.set(Calendar.HOUR_OF_DAY,closeHour);
		closeCalendar.set(Calendar.MINUTE,closeMinute);
		closeCalendar.set(Calendar.SECOND,0);
		
		Date openDate = (Date) openCalendar.getTime();
		Date closeDate = (Date) closeCalendar.getTime();
		Date now = (Date) Calendar.getInstance().getTime();
		
/*		System.out.println("Date is "+date);
		System.out.println("Calendar is "+openCalendar.getTime());
		System.out.println("Instance of calendar is "+Calendar.getInstance().getTime());*/
		
		
		if(openDate.before(new java.util.Date())&&closeDate.after(new java.util.Date())){
			closeDate = this.addDay(closeDate, 1);
		}else{
			openDate = this.addDay(openDate,1);
		}
		
		Timer openTimer = new Timer();
		Timer closeTimer = new Timer();
		Timer tempretureTimer = new Timer();
		OpenTask openTask = new OpenTask();
		CloseTask closeTask = new CloseTask();
		ReceiveTempretureTask receiveTempretureTask = new ReceiveTempretureTask();
		openTimer.schedule(openTask, openDate, PERIOD_DAY);
		closeTimer.schedule(closeTask, closeDate, PERIOD_DAY);
		tempretureTimer.schedule(receiveTempretureTask,now,PERIOD_20);
		
		return "Everythig is ready. You can use your room now!";
	}
	
	
	@GET
	@Path("/Movement")
	@Produces(MediaType.TEXT_PLAIN)
	public String movement(){
		Response responseMove = client.target(middleURL+"/ReceiveAMovement").request().get();
		responseMove.close();
		return "Light is open for 10 minutes.";
	}
	
	public Date addDay(Date date, int num){
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return (Date) startDT.getTime();
	}
	
	class OpenTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Response responseOpen = client.target(middleURL+"/Open").request().get();
			responseOpen.close();
		}
		
	}
	
	class CloseTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Response responseClose = client.target(middleURL+"/Close").request().get();
			responseClose.close();
		}
		
	}
	
	class PreHeatTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Response responsePre = client.target(middleURL+"/PreHeating").request().get();
			responsePre.close();
		}

	}
	
	// regular every 20 minutes
	class ReceiveTempretureTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Response responseTemp = client.target(middleURL+"/ReceiveATempreture").request().get();
			responseTemp.close();
		}
		
	}
	
}
