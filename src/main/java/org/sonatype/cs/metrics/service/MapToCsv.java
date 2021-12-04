package org.sonatype.cs.metrics.service;

import java.io.BufferedWriter;
import java.util.HashMap;

public interface MapToCsv {
    public String[] getLine(HashMap<String, Object> map);
}

