package csc130.chengc.project3;
/**
 * <p>
 * Title: The Arrival Thread
 * </p>
 * 
 * <p>
 * Description: simulates a queue of airplanes arriving at the airport at random times.
 * </p>
 * 
 * @author Chunbo Cheng
 */
public class Arrival extends Thread {
	private ArrayQueue<Airline> queue = new ArrayQueue<>(); // Arrival queue
	private int time; // Landing duration (sleep time) in minute
	private boolean running = true; // Used by main program to stop this thread
	
	/**
	 * Parameterized constructor
	 * @param duration The duration of the simulation
	 */
	public Arrival(int duration) {
		time = duration;
	}
	/**
	 * returns the arrival queue
	 * @return the arrival queue
	 */
	public ArrayQueue<Airline> getQueue() {
		return queue;
	}
	/**
	 * returns the duration of the simulation
	 * @return The duration of the simulation
	 */
	public int getTime() {
		return time;
	}
	/**
	 * stops the thread
	 */
	public void stopRunning() {
		running = false;
	}
	/**
	 * returns the arrival queue information in a string
	 */
	@Override
	public String toString() {
		return "Arrival [queue=" + queue + ", time=" + time + ", running=" + running + "]";
	}

	/**
	 * Puts random airplane in the arrival queue at random times
	 */
	@Override
	public void run() {
		long randomWaitTime = 0;
		while (running) {
			try {
				sleep(randomWaitTime);
			} catch (InterruptedException e) {
				System.out.println("Arrival Thread interrupted!");
				break;
			}
			int planeNum = (int) (Math.random() * 20) + 10;
			int arrLen = SimulationTime.AIRLINES.length;
			String planeID = SimulationTime.AIRLINES[(int) (Math.random() * arrLen)] + planeNum;
			Airline plane = new Airline(planeID, System.currentTimeMillis());
			randomWaitTime = SimulationTime.timeTillNext(12000);
			queue.enqueue(plane);
			System.out.println("Minute: "
					+ ((System.currentTimeMillis() - Program3.getStartTime()) / 1000) + " - "
					+ "Added flight " + plane.getID() + " to arrival Queue\n" + "Random wait time before next arrival: "
					+ randomWaitTime / 1000 + " mins");
		}
	}

}
