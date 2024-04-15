package csc130.chengc.project3;

import java.util.Random;

public class SimulationTime {
	public static final int TAKEOFF_TIME = 4000;
	public static final int LANDING_TIME = 5000;
	public static String[] AIRLINES = {"AA","AI","AF","AZ","KL","BA","BW","DL","FL",
		 "BA","AC","ET","GH","LH","JM","KE","TW","UA"};	
	
	public static long timeInMinutes(long millisecs){
		return millisecs / 60000;
	}
	
	public static long timeInMilisecs(long timeInMinutes){
		return timeInMinutes * 60000;
	}
	
	public static int timeTillNext(int meanArrivalTime){
		//meanArrivalTime = 5000;
		Random random = new Random();
		double randomDouble = random.nextDouble();
    	int timeTillNext = (int)Math.round (-meanArrivalTime * Math.log (1 - randomDouble));
    	//System.out.println("Next plane in " + timeTillNext / 1000 + " mins.");
    	return timeTillNext;
	}
}
