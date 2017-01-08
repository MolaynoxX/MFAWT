package de.molaynoxx.wtracker.api.config;

/**
 * Class for ConfigValues which are Numbers
 * @param <T>
 */
public class NumberConfigValue<T extends Number> extends ConfigValue<T> {

    public NumberConfigValue(T value) {
        super(value);
    }

}
