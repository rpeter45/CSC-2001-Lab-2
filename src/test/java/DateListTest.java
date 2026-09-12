import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateListTest {
    @Test
    void listLen() {
        DateList list = new DateList();
        assertEquals(0, DateList.listLen(list));
        DateList.addToEnd(list, new SimpleDate(2024, 1, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 2, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 3, 1));
        assertEquals(3, DateList.listLen(list));
    }

    @Test
    void minDate() {
        DateList list = new DateList();
        DateList.addToEnd(list, new SimpleDate(2024, 3, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 1, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 2, 1));
        assertEquals(new SimpleDate(2024, 1, 1), DateList.minDate(list));

        assertThrows(IllegalArgumentException.class, () -> DateList.minDate(new DateList()));
    }

    @Test
    void maxDate() {
        DateList list = new DateList();
        DateList.addToEnd(list, new SimpleDate(2024, 1, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 2, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 3, 1));
        assertEquals(new SimpleDate(2024, 3, 1), DateList.maxDate(list));

        assertThrows(IllegalArgumentException.class, () -> DateList.maxDate(new DateList()));
    }

    @Test
    void dateCover() {
        DateList list = new DateList();
        DateList.addToEnd(list, new SimpleDate(2024, 1, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 2, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 3, 1));
        DateInterval cover = DateList.dateCover(list);
        assertEquals(new SimpleDate(2024, 1, 1), cover.start());
        assertEquals(new SimpleDate(2024, 3, 1), cover.end());

        assertThrows(IllegalArgumentException.class, () -> DateList.dateCover(new DateList()));
    }

    @Test
    void allTomorrows() {
        DateList list = new DateList();
        DateList.addToEnd(list, new SimpleDate(2024, 1, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 2, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 3, 1));
        DateList result = DateList.allTomorrows(list);
        assertEquals(new SimpleDate(2024, 1, 2), result.head.data);
        assertEquals(new SimpleDate(2024, 2, 2), result.head.next.data);
        assertEquals(new SimpleDate(2024, 3, 2), result.head.next.next.data);

        DateList emptyResult = DateList.allTomorrows(new DateList());
        assertTrue(emptyResult.isEmpty());
    }

    @Test
    void addToEnd() {
        DateList list = new DateList();
        DateList.addToEnd(list, new SimpleDate(2024, 1, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 2, 1));
        DateList.addToEnd(list, new SimpleDate(2024, 3, 1));
        assertEquals(3, DateList.listLen(list));
        assertEquals(new SimpleDate(2024, 1, 1), list.head.data);
        assertEquals(new SimpleDate(2024, 2, 1), list.head.next.data);
        assertEquals(new SimpleDate(2024, 3, 1), list.head.next.next.data);
    }

    @Test
    void append() {
        DateList l1 = new DateList();
        DateList.addToEnd(l1, new SimpleDate(2024, 1, 1));
        DateList.addToEnd(l1, new SimpleDate(2024, 1, 2));

        DateList l2 = new DateList();
        DateList.addToEnd(l2, new SimpleDate(2024, 2, 1));
        DateList.addToEnd(l2, new SimpleDate(2024, 2, 2));

        DateList result = DateList.append(l1, l2);
        assertEquals(4, DateList.listLen(result));
        assertEquals(new SimpleDate(2024, 1, 1), result.head.data);
        assertEquals(new SimpleDate(2024, 1, 2), result.head.next.data);
        assertEquals(new SimpleDate(2024, 2, 1), result.head.next.next.data);
        assertEquals(new SimpleDate(2024, 2, 2), result.head.next.next.next.data);

        DateList emptyFirst = DateList.append(new DateList(), l2);
        assertEquals(2, DateList.listLen(emptyFirst));
        assertEquals(new SimpleDate(2024, 2, 1), emptyFirst.head.data);
    }

    @Test
    void baseTest() {
        DateList list = new DateList();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());

        list.add(new SimpleDate(2024, 1, 1), 0);
        list.add(new SimpleDate(2024, 3, 1), 1);
        list.add(new SimpleDate(2024, 2, 1), 1);

        assertEquals(new SimpleDate(2024, 1, 1), list.get(0));
        assertEquals(new SimpleDate(2024, 2, 1), list.get(1));
        assertEquals(new SimpleDate(2024, 3, 1), list.get(2));

        list.set(1, new SimpleDate(2024, 2, 15));
        assertEquals(new SimpleDate(2024, 2, 15), list.get(1));

        assertEquals(new SimpleDate(2024, 2, 15), list.remove(1));
        assertEquals(2, list.size());
        assertEquals(new SimpleDate(2024, 3, 1), list.get(1));

        assertEquals(new SimpleDate(2024, 1, 1), list.remove(0));
        assertEquals(1, list.size());

        assertThrows(IllegalArgumentException.class, () -> list.add(new SimpleDate(2024, 4, 1), 5));
        assertThrows(IllegalArgumentException.class, () -> list.get(-1));
        assertThrows(IllegalArgumentException.class, () -> list.get(5));
        assertThrows(IllegalArgumentException.class, () -> list.remove(-1));
        assertThrows(IllegalArgumentException.class, () -> list.set(5, new SimpleDate(2024, 5, 1)));
    }
}
