package org.sonatype.cs.metrics.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.cs.metrics.service.ICsvFileService;
import org.sonatype.cs.metrics.service.NexusIQApiService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;

public static class ApplicationEvaluations implements ICsvFileService {
    private static final Logger log = LoggerFactory.getLogger(ApplicationEvaluations.class);

    @Autowired
    private NexusIQApiService nexusIQApiService;

    @Override
    public void makeCsvFile() {

        try (InputStream is = nexusIQApiService.getData("/reports/applications");
             JsonReader rdr = Json.createReader(is)) {

            JsonObject obj = rdr.readObject();
            System.out.println(obj.toString());
            JsonArray results = obj.getJsonArray("");

            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                String stage = result.getString("stage");
                String applicationId = result.getString("applicationId");
                String evaluationDate = result.getString("evaluationDate");

                System.out.println(stage + " -> " + applicationId);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
