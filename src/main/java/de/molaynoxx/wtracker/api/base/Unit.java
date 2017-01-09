package de.molaynoxx.wtracker.api.base;

/**
 * Class made to contain information about the unit of data from an exercise (i.e. 1km running)
 */
public class Unit {

    private final String unitName;
    private final String abbreviation;

    /**
     * Creates a new Unit instance
     * @param unitName full name of the unit
     * @param abbreviation shorted name of the unit
     */
    public Unit(String unitName, String abbreviation) {
        this.unitName = unitName;
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the full name of the unit
     * @return full name of the unit
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * Returns the abbreviation of the unit
     * @return abbreviation of the unit
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Overridden hashcode() to avoid issues from deserialization into new identical instances
     * @return hashcode() of the name of the unit
     */
    @Override
    public int hashCode() {
        return unitName.hashCode();
    }

    /**
     * Overridden equals() to avoid issues from deserialization into new identical instances
     * @return equals() of the name of the unit if obj is instanceof Unit
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Unit) {
            return ((Unit) obj).unitName.equals(unitName);
        }
        return super.equals(obj);
    }

}
