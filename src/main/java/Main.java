public class Main {
    public static void main(String[] args) {
        SimpleDate d1 = new SimpleDate(2025, 1, 1);
        SimpleDate d2 = new SimpleDate(2026, 9, 11);
        SimpleDate d3 = new SimpleDate(2024, 1, 1);

        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);

        System.out.println("tomorrow(d2) = " + SimpleDate.tomorrow(d2));
        System.out.println("dayOfYear(d2) = " + SimpleDate.dayOfYear(d2));
        System.out.println("comesBefore(d3, d1) = " + SimpleDate.comesBefore(d3, d1));

        DateInterval dI1 = new DateInterval(d3, d1);
        DateInterval dI2 = new DateInterval(d1, d2);

        System.out.println(DateInterval.dateIntervalDays(dI1));
        System.out.println(DateInterval.dateOverlap(dI1, dI2));

        DateInterval dI3 = DateInterval.dateIntervalIntersect(dI1, dI2);
        System.out.println(DateInterval.dateIntervalDays(dI3));
    }
}
