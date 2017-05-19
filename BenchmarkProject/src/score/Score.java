package score;


public class Score {

    private double fileSize;       //size of File
    private Timer timer = new Timer();
    private int counter = 0;
    private int nTests;
    private double avgSpeed = 0;
    private double minSpeed = Double.MAX_VALUE;
    private double maxSpeed = 0;

    private double roundThreeDecimals(double n) {
        n = Math.round(n * 1000);
        n = n / 1000.0;
        return n;
    }

    public Score(int n, double fileSize) {
        this.fileSize = fileSize * 1024;        //convert GB to MB
        this.nTests = n;
    }

    public void start() {
        timer.start();
        counter++;
    }

    public void stop() {
        long time = timer.stop();
        double score;
        score = fileSize / (time / (1000 * 1000 * 1000));  // MB/s
        //score = roundTwoDecimals(score);
        avgSpeed += score;

        if (score < minSpeed) minSpeed = score;
        else if (score > maxSpeed) maxSpeed = score;

        if (counter == nTests) avgSpeed /= nTests;
    }

    private void avgSpeed() {
        double sum = 0;
        for (int i = 0; i < counter; i++) {
        }
        avgSpeed = sum / counter;
    }

    public double getMinSpeed() {
        return roundThreeDecimals(minSpeed);
    }

    public double getMaxSpeed() {
        return roundThreeDecimals(maxSpeed);
    }

    public double getAvgSpeed() {
        return roundThreeDecimals(avgSpeed);
    }

}