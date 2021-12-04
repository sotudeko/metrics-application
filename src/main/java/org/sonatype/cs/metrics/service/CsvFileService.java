package org.sonatype.cs.metrics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CsvFileService {
    private static final Logger log = LoggerFactory.getLogger(CsvFileService.class);

    public void makeFile(MapToCsv aoc, Object obj, String csvfile, String[] header) {

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
