package filehandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class FileSystem {

    private String username;
    private String os;
    private String desktopPath;
    public File fileBench;
    private RandomAccessFile fileBenchRAND;
    private boolean randFile;

    public FileSystem(String access) throws FileNotFoundException {
        // TODO Auto-generated constructor stub
        pathBuilder();
        String path = desktopPath + File.separator + "bench.dat";
        fileBench = new File(path);
        if (access.toLowerCase().equals("seq")) {
            randFile = false;
        } else if (access.toLowerCase().equals("rand")) {
            fileBenchRAND = new RandomAccessFile(fileBench, "rw");
            randFile = true;
        }
    }

    private void getUsername() {
        username = System.getProperty("user.name");
    }

    private void getOS() {
        os = System.getProperty("os.name");
    }

    private void pathBuilder() {
        getOS();
        getUsername();
        if (os.equals("Linux")) {
            desktopPath = "/home/" + username + "/Desktop";
        } else if (os.contains("Windows")) {
            desktopPath = "C:\\Users\\" + username + "\\Desktop";
        } else if (os.toLowerCase().contains("mac")) {
            desktopPath = "/Users/" + username + "/Desktop";
        }
    }

    public String getDesktopPath() {
        return desktopPath;
    }

    public File getFileSEQ() {
        return fileBench;
    }

    public RandomAccessFile getFileRAND() {
        return fileBenchRAND;
    }


    public boolean deleteFile() {
        //returns true if file was successfully deleted
        //returns false if file was not successfully deleted
        return fileBench.delete();
    }
}
