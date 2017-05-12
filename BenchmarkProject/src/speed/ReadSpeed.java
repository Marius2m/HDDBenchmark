package speed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import accesstype.Access;
import accesstype.RandomAccess;
import accesstype.SequentialAccess;
import filehandling.FileSystem;
import score.Score;

public class ReadSpeed {

	private Access access;
	private int bufferSize = 2 * 1024 * 1024; //2 MB default
	private String accessType = "SEQ";        //sequential access by default
	private int numTests = 5;                 //5 tests default
	private double fileSizeGB = 1;			      //1 GB file size by default
	private double maxScore;
	private double minScore = Double.MAX_VALUE; //initial score is the largest integer value for further comparisons
	private double avgScore;

	/**
	 *  Used if benchmark should run with default configurations:
	 *  	-2 MB block size
	 *  	-sequential access in file
	 *  	-5 tests in total
	 *  	-1 GB file size
	 */
	public ReadSpeed() {
		access = new SequentialAccess();
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
		if(isSequentialFile())
			access = new SequentialAccess();
		else
			access = new RandomAccess();
	}



	/**
	 * The method reads a file with a given buffer size for a number of times and then computes the minimum
	 * maximum and average of the scores obtained
	 */
	public void read() {
		Score score = new Score(numTests, fileSizeGB);
		for(int i = 0; i < numTests; i++){
			score.start();
			access.read(bufferSize, fileSizeGB);
			double crntScore = score.getScore();
			if(crntScore > maxScore)
				maxScore = crntScore;
			else if (crntScore < minScore)
				minScore = crntScore;
			avgScore += crntScore;
		}
		avgScore = avgScore / numTests;
	}

	private boolean isSequentialFile(){
		if(accessType.toLowerCase().contains("SEQ"))
			return true;
		else
			return false;
	}

	public static void main(String[] args){

	}
}
