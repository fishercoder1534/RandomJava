package randomExamples;

import java.io.UnsupportedEncodingException;

public class Size {
    public static void main(String... args) throws UnsupportedEncodingException {
        System.out.println("Program started.");
        System.out.println(Integer.MAX_VALUE);//2,147,483,647
        System.out.println(Integer.MIN_VALUE);//-2,147,483,648
        System.out.println(getByteBits());//8
        System.out.println(getShortBits());//16
        System.out.println(getIntegerBits());//32
        System.out.println(getLongBits());//64
        System.out.println(is64bit0());//check if the machine that this program runs on is 64-bit
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1).length());//32
        System.out.println(Integer.toBinaryString(1).length());//1

        // The input string for this test
        final String string = "Hello World";

        // Check length, in characters
        System.out.println(string.length()); // prints "11"

        // Check encoded sizes
        final byte[] utf8Bytes = string.getBytes("UTF-8");
        System.out.println(utf8Bytes.length); // prints "11"

        final byte[] utf16Bytes = string.getBytes("UTF-16");
        System.out.println(utf16Bytes.length); // prints "24"

        final byte[] utf32Bytes = string.getBytes("UTF-32");
        System.out.println(utf32Bytes.length); // prints "44"

        final byte[] isoBytes = string.getBytes("ISO-8859-1");
        System.out.println(isoBytes.length); // prints "11"

        final byte[] winBytes = string.getBytes("CP1252");
        System.out.println(winBytes.length); // prints "11"


        System.out.println("Program finished.");
    }

    private static boolean is64bit0() {
        String systemProp = System.getProperty("com.ibm.vm.bitmode");
        if (systemProp != null) {
            return "64".equals(systemProp);
        }
        systemProp = System.getProperty("sun.arch.data.model");
        if (systemProp != null) {
            return "64".equals(systemProp);
        }
        systemProp = System.getProperty("java.vm.version");
        return systemProp != null && systemProp.contains("_64");
    }

    public static int getByteBits() {
        int i = 1;
        byte j = 1;
        for (; (j = (byte) (j << 1)) > 0; i++) {
        }
        //Compensation of the sign bit 1
        return i + 1;
    }

    public static int getShortBits() {
        int i = 1;
        short j = 1;

        for (; (j = (short) (j << 1)) > 0; i++)
            ;

        //Compensation of the sign bit 1
        return i + 1;
    }

    public static int getIntegerBits() {
        int i = 1;
        int j = 1;

        for (; (j = j << 1) > 0; i++)
            ;

        //Compensation of the sign bit 1
        return i + 1;
    }

    public static int getLongBits() {
        int i = 1;
        long j = 1;

        for (; (j = j << 1) > 0; i++)
            ;

        //Compensation of the sign bit 1
        return i + 1;
    }
}
