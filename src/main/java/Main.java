public class Main {
    public static void main(String[] args) {
        Date d1 = new Date(2025, 1, 1);
        Date d2 = new Date(2026, 9, 11);
        Date d3 = new Date(2024, 1, 1);

        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);

        System.out.println("tomorrow(d2) = " + Date.tomorrow(d2));
        System.out.println("dayOfYear(d2) = " + Date.dayOfYear(d2));
        System.out.println("comesBefore(d3, d1) = " + Date.comesBefore(d3, d1));

        DateInterval dI1 = new DateInterval(d3, d1);
        DateInterval dI2 = new DateInterval(d1, d2);

        System.out.println(DateInterval.dateIntervalDays(dI1));
        System.out.println(DateInterval.dateOverlap(dI1, dI2));

        DateInterval dI3 = DateInterval.dateIntervalIntersect(dI1, dI2);
        System.out.println(DateInterval.dateIntervalDays(dI3));

    }
}
