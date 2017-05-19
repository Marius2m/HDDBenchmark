package accesstype;

public abstract class Access {

    private int numTests;

    public Access(int numTests) {
        this.numTests = numTests;  //how many tests will be run on the current benchmark

    }

    public void read(int fileSize, int blockSize) {
    }

    public void write(int fileSize) {
    }

    public void write(int fileSize, int blockSize) {
    }

    public boolean shouldDeleteFile(int cntTests) {
        if (numTests == cntTests)
            return true;     //when all tests were performed send message to delete file
        else
            return false;
    }


}
