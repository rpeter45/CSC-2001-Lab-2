import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleDateTest {

    @Test
    void daysInMonth() {
        assertEquals(31, Date.daysInMonth(1));
        assertEquals(28, Date.daysInMonth(2));
        assertEquals(30, Date.daysInMonth(4));
        assertThrows(IllegalArgumentException.class, () -> Date.daysInMonth(0));
        assertThrows(IllegalArgumentException.class, () -> Date.daysInMonth(13));
    }

    @Test
    void dateConstructor() {
        Date date = new Date(2024, 3, 15);
        assertEquals(2024, date.year());
        assertEquals(3, date.month());
        assertEquals(15, date.day());
        
        assertThrows(IllegalArgumentException.class, () -> new Date(2024, 1, 32));
        assertThrows(IllegalArgumentException.class, () -> new Date(2024, 2, 29));
    }

    @Test
    void tomorrow() {
        assertEquals(new Date(2024, 3, 16), Date.tomorrow(new Date(2024, 3, 15)));
        assertEquals(new Date(2024, 2, 1), Date.tomorrow(new Date(2024, 1, 31)));
        assertEquals(new Date(2025, 1, 1), Date.tomorrow(new Date(2024, 12, 31)));
    }

    @Test
    void dayOfYear() {
        assertEquals(0, Date.dayOfYear(new Date(2024, 1, 1)));
        assertEquals(31, Date.dayOfYear(new Date(2024, 2, 1)));
        assertEquals(364, Date.dayOfYear(new Date(2024, 12, 31)));
    }

    @Test
    void comesBefore() {
        assertTrue(Date.comesBefore(new Date(2023, 12, 31), new Date(2024, 1, 1)));
        assertTrue(Date.comesBefore(new Date(2024, 1, 31), new Date(2024, 2, 1)));
        assertTrue(Date.comesBefore(new Date(2024, 3, 1), new Date(2024, 3, 2)));
    }
}
