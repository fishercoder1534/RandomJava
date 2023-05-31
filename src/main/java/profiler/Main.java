package profiler;

public class Main {
    /**
     * From this post: https://stackoverflow.com/a/9415368/4117496
     * To run this: go to terminal and use this command:
     * java -server -XX:+UnlockDiagnosticVMOptions '-XX:CompileCommand=print,*Main.main' src/main/java/profiler/Main.java
     * but it produces this error:
     * Could not load hsdis-aarch64.dylib; library not loadable; PrintAssembly is disabled
     * I didn't dig further after this.
     * */
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
