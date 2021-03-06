package Clases;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CustomDeserializer<T> implements JsonDeserializer<List<T>> {

    private static Map<String, Class> map = new TreeMap<String, Class>();

    static {
        map.put("Usuario", Usuario.class);
        map.put("Vuelo", Vuelo.class);
        map.put("Avion", Avion.class);
        map.put("Gold", Gold.class);
        map.put("Silver", Silver.class);
        map.put("Bronze", Bronze.class);

    }

    public List<T> deserialize(JsonElement json, Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException {

        ArrayList<T> list = new ArrayList<T>();
        JsonArray ja = json.getAsJsonArray();

        for (JsonElement je : ja) {

            String type = je.getAsJsonObject().get("isA").getAsString();
            Class c = map.get(type);
            if (c == null)
                throw new RuntimeException("Unknow class: " + type);
            list.add(context.deserialize(je, c));
        }

        return list;

    }

}