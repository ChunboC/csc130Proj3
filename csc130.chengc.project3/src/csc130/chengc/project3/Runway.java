package csc130.chengc.project3;

public class Runway extends Thread {
	private Arrival arrival; // A reference to the arrival thread
	private Departure departure; // A reference to the departure thread
	private boolean running = true; // Used by main program to stop this

	public Runway(Arrival arr, Departure dep) {
		arrival = arr;
		departure = dep;
	}

	public void stopRunning() {
		running = false;
	}

	@Override
	public String toString() {
		return "Runway [arrival=" + arrival + ", departure=" + departure + ", running=" + running + "]";
	}

	@Override
	public void run() {
		while (running) {
			while (!arrival.getQueue().isEmpty() || !departure.getQueue().isEmpty()) {
				if (arrival.getQueue().getSize() >= departure.getQueue().getSize()) {
					try {
						Airline currPlane = arrival.getQueue().dequeue();
						currPlane.setExited(System.currentTimeMillis());
						System.out.println(("Minute: "
								+ ((System.currentTimeMillis() - Program3.getStartTime()) / 1000)
								+ " - Flight " + currPlane.getID() + " cleared for landing - " + currPlane.toString()));
						sleep(SimulationTime.LANDING_TIME);
					} catch (InterruptedException e) {
						System.out.println("Runway Thread interrupted!");
					}
				} else {
					try {
						Airline currPlane = departure.getQueue().dequeue();
						currPlane.setExited(System.currentTimeMillis());
						System.out.println(("Minute: "
								+ ((System.currentTimeMillis() - Program3.getStartTime()) / 1000)
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
