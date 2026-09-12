

public record Date(int year, int month, int day) {
    // Constructor
    public Date {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        if (day < 1 || day > daysInMonth(month)) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
    }

    // Sets the number of days in the month based on which month it is
    public static int daysInMonth(int month) {
        int days;
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12: days = 31; break;
            case 4, 6, 9, 11: days = 30; break;
            case 2: days = 28; break;
            default: throw new IllegalArgumentException("Invalid month: " + month);
        }
        return days;
    }

    // Returns the date immediately following the input, rolls over months/years
    public static Date tomorrow (Date date) {
        if (date.day() < daysInMonth(date.month())) {
            return new Date(date.year(), date.month(), date.day() + 1);
        } else if (date.month() < 12) {
            return new Date(date.year(), date.month() + 1, 1);
        } else {
            return new Date(date.year() + 1, 1 ,1);
        }
    }

    // Counts the number of 24 hour periods since noon on the beginning of the year (effectively just the day of the year - 1)
    public static int dayOfYear(Date date) {
        int result = 0;
        for (int i = 1; i < date.month(); i++) {
            result += daysInMonth(i);
        }
        return result + date.day() - 1;
    }

    // Returns true if the first date occurred before or on the second date
    public static boolean comesBefore(Date d1, Date d2) {
        if (d1.year() != d2.year()) {
            return d1.year() < d2.year();
        } else if (d1.month() != d2.month()) {
            return d1.month() < d2.month();
        } else {
            return d1.day() <= d2.day();
        }
    }
}