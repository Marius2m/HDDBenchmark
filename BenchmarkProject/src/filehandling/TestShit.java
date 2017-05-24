package filehandling;

import speed.ReadSpeed;
import speed.WriteSpeed;

/**
 * Created by georgeoprea on 10/05/17.
 */
public class TestShit {

    public static void main(String[] args) {
        System.out.println("\n\n /////Read/////\n\n");
        TestShit.testRead();
        //System.out.println("\n\n /////Write/////\n\n");
        //TestShit.testWrite();
    }


    public static void testRead() {
        ReadSpeed test;

        int buffs[] = {64 * 1024 * 1024};
        //int buffs[] = {512, 4 * 1024, 64 * 1024, 128 * 1024, 1 * 1024 * 1024};

        int numTests[] = {1};
        //int numTests[] = {1, 2, 3, 4, 5};

        int filesizes[] = {3};   //MB
        // int filesizesM] = {1, 3, 5}; //MB

        String accesses[] = {"seq"};
        //String accesses[] = {"seq", "rand"};

        for (String access : accesses)
            for (int buffer : buffs)
                for (int filesize : filesizes)
                    for (int tests : numTests) {
                        System.out.println("\n[Read-" + access + "] buffer: " + buffer + " bytes   Number of tests: " + tests + "     FileSize: " + filesize + " GB");

                        test = new ReadSpeed(buffer, access, tests, filesize);
                        test.read();

                        System.out.println("\nMin: " + test.getMinScore() + " MB/s" + "\nMax: " + test.getMaxScore() + " MB/s" + "\nAVG: " + test.getAvgScore() + " MB/s");
                    }


    }

    public static void testWrite() {
        WriteSpeed test;

        //int buffs[] = {4*1024};
        int buffs[] = {4 * 1024, 64 * 1024, 128 * 1024, 1 * 1024 * 1024};

        int numTests[] = {1};
        //int numTests[] = {1, 2, 3, 4, 5};

        int filesizes[] = {1};   //GB
        // int filesizesM] = {1, 3, 5};     //GB

        //String accesses[] = {"seq"};
        String accesses[] = {"seq", "rand"};

        for (String access : accesses)
            for (int buffer : buffs)
                for (int filesize : filesizes)
                    for (int tests : numTests) {
                        System.out.println("\n\n[Write-" + access + "] buffer: " + buffer + " bytes   Number of tests: " + tests + "     FileSize: " + filesize + " GB");

                        test = new WriteSpeed(buffer, access, tests, filesize);
                        test.write();

                        System.out.println("\nMin: " + test.getMinScore() + " MB/s" + "\nMax: " + test.getMaxScore() + " MB/s" + "\nAVG: " + test.getAvgScore() + " MB/s");
                    }

    }
}