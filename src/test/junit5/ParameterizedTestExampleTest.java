package junit5;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedTestExampleTest {
    @ParameterizedTest
    @MethodSource("data")
    public void parameterizedTest(String input, boolean expected) {
        System.out.println("input is: " + input + ", expected is: " + expected);
        assertEquals(expected, Strings.isBlank(input));
    }

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of("  ", true),
                Arguments.of("not blank", false)
        );
    }
}
