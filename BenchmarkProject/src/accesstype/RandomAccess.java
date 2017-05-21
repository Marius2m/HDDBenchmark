package accesstype;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import filehandling.FileSystem;

public class RandomAccess extends Access {

	private FileSystem fs;
	private File file;
	private RandomAccessFile raf;
	private static final int GB_TO_BYTES = 1024 * 1024 * 1024;
	private static final int FILL_BUFFER_SIZE = 4 * 1024 * 1024;
	private final int repeat;
	private int cntTests;  //how many tests have been run on this file
			
	public RandomAccess(int repeat, int numTests) {
		super(numTests);
		this.repeat = repeat;
		fs = new FileSystem();
		try {
			file = new File(fs.getFilePath());
			raf = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void read(int fileSize, int bufferSize){
		//Random rand = new Random();
		long fSize = GB_TO_BYTES;
		fSize = fSize * fileSize;
		byte[] buf = new byte[bufferSize];

		for(int i = 0; i < repeat; i++)
			try {
				raf.seek(ThreadLocalRandom.current().nextLong(fSize));
				raf.read(buf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(super.shouldDeleteFile(++cntTests)){
			cntTests = 0;
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			raf = null;
			fs.deleteFile(file);
		}
	}

	public void write(int fileSize){
		long fSize = GB_TO_BYTES;
		fSize = fSize * fileSize;
		byte[] buf = new byte[FILL_BUFFER_SIZE];
		long crntWrittenSize = 0;

		while(crntWrittenSize < fSize)
			try {
				fillBuffer(buf);
				raf.write(buf);
				crntWrittenSize += FILL_BUFFER_SIZE;
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void write(int fileSize, int bufferSize){
		//Random rand = new Random();
		long fSize = GB_TO_BYTES;
		fSize = fSize * fileSize;			  //actual file size in bytes
		byte[] buf = new byte[bufferSize];    //buffer used for reading
		long range = fSize- bufferSize + 1;

		for(int i = 0; i < repeat; i++){
			try {
				raf.seek(ThreadLocalRandom.current().nextLong(range));	//seek a random position in the file
																						//where bufferSize + soughtPosition < fSize is ensured
				fillBuffer(buf);				//put random values in the buffer
				raf.write(buf);					//write the values from the buffer in the file
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	private void fillBuffer(byte[] bytes){
		Random rand = new Random();
		rand.nextBytes(bytes);
	}

}
