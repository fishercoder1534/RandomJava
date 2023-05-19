package profiler;

public class Main {
    public static void main(final String[] args) {
        long x = 0;
        for (int i = 0; i < 1000000; i++) {
            x += calculate(i);
        }
        System.out.println("x = " + x);
    }

    private static long calculate(final int i) {
        return (long) i * (long) i;
    }
}
