package src.data_access;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class TiingoAPIDataAccessObject {
    private final String API_KEY = "bf408e3ee6e0a153be501f96b468efecc351c902";
    private String ticker;
    public TiingoAPIDataAccessObject(String ticker) {
        this.ticker = ticker;

    }

    private HashMap<String, Integer> getAPIData(String ticker) {
        // Create a new HashMap
        HashMap<String, Integer> map = new HashMap<>();
        try {

            String urlString = "https://api.tiingo.com/tiingo/daily/" + this.ticker + "/prices";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Add request headers
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Token " + API_KEY);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the response
                System.out.println(response.toString());
            } else {
                System.out.println("GET request not worked. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add some key-value pairs to the map
        map.put("Apple", 3);
        map.put("Banana", 5);
        map.put("Orange", 2);

        // Return the map
        return map;
    }


}
