package org.sonatype.cs.metrics;

import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.cs.metrics.domain.ApplicationEvaluations;
import org.sonatype.cs.metrics.service.ApiObjectToCsv;
import org.sonatype.cs.metrics.service.ApplicationEvaluation;
import org.sonatype.cs.metrics.service.NexusIQApiService3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MetricsApplication implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(MetricsApplication.class);

	@Autowired
	private NexusIQApiService3 nexusIQApiService3;

	@Autowired
	private ApplicationEvaluations applicationEvaluations;

	public static void main(String[] args) {
		SpringApplication.run(MetricsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Running MetricsApplication");

		//applicationEvaluations.makeCsvFile();
		//ApiObjectToCsv aoc = new ApplicationEvaluation();
		nexusIQApiService3.getData("/reports/applications", "array", new ApplicationEvaluation());


		

	}

}
