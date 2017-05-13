package speed;

import accesstype.Access;
import accesstype.RandomAccess;
import accesstype.SequentialAccess;
import score.Score;

public class WriteSpeed {

    private Access access;
    private int bufferSize = 2 * 1024 * 1024; //2 MB default
    private String accessType = "SEQ";        //sequential access by default
    private int numTests = 5;                 //5 tests default
    private int fileSizeGB = 9;               //1 GB file size by default
    private Score score;

    /*
     * Default settings:
     *      - 2 MB block size
     *      - sequential acces in file
     *      - 5 tests
     *      - 9 GB file size
     */
    public WriteSpeed() {
        score = new Score(numTests, fileSizeGB);
        access = new SequentialAccess(numTests);
    }


    public WriteSpeed(int bufferSize, String accessType, int numTests, int fileSizeGB){
        this.bufferSize = bufferSize;
        this.accessType = accessType;
        this.numTests = numTests;
        this.fileSizeGB = fileSizeGB;
        if(isSequentialFile())
            access = new SequentialAccess(numTests);
        else
            access = new RandomAccess();
    }

    public void write() {
        for (int i = 0; i < numTests; i++) {
            score.start();
            access.write(fileSizeGB, bufferSize);
            score.stop();
        }
    }

    public double getAvgScore() {
        return score.getAvgSpeed();
    }

    public double getMinScore() {
        return score.getMinSpeed();
    }

    public double getMaxScore() {
        return score.getMaxSpeed();
    }


    private boolean isSequentialFile(){
        if(accessType.toLowerCase().contains("seq"))
            return true;
        else
            return false;
    }

}
