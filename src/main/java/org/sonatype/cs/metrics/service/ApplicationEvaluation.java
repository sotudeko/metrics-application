package org.sonatype.cs.metrics.service;

public class ApplicationEvaluation implements ApiObjectToCsv {

    @Override
    public void makeCsvFile(Object obj) {
        System.out.println("Hello from ApplicationEvaluation");
    }
}
