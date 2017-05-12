package score;

import java.util.ArrayList;

public class Score {

    private double[] times;
    private int nTests;         //number of tests
    private double fileSize;       //size of File
    private Timer timer = new Timer();
    private static int counter = 0;

    public Score(int n, long fileSize) {
        // TODO Auto-generated constructor stub
        this.fileSize = fileSize;
        this.nTests = n;
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        long time = timer.stop();
        times[counter] = time;
        counter++;
    }

    public double getScore() {
        double score = 0;
        score = fileSize / (1024 * 1024);
        return score;
    }

}
