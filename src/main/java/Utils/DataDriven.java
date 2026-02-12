package Utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class DataDriven {
    public static String jsonReader(String key) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/TestData.json")));
            JSONObject json = new JSONObject(content);
            return json.getString(key);
        } catch (Exception e) {
            System.out.println("Error reading JSON: " + e.getMessage());
            return null;
        }
    }
}
