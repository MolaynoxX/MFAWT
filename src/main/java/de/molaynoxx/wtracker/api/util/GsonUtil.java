package de.molaynoxx.wtracker.api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.molaynoxx.wtracker.api.config.ConfigValue;
import de.molaynoxx.wtracker.api.config.ConfigValueAdapter;
import de.molaynoxx.wtracker.api.config.NumberConfigValue;
import de.molaynoxx.wtracker.api.config.StringConfigValue;

/**
 * Provides the Gson instance with registered custom (de-)serializers
 */
public class GsonUtil {

    public static final GsonUtil instance = new GsonUtil();
    public final Gson gson;

    private GsonUtil() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ConfigValue.class, new ConfigValueAdapter());
        gsonBuilder.registerTypeAdapter(StringConfigValue.class, new ConfigValueAdapter());
        gsonBuilder.registerTypeAdapter(NumberConfigValue.class, new ConfigValueAdapter());
        this.gson = gsonBuilder.create();
    }

}
