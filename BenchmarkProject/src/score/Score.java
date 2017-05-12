package score;

import java.util.ArrayList;

public class Score {

    private double[] times;
    private int nTests;         //number of tests
    private double fileSize;       //size of File
    private Timer timer = new Timer();
    private static int counter = 0;

    public Score(int n, double fileSize) {
        // TODO Auto-generated constructor stub
        this.fileSize = fileSize;
        this.nTests = n;
        times = new double[n];
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
        this.stop();
        double score;
        score = (1024 * fileSize) / times[0];  // MB/s
        return score;
    }

    public String toString() {
        String message = (1024 * fileSize) / times[0] + " MB/s";
        return message;
    }

}
