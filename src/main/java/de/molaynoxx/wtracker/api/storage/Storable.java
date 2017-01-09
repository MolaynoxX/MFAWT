package de.molaynoxx.wtracker.api.storage;

/**
 * Interface for classes that are intended to be stored on the hard drive through gson
 */
public abstract class Storable {

    /**
     * Needs to be overridden in order for Storable objects to be able to be written to the hard drive by the StorableContainer class
     * @return StorablePathBuilder instance specifically for this class
     */
    public static StorablePathBuilder getPathBuilder() {
        throw new IllegalStateException("No StorablePathBuilder has been implemented for this class.");
    }

    /**
     * Needs to return a valid filename which gets used to store the instance to the hard drive
     * @return valid filename as String
     */
    public abstract String getFileName();

}
