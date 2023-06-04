import java.net.*;
import java.io.*;
import org.json.*;

public class PhotoFetcher {
    private String apiKey = "NKbUVv3lJFO3RFcKOIS675vWJtsV2Ndn4s3INpJI";

    //random comment

    public PhotoFetcher(String apiKey) {
        this.apiKey = apiKey;
    }

    public JSONObject fetchPhotoDetails(String photoId) throws IOException, JSONException {
        String apiUrl = "https://api.nli.org.il/openlibrary/photo/" + photoId + "?api_key=" + this.apiKey;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse;
        } else {
            throw new IOException("API request failed. Response Code: " + responseCode);
        }
    }
}
