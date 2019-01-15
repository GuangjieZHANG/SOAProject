package fr.insa.room.managements;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class RoomManager implements ServletContextListener{

	private static final int MAXIMUM_CONCURRENT = 1;
	
	private ScheduledThreadPoolExecutor executor = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		executor.shutdown();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		executor = new ScheduledThreadPoolExecutor(MAXIMUM_CONCURRENT);
	}
	
	
	

}
