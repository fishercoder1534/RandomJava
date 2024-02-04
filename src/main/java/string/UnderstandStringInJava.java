package string;

public class UnderstandStringInJava {
    public static void main(String... args) {
        UnderstandStringInJava understandStringInJava = new UnderstandStringInJava();
        understandStringInJava.checkStringEquality();
        System.out.println("Program finished.");
    }

    public void checkStringEquality() {
        String a = "abc";
        String b = "abc";

        /**
         * One can use == operators for reference comparison (address comparison) and the .equals() method for content comparison.
         * Both s1 and s2 refer to different objects.
         * When one uses == operator for the s1 and s2 comparison then the result is false as both have different addresses in memory.
         * Using equals, the result is true because it’s only comparing the values given in s1 and s2.*/
        System.out.println(a == b);
        System.out.println(a.equals(b));

        String c = new String(new char[]{'a', 'b', 'c', 'd'});
        String d = new String(new char[]{'a', 'b', 'c', 'd'});
        System.out.println("c is: " + c);
        System.out.println("d is: " + d);
        System.out.println(c == d);
        System.out.println(c.equals(d));
    }
}
