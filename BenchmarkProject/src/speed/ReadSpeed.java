package speed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import accesstype.Access;
import accesstype.RandomAccess;
import accesstype.SequentialAccess;
import filehandling.FileSystem;
import score.Score;

public class ReadSpeed{
	
	private Access access;					
	private int bufferSize = 64 * 1024;       //6 KB default 
	private String accessType = "seq";        //sequential access by default
	private int numTests = 3;                 //3 tests default
	private int fileSizeGB = 6;               //6 GB file size by default
	private Score score;
	private static final int REPEAT = 65536;
	
	/**
	 *  Used if benchmark should run with default configurations:
	 *  	-6 KB block size
	 *  	-sequential access in file
	 *  	-3 tests in total
	 *  	-6 GB file size
	 */
	public ReadSpeed() { //asta e constructorul pt default case
            score = new Score(numTests, fileSizeGB * 1024);
            access = new SequentialAccess(numTests); 
	}
	
	/**
	 * Used if benchmark should run with user inputed configurations
	 * @param bufferSize 
	 * @param accessType
	 * @param numTests   
	 * @param fileSizeGB 
	 */
	
	public ReadSpeed(int bufferSize, String accessType, int numTests, int fileSizeGB) {
		this.bufferSize = bufferSize;
		this.accessType = accessType;
		this.numTests = numTests;
		this.fileSizeGB = fileSizeGB;
		if(isSequentialFile())	{
			score = new Score(numTests, fileSizeGB * 1024);
			access = new SequentialAccess(numTests);
		}
		else{ 
			double size = ((double)bufferSize * REPEAT) /1024/1024;
			System.out.println(size);
			score = new Score(numTests, size);
			access = new RandomAccess(REPEAT, numTests);
		}
	}
	
	/**
	 * The method reads a file with a given buffer size for a number of times and then computes the minimum
	 * maximum and average of the scores obtained 
	 */
	public void read() {
		access.write(fileSizeGB);       //first write the file that will be used for reading
		for(int i = 0; i < numTests; i++){
			score.start();
			access.read(fileSizeGB, bufferSize);
			score.stop();
		}
	}
	
	public double getAvgScore(){
		return score.getAvgSpeed();
	}
	
	public double getMinScore(){
		return score.getMinSpeed();
	}
	
	public double getMaxScore(){
		return score.getMaxSpeed();
	}
	
	private boolean isSequentialFile(){
		if(accessType.toLowerCase().contains("seq"))
			return true;
		else 
			return false;
	}
	
	public static void main(String[] args){
		ReadSpeed readSpeed = new ReadSpeed(128 * 1024 , "seq", 1, 8);
		readSpeed.read();
		System.out.println(readSpeed.getAvgScore() + " " + readSpeed.getMaxScore() + " " + readSpeed.getMinScore());
	}

}
