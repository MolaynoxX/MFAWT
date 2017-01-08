package de.molaynoxx.mfawt.api.base;

import java.util.ArrayList;

/**
 * An ExerciseSet can contain many of the same exercise with differing amounts of repetitions
 */
public class ExerciseSet {

    private Exercise exercise;
    private ArrayList<Integer> repetitions = new ArrayList<>();
    private double unitData;

    /**
     * Creates a new ExerciseSet based on the given exercise
     * @param exercise the exercise describing this set
     */
    public ExerciseSet(Exercise exercise) {
        this.exercise = exercise;
    }

    /**
     * Returns the exercise describing this set
     * @return the exercise describing this set
     */
    public Exercise getExercise() {
        return exercise;
    }

    /**
     * Sets the exercise describing this set
     * @param exercise the exercise describing this set
     */
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    /**
     * Returns a list with the number of repetitions of this set
     * @return list with the number of repetitions of this set
     */
    public ArrayList<Integer> getRepetitions() {
        return repetitions;
    }

    /**
     * Returns the data of this set according to its unit (i.e. kg when weightlifting)
     * @return the data of this set
     */
    public double getUnitData() {
        return unitData;
    }

    /**
     * Sets the data of this set according to its unit (i.e. kg when weightlifting)
     * @param unitData the data of this set
     */
    public void setUnitData(double unitData) {
        this.unitData = unitData;
    }

}
