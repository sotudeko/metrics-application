package org.sonatype.cs.metrics.service;

public interface SendDataToCsvFile {
    default void makeCsvFile(Object obj) {}
}
