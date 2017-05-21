package score;


public class Score {

    private double fileSize;       //size of File
    private Timer timer = new Timer();
    private int counter = 0;
    private int nTests;
    private double avgSpeed = 0;
    private double minSpeed = Double.MAX_VALUE;
    private double maxSpeed = 0;

    public Score(int n, double fileSize) {
        this.fileSize = fileSize;//* 1024;        //convert GB to MB
        this.nTests = n;
    }

    private double roundThreeDecimals(double n) {
        n = Math.round(n * 1000);
        n = n / 1000.0;
        return n;
    }

    public void start() {
        timer.start();
        counter++;
    }

    public void stop() {
        long time = timer.stop();
        time = time / (1000_000_000);  //convert from nanoseconds to seconds
        double score;
        score = fileSize / time;  // MB/s
        //score = roundTwoDecimals(score);
        avgSpeed += score;
        System.out.println("Test " + counter + " = " + score + " MB/s");
        if (score < minSpeed) minSpeed = score;
        if (score > maxSpeed) maxSpeed = score;
        if (counter == nTests) avgSpeed /= nTests;
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