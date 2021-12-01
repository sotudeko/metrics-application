package org.sonatype.cs.metrics.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.cs.metrics.service.ICsvFileService;
import org.sonatype.cs.metrics.service.NexusIQApiService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
public class ApplicationEvaluations implements ICsvFileService {
    private static final Logger log = LoggerFactory.getLogger(ApplicationEvaluations.class);

    @Autowired
    private NexusIQApiService2 nexusIQApiService2;

    @Override
    public void makeCsvFile() {

        String[] header = {"Stage", "Application", "Evaluation Date"};
        String csvFilename = "applicationevaluations.csv";

        try {

            BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFilename));
            writer.write(String.join(",", header));
            writer.newLine();

            JsonArray obj = (JsonArray) nexusIQApiService2.getData("/reports/applications", "array");

            for (JsonObject result : obj.getValuesAs(JsonObject.class)) {

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
