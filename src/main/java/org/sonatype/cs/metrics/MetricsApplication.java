package org.sonatype.cs.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.cs.metrics.reports.ApplicationEvaluations;
import org.sonatype.cs.metrics.reports.FirewallSummary;
import org.sonatype.cs.metrics.reports.Organizations;
import org.sonatype.cs.metrics.service.DataStreamService;
import org.sonatype.cs.metrics.util.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MetricsApplication implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(MetricsApplication.class);

//	@Autowired
//	private NexusIQApiObjectService nexusIQApiObjectService;
//
//	@Autowired
//	private NexusIQAPIArrayService nexusIQAPIArrayService;
//
//	@Autowired
//	private NexusIQApiStreamService nexusIQApiStreamService;
//
//	@Autowired
//	private NexusIQApiStreamService nexusIQApiStreamService;

	@Autowired
	private DataStreamService dataStreamService;

	public static void main(String[] args) {
		SpringApplication.run(MetricsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Running MetricsApplication");

		//dataStreamService.getData("/firewall/releaseQuarantine/summary", new FirewallSummary(), DataInfo.releaseQuarantineSummarCsvFile, DataInfo.releaseQuarantineSummarFileHeader);
		dataStreamService.getData("/reports/applications", new ApplicationEvaluations(), DataInfo.applicationEvaluationsCsvFile, DataInfo.applicationEvaluationsFileHeader, false);
		dataStreamService.getData("/organizations", new Organizations(), DataInfo.organizationsCsvFile, DataInfo.organizationsFileHeader, true);

		//nexusIQApiService.getData("/reports/components/waivers", new ComponentWaivers(), "array", DataInfo.componentWaiversCsvFile, DataInfo.componentWaiversHeader);

	}
}
