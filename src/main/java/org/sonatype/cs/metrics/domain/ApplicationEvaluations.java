package org.sonatype.cs.metrics.domain;

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

public class ApplicationEvaluations implements SendDataToCsvFile {
    private static final Logger log = LoggerFactory.getLogger(ApplicationEvaluations.class);

    @Override
    public void makeCsvFile(Object obj) {
        System.out.println("Hello from ApplicationEvaluation");

        String[] header = {"Stage", "Application", "Evaluation Date"};
        String csvFilename = "applicationevaluations.csv";

        try {

            BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFilename));
            writer.write(String.join(",", header));
            writer.newLine();

            JsonArray results = (JsonArray) obj;

            for (JsonObject result : results.getValuesAs(JsonObject.class)) {

                String stage = result.getString("stage");
                String applicationId = result.getString("applicationId");
                String evaluationDate = result.getString("evaluationDate");

                String[] line = {stage, applicationId, evaluationDate};

                writer.write(String.join(",", Arrays.asList(line)));
                writer.newLine();
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        log.info(csvFilename);
    }
}
