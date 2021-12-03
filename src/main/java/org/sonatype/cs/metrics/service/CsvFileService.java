package org.sonatype.cs.metrics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
public class CsvFileService {
    private static final Logger log = LoggerFactory.getLogger(CsvFileService.class);

    public void makeFile(SendDataToCsvFile aoc, Object obj, String csvfile, String[] header) {

//        try {
//
//            BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvfile));
//            writer.write(String.join(",", header));
//            writer.newLine();
//
//            aoc.writeCsvFile(writer, obj);
//
//            writer.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        log.info(csvfile);
    }
}
