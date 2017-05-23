package score;

public class Timer{
	private long elapsed = 0;
	private long stored = 0;

	public void start() {
		stored = System.nanoTime();
	}

	public long stop() {
		return System.nanoTime() - stored;
	}

}
