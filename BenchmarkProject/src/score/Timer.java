package score;

public class Timer {
	private long elapsed = 0;
	private long stored = 0;
	//private long startTime = 0;
	//private long pauseTime = 0;
	//private long endTime   = 0;

	public void start() {
		stored = System.nanoTime();
		//starTime = System.nanoTime();

	}

	public long stop() {
		/*elapsed = System.nanoTime() - elapsed;
		stored += elapsed;*/
		return System.nanoTime() - stored;
		//return endTime += System.nanoTime() - startTime;
	}

}
