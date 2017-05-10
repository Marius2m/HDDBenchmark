package score;

/**
 * Created by georgeoprea on 11/05/17.
 */
public interface ITimer {
    /**
     * Starts a new timer.
     */
    public void start();

    /**
     * Stops the timer.
     * @return Returns the time in ns since start was called.
     */
    public long stop();

    /**
     * Pauses the timer.
     * @return Returns the time in ns since <b> start </b> was called.
     */
    public long pause();

    /**
     * Resumes the timer. <br>
     */
    public void resume();
}
