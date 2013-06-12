package de.twenty11.skysail.server.ext.notes.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpUtils {

    private HttpClient httpclient = new DefaultHttpClient();

    public String get(String link) throws IOException, ClientProtocolException {
        HttpGet httpget = new HttpGet(link);
        HttpResponse response = httpclient.execute(httpget);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

}
