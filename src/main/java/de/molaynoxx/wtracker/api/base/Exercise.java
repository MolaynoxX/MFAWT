package de.molaynoxx.wtracker.api.base;

import de.molaynoxx.wtracker.api.storage.Storable;
import de.molaynoxx.wtracker.api.storage.StorablePathBuilder;

/**
 * Class describing an exercise (not any data concerning any actually performed exercise)
 */
public class Exercise extends Storable {

    private static final StorablePathBuilder<Exercise> spb = new StorablePathBuilder<>("Exercises/");

    /**
     * needs to be a valid filename (no invalid characters)
     */
    private String name;
    private Unit unit;

    /**
     * Creates a new Exercise instance
     * @param name name of the exercise
     * @param unit unit of data describing the exercise (i.e. kilometer when running)
     */
    public Exercise(String name, Unit unit) {
        this.name = name;
        this.unit = unit;
    }

    public static StorablePathBuilder getPathBuilder() {
        return spb;
    }

    /**
     * Returns the name of the exercise
     * @return the name of the exercise
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the exercise
     * @param name new name of the exercise, needs to be a valid filename (no invalid characters)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the Unit instance corresponding to this exercise
     * @return the Unit instance corresponding to this exercise
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the Unit of this exercise
     * @param unit new unit for this exercise
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String getFileName() {
        return name + ".json";
    }

    /**
     * Overridden equals() to avoid exercises with duplicate names
     * @param obj Object to compare to
     * @return boolean if the compared Object is the same (or has the same name [case-insensitive])
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Exercise) {
            return ((Exercise) obj).getName().equalsIgnoreCase(name);
        }
        return super.equals(obj);
    }

    /**
     * Overridden hashcode() to avoid exercises with duplicate names
     * @return hashcode() of the name of the exercise
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
