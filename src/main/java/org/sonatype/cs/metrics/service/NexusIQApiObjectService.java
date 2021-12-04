package org.sonatype.cs.metrics.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

@Service
public class NexusIQApiObjectService {
    private static final Logger log = LoggerFactory.getLogger(NexusIQApiObjectService.class);

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


    public JsonObject getData(String endPoint, MapToCsv aoc, String csvfile, String[] header) throws IOException {
        String urlString = iqUrl + iqApi + endPoint;
        log.info("Fetching data from " + urlString);

        URL url = new URL(urlString);

        String authString = iqUser + ":" + iqPasswd;
        byte[] encodedAuth = Base64.encodeBase64(authString.getBytes(StandardCharsets.ISO_8859_1));
        String authStringEnc = new String(encodedAuth);

        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvfile));
        writer.write(String.join(",", header));
        writer.newLine();

        JsonObject obj = null;
        HashMap<String,Object> map = new HashMap();

        try {
            InputStream is = urlConnection.getInputStream();
            JsonReader reader = Json.createReader(is);
            obj = reader.readObject();

            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(obj);
        return obj;
    }
}


