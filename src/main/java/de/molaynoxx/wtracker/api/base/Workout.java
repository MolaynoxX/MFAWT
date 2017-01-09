package de.molaynoxx.wtracker.api.base;

import de.molaynoxx.wtracker.api.storage.Storable;
import de.molaynoxx.wtracker.api.storage.StorablePathBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * A workout can contain many different ExerciseSets
 */
public class Workout extends Storable {

    private static final StorablePathBuilder<Workout> spb = new StorablePathBuilder<>("Workouts/");

    private final ArrayList<ExerciseSet> exercises = new ArrayList<>();
    /**
     * needs to be a valid filename (no invalid characters)
     */
    private String name;

    /**
     * Creates a new workout
     * @param name name of the new workout, needs to be needs to be a valid filename (no invalid characters)
     */
    public Workout(String name) {
        this.name = name;
    }

    public static StorablePathBuilder<Workout> getPathBuilder() {
        return spb;
    }

    /**
     * Returns the name of the workout
     * @return name of the workout
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the workout
     * @param name name of the workout
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a list of the ExerciseSets contained within this workout
     * @return a list of the ExerciseSets contained within this workout
     */
    public ArrayList<ExerciseSet> getExercises() {
        return exercises;
    }

    @Override
    public String getFileName() {
        return name + ".json";
    }

    /**
     * Overridden hashCode() to avoid issues from deserialization into new identical instances
     * @return hashCode() of the content of the Workout
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(name);
        for(ExerciseSet e : exercises) {
            hcb.append(e);
        }
        return hcb.toHashCode();
    }

    /**
     * Overridden equals() to avoid issues from deserialization into new identical instances
     * @return equals() of the content of the Workout if obj is instanceof Workout
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Workout) {
            if(!name.equals(((Workout) obj).name)) return false;

            HashSet<ExerciseSet> exerciseSets = new HashSet<>();
            exerciseSets.addAll(exercises);
            exerciseSets.removeAll(((Workout) obj).getExercises());

            // If all exercises in this instance are contained in obj's instance size == 0
            if(exerciseSets.size() != 0) return false;

            // If all exercises in this obj are contained in this instance size == 0
            // Therefore both Workout instances are equal
            exerciseSets.addAll(((Workout) obj).getExercises());
            exerciseSets.removeAll(exercises);

            return exerciseSets.size() == 0;
        }
        return super.equals(obj);
    }

}
