package de.molaynoxx.mfawt.tests.api.util;

import de.molaynoxx.mfawt.api.base.PerformedWorkout;
import de.molaynoxx.mfawt.api.base.Workout;
import de.molaynoxx.mfawt.api.util.PerformedWorkoutDateComparator;
import org.junit.Test;

import java.util.Comparator;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WorkoutComparatorTest {

    @Test
    public void testPerformedWorkoutDateComparator() {
        Workout w = new Workout("MyWorkout");

        Date d1 = new Date(0);
        Date d2 = new Date(1483841357000L); // Time when this test was written

        PerformedWorkout p1 = new PerformedWorkout(w, d1);
        PerformedWorkout p2 = new PerformedWorkout(w, d2);
        PerformedWorkout p3 = new PerformedWorkout(w, d2);

        Comparator<PerformedWorkout> comp = new PerformedWorkoutDateComparator();

        assertTrue(comp.compare(p1, p2) < 0);
        assertTrue(comp.compare(p2, p1) > 0);
        assertEquals(0, comp.compare(p2, p3));
    }

}
