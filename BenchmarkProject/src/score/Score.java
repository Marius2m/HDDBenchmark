package score;

import java.util.ArrayList;

public class Score {

    private long[] times;
    private int nTests;         //number of tests
    private long fileSize;       //size of File

    public Score(int n, long fileSize) {
        // TODO Auto-generated constructor stub
        this.fileSize = fileSize;
        this.nTests = n;
    }

    public void start() {
        Timer timer = new Timer();
        timer.start();
    }

}
