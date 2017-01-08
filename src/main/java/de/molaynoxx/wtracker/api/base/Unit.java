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

}
