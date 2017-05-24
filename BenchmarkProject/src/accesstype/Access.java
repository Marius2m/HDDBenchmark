package accesstype;

public abstract class Access {

    protected static final int GB_TO_BYTES = 1024 * 1024 * 1024;    // for conversion of the file size from GB to bytes
    protected static final int MB_TO_BYTES = 1024 * 1024;           // for conversion of the files size form MB to bytes
    private int numTests;


    public Access(int numTests) {
        this.numTests = numTests;  //how many tests will be run on the current benchmark

    }

    public void read(int fileSize, int blockSize) {
    }

    public void write(int fileSize) {
    }

    public void write(int fileSize, int blockSize, boolean GB) {
    }

    public boolean shouldDeleteFile(int cntTests) {
        if (numTests == cntTests)
            return true;     //when all tests were performed send message to delete file
        else
            return false;
    }

    public void deleteFile() {
    }

}
