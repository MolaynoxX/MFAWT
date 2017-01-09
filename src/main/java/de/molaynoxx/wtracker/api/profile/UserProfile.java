package de.molaynoxx.wtracker.api.profile;

import de.molaynoxx.wtracker.api.base.PerformedWorkout;
import de.molaynoxx.wtracker.api.storage.Storable;
import de.molaynoxx.wtracker.api.storage.StorablePathBuilder;

import java.util.ArrayList;

/**
 * Profile of the user containing all his performed workouts as well as the scheduled ones
 */
public class UserProfile extends Storable {

    private static final StorablePathBuilder<UserProfile> spb = new StorablePathBuilder<>("Profiles/");

    private ArrayList<PerformedWorkout> performedWorkouts = new ArrayList<>();
    private String name;

    public UserProfile(String name) {}

    public static StorablePathBuilder getPathBuilder() {
        return spb;
    }

    @Override
    public String getFileName() {
        return name + ".json";
    }

    /**
     * Returns the name of the user/profile
     * @return name of the user/profile
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of the user/profile
     * @param name new name for user/profile
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a List with all the PerformedWorkouts
     * @return List with all the PerformedWorkouts
     */
    public ArrayList<PerformedWorkout> getPerformedWorkouts() {
        return performedWorkouts;
    }

    /**
     * Adds a PerformedWorkout to the list for this user
     * @param w PerformedWorkout to be added to the list
     */
    public void addPerformedWorkout(PerformedWorkout w) {
        performedWorkouts.add(w);
    }

    /**
     * Removes a PerformedWorkout to the list for this user
     * @param w PerformedWorkout to be removed from the list
     */
    public void removePerformedWorkout(PerformedWorkout w) {
        performedWorkouts.remove(w);
    }

}
