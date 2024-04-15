package csc130.chengc.project3;

public class Departure extends Thread {
	private ArrayQueue<Airline> queue = new ArrayQueue<>(); // Arrival queue
	private int time; // Takeoff duration (sleep time) in minute
	private boolean running = true; // Used by main program to stop this thread

	public Departure(int duration) {
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
		return "Departure [queue=" + queue + ", time=" + time + ", running=" + running + "]";
	}

	@Override
	public void run() {
		long randomWaitTime;
		while (running) {
			randomWaitTime = SimulationTime.timeTillNext(5000);
			try {
				sleep(randomWaitTime);
			} catch (InterruptedException e) {
				System.out.println("Departure Thread interrupted!");
			}
			int planeNum = (int) (Math.random() * 20) + 10;
			int arrLen = SimulationTime.AIRLINES.length;
			String planeID = SimulationTime.AIRLINES[(int) (Math.random() * arrLen)] + planeNum;
			Airline plane = new Airline(planeID, System.currentTimeMillis());
			queue.enqueue(plane);
			System.out.println(
					"Minute: " + ((System.currentTimeMillis() - Program3.getStartTime()) / 1000)
							+ " - " + "Added flight " + plane.getID() + " to departure Queue\n"
							+ "Random wait time before next departure: " + randomWaitTime / 1000
							+ " mins");
		}
	}
}
