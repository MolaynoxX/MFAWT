package de.molaynoxx.wtracker.api.base;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Class extending workout to contain data about when the workout was performed
 */
public class PerformedWorkout extends Workout {

    private Date datePerformed;

    /**
     * Creates a PerformedWorkout from an existing workout and the date when it was performed
     * @param workout existing workout which contains the exercises which were performed
     * @param datePerformed date when the workout was performed
     */
    public PerformedWorkout(Workout workout, Date datePerformed) {
        super(workout.getName());
        workout.getExercises().forEach(this.getExercises()::add);
        this.datePerformed = datePerformed;
    }

    /**
     * Returns the date when the workout was performed
     * @return the date when the workout was performed
     */
    public Date getDatePerformed() {
        return datePerformed;
    }

    /**
     * Sets the date when the workout was performed
     * @param datePerformed new date when the workout was performed
     */
    public void setDatePerformed(Date datePerformed) {
        this.datePerformed = datePerformed;
    }

    /**
     * Overridden hashCode() to avoid issues from deserialization into new identical instances
     * @return hashCode() of the content of the PerformedWorkout
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(getName());
        for(ExerciseSet e : getExercises()) {
            hcb.append(e);
        }
        hcb.append(datePerformed);
        return hcb.toHashCode();
    }

    /**
     * Overridden equals() to avoid issues from deserialization into new identical instances
     * @return equals() of the content of the PerformedWorkout if obj is instanceof PerformedWorkout
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PerformedWorkout) {
            return super.equals(obj) && datePerformed.equals(((PerformedWorkout) obj).datePerformed);
        }
        return super.equals(obj);
    }

}
