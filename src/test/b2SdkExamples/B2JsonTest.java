package b2SdkExamples;

import com.backblaze.b2.json.B2Json;
import com.backblaze.b2.json.B2JsonException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Reference: https://github.com/Backblaze/b2-sdk-java/blob/0ecd68df94691cbba5a6af363246b7193aead234/core/src/test/java/com/backblaze/b2/json/B2JsonTest.java
 */
public class B2JsonTest {

    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2022, 12, 18, 16, 21);
    private static final LocalDate LOCAL_DATE = LocalDate.of(2022, 12, 18);

    private static final String JSON_STRING_1 = "{\n" +
//            "  \"localDate\": \"20221218\",\n" +
//            "  \"localDateTime\": \"d20221218_m162100\",\n" +
            "  \"message\": \"message\",\n" +
            "  \"str\": 123,\n" +
            "  \"reason\": \"A TEST STRING FOR REASON\"\n" +
            "}";

    private static final String JSON_STRING_2 = "{\n" +
            "  \"aMessage\": \"aMessage\",\n" +
            "  \"bMessage\": \"bMessage\",\n" +
            "  \"message\": \"message\",\n" +
            "  \"pMessage\": \"pMessage\",\n" +
            "  \"reason\": \"A TEST STRING FOR REASON\",\n" +
            "  \"str\": 123,\n" +
            "  \"zMessage\": \"zMessage\"\n" +
            "}";
    private static final String REASON_STR = "A TEST STRING FOR REASON";

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
    private static final Gson gson = (new GsonBuilder())
            .disableHtmlEscaping()
            .setPrettyPrinting().create();

    private static class TestRequest {
        @B2Json.optional(omitNull = true)
        public final Integer str;

        @B2Json.optional(omitNull = true)
        public final String message;

        @B2Json.optional(omitNull = true)
        public final String aMessage;

        @B2Json.optional(omitNull = true)
        public final String bMessage;

        @B2Json.optional(omitNull = true)
        public final String pMessage;

        @B2Json.optional(omitNull = true)
        public final String zMessage;

        @B2Json.optional(omitNull = true)
        public final LocalDateTime localDateTime;

        @B2Json.optional(omitNull = true)
        public final LocalDate localDate;

        @B2Json.optional(omitNull = true)
        public final String reason;

        @B2Json.constructor(params = "" +
                "str," +
                "message," +
                "aMessage," +
                "bMessage," +
                "pMessage," +
                "zMessage," +
                "localDateTime," +
                "localDate," +
                "reason"
        )
        public TestRequest(Integer str, String message, String aMessage, String bMessage, String pMessage, String zMessage, LocalDateTime localDateTime, LocalDate localDate, String reason) {
            this.str = str;
            this.message = message;
            this.aMessage = aMessage;
            this.bMessage = bMessage;
            this.pMessage = pMessage;
            this.zMessage = zMessage;
            this.localDateTime = localDateTime;
            this.localDate = localDate;
            this.reason = reason;
        }
    }

    private static class TestResponse {
        @B2Json.optional(omitNull = true)
        public final String str;

        @B2Json.optional(omitNull = true)
        public final String message;

        @B2Json.optional(omitNull = true)
        public final String reason;

        @B2Json.optional(omitNull = true)
        public final Boolean succeeded;

        @B2Json.optional(omitNull = true)
        public final Integer status;

        @B2Json.optional(omitNull = true)
        public final LocalDateTime localDateTime;

        @B2Json.optional(omitNull = true)
        public final LocalDate localDate;

        @B2Json.constructor(params = "" +
                "str," +
                "message," +
                "reason," +
                "succeeded," +
                "status," +
                "localDateTime," +
                "localDate"
        )
        public TestResponse(String str, String message, String reason, boolean succeeded, int status, LocalDateTime localDateTime, LocalDate localDate) {
            this.str = str;
            this.message = message;
            this.reason = reason;
            this.succeeded = succeeded;
            this.status = status;
            this.localDateTime = localDateTime;
            this.localDate = localDate;
        }
    }

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

    @Test
    public void testRequestUsingB2Json() throws B2JsonException {
        //B2Json always reorders the fields in the object in alphabetical order
        TestRequest obj = new TestRequest(123, "message", "aMessage", "bMessage", "pMessage", "zMessage", null, null, REASON_STR);
        assertEquals(JSON_STRING_2, b2Json.toJson(obj));
        System.out.println("obj is: " + obj);
        System.out.println("json is: " + JSON_STRING_2);
//        assertEquals(obj, b2Json.fromJson(JSON_STRING_2, TestRequest.class));
    }

    @Test
    public void testRequestUsingGson() {
        //GSON deserializes object fields in their given order in the constructor
        TestRequest obj = new TestRequest(123, "message", "aMessage", "bMessage", "pMessage", "zMessage", null, null, REASON_STR);
//        assertEquals(JSON_STRING_2, gson.toJson(obj));
        System.out.println("obj is: " + obj);
        System.out.println("json is: " + JSON_STRING_2);
//        assertEquals(obj, gson.fromJson(JSON_STRING_2, TestRequest.class));
    }

}
