package de.molaynoxx.wtracker.tests.api.container;

import de.molaynoxx.wtracker.api.base.*;
import de.molaynoxx.wtracker.api.config.Config;
import de.molaynoxx.wtracker.api.container.StorableContainer;
import de.molaynoxx.wtracker.api.util.Units;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class StorableContainerTest {

    @Test
    public void testStorableContainerSerializationFull() throws IOException {
        try {
            Config cfg = createDummyConfig();
            StorableContainer<Exercise> scExer = new StorableContainer<>(cfg, Exercise.getPathBuilder(), Exercise.class);
            StorableContainer<Workout> scWork = new StorableContainer<>(cfg, Workout.getPathBuilder(), Workout.class);
            StorableContainer<UserProfile> scUser = new StorableContainer<>(cfg, UserProfile.getPathBuilder(), UserProfile.class);

            Exercise exerciseDummy = new Exercise("Dummy Exercise", Units.KILOGRAM);
            scExer.getElements().add(exerciseDummy);

            Workout workoutDummy1 = new Workout("Dummy Workout");
            scWork.getElements().add(workoutDummy1);
            Workout workoutDummy2 = new Workout("Dummy Workout Light");
            scWork.getElements().add(workoutDummy2);

            ExerciseSet exerciseSet1 = new ExerciseSet(exerciseDummy);
            exerciseSet1.getRepetitions().add(5);
            exerciseSet1.getRepetitions().add(5);
            exerciseSet1.getRepetitions().add(5);
            workoutDummy1.getExercises().add(exerciseSet1);

            ExerciseSet exerciseSet2 = new ExerciseSet(exerciseDummy);
            exerciseSet2.getRepetitions().add(3);
            exerciseSet2.getRepetitions().add(3);
            workoutDummy2.getExercises().add(exerciseSet2);
            workoutDummy2.getExercises().add(exerciseSet1);

            UserProfile userProfile = new UserProfile("Dummy User");
            scUser.getElements().add(userProfile);

            ArrayList<PerformedWorkout> performedWorkouts = new ArrayList<>();
            performedWorkouts.add(new PerformedWorkout(workoutDummy1, new Date(1483995830000L)));
            performedWorkouts.add(new PerformedWorkout(workoutDummy2, new Date(1000L)));
            performedWorkouts.add(new PerformedWorkout(workoutDummy2, new Date(10000L)));

            performedWorkouts.forEach(userProfile::addPerformedWorkout);

            scExer.writeToDisk();
            scWork.writeToDisk();
            scUser.writeToDisk();

            scExer.loadFromDisk();
            scWork.loadFromDisk();
            scUser.loadFromDisk();

            assertThat(scExer.getElements().size(), is(1));
            assertThat(scWork.getElements().size(), is(2));
            assertThat(scUser.getElements().size(), is(1));

            Exercise[] ex = new Exercise[1];
            Workout[] wo = new Workout[2];
            UserProfile[] up = new UserProfile[1];

            scExer.getElements().toArray(ex);
            scWork.getElements().toArray(wo);
            scUser.getElements().toArray(up);

            assertThat(ex[0].getName(), is("Dummy Exercise"));
            assertThat(ex[0].getUnit(), is(Units.KILOGRAM));
            assertThat(up[0].getName(), is("Dummy User"));
            assertThat(up[0].getPerformedWorkouts().size(), is(3));

            // Checks if PerformedWorkouts get deserialized correctly
            for(PerformedWorkout pfw : up[0].getPerformedWorkouts()) {
                performedWorkouts.remove(pfw);
            }
            assertThat(performedWorkouts.size(), is(0));

            assertThat(ex[0], is(exerciseDummy));
            assertThat(wo[0], either(is(workoutDummy1)).or(is(workoutDummy2)));
            assertThat(wo[1], either(is(workoutDummy1)).or(is(workoutDummy2)));
            assertThat(up[0], is(userProfile));
        } finally {
            cleanupTests();
        }
    }

    @Test
    public void testStorableContainerSerializationSimple() throws IOException {
        try {
            Config cfg = createDummyConfig();
            StorableContainer<Exercise> scExer = new StorableContainer<>(cfg, Exercise.getPathBuilder(), Exercise.class);
            StorableContainer<Workout> scWork = new StorableContainer<>(cfg, Workout.getPathBuilder(), Workout.class);
            StorableContainer<UserProfile> scUser = new StorableContainer<>(cfg, UserProfile.getPathBuilder(), UserProfile.class);

            Exercise e = new Exercise("Dummy Exercise", Units.KILOGRAM);
            scExer.getElements().add(e);

            Workout w = new Workout("Dummy Workout");
            scWork.getElements().add(w);

            UserProfile u = new UserProfile("Dummy User");
            scUser.getElements().add(u);

            scExer.writeToDisk();
            scWork.writeToDisk();
            scUser.writeToDisk();

            scExer.loadFromDisk();
            scWork.loadFromDisk();
            scUser.loadFromDisk();

            assertThat(scExer.getElements().size(), is(1));
            assertThat(scWork.getElements().size(), is(1));
            assertThat(scUser.getElements().size(), is(1));

            Exercise[] ex = new Exercise[1];
            Workout[] wo = new Workout[1];
            UserProfile[] up = new UserProfile[1];

            scExer.getElements().toArray(ex);
            scWork.getElements().toArray(wo);
            scUser.getElements().toArray(up);

            assertThat(ex[0].getName(), is("Dummy Exercise"));
            assertThat(wo[0].getName(), is("Dummy Workout"));
            assertThat(up[0].getName(), is("Dummy User"));
        } finally {
            cleanupTests();
        }
    }

    @Test
    public void testPathBuilders() {
        assertThat(Exercise.getPathBuilder().getSubPath(), notNullValue());
        assertThat(Workout.getPathBuilder().getSubPath(), notNullValue());
        assertThat(UserProfile.getPathBuilder().getSubPath(), notNullValue());
    }

    @Test
    public void testStorableContainerPathGeneration() throws IOException {
        try {
            Config cfg = createDummyConfig();
            StorableContainer<Exercise> scExer = new StorableContainer<>(cfg, Exercise.getPathBuilder(), Exercise.class);
            StorableContainer<Workout> scWork = new StorableContainer<>(cfg, Workout.getPathBuilder(), Workout.class);
            StorableContainer<UserProfile> scUser = new StorableContainer<>(cfg, UserProfile.getPathBuilder(), UserProfile.class);

            Exercise e = new Exercise("Dummy Exercise", Units.KILOGRAM);
            scExer.getElements().add(e);
            scExer.removeElement(e);

            Workout w = new Workout("Dummy Workout");
            scWork.getElements().add(w);
            scWork.removeElement(w);

            UserProfile u = new UserProfile("Dummy User");
            scUser.getElements().add(u);
            scUser.removeElement(u);
        } finally {
            cleanupTests();
        }
    }

    private Config createDummyConfig() throws IOException {
        File cfgFile = new File("config.json");
        Config cfg = new Config(cfgFile);
        cfg.setConfiguration("basePath", "test/");
        return cfg;
    }

    private void cleanupTests() throws IOException {
        File cfgFile = new File("config.json");
        cfgFile.delete();

        File dataDir = new File("test/");
        if(dataDir.exists()) delete(dataDir);
    }

    private void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles())
                delete(c);
        }
        if (!f.delete())
            throw new FileNotFoundException("Failed to delete file: " + f);
    }

}
