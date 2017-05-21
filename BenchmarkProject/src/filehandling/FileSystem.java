package filehandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class FileSystem {

    private String username;
    private String os;
    private String desktopPath;
    private String path;
    private static int cnt = 0;

    public FileSystem() {
        pathBuilder();
    }

    public String getFilePath(){
        //path = desktopPath + File.separator + "bench" + cnt + ".dat";
        path = "/Volumes/Whatever" + File.separator + "bench" + cnt + ".dat";       //testing path
        cnt++;
        return path;
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

    private String getDesktopPath() {
        return desktopPath;
    }


    public boolean deleteFile(File file) {
        return file.delete();  //returns true if file was successfully deleted
    }

    public void endTests(){
        cnt = 0;
    }

}
