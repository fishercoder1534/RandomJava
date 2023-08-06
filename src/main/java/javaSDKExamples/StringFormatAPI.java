package javaSDKExamples;

public class StringFormatAPI {

    public static void main(String... args) {
        /**https://www.javatpoint.com/java-string-format
         * %d decimal integer
         * %x hex string, %06x means padding up to 6 leading zeroes to make it 6 digits
         * %s string value
         *
         * */
        final String actual = String.format(
                "coolString%d%c%02d%02d%02d%02d%06x%012x%s%s",
                1,
                'a',
                17,
                3,
                9,
                3,
                1234,
                1234567890,
                "4_abc_12",
                ""
        );
        System.out.println("actual is: " + actual);
    }

}
