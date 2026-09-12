import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleDateTest {

    @Test
    void daysInMonth() {
        assertEquals(31, SimpleDate.daysInMonth(1));
        assertEquals(28, SimpleDate.daysInMonth(2));
        assertEquals(30, SimpleDate.daysInMonth(4));
        assertThrows(IllegalArgumentException.class, () -> SimpleDate.daysInMonth(0));
        assertThrows(IllegalArgumentException.class, () -> SimpleDate.daysInMonth(13));
    }

    @Test
    void dateConstructor() {
        SimpleDate date = new SimpleDate(2024, 3, 15);
        assertEquals(2024, date.year());
        assertEquals(3, date.month());
        assertEquals(15, date.day());

        assertThrows(IllegalArgumentException.class, () -> new SimpleDate(2024, 1, 32));
        assertThrows(IllegalArgumentException.class, () -> new SimpleDate(2024, 2, 29));
    }

    @Test
    void tomorrow() {
        assertEquals(new SimpleDate(2024, 3, 16), SimpleDate.tomorrow(new SimpleDate(2024, 3, 15)));
        assertEquals(new SimpleDate(2024, 2, 1), SimpleDate.tomorrow(new SimpleDate(2024, 1, 31)));
        assertEquals(new SimpleDate(2025, 1, 1), SimpleDate.tomorrow(new SimpleDate(2024, 12, 31)));
    }

    @Test
    void dayOfYear() {
        assertEquals(0, SimpleDate.dayOfYear(new SimpleDate(2024, 1, 1)));
        assertEquals(31, SimpleDate.dayOfYear(new SimpleDate(2024, 2, 1)));
        assertEquals(364, SimpleDate.dayOfYear(new SimpleDate(2024, 12, 31)));
    }

    @Test
    void comesBefore() {
        assertTrue(SimpleDate.comesBefore(new SimpleDate(2023, 12, 31), new SimpleDate(2024, 1, 1)));
        assertTrue(SimpleDate.comesBefore(new SimpleDate(2024, 1, 31), new SimpleDate(2024, 2, 1)));
        assertTrue(SimpleDate.comesBefore(new SimpleDate(2024, 3, 1), new SimpleDate(2024, 3, 2)));
    }
}
