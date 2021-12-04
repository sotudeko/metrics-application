package org.sonatype.cs.metrics.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.cs.metrics.service.MapToCsv;

import java.util.HashMap;

public class Organizations implements MapToCsv {
    private static final Logger log = LoggerFactory.getLogger(Organizations.class);

    @Override
    public String[] getLine(HashMap<String, Object> map) {

        String id = (String) map.get("id");
        String oname = (String) map.get("name");

        String[] line = {id, oname};

        log.info("org-> " + id + ":" + oname);

        return line;
    }
}
