package de.molaynoxx.wtracker.tests.api.container;

import de.molaynoxx.wtracker.api.base.Exercise;
import de.molaynoxx.wtracker.api.base.Workout;
import de.molaynoxx.wtracker.api.config.Config;
import de.molaynoxx.wtracker.api.container.StorableContainer;
import de.molaynoxx.wtracker.api.profile.UserProfile;
import de.molaynoxx.wtracker.api.util.Units;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class StorableContainerTest {

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
