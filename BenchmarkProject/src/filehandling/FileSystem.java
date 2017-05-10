package filehandling;

import java.io.File;

public class FileSystem {

    private String username;
    private String os;
    private String desktopPath;
    private File fileBench;

    public FileSystem() {
        // TODO Auto-generated constructor stub
        pathBuilder();
        String path = desktopPath + File.separator + "bench.dat";
        fileBench = new File(path);
    }

    public void getUsername() {
        username = System.getProperty("user.name");
    }

    public void getOS() {
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

    public File getFile(){
        return fileBench;
    }

    public boolean deleteFile(){
        //returns true if file was successfully deleted
        //returns false if file was not successfully deleted
        return fileBench.delete();
    }
}
