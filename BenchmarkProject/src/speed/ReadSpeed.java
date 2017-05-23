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
	private int bufferSize = 2 * 1024 * 1024; //2 MB default 
	private String accessType = "SEQ";        //sequential access by default
	private int numTests = 5;                 //5 tests default
	private int fileSizeGB = 6;			      //1 GB file size by default
	private Score score;
	private static final int REPEAT = 65536;
	
	/**
	 *  Used if benchmark should run with default configurations:
	 *  	-2 MB block size
	 *  	-sequential access in file
	 *  	-5 tests in total
	 *  	-9 GB file size
	 */
	public ReadSpeed() {
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
		ReadSpeed readSpeed = new ReadSpeed(4 * 1024 , "rand", 2, 5);
		readSpeed.read();
		System.out.println(readSpeed.getAvgScore() + " " + readSpeed.getMaxScore() + " " + readSpeed.getMinScore());
	}

}
