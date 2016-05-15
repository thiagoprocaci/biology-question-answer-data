package com.biology;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 */
public class Crawler {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader((new InputStreamReader(new GZIPInputStream(is), Charset.forName("UTF-8"))));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static void saveJson(JSONObject json, String fileName) throws IOException {
        String baseDir = Crawler.class.getResource("/").getFile();
        System.out.println("Saving " + baseDir + File.separator + fileName);
        FileWriter fstream = new FileWriter(baseDir + File.separator + fileName);
        try {
            fstream.write(json.toString());
        } finally {
            fstream.flush();
            fstream.close();
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        Integer page = 1;
        String baseFileName = "file_";
        boolean hasNext = true;
        while(hasNext) {
            try {
                System.out.println("Downloading page " + page);
                String url = "https://api.stackexchange.com/2.2/questions?key=U4DMV*8nvpm3EOpvf69Rxw((&site=biology&page=" + page + "&pagesize=100&order=desc&sort=activity&filter=9tYmPbCqA7PvG9J4V6eM6OshMURsq";
                JSONObject json = readJsonFromUrl(url);

                //System.out.println(json.toString());
                String fileName = baseFileName + page;
                saveJson(json, fileName);

                hasNext = (Boolean) json.get("has_more");
                page++;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

}
