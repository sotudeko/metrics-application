package org.sonatype.cs.metrics;

import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

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
	private NexusIQApiService nexusIQApiService;

	public static void main(String[] args) {
		SpringApplication.run(MetricsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Running MetricsApplication");


//		try (
//				InputStream is = nexusIQApiService.getData("/organizations");
//				JsonReader rdr = Json.createReader(is)) {
//
//				JsonObject obj = rdr.readObject();
//				System.out.println(obj.toString());
//				JsonArray results = obj.getJsonArray("organizations");
//
//				for (JsonObject result : results.getValuesAs(JsonObject.class)) {
//					//System.out.print(result.getJsonObject("from").getString("name"));
//					//System.out.print(": ");
//					//System.out.println(result.getString("message", ""));
//					//System.out.println("-----------");
//
//					String id = result.getString("id");
//					String oname = result.getString("name");
//					System.out.println(id + " -> " + oname);
//				}
//			}
		

	}

}
