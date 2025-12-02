package api;

public class ExampleAPI {
    //Made by ChatGPT to provide an example on usage
    public static void main(String[] args) {

        String url = "https://bored-api.appbrewery.com/random";

        // Example 1: Get raw JSON text
        System.out.println("Raw JSON:");
        String json = APIUtility.getJson(url);
        System.out.println(json);

        // Example 2: Get one value from the JSON
        System.out.println("\nActivity:");
        String activity = APIUtility.getJsonValue(url, "activity");
        System.out.println(activity);

        // Example 3: Get JSON as a JsonObject
        System.out.println("\nMore details:");
        var jsonObj = APIUtility.getJsonObject(url);
        System.out.println("Type: " + jsonObj.get("type").getAsString());
        System.out.println("Participants: " + jsonObj.get("participants").getAsInt());
    }
}
