package randomExamples;

public class MainClass {

    public static class A {
        int a;

        public A(int a) {
            this.a = a;
        }
    }

    public static void method1(A a) {
        a = new A(2);
    }

    public static void main(String... args) {
        System.out.println("Program started.");
        A a = new A(9);
        System.out.println(a.a);//should print out 9
        method1(new A(10));
        System.out.println(a.a);//should print out 9 as well because as soon as the function method1's scope exits, the changed object of a within method1 undoes the change.
        System.out.println("Program finished.");
    }
}
