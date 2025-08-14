package catchExceptions;

/**This program shows that if the inner catch block:
 * catch (ArithmeticException ae)
 * cannot catch the exception, in this case, it's RuntimeException, it'll be caught by the outer catch:
 * catch (Exception e)
 * although the exception is being thrown from the inner try block.
 * */
public class CatchException {
    private static void throwExceptionMethod(int a, int b) {
        try {
            //do something here..
            int c = a + b;
            System.out.println("c is " + c );

            try {
                System.out.println(a / b);
                throw new RuntimeException("a random exception");
            } catch (ArithmeticException ae) {
                System.out.println("It enters ArithmeticException branch.");
                ae.printStackTrace();
                // throw ae;
            }
        } catch (Exception e) {
            System.out.println("It enters Exception branch: " + e.getMessage());
            e.printStackTrace();
            // throw e;
        }
    }

    public static void main(String[] args) {
        throwExceptionMethod(2, 4);
        System.out.println("That's the end of the program!");
    }
}
