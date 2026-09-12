public class DateList {
    // Implementation of a Linked List, mainly practice and so that I can reuse this code for the project, didn't use most of it
    // head, size variables
    Node head;
    int size;

    // constructor
    public static class Node {

        SimpleDate data;
        Node next;

        Node(SimpleDate d) {
            data = d;
            next = null;
        }
    }

    // add method, adds a node with set data to a specific point in the dateList
    public void add(SimpleDate data, int pos) {
        Node newNode = new Node(data);

        if (pos == 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        Node current = head;
        for (int i = 0; current != null && i < pos - 1; i++) {
            current = current.next;
        }

        if (current == null) {
            throw new IllegalArgumentException("Position is out of bounds!");
        }

        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    // getter method, returns the SimpleDate at the position specified
    public SimpleDate get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IllegalArgumentException("Position is out of bounds");
        }

        Node current = head;

        for (int i = 0; i < pos; i++) {
            current = current.next;
        }

        return current.data;
    }

    // remove method, removes the node at a specific position
    public SimpleDate remove(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IllegalArgumentException("Position is out of bounds");
        }

        SimpleDate removed;
        if (pos == 0) {
            removed = head.data;
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < pos - 1; i++) {
                current = current.next;
            }
            removed = current.next.data;
            current.next = current.next.next;
        }

        size--;
        return removed;
    }

    // set method, changes the data of the node at a specific position
    public void set(int pos, SimpleDate data) {
        if (pos < 0 || pos >= size) {
            throw new IllegalArgumentException("Position is out of bounds");
        }

        Node current = head;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        current.data = data;
    }

    // Returns the size of the linked list (tracked through add method)
    public int size() {
        return size;
    }

    // Returns true if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the name of the input list
    public static int listLen(DateList list) {
        return list.size();
    }

    // Returns the earliest date in a DateList by iterating through and comparing to the earliest found, or throws if empty
    public static SimpleDate minDate(DateList list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        SimpleDate min = list.head.data;
        Node current = list.head.next;
        while (current != null) {
            if (SimpleDate.comesBefore(current.data, min)) {
                min = current.data;
            }
            current = current.next;
        }
        return min;
    }

    // Returns the lastest date in a DateList by iterating through and comparing to the latest found, or throws if empty
    public static SimpleDate maxDate(DateList list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        SimpleDate max = list.head.data;
        Node current = list.head.next;
        while (current != null) {
            if (SimpleDate.comesBefore(max, current.data)) {
                max = current.data;
            }
            current = current.next;
        }
        return max;
    }

    // Returns a DateInterval stretching from the earliest to latest date of an input list, or throws if empty
    public static DateInterval dateCover(DateList list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        return new DateInterval(minDate(list), maxDate(list));
    }

    /*
    Creates a new DateList to return as an output, then iterates through the input list applying SimpleDate.tomorrow to
    each node, then adding it to the result list
     */
    public static DateList allTomorrows(DateList list) {
        DateList result = new DateList();

        Node current = list.head;
        Node tail = null;
        while (current != null) {
            Node newNode = new Node(SimpleDate.tomorrow(current.data));
            if (tail == null) {
                result.head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
            current = current.next;
        }
        result.size = list.size;
        return result;
    }

    // Adds a SimpleDate to the end of an input DateList
    public static DateList addToEnd(DateList list, SimpleDate d1) {
        Node newNode = new Node(d1);

        if (list.head == null) {
            list.head = newNode;
        } else {
            Node current = list.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        list.size++;
        return list;
    }

    // Append method adds one DateList to the end of another
    public static DateList append(DateList l1, DateList l2) {
        DateList result = new DateList();

        // Iterates through input list with current
        Node current = l1.head;
        // Tracks the last thing added to result with pointer
        Node pointer = null;
        while (current != null) {
            // Creates the node to be added to result
            Node newNode = new Node(current.data);
            if (pointer == null) {
                // Sets the head of the result on the first run
                result.head = newNode;
            } else {
                // Otherwise adds the new node such that the previous is pointing at it
                pointer.next = newNode;
            }
            // Iterates pointer and current
            pointer = newNode;
            current = current.next;
        }

        // If the first list is empty, sets the head of result, otherwise simply links the second list to result
        if (pointer == null) {
            result.head = l2.head;
        } else {
            pointer.next = l2.head;
        }

        result.size = l1.size + l2.size;
        return result;
    }
}
