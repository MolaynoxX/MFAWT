package de.molaynoxx.wtracker.api.config;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.reflect.TypeToken;
import de.molaynoxx.wtracker.api.exceptions.ConfigValueInvalidTypeException;
import de.molaynoxx.wtracker.api.util.GsonUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Contains all configuration for the API itself and the UI later on
 */
public class Config {

    private final File configPath;
    private HashMap<String, ConfigValue<?>> configurationValues = new HashMap<>();

    /**
     * Initializes a new configuration instance
     * Creates needed folders and files if not yet existing
     * @param configFile Path with filename for the configuration file
     * @throws IOException gets thrown when the folder or the configuration file itself can not be created or accessed
     */
    public Config(File configFile) throws IOException {
        configPath = configFile.getAbsoluteFile();
        if(!configPath.getParentFile().exists()) {
            System.out.println();
            if(!configPath.getParentFile().mkdirs()) throw new IOException("Unable to create configuration file directory: " + configPath.getParent());
        }
        if(!configPath.exists()) {
            if(!configPath.createNewFile()) throw new IOException("Unable to create configuration file: " + configPath.getAbsolutePath());
            writeToDisk();
        }
        loadFromDisk();
    }

    /**
     * Clears the current configuration options and loads them from the hard drive
     * @throws IOException gets thrown if the configuration file does not exist or is not accessible
     */
    public void loadFromDisk() throws IOException {
        configurationValues.clear();
        Type type = new TypeToken<HashMap<String, ConfigValue>>(){}.getType();
        configurationValues = GsonUtil.instance.gson.fromJson(Files.toString(configPath, Charsets.UTF_8), type);
        if(configurationValues == null) configurationValues = new HashMap<>();
    }

    /**
     * Writes the current configuration options to the hard drive
     * @throws IOException gets thrown if the configuration file is not accessible
     */
    public void writeToDisk() throws IOException {
        FileWriter fw = new FileWriter(configPath);
        GsonUtil.instance.gson.toJson(configurationValues, fw);
        fw.close();
    }

    /**
     * Returns value of a configuration option specified by its key
     * @param key key specifying the option
     * @param clazz class of the value returned
     * @param <T> type of the value returned
     * @throws ConfigValueInvalidTypeException gets thrown if clazz parameter and Class of the stored value for the key parameter do not match
     * @return value of the configuration option, null if the specified option does not yet exist
     */
    public <T> T getConfiguration(String key, Class<T> clazz) {
        if(!configurationValues.containsKey(key.toLowerCase())) return null;
        Object value = configurationValues.get(key.toLowerCase()).getValue();
        if(clazz.isInstance(value)) {
            return clazz.cast(value);
        } else {
            throw new ConfigValueInvalidTypeException(key, value.getClass(), clazz);
        }
    }

    /**
     * Sets the value of an option, creates the necessary object structure if not yet existing
     * @param key key specifying the option
     * @param value value to be stored for the specified key
     * @param <T> type of the value
     * @throws ConfigValueInvalidTypeException gets thrown if clazz parameter and Class of the stored value for the key parameter do not match
     */
    @SuppressWarnings("unchecked")
    public <T extends Number> void setConfiguration(String key, T value) {
        ConfigValue<T> cfgValue;
        if(!configurationValues.containsKey(key.toLowerCase())) {
            cfgValue = new NumberConfigValue<>(value);
            setConfiguration(key, cfgValue);
        } else {
            ConfigValue cval = configurationValues.get(key.toLowerCase());
            if(cval.getClazz() != value.getClass()) throw new ConfigValueInvalidTypeException(key, cval.getClazz(), value.getClass());
            cfgValue = (ConfigValue<T>) configurationValues.get(key.toLowerCase());
            cfgValue.setValue(value);
        }
    }

    /**
     * Sets the value of an option, creates the necessary object structure if not yet existing
     * @param key key specifying the option
     * @param value value to be stored for the specified key
     */
    @SuppressWarnings("unchecked")
    public void setConfiguration(String key, String value) {
        ConfigValue<String> cfgValue;
        if(!configurationValues.containsKey(key.toLowerCase())) {
            cfgValue = new StringConfigValue(value);
            setConfiguration(key, cfgValue);
        } else {
            ConfigValue cval = configurationValues.get(key.toLowerCase());
            if(cval.getClazz() != value.getClass()) throw new ConfigValueInvalidTypeException(key, cval.getClazz(), value.getClass());
            cfgValue = (ConfigValue<String>) configurationValues.get(key.toLowerCase());
            cfgValue.setValue(value);
        }
    }

    private void setConfiguration(String key, ConfigValue configValue) {
        configurationValues.put(key.toLowerCase(), configValue);
    }

}
