package org.sonatype.cs.metrics.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.json.stream.JsonParser.Event.*;

@Service
public class NexusIQAPIStreamService {
    private static final Logger log = LoggerFactory.getLogger(NexusIQAPIStreamService.class);

    @Value("${iq.url}")
    private String iqUrl;

    @Value("${iq.user}")
    private String iqUser;

    @Value("${iq.passwd}")
    private String iqPasswd;

    @Value("${iq.api}")
    private String iqApi;

    @Autowired
    private CsvFileService csvFileService;

    public void getData(String endPoint, SendDataToCsvFile aoc, String csvfile, String[] header) throws IOException {
        String urlString = iqUrl + iqApi + endPoint;
        log.info("Fetching data from " + urlString);

        URL url = new URL(urlString);

        String authString = iqUser + ":" + iqPasswd;
        byte[] encodedAuth = Base64.encodeBase64(authString.getBytes(StandardCharsets.ISO_8859_1));
        String authStringEnc = new String(encodedAuth);

        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);

        try (InputStream is = urlConnection.getInputStream();
             JsonParser parser = Json.createParser(is)) {

            BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvfile));
            writer.write(String.join(",", header));
            writer.newLine();

            Event event = parser.next();  // advance past START_OBJECT
            event = parser.next();

            while (!event.equals(Event.END_OBJECT) && parser.hasNext()) {
                HashMap<String, Object> map = getMap(parser);
                System.out.println(map);
                event = parser.next();
            }

            is.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    /*  Returns the HashMap parsed by the specified parser.
        Called when event.equals(event.START_OBJECT):
    */
    public static HashMap getMap(JsonParser parser) {
        HashMap<String,Object> map = new HashMap();

        Event event = parser.next();  // advance past START_OBJECT
        String key = parser.getString();
        event = parser.next();       // advance past KEY_NAME
        while (!event.equals(Event.END_OBJECT)) {
            if (event.equals(Event.VALUE_STRING)) {
                String value = parser.getString();

                // if key is what we need
                map.put(key, value);
            } else if (event.equals(Event.VALUE_NUMBER)) {
                Integer value = parser.getInt();
                map.put(key, value);
            } else if (event.equals(Event.START_ARRAY)) {
                ArrayList<String> list = getList(parser);
                map.put(key, list);
            }
            event = parser.next();
            if (event.equals(Event.END_OBJECT)) {
                break;
            }
            key = parser.getString();
            event = parser.next();
        }
        return map;
    }

    /*  Returns the ArrayList parsed by the specified parser.
        Called when event.equals(event.START_ARRAY):
    */
    public static ArrayList getList(JsonParser parser) {
        ArrayList list = new ArrayList();
        Event event = parser.next();  // advance past START_ARRAY
        while (!event.equals(Event.END_ARRAY)) {
            if (event.equals(Event.VALUE_STRING)) {
                list.add(parser.getString());
                event = parser.next();
            } else if (event.equals(Event.START_OBJECT)) {
                HashMap<String,Object> map = getMap(parser);
                list.add(map);
                event = parser.next();
            } else if (event.equals(Event.START_ARRAY)) {
                ArrayList subList = getList(parser);   //  recursion
                list.add(subList);
                event = parser.next();
            }
        }
        return list;
    }
}
