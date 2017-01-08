package de.molaynoxx.wtracker.api.exceptions;

public class ConfigValueInvalidTypeException extends RuntimeException {

    /**
     * Exception for when types of a configuration option are not matching
     * @param key key of the configuration option
     * @param c1 class of the new value
     * @param c2 class of the old value
     */
    public ConfigValueInvalidTypeException(String key, Class c1, Class c2) {
        super("Requested config value '" + key + "' is instance of '" + c1.getName() + "' but was expected to be instance of '" + c2.getName() + "'");
    }

}
