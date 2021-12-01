package org.sonatype.cs.metrics.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Service
public class NexusIQApiService3 {
    private static final Logger log = LoggerFactory.getLogger(NexusIQApiService3.class);

    @Value("${iq.url}")
    private String iqUrl;

    @Value("${iq.user}")
    private String iqUser;

    @Value("${iq.passwd}")
    private String iqPasswd;

    @Value("${iq.api}")
    private String iqApi;
    private Object obj;

    public Object getData(String endPoint, String objectType, ApiObjectToCsv aoc) throws IOException {
        String urlString = iqUrl + iqApi + endPoint;
        log.info("Fetching data from " + urlString);

        URL url = new URL(urlString);

        String authString = iqUser + ":" + iqPasswd;
        byte[] encodedAuth = Base64.encodeBase64(authString.getBytes(StandardCharsets.ISO_8859_1));
        String authStringEnc = new String(encodedAuth);

        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);

        try {
            InputStream is = urlConnection.getInputStream();
            JsonReader reader = Json.createReader(is);

            switch (objectType){
                case "object": obj = reader.readObject(); break;
                case "array": obj = reader.readArray(); break;
                default:
                    throw new IllegalStateException("Unexpected value: " + objectType);
            }

            aoc.makeCsvFile(obj);
            reader.close();


        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(obj);
        return obj;
    }
}


