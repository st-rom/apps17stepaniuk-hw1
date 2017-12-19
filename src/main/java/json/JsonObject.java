package json;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final List<JsonPair> jsonPairs;
    public JsonObject(JsonPair... jsonPairs) {
        this.jsonPairs = new ArrayList<JsonPair>(Arrays.asList(jsonPairs));
    }

    @Override
    public String toJson() {
        String jsonStr = "";
        Iterator<JsonPair> jsonIterator = jsonPairs.iterator();
        while (jsonIterator.hasNext()) {
            JsonPair json = jsonIterator.next();
            jsonStr += json.toJson();
            if (jsonIterator.hasNext())
                jsonStr += ", ";
        }
        return "{" + jsonStr + "}";
    }

    public void add(JsonPair jsonPair) {
        jsonPairs.add(jsonPair);
    }

    public Json find(String name) {
        Iterator<JsonPair> jsonIterator = jsonPairs.iterator();
        while (jsonIterator.hasNext()) {
            JsonPair json = jsonIterator.next();
            if (json.getKey().equals(name)) {
                return json.getValue();
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject jo = new JsonObject();
        ArrayList<String> all_names = new ArrayList<String>(Arrays.asList(names));
        Iterator<String> jsonIterator = all_names.iterator();
        while (jsonIterator.hasNext()) {
            String name = jsonIterator.next();
            Json found = find(name);
            if (find(name) != null) {
                jo.add(new JsonPair(name, found));
            }
        }
        return jo;
    }
}
