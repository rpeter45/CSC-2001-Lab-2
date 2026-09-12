import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateIntervalTest {
    @Test
    void dateIntervalConstructor() {
        DateInterval interval = new DateInterval(new SimpleDate(2024, 1, 1), new SimpleDate(2024, 1, 10));
        assertEquals(new SimpleDate(2024, 1, 1), interval.start());
        assertEquals(new SimpleDate(2024, 1, 10), interval.end());

        assertThrows(IllegalArgumentException.class, () -> new DateInterval(new SimpleDate(2024, 1, 10), new SimpleDate(2024, 1, 1)));
    }

    @Test
    void dateIntervalDays() {
        assertEquals(10, DateInterval.dateIntervalDays(new DateInterval(new SimpleDate(2024, 1, 1), new SimpleDate(2024, 1, 11))));
    }

    @Test
    void dateOverlap() {
        DateInterval overlap1 = new DateInterval(new SimpleDate(2024, 1, 1), new SimpleDate(2024, 1, 10));
        DateInterval overlap2 = new DateInterval(new SimpleDate(2024, 1, 5), new SimpleDate(2024, 1, 15));
        assertTrue(DateInterval.dateOverlap(overlap1, overlap2));

        DateInterval separate1 = new DateInterval(new SimpleDate(2024, 1, 1), new SimpleDate(2024, 1, 5));
        DateInterval separate2 = new DateInterval(new SimpleDate(2024, 1, 10), new SimpleDate(2024, 1, 15));
        assertFalse(DateInterval.dateOverlap(separate1, separate2));
    }

    @Test
    void dateIntervalIntersect() {
        DateInterval i1 = new DateInterval(new SimpleDate(2024, 1, 1), new SimpleDate(2024, 1, 10));
        DateInterval i2 = new DateInterval(new SimpleDate(2024, 1, 5), new SimpleDate(2024, 1, 15));
        DateInterval overlap = DateInterval.dateIntervalIntersect(i1, i2);
        assertNotNull(overlap);
        assertEquals(new SimpleDate(2024, 1, 5), overlap.start());
        assertEquals(new SimpleDate(2024, 1, 10), overlap.end());

        DateInterval reverse = DateInterval.dateIntervalIntersect(i2, i1);
        assertNotNull(reverse);
        assertEquals(new SimpleDate(2024, 1, 5), reverse.start());
        assertEquals(new SimpleDate(2024, 1, 10), reverse.end());

        DateInterval separate1 = new DateInterval(new SimpleDate(2024, 1, 1), new SimpleDate(2024, 1, 5));
        DateInterval separate2 = new DateInterval(new SimpleDate(2024, 1, 10), new SimpleDate(2024, 1, 15));
        assertNull(DateInterval.dateIntervalIntersect(separate1, separate2));
    }

    @Test
    void maybeDateIntervalIntersect() {
        DateInterval i1 = new DateInterval(new SimpleDate(2024, 1, 1), new SimpleDate(2024, 1, 10));
        DateInterval i2 = new DateInterval(new SimpleDate(2024, 1, 5), new SimpleDate(2024, 1, 15));

        assertNull(DateInterval.maybeDateIntervalIntersect(null, i1));
        assertNull(DateInterval.maybeDateIntervalIntersect(i1, null));
        assertNull(DateInterval.maybeDateIntervalIntersect(null, null));
        assertNotNull(DateInterval.maybeDateIntervalIntersect(i1, i2));
    }
}
