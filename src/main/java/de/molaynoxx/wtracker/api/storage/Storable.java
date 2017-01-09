package de.molaynoxx.wtracker.api.storage;

/**
 * Interface for classes that are intended to be stored on the hard drive through gson
 */
public abstract class Storable {

    /**
     * Needs to return a valid filename which gets used to store the instance to the hard drive
     * @return valid filename as String
     */
    public abstract String getFileName();

}
