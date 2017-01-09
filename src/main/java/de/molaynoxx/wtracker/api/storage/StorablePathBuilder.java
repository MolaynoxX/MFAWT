package de.molaynoxx.wtracker.api.storage;

import java.io.File;

/**
 * Helper class to determine paths and filenames on the hard drive for Storable objects
 * @param <T> Class extending Storable for which this class contains information
 */
public class StorablePathBuilder<T extends Storable> {

    private final String subPath;

    /**
     * Creates a new helper class instance with the specified sub-path
     * @param subPath sub-path for the specific class of elements this class describes (i.e. a sub folder like "folder/")
     */
    public StorablePathBuilder(String subPath) {
        this.subPath = subPath;
    }

    /**
     * Returns the sub-path for the specific class of elements this class describes
     * @return sub-path for the specific class of elements this class describes (i.e. a sub folder like "folder/")
     */
    public String getSubPath() {
        return this.subPath;
    }

    /**
     * Returns a File object containing the full path to which file should be used for the storable instance
     * @param basePath First part of the path on the hard drive (i.e. AppData)
     * @param storable instance of a storable object that is supposed to be stored on the hard drive
     * @return absolute File of the path for the element
     */
    public File buildPath(String basePath, T storable) {
        return new File(basePath, subPath + storable.getFileName()).getAbsoluteFile();
    }

}
