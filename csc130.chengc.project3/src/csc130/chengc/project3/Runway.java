package csc130.chengc.project3;

/**
 * <p>
 * Title: The runway Thread
 * </p>
 * 
 * <p>
 * Description: simulates a single runway at an airport, it handles take offs
 * and landings of airplanes
 * </p>
 * 
 * @author Chunbo Cheng
 */
public class Runway extends Thread {
	private Arrival arrival; // A reference to the arrival thread
	private Departure departure; // A reference to the departure thread
	private boolean running = true; // Used by main program to stop this

	/**
	 * Parameterized constructor
	 * 
	 * @param arr the arrival Thread
	 * @param dep the departure Thread
	 */
	public Runway(Arrival arr, Departure dep) {
		arrival = arr;
		departure = dep;
	}

	/**
	 * stops the thread
	 */
	public void stopRunning() {
		running = false;
	}

	/**
	 * returns the runway information in a string
	 */
	@Override
	public String toString() {
		return "Runway [arrival=" + arrival + ", departure=" + departure + ", running=" + running + "]";
	}

	/**
	 * Grabs and displays an airplane from the arrival queue or the departure queue
	 * depending on the size of each queue to simulate the take offs and landings
	 */
	@Override
	public void run() {
		while (running) {
			while (!arrival.getQueue().isEmpty() || !departure.getQueue().isEmpty()) {
				if (arrival.getQueue().getSize() >= departure.getQueue().getSize()) {
					try {
						Airline currPlane = arrival.getQueue().dequeue();
						currPlane.setExited(System.currentTimeMillis());
						System.out.println(("Minute: " + ((System.currentTimeMillis() - Program3.getStartTime()) / 1000)
								+ " - Flight " + currPlane.getID() + " cleared for landing - " + currPlane.toString()));
						sleep(SimulationTime.LANDING_TIME);
					} catch (InterruptedException e) {
						System.out.println("Runway Thread interrupted!");
					}
				} else {
					try {
						Airline currPlane = departure.getQueue().dequeue();
						currPlane.setExited(System.currentTimeMillis());
						System.out.println(("Minute: " + ((System.currentTimeMillis() - Program3.getStartTime()) / 1000)
								+ " - Flight " + currPlane.getID() + " cleared for takeoff - " + currPlane.toString()));
						sleep(SimulationTime.TAKEOFF_TIME);
					} catch (InterruptedException e) {
						System.out.println("Runway Thread interrupted!");
					}
				}
			}
		}
		System.out.println("Simulation ended!");
	}
}
