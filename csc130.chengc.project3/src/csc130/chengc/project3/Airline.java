package csc130.chengc.project3;

public class Airline {
	private String flightID; // Plane’s id tag example: AA204
	private long entered; // Time the plane entered the queue
	private long exited; // Time the plane exited the queue
	
	public Airline(String id, long enterTime) {
		flightID = id;
		entered = enterTime;
	}
	
	public String getID() {
		return flightID;
	}

	public long getEntered() {
		return entered;
	}

	public void setEntered(long entered) {
		this.entered = entered;
	}

	public void setExited(long exited) {
		this.exited = exited;
	}

	@Override
	public String toString() {
		return "Entered Queue at " + ((entered - Program3.getStartTime()) / 1000) + " - waited " + ((exited - entered) / 1000) + " mins";
	}
}
