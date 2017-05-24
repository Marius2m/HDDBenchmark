package score;


public class Score {

    private double fileSize;       //size of File in MB
    private Timer timer = new Timer();
    private int counter = 0;
    private int nTests;
    private double avgSpeed = 0;
    private double minSpeed = Double.MAX_VALUE;
    private double maxSpeed = 0;

    private double roundThreeDecimals(double n) {
        long temp = Math.round(n * 1000);
        n = temp / 1000.0;
        return n;
    }

    public Score(int n, double fileSize) {
        this.fileSize = fileSize;      
        this.nTests = n;
    }

    public void start() {
        timer.start();
        counter++;
    }

    public void stop() {
        double time = timer.stop();
        time = time /(1000_000_000);

        double score;
        score = fileSize / time ;  // MB/s
        avgSpeed += score;

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