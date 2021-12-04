package org.sonatype.cs.metrics.reports;

import org.sonatype.cs.metrics.service.MapToCsv;

import java.util.HashMap;

public class FirewallSummary implements MapToCsv {

    @Override
    public String[] getLine(HashMap<String, Object> map) {

        String repositoryCount = (String) map.get("repositoryCount");
        String quarantineEnabledRepositoryCount = (String) map.get("quarantineEnabledRepositoryCount");
        String quarantineEnabled = (String) map.get("quarantineEnabled");
        String totalComponentCount = (String) map.get("totalComponentCount");
        String quarantinedComponentCount = (String) map.get("quarantinedComponentCount");

        String[] line = {repositoryCount, quarantineEnabledRepositoryCount, quarantineEnabled, totalComponentCount, quarantinedComponentCount};

        return line;
    }
}
