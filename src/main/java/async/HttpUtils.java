package async;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

class HttpUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private static HttpsURLConnection openConnection(String route) throws IOException {
        URL url = new URL(route);
        return (HttpsURLConnection) url.openConnection();
    }

    static JsonNode get(String route) throws IOException, HttpNoSuccessException {
        HttpsURLConnection connection = openConnection(route);
        int responseCode = connection.getResponseCode();

        JsonNode response = objectMapper.readTree(connection.getInputStream());

        if (responseCode != 200) {
            throw new HttpNoSuccessException("Response code != 200");
        } else {
            return response;
        }
    }
}
