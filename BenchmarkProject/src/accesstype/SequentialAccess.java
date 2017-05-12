package accesstype;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import filehandling.FileSystem;

public class SequentialAccess extends Access {

	private File file;
	private FileSystem fs;
	//private Block block;

	public SequentialAccess() {
		fs = new FileSystem();
		file = new File(fs.getFilePath());
	}

	public void read(int bufferSize, int fileSize) {
		ByteBuffer buf = ByteBuffer.allocateDirect(bufferSize);
		FileChannel channel;

		try {
			channel = (new FileInputStream(file)).getChannel();
			while(channel.read(buf) != -1)
				buf.clear();
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
