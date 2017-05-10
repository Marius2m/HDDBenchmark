package filehandling;

public class FileSystem {

    private String username;
    private String os;
    private String desktopPath;

    public FileSystem() {
        // TODO Auto-generated constructor stub
    }

    private void getUsername() {
        username = System.getProperty("user.name");
    }

    private void getOS() {
        os = System.getProperty("os.name");
    }

    private void pathBuilder() {
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
}
