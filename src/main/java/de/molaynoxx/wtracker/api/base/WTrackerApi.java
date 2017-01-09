package de.molaynoxx.wtracker.api.base;

import de.molaynoxx.wtracker.api.config.Config;
import de.molaynoxx.wtracker.api.container.StorableContainer;

import java.io.File;
import java.io.IOException;

/**
 * Main class for the API
 * Holds the configuration, user profile, exercises and workouts
 */
public class WTrackerApi {

    private final Config config;
    private final StorableContainer<Workout> workouts;
    private final StorableContainer<Exercise> exercises;
    private final StorableContainer<UserProfile> profiles;

    /**
     * Creates a new WTrackerApi instance with the specific Config
     * @param config specific Config instance to be used
     */
    public WTrackerApi(Config config) {
        this.config = config;
        this.workouts = new StorableContainer<>(config, Workout.getPathBuilder(), Workout.class);
        this.exercises = new StorableContainer<>(config, Exercise.getPathBuilder(), Exercise.class);
        this.profiles = new StorableContainer<>(config, UserProfile.getPathBuilder(), UserProfile.class);
    }

    /**
     * Creates a new WTrackerApi instance with the default Config location ("config.json")
     * @throws IOException gets thrown if the Config can not be created/accessed in its default location
     */
    public WTrackerApi() throws IOException {
        this(new Config(new File("config.json")));
    }

    /**
     * Returns a StorableContainer instance containing all configured Workout instances
     * @return StorableContainer instance containing all configured Workout instances
     */
    public StorableContainer<Workout> getWorkouts() {
        return workouts;
    }

    /**
     * Returns a StorableContainer instance containing all configured Exercise instances
     * @return StorableContainer instance containing all configured Exercise instances
     */
    public StorableContainer<Exercise> getExercises() {
        return exercises;
    }

    /**
     * Returns a StorableContainer instance containing all configured UserProfile instances
     * @return StorableContainer instance containing all configured UserProfile instances
     */
    public StorableContainer<UserProfile> getProfiles() {
        return profiles;
    }

    /**
     * Loads all stored data (workouts, exercises, profiles) from the hard drive
     */
    public void loadFromDisk() throws IOException {
        workouts.loadFromDisk();
        exercises.loadFromDisk();
        profiles.loadFromDisk();
    }

    /**
     * Writes all data (workouts, exercises, profiles) to the hard drive
     */
    public void writeToDisk() throws IOException {
        workouts.writeToDisk();
        exercises.writeToDisk();
        profiles.writeToDisk();
    }

}
