public record DateInterval(SimpleDate start, SimpleDate end) {
    // Constructor to throw an error
    public DateInterval {
        if (!SimpleDate.comesBefore(start, end)) {
            throw new IllegalArgumentException("End date is before start date");
        }
    }

    // Returns the difference in dayOfYear between the start and end date
    public static int dateIntervalDays(DateInterval i) {
        return (i.end.year() - i.start.year()) * 365 + SimpleDate.dayOfYear(i.end()) - SimpleDate.dayOfYear(i.start());
    }

    // Returns true if the start of the second interval occurs before the end of the first, or vice versa
    public static boolean dateOverlap(DateInterval i1, DateInterval i2) {
        return SimpleDate.comesBefore(i1.start(), i2.end()) && SimpleDate.comesBefore(i2.start(), i1.end());
    }

    // Returns a DateInterval including all the dates included in both input DateIntervals, or null if there is no overlap
    public static DateInterval dateIntervalIntersect(DateInterval i1, DateInterval i2) {
        SimpleDate start;
        SimpleDate end;
        if (SimpleDate.comesBefore(i1.start(), i2.start())) {
            start = i2.start();
        } else {
            start = i1.start();
        }
        if (SimpleDate.comesBefore(i1.end(), i2.end())) {
            end = i1.end();
        } else {
            end = i2.end();
        }

        if (SimpleDate.comesBefore(start, end)) {
            return new DateInterval(start, end);
        }

        return null;
    }

    // dateIntervalIntersect if the inputs are potentially null, returns null if either input is null otherwise
    // returns the dateIntervalIntersect of the inputs
    public static DateInterval maybeDateIntervalIntersect(DateInterval i1, DateInterval i2) {
        if (i1 == null || i2 == null) {
            return null;
        }

        return DateInterval.dateIntervalIntersect(i1, i2);
    }
}
