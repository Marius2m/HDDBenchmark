package accesstype;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import filehandling.FileSystem;

public class RandomAccess extends Access {

    private static final int FILL_BUFFER_SIZE = 4 * 1024 * 1024;
    private final int repeat;
    private FileSystem fs;
    private File file;
    private RandomAccessFile raf;
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

    public void read(int fileSize, int bufferSize) {
        long fSize = GB_TO_BYTES;
        fSize = fSize * fileSize;
        byte[] buf = new byte[bufferSize];
        long range = fSize - bufferSize;

        for (int i = 0; i < repeat; i++)
            try {
                long random = ThreadLocalRandom.current().nextLong(range);
                raf.seek(random);
                raf.read(buf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        if (super.shouldDeleteFile(++cntTests)) {
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

    public void write(int fileSize) {
        long fSize = GB_TO_BYTES;
        fSize = fSize * fileSize;
        byte[] buf = new byte[FILL_BUFFER_SIZE];
        long crntWrittenSize = 0;

        while (crntWrittenSize < fSize)
            try {
                fillBuffer(buf);
                raf.write(buf);
                crntWrittenSize += FILL_BUFFER_SIZE;
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void write(int fileSize, int bufferSize, boolean GB) {
        long fSize;
        if (GB)
            fSize = GB_TO_BYTES;
        else
            fSize = MB_TO_BYTES;
        fSize = fSize * fileSize;              //actual file size in bytes
        byte[] buf = new byte[bufferSize];    //buffer used for reading
        long range = fSize - bufferSize;

        for (int i = 0; i < repeat; i++) {
            try {
                raf.seek(ThreadLocalRandom.current().nextLong(range));    //seek a random position in the file
                //where bufferSize + soughtPosition < fSize is ensured
                fillBuffer(buf);                //put random values in the buffer
                raf.write(buf);                    //write the values from the buffer in the file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteFile() {
        try {
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        raf = null;
        fs.deleteFile(file);
    }

    private void fillBuffer(byte[] bytes) {
        Random rand = new Random();
        rand.nextBytes(bytes);
    }

}
