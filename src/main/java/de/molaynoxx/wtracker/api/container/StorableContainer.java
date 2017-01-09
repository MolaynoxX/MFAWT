package de.molaynoxx.wtracker.api.container;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import de.molaynoxx.wtracker.api.config.Config;
import de.molaynoxx.wtracker.api.storage.Storable;
import de.molaynoxx.wtracker.api.storage.StorablePathBuilder;
import de.molaynoxx.wtracker.api.util.GsonUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for storage and handling of Elements extending Storable, handles all writing and deleting from hard drive
 * @param <T> Class extending Storable to be stored using this instance
 */
public class StorableContainer<T extends Storable> {

    private final HashSet<T> storage = new HashSet<>();
    private final StorablePathBuilder<T> pathBuilder;
    private final Class<T> type;
    private final Config config;

    /**
     * Returns a new StorableContainer instance for the specified Class type
     * @param config Configuration for the WTracker API (used for things like basePath)
     * @param pathBuilder Path builder for the stored elements
     */
    public StorableContainer(Config config, StorablePathBuilder<T> pathBuilder, Class<T> type) {
        this.config = config;
        this.pathBuilder = pathBuilder;
        this.type = type;
    }

    /**
     * Returns a Set of the stored elements
     * To remove elements the removeElements() method should be used to ensure deletion of the Storable instance from the hard drive
     * @return Set of the stored elements
     */
    public Set<T> getElements() {
        return storage;
    }

    /**
     * Removes an element from the Set and hard drive
     * @param element element to be removed
     */
    public void removeElement(T element) throws IOException {
        storage.remove(element);
        File storedFile = getPathForElement(element);
        if(storedFile.exists()) {
            if(!storedFile.delete()) throw new IOException("Could not delete " + storedFile.getAbsolutePath());
        }
    }

    private File getPathForElement(T element) {
        return pathBuilder.buildPath(config.getConfiguration("basePath", String.class), element);
    }

    /**
     * Writes all the Storable instances to the disk using gson
     */
    public void writeToDisk() throws IOException {
        for(T element : storage) {
            File f = getPathForElement(element);
            try (FileWriter fw = new FileWriter(f)) {
                GsonUtil.instance.gson.toJson(element, fw);
            }
        }
    }

    /**
     * Loads all the Storable instances from the disk using gson
     */
    public void loadFromDisk() throws IOException {
        File base = new File(config.getConfiguration("basePath", String.class), pathBuilder.getSubPath());
        for(File fileElement : base.listFiles()) {
            T element = GsonUtil.instance.gson.fromJson(Files.toString(fileElement, Charsets.UTF_8), type);
            storage.add(element);
        }
    }

}
