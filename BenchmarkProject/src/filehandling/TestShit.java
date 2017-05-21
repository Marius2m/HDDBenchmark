package filehandling;

import speed.WriteSpeed;

/**
 * Created by georgeoprea on 10/05/17.
 */
public class TestShit {
    public static void main(String[] args) {
        WriteSpeed test;

        //WriteSpeed test = new WriteSpeed(4 * 1024 * 1024, "seq", 3, 3);
        //test.write();
        //ReadSpeed test = new ReadSpeed(512 * 1024, "seq", 3, 3); test.read();
//        System.out.println("AVG: " + test.getAvgScore() + "\nMax: " + test.getMaxScore() + "\nMin: " + test.getMinScore());
        int buffs[] = {512, 4 * 1024, 64 * 1024, 1 * 1024 * 1024, 2 * 1024 * 1024, 4 * 1024 * 1024, 8 * 1024 * 1024};

        //int numTests[] = {1, 2, 3, 4, 5};
        int nTests = 5;

        int filesize = 4096;   //MB
        // int filesizesMB[] = {10, 20, 50, 100, 200, 256, 300, 512, 600, 800, 1024, 2048, 4096};
        //  for (int k = 0; k < filesizesMB.length; k++)
        // for (int i = 0; i < buffs.length; i++)
        //for (int j = 0; j < numTests.length; j++)
        //{
        System.out.println("\n\n[SEQ]buffer: " + 65536 + " bytes   Number of tests: " + nTests + "     FileSize:" + filesize + " MB");
        test = new WriteSpeed(64 * 1024, "seq", nTests, filesize);
        test.write();
        //ReadSpeed test = new ReadSpeed(512 * 1024, "seq", 3, 3); test.read();
        System.out.println("\nMin: " + test.getMinScore() + " MB/s" + "\nMax: " + test.getMaxScore() + " MB/s" + "\nAVG: " + test.getAvgScore() + " MB/s");
    }
}
//}