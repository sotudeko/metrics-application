package org.sonatype.cs.metrics.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.cs.metrics.service.SendDataToCsvFile;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class ComponentWaivers implements SendDataToCsvFile {
    private static final Logger log = LoggerFactory.getLogger(ComponentWaivers.class);

    @Override
    public String[] getLine(HashMap<String, Object> map) {
        return new String[0];
    }
}
