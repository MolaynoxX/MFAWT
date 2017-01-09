package de.molaynoxx.wtracker.api.base;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;

/**
 * An ExerciseSet can contain many of the same exercise with differing amounts of repetitions
 */
public class ExerciseSet {

    private final ArrayList<Integer> repetitions = new ArrayList<>();
    private Exercise exercise;
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

    /**
     * Overridden hashCode() to avoid issues from deserialization into new identical instances
     * @return hashCode() of the content of the ExerciseSet
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(exercise);
        hcb.append(unitData);
        repetitions.forEach(hcb::append);
        return hcb.toHashCode();
    }

    /**
     * Overridden equals() to avoid issues from deserialization into new identical instances
     * @return equals() of the content of the ExerciseSet if obj is instanceof ExerciseSet
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ExerciseSet) {
            ExerciseSet eObj = (ExerciseSet) obj;

            if(unitData != eObj.unitData) return false;
            if(!exercise.equals(eObj.exercise)) return false;
            if(repetitions.size() != eObj.repetitions.size()) return false;

            for(int i = 0; i < repetitions.size(); i++) {
                if(!repetitions.get(i).equals(eObj.repetitions.get(i))) return false;
            }
            return true;
        }
        return super.equals(obj);
    }

}
