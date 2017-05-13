package score;

import java.util.ArrayList;

public class Score {

    private double[] times;
    private double fileSize;       //size of File
    private Timer timer = new Timer();
    private int counter = 0;
    private int nTests;
    private double avgSpeed = 0;
    private double minSpeed = Double.MAX_VALUE;
    private double maxSpeed = 0;

    public Score(int n, double fileSize) {
        // TODO Auto-generated constructor stub
        this.fileSize = fileSize * 1024;        //convert GB to MB
        this.nTests = n;
    }

    public void start() {
        timer.start();
        counter++;
    }

    public double stop() {
        long time = timer.stop();
        double score;
        score = fileSize / (time / (1000 * 1000 * 1000));  // MB/s
        avgSpeed += score;

        if (score < minSpeed) minSpeed = score;
        else if (score > maxSpeed) maxSpeed = score;

        if (counter == nTests) avgSpeed /= nTests;
        return score;
    }

    private void avgSpeed() {
        double sum = 0;
        for (int i = 0; i < counter; i++) {
        }
        avgSpeed = sum / counter;
    }


    public double getScore() {
        this.stop();
        double score;
        score = (1024 * fileSize) / (times[0] / 1000 * 1000 * 1000);  // MB/s
        return score;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }
    
    public String toString() {
        String message = fileSize / (times[0] / 1000 * 1000 * 1000) + " MB/s";
        return message;
    }

}
