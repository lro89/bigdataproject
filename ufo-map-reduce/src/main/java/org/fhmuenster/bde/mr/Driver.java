package org.fhmuenster.bde.mr;

import org.apache.hadoop.util.ProgramDriver;
import org.fhmuenster.bde.mr.preparation.UfoCitiesJoinDriver;
import org.fhmuenster.bde.mr.ufocount.UfoCount;

public class Driver {

    public static void main(String argv[]) {
        int exitCode = -1;
        ProgramDriver pgd = new ProgramDriver();
        try {
            pgd.addClass("ufocount", UfoCount.class, "MapReduce program to calculate UFO-Count.");
            pgd.addClass("ufojoin", UfoCitiesJoinDriver.class, "MapReduce program to join Input Files.");

            pgd.driver(argv);
            // Success
            exitCode = 0;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.exit(exitCode);
    }
}
