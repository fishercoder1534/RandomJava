package junit5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ChildTest extends BaseTest {

    private static Stream<Arguments> data() {
        return Stream.of(Arguments.of("string1"));
    }

    @ParameterizedTest
    @MethodSource("data")
    public void test1() {
        assertNull(this.field2);
    }
}
