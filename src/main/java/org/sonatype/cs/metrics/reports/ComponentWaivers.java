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

public class ComponentWaivers implements SendDataToCsvFile {
    private static final Logger log = LoggerFactory.getLogger(ComponentWaivers.class);

    @Override
    public void writeCsvFile(BufferedWriter writer, Object obj) {
        log.info("Writing ComponentWaivers report");

        JsonArray results = (JsonArray) obj;

        for (JsonObject result : results.getValuesAs(JsonObject.class)) {

//            String applicationWaivers = result.getString("stage");
//
//            String[] line = {applicationName, evaluationDate, stage};
//
//            try {
//                writer.write(String.join(",", Arrays.asList(line)));
//                writer.newLine();
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
