package csc130.chengc.project3;

public class Arrival extends Thread {
	private ArrayQueue<Airline> queue = new ArrayQueue<>(); // Arrival queue
	private int time; // Landing duration (sleep time) in minute
	private boolean running = true; // Used by main program to stop this thread

	public Arrival(int duration) {
		time = duration;
	}

	public ArrayQueue<Airline> getQueue() {
		return queue;
	}

	public int getTime() {
		return time;
	}

	public void stopRunning() {
		running = false;
	}

	@Override
	public String toString() {
		return "Arrival [queue=" + queue + ", time=" + time + ", running=" + running + "]";
	}

	@Override
	public void run() {
		long randomWaitTime;
		while (running) {
			randomWaitTime = SimulationTime.timeTillNext(4000);
			try {
				sleep(randomWaitTime);
			} catch (InterruptedException e) {
				System.out.println("Arrival Thread interrupted!");
			}
			int planeNum = (int) (Math.random() * 20) + 10;
			int arrLen = SimulationTime.AIRLINES.length;
			String planeID = SimulationTime.AIRLINES[(int) (Math.random() * arrLen)] + planeNum;
			Airline plane = new Airline(planeID, System.currentTimeMillis());
			queue.enqueue(plane);
			System.out.println("Minute: "
					+ ((System.currentTimeMillis() - Program3.getStartTime()) / 1000) + " - "
					+ "Added flight " + plane.getID() + " to arrival Queue\n" + "Random wait time before next arrival: "
					+ randomWaitTime / 1000 + " mins");
		}
	}

}
