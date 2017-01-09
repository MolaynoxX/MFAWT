package de.molaynoxx.wtracker.api.container;

import de.molaynoxx.wtracker.api.config.Config;
import de.molaynoxx.wtracker.api.storage.Storable;
import de.molaynoxx.wtracker.api.storage.StorablePathBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for storage and handling of Elements extending Storable, handles all writing and deleting from hard drive
 * @param <T> Class extending Storable to be stored using this instance
 */
public class StorableContainer<T extends Storable> {

    private final HashSet<T> store = new HashSet<>();
    private final Class<T> type;
    private final Config config;

    /**
     * Returns a new StorableContainer instance for the specified Class type
     * @param config Configuration for the WTracker API (used for things like basePath)
     * @param type Class type of the stored elements
     */
    public StorableContainer(Config config, Class<T> type) {
        this.config = config;
        this.type = type;
    }

    /**
     * Returns a Set of the stored elements
     * To remove elements the removeElements() method should be used to ensure deletion of the Storable instance from the hard drive
     * @return Set of the stored elements
     */
    public Set<T> getElements() {
        return store;
    }

    /**
     * Removes an element from the Set and hard drive
     * @param element element to be removed
     */
    public void removeElement(T element) throws IOException {
        store.remove(element);
        File storedFile = getPathForElement(element);
        if(storedFile.exists()) {
            if(!storedFile.delete()) throw new IOException("Could not delete " + storedFile.getAbsolutePath());
        }
    }

    @SuppressWarnings("unchecked")
    private File getPathForElement(T element) {
        try {
            Method m = type.getDeclaredMethod("getPathBuilder");
            StorablePathBuilder<T> spb = (StorablePathBuilder<T>) m.invoke(null);
            return spb.buildPath(config.getConfiguration("basePath", String.class), element);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Could not get StorablePathBuilder for " + type.getName(), e);
        }
    }

    /**
     * Writes all the Storable instance to the disk using gson
     */
    public void writeToDisk() {

    }

    /**
     * Loads all the Storable instances from the disk using gson
     */
    public void loadFromDisk() {

    }

}
