package de.molaynoxx.wtracker.api.storage;

/**
 * Interface for classes that are intended to be stored on the hard drive through gson
 */
public interface Storable {

    /**
     * Intended to return part of the path to save profile, workouts, exercise to the hard drive (i.e. folder name: "Exercises/")
     * @return String containing end part of the path to save the affected object to
     */
    String getSubPath();

    /**
     * Intended to return the filename which gets used to write the affected Object to the hard drive
     * @return filename which gets used to write the affected Object to the hard drive
     */
    String getFileName();

}
