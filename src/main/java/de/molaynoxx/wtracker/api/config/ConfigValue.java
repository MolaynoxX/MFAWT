package de.molaynoxx.wtracker.api.config;

/**
 * Contains a configuration value identified by a String as key
 * @param <T> Type of the value
 */
public abstract class ConfigValue<T> {

    private final Class clazz;
    private T value;

    /**
     * Creates a new configuration option
     * @param value value belonging to the key
     */
    public ConfigValue(T value) {
        this.value = value;
        this.clazz = value.getClass();
    }

    /**
     * Returns the value of the configuration option
     * @return value of the configuration option
     */
    public T getValue() {
        return value;
    }

    /**
     * Changes the value of the configuration option
     * @param value new value for the configuration option
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Returns class of the value
     * @return class of the value
     */
    public Class getClazz() {
        return clazz;
    }

}
