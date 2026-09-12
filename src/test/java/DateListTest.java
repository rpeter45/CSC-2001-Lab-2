import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateListTest {
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
