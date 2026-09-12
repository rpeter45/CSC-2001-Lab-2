import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRecords {

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


    @Test
    void dateIntervalConstructor() {
        DateInterval interval = new DateInterval(new Date(2024, 1, 1), new Date(2024, 1, 10));
        assertEquals(new Date(2024, 1, 1), interval.start());
        assertEquals(new Date(2024, 1, 10), interval.end());

        assertThrows(IllegalArgumentException.class,
                () -> new DateInterval(new Date(2024, 1, 10), new Date(2024, 1, 1)));
    }

    @Test
    void dateIntervalDays() {
        assertEquals(10, DateInterval.dateIntervalDays(
                new DateInterval(new Date(2024, 1, 1), new Date(2024, 1, 11))));
    }

    @Test
    void dateOverlap() {
        DateInterval overlap1 = new DateInterval(new Date(2024, 1, 1), new Date(2024, 1, 10));
        DateInterval overlap2 = new DateInterval(new Date(2024, 1, 5), new Date(2024, 1, 15));
        assertTrue(DateInterval.dateOverlap(overlap1, overlap2));

        DateInterval separate1 = new DateInterval(new Date(2024, 1, 1), new Date(2024, 1, 5));
        DateInterval separate2 = new DateInterval(new Date(2024, 1, 10), new Date(2024, 1, 15));
        assertFalse(DateInterval.dateOverlap(separate1, separate2));
    }

    @Test
    void dateIntervalIntersect() {
        DateInterval i1 = new DateInterval(new Date(2024, 1, 1), new Date(2024, 1, 10));
        DateInterval i2 = new DateInterval(new Date(2024, 1, 5), new Date(2024, 1, 15));
        DateInterval overlap = DateInterval.dateIntervalIntersect(i1, i2);
        assertNotNull(overlap);
        assertEquals(new Date(2024, 1, 5), overlap.start());
        assertEquals(new Date(2024, 1, 10), overlap.end());
    }

    @Test
    void maybeDateIntervalIntersect() {
        DateInterval i1 = new DateInterval(new Date(2024, 1, 1), new Date(2024, 1, 10));
        DateInterval i2 = new DateInterval(new Date(2024, 1, 5), new Date(2024, 1, 15));

        assertNull(DateInterval.maybeDateIntervalIntersect(null, i1));
        assertNull(DateInterval.maybeDateIntervalIntersect(i1, null));
        assertNull(DateInterval.maybeDateIntervalIntersect(null, null));
        assertNotNull(DateInterval.maybeDateIntervalIntersect(i1, i2));
    }


    @Test
    void listLen() {
        DateList list = new DateList();
        assertEquals(0, DateList.listLen(list));
        DateList.addToEnd(list, new Date(2024, 1, 1));
        DateList.addToEnd(list, new Date(2024, 2, 1));
        DateList.addToEnd(list, new Date(2024, 3, 1));
        assertEquals(3, DateList.listLen(list));
    }

    @Test
    void minDate() {
        DateList list = new DateList();
        DateList.addToEnd(list, new Date(2024, 1, 1));
        DateList.addToEnd(list, new Date(2024, 2, 1));
        DateList.addToEnd(list, new Date(2024, 3, 1));
        assertEquals(new Date(2024, 1, 1), DateList.minDate(list));

        assertThrows(IllegalArgumentException.class, () -> DateList.minDate(new DateList()));
    }

    @Test
    void maxDate() {
        DateList list = new DateList();
        DateList.addToEnd(list, new Date(2024, 1, 1));
        DateList.addToEnd(list, new Date(2024, 2, 1));
        DateList.addToEnd(list, new Date(2024, 3, 1));
        assertEquals(new Date(2024, 3, 1), DateList.maxDate(list));

        assertThrows(IllegalArgumentException.class, () -> DateList.maxDate(new DateList()));
    }

    @Test
    void dateCover() {
        DateList list = new DateList();
        DateList.addToEnd(list, new Date(2024, 1, 1));
        DateList.addToEnd(list, new Date(2024, 2, 1));
        DateList.addToEnd(list, new Date(2024, 3, 1));
        DateInterval cover = DateList.dateCover(list);
        assertEquals(new Date(2024, 1, 1), cover.start());
        assertEquals(new Date(2024, 3, 1), cover.end());

        assertThrows(IllegalArgumentException.class, () -> DateList.dateCover(new DateList()));
    }

    @Test
    void allTomorrows() {
        DateList list = new DateList();
        DateList.addToEnd(list, new Date(2024, 1, 1));
        DateList.addToEnd(list, new Date(2024, 2, 1));
        DateList.addToEnd(list, new Date(2024, 3, 1));
        DateList result = DateList.allTomorrows(list);
        assertEquals(new Date(2024, 1, 2), result.head.data);
        assertEquals(new Date(2024, 2, 2), result.head.next.data);
        assertEquals(new Date(2024, 3, 2), result.head.next.next.data);
    }

    @Test
    void addToEnd() {
        DateList list = new DateList();
        DateList.addToEnd(list, new Date(2024, 1, 1));
        DateList.addToEnd(list, new Date(2024, 2, 1));
        DateList.addToEnd(list, new Date(2024, 3, 1));
        assertEquals(3, DateList.listLen(list));
        assertEquals(new Date(2024, 1, 1), list.head.data);
        assertEquals(new Date(2024, 2, 1), list.head.next.data);
        assertEquals(new Date(2024, 3, 1), list.head.next.next.data);
    }

    @Test
    void append() {
        DateList l1 = new DateList();
        DateList.addToEnd(l1, new Date(2024, 1, 1));
        DateList.addToEnd(l1, new Date(2024, 1, 2));

        DateList l2 = new DateList();
        DateList.addToEnd(l2, new Date(2024, 2, 1));
        DateList.addToEnd(l2, new Date(2024, 2, 2));

        DateList result = DateList.append(l1, l2);
        assertEquals(4, DateList.listLen(result));
        assertEquals(new Date(2024, 1, 1), result.head.data);
        assertEquals(new Date(2024, 1, 2), result.head.next.data);
        assertEquals(new Date(2024, 2, 1), result.head.next.next.data);
        assertEquals(new Date(2024, 2, 2), result.head.next.next.next.data);
    }
}
