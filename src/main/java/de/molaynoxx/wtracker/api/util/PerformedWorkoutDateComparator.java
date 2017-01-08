package de.molaynoxx.wtracker.api.util;

import de.molaynoxx.wtracker.api.base.PerformedWorkout;

import java.util.Comparator;

/**
 * Comparator to compare two PerformedWorkout instances by their dates
 */
public class PerformedWorkoutDateComparator implements Comparator<PerformedWorkout> {

    /**
     * Compares two PerformedWorkout instances by the date at which they were performed
     * Returns a negative Integer if the first PerformedWorkout was performed earlier than the second one
     * Returns zero if they were performed at the same time
     * Returns a positive Integer if the first PerformedWorkout was performed after the second one
     * @param o1 First PerformedWorkout
     * @param o2 Second PerformedWorkout
     * @return integer describing the order in which the two workouts were performed
     */
    @Override
    public int compare(PerformedWorkout o1, PerformedWorkout o2) {
        return o1.getDatePerformed().compareTo(o2.getDatePerformed());
    }

}
