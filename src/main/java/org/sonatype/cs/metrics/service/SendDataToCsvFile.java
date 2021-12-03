package org.sonatype.cs.metrics.service;

import java.io.BufferedWriter;

public interface SendDataToCsvFile {
    //public void makeCsvFile(Object obj, String header, String csvfile);
    public void writeCsvFile(BufferedWriter writer, Object obj);
}
