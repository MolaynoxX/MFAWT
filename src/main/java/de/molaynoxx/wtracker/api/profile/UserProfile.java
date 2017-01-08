package de.molaynoxx.wtracker.api.profile;

import de.molaynoxx.wtracker.api.base.PerformedWorkout;
import de.molaynoxx.wtracker.api.storage.Storable;

import java.util.ArrayList;

/**
 * Profile of the user containing all his performed workouts as well as the scheduled ones
 */
public class UserProfile implements Storable {

    private ArrayList<PerformedWorkout> performedWorkouts = new ArrayList<>();

    public UserProfile() {}

    @Override
    public String getSubPath() {
        return "";
    }

    @Override
    public String getFileName() {
        return "profile.json";
    }

}
