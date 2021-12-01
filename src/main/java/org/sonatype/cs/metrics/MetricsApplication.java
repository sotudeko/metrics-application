package org.sonatype.cs.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.cs.metrics.domain.ApplicationEvaluations;
import org.sonatype.cs.metrics.service.NexusIQApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MetricsApplication implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(MetricsApplication.class);

	@Autowired
	private NexusIQApiService nexusIQApiService3;

	public static void main(String[] args) {
		SpringApplication.run(MetricsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Running MetricsApplication");

		nexusIQApiService3.getData("/reports/applications", new ApplicationEvaluations(), "array");
	}

}
