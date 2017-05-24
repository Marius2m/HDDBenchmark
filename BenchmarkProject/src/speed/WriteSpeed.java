package speed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import accesstype.Access;
import accesstype.RandomAccess;
import accesstype.SequentialAccess;
import score.Score;

public class WriteSpeed {

    private Access access;
    private int bufferSize = 4 * 1024; 		  //4 KB default
    private String accessType = "SEQ";        //sequential access by default
    private int numTests = 5;                 //5 tests default
    private int fileSizeMB = 512;             //512 MB file size by default
    private Score score;
    private static final int REPEAT = 4096;

    /**
     * Default settings:
     *      - 4 KB block size
     *      - sequential access in file
     *      - 5 tests
     *      - 256 MB file size
     */
    public WriteSpeed() {
        score = new Score(numTests, fileSizeMB);
        access = new SequentialAccess(numTests);
    }

    public WriteSpeed(int bufferSize, String accessType, int numTests, int fileSizeMB){
        this.bufferSize = bufferSize;
        this.accessType = accessType;
        this.numTests = numTests;
        this.fileSizeMB = fileSizeMB;
        if(isSequentialFile()){
            score = new Score(numTests, fileSizeMB);
        	access = new SequentialAccess(numTests);
        }
        else{
        	double size = ((double)bufferSize * REPEAT) /1024/1024;
         	score = new Score(numTests, size);
            access = new RandomAccess(REPEAT, numTests);
        }
    }

    public void write() {
        for (int i = 0; i < numTests; i++) {
            score.start();
            access.write(fileSizeMB, bufferSize, false);
            score.stop();
        }
        access.deleteFile();
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
    
  /*  public static void main(String[] args){
    	WriteSpeed speed = new WriteSpeed(1024 * 1024 , "rand", 1, 1024);
    	speed.write();
    	System.out.println(speed.getAvgScore() + " " + speed.getMaxScore() + " " + speed.getMinScore());
    }
*/
}