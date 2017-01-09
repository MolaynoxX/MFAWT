package de.molaynoxx.wtracker.api.base;

import de.molaynoxx.wtracker.api.storage.Storable;
import de.molaynoxx.wtracker.api.storage.StorablePathBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;

/**
 * Profile of the user containing all his performed workouts as well as the scheduled ones
 */
public class UserProfile extends Storable {

    private static final StorablePathBuilder<UserProfile> spb = new StorablePathBuilder<>("Profiles/");

    private ArrayList<PerformedWorkout> performedWorkouts = new ArrayList<>();
    private String name;

    public UserProfile(String name) {
        this.name = name;
    }

    public static StorablePathBuilder<UserProfile> getPathBuilder() {
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

    /**
     * Overridden hashCode() to avoid issues from deserialization into new identical instances
     * @return hashCode() of the content of the UserProfile
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(name);
        performedWorkouts.forEach(hcb::append);
        return hcb.toHashCode();
    }

    /**
     * Overridden equals() to avoid issues from deserialization into new identical instances
     * @return equals() of the content of the UserProfile if obj is instanceof UserProfile
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UserProfile) {
            UserProfile uObj = (UserProfile) obj;

            if(!name.equals(uObj.name)) return false;
            if(performedWorkouts.size() != uObj.performedWorkouts.size()) return false;

            for(PerformedWorkout pfw : performedWorkouts) {
                if(!uObj.performedWorkouts.contains(pfw)) return false;
            }
            for(PerformedWorkout pfw : uObj.performedWorkouts) {
                if(!performedWorkouts.contains(pfw)) return false;
            }
            return true;
        }
        return super.equals(obj);
    }

}
