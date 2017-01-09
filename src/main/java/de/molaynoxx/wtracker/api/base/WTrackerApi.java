package de.molaynoxx.wtracker.api.base;

import de.molaynoxx.wtracker.api.config.Config;

import java.io.File;
import java.io.IOException;

/**
 * Main class for the API
 * Holds the configuration, user profile, exercises and workouts
 */
public class WTrackerApi {

    private final Config config;

    /**
     * Creates a new WTrackerApi instance with the specific Config
     * @param config specific Config instance to be used
     */
    public WTrackerApi(Config config) {
        this.config = config;
    }

    /**
     * Creates a new WTrackerApi instance with the default Config location ("config.json")
     * @throws IOException gets thrown if the Config can not be created/accessed in its default location
     */
    public WTrackerApi() throws IOException {
        this(new Config(new File("config.json")));
    }

}
