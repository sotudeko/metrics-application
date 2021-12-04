package org.sonatype.cs.metrics.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.cs.metrics.service.MapToCsv;

import java.util.HashMap;

public class ApplicationEvaluations implements MapToCsv {
    private static final Logger log = LoggerFactory.getLogger(ApplicationEvaluations.class);

//    @Override
//    public void writeCsvFile(BufferedWriter writer, Object obj) {
//        log.info("Writing ApplicationEvaluations report");
//
//        JsonArray results = (JsonArray) obj;
//
//        for (JsonObject result : results.getValuesAs(JsonObject.class)) {
//
//            String stage = result.getString("stage");
//            String applicationId = result.getString("applicationId");
//            String evaluationDate = result.getString("evaluationDate");
//            String reportDataUrl = result.getString("reportDataUrl");
//            String applicationName = reportDataUrl.split("/")[3];
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
//        }
//
//    }

    public String[] getLine(HashMap<String, Object> map) {

        String stage = (String) map.get("stage");
        String applicationId = (String) map.get("applicationId");
        String evaluationDate = (String) map.get("evaluationDate");
        String reportDataUrl = (String) map.get("reportDataUrl");
        String applicationName = reportDataUrl.split("/")[3];

        String[] line = {applicationName, evaluationDate, stage};

        log.info("ae-> " + stage + ":" + applicationName + ":" + evaluationDate);

        return line;

    }
}
