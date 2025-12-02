package api;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
// JavaDoc is made by both ChatGPT and Lucas
/**
 * API Utility is made to be able to handle API readings etc more smooth.
 * @author Lucas
 */
public class APIUtility {
    // Fælles HttpClient, så vi ikke laver en ny hver gang
    private static final HttpClient client = HttpClient.newHttpClient();

    // Gson bruges til at lave JSON om til Java-objekter
    private static final Gson gson = new Gson();

    /**
     * Sendes a GET request and makes returns the body
     *
     * @param url Receives a URL
     * @return Returns JSon body
     */
    public static String getJson(String url) {
        try {
            // Byg et GET-request til den givne URL
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Send requestet og få svaret som tekst
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // Returnér selve svaret (body), som er JSON-tekst
            return response.body();
        }
        catch (IOException | InterruptedException e) {
            // Hvis noget går galt, laver vi en fejlbesked
            throw new RuntimeException("Kunne ikke kalde API: " + e.getMessage());
        }
    }

    /**
     * Sendes a GET request and makes a JSON-answer to a Java-object.
     *
     * @param url Receives a URL
     * @return JsonObject with data from JSON
     */
    public static JsonObject getJsonObject(String url) {
        // Først henter vi JSON som tekst
        String jsonText = getJson(url);

        // Så laver vi teksten om til et JsonObject
        return JsonParser.parseString(jsonText).getAsJsonObject();
    }

    /**
     * Sendes a GET request and makes a JSON-answer to a Java-object.
     *
     * @param url   Receives a URL
     * @param type  What kind of object do we want?
     * @param <T>   type of object that returns
     * @return Type T that returns
     */
    public static <T> T getObject(String url, Class<T> type) {
        // Hent JSON som tekst
        String jsonText = getJson(url);

        // Lav JSON-teksten om til et objekt af typen T
        return gson.fromJson(jsonText, type);
    }

    /**
     * Method reives a JSON URL and finds the asked value.
     *
     * @param url   JSON URL
     * @param key   Value
     * @throws RuntimeException if key doesn't exist
     * @return Returns JSON Value as text
     */
    // Henter én value fra JSON ud fra et key-navn
    public static String getJsonValue(String url, String key) {
        // Hent hele JSON-objektet
        JsonObject json = getJsonObject(url);
        // Tjek om key findes, ellers giv en fejl
        if (!json.has(key)) {
            throw new RuntimeException("Key doesn't exist: " + key);
        }

        // Returnér value som String
        return json.get(key).getAsString();
    }
}
