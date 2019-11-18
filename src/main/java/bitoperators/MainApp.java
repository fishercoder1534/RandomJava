package bitoperators;

public class MainApp {

    public static void main(String... args) {
        int value1 = 3;
        int value2 = 4;
        System.out.println(Integer.toBinaryString(value1));
        System.out.println(Integer.toBinaryString(value2));

        int result = value1 ^ value2;
        System.out.println(result);
        System.out.println(Integer.toBinaryString(result));
    }
}
