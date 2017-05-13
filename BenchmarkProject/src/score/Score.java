package score;

import java.util.ArrayList;

public class Score {

    private double[] times;
    private double fileSize;       //size of File
    private Timer timer = new Timer();
    private int counter = 0;
    private double avgSpeed = 0;

    public Score(int n, double fileSize) {
        // TODO Auto-generated constructor stub
        this.fileSize = fileSize;
        times = new double[n];
    }

    public void start() {
        timer.start();
        counter++;
    }

    public void stop() {
        long time = timer.stop();
        times[counter] = time;
    }

    private void avgSpeed(){
        double sum=0;
        for(int i = 0; i<counter; i++){
            sum += times[i];
        }
        avgSpeed = sum/counter;
    }

    public double getScore() {
        this.stop();
        double score;
        score = (1024 * fileSize) / (times[0] / 1000 * 1000 * 1000);  // MB/s
        return score;
    }

    public String toString() {
        String message = (1024 * fileSize) / (times[0] / 1000 * 1000 * 1000) + " MB/s";
        return message;
    }

}
