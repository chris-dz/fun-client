import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequest {
    private final List<String> dataList;

    public HttpRequest() {
        dataList = new ArrayList<>();
    }

    public String getRequestDataAsString(String url) {
        String json = "";
        for (String line : requestData(url)) {
            if (json.isEmpty()) {
                json += line;
            } else {
                json += System.lineSeparator() + line;
            }
        }
        return json;
    }

    public List<String> requestData(String url) {
        try {
            URL url2 = new URL(url);
            HttpURLConnection connection = (HttpsURLConnection) url2.openConnection();
            connection.connect();

            readFromConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

	public String send(String url, String entry) {
        String res = null;
        try {
            URL url2 = new URL(url);
            HttpURLConnection connection = (HttpsURLConnection) url2.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            connection.connect();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            bw.write(URLEncoder.encode("message=" + entry, "utf-8"));
            bw.flush();
            bw.close();

            res = readStringFromConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return res;
    }
    
    private void readFromConnection(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        dataList.clear();
        while ((line = reader.readLine()) != null) {
             dataList.add(line);
        }
        reader.close();
        connection.disconnect();
    }

    private String readStringFromConnection(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String res = null;
        String line;
        dataList.clear();
        while ((line = reader.readLine()) != null) {
             if (res == null) {
                 res = line;
             } else {
                 res += System.lineSeparator() + line;
             }
        }
        reader.close();
        connection.disconnect();
        return res;
    }
}