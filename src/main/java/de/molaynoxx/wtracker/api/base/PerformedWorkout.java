package de.molaynoxx.wtracker.api.base;

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

}
