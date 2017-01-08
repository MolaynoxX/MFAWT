package de.molaynoxx.mfawt.api.base;

/**
 * Class describing an exercise (not any data concerning any actually performed exercise)
 */
public class Exercise {

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

    /**
     * Returns the name of the exercise
     * @return the name of the exercise
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the exercise
     * @param name new name of the exercise
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

}
