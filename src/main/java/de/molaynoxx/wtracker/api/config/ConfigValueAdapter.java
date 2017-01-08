package de.molaynoxx.wtracker.api.config;

import com.google.gson.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

/**
 * Handles (de-)serialization of ConfigValue, StringConfigValue and NumberConfigValue to ensure correct types
 */
public class ConfigValueAdapter implements JsonSerializer<ConfigValue>, JsonDeserializer<ConfigValue> {

    @Override
    public JsonElement serialize(ConfigValue src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("class", src.getClazz().getName());
        obj.add("value", context.serialize(src.getValue()));
        return obj;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ConfigValue deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Class c;
        try {
            c = Class.forName(json.getAsJsonObject().get("class").getAsString());
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Could not parse class type from json", e);
        }
        JsonElement value = json.getAsJsonObject().get("value");

        if(value.isJsonPrimitive()) {
            if(c == String.class) {
                return new StringConfigValue(value.getAsString());
            } else {
                try {
                    Number castedNumber = (Number) c.cast(c.getConstructor(String.class).newInstance(value.getAsString()));
                    return new NumberConfigValue(castedNumber);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
                    throw new JsonParseException("Could not cast number from value", e);
                }
            }
        } else {
            throw new JsonParseException("Expected primitive value for configuration");
        }
    }

}
