package csc130.chengc.project3;

import java.util.Scanner;

public class Program3 {
	private static long startTime;
	private static long simulationTime;
	private static Arrival arrival;
	private static Departure departure;
	private static Runway runway;

	public Program3(int minute) {
		arrival = new Arrival(minute);
		departure = new Departure(minute);
		runway = new Runway(arrival, departure);
	}

	public static long getStartTime() {
		return startTime;
	}

	public static long getSimulationTime() {
		return simulationTime;
	}

	public static void startSimulation(long time) {
		startTime = System.currentTimeMillis();
		simulationTime = time;
		arrival.start();
		departure.start();
		runway.start();
		while (System.currentTimeMillis() < startTime + simulationTime) {
			// do nothing
		}
		// stop the loop in each thread
		arrival.stopRunning();
		departure.stopRunning();
		// interrupt each thread
		arrival.interrupt();
		departure.interrupt();
		try {
			arrival.join();
			departure.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.print("Enter simulation duration in minute: ");
		Scanner scan = new Scanner(System.in);
		int time = scan.nextInt();
		Program3 program = new Program3(time);
		program.startSimulation(time * 60000);
		while (program.arrival.isAlive() || program.departure.isAlive()) {
			// do nothing
		}
		program.runway.stopRunning();
		scan.close();
	}
}
