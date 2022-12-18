package b2SdkExamples;

import com.backblaze.b2.json.B2Json;
import com.backblaze.b2.json.B2JsonException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Reference: https://github.com/Backblaze/b2-sdk-java/blob/0ecd68df94691cbba5a6af363246b7193aead234/core/src/test/java/com/backblaze/b2/json/B2JsonTest.java
 */
public class B2JsonTest {
    private static final class Container {

        @B2Json.required
        public final int a;

        @B2Json.optional
        public final String b;

        @B2Json.ignored
        public int c;

        @B2Json.constructor(params = "a, b")
        public Container(int a, String b) {
            this.a = a;
            this.b = b;
            this.c = 5;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Container)) {
                return false;
            }
            Container other = (Container) o;
            return a == other.a && (b == null ? other.b == null : b.equals(other.b));
        }
    }

    private static final B2Json b2Json = B2Json.get();

    @Test
    public void testObject() throws B2JsonException {
        String json =
                "{\n" +
                        "  \"a\": 41,\n" +
                        "  \"b\": \"hello\"\n" +
                        "}";
        Container obj = new Container(41, "hello");
        assertEquals(json, b2Json.toJson(obj));
        System.out.println("obj is: " + obj);
        System.out.println("json is: " + json);
        assertEquals(obj, b2Json.fromJson(json, Container.class));
    }

}
