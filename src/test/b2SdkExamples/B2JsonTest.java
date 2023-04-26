package b2SdkExamples;

import com.backblaze.b2.json.B2Json;
import com.backblaze.b2.json.B2JsonException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Ignore;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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

        @B2Json.optionalWithDefault(defaultValue = "0")
        public int d;

        @B2Json.constructor(params = "a, b, d")
        public Container(int a, String b, int d) {
            this.a = a;
            this.b = b;
            this.c = 5;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            System.out.println("Entered overridden equals() method to check now...");
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Container container = (Container) o;
            return a == container.a && d == container.d && Objects.equals(b, container.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
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

        @B2Json.required
        public final Map<Integer, Map<String, BigDecimal>> revenueMap;

        @B2Json.required
        public final Map<String, Long> simpleMap;

        @B2Json.required
        public final Set<String> categories;

        @B2Json.constructor(params = "" +
                "str," +
                "message," +
                "reason," +
                "succeeded," +
                "status," +
                "localDateTime," +
                "localDate," +
                "revenueMap," +
                "simpleMap," +
                "categories"
        )
        public TestResponse(String str, String message, String reason, boolean succeeded, int status, LocalDateTime localDateTime, LocalDate localDate,
                            Map<Integer, Map<String, BigDecimal>> revenueMap,
                            Map<String, Long> simpleMap,
                            Set<String> categories) {
            this.str = str;
            this.message = message;
            this.reason = reason;
            this.succeeded = succeeded;
            this.status = status;
            this.localDateTime = localDateTime;
            this.localDate = localDate;
            this.simpleMap = simpleMap;
            this.revenueMap = revenueMap;
            this.categories = categories;
        }
    }

//    @Test
//    public void testResponseUsingB2Json() throws B2JsonException, JSONException {
//        Map<String, Map<String, BigDecimal>> revenueMap = new TreeMap<>();
//        Map<String, Long> simpleMap = new TreeMap<>();
//        TestResponse obj = new TestResponse("str",
//                "message",
//                "reason",
//                true,
//                200,
//                LocalDateTime.of(2023, 03, 31, 12, 21),
//                LocalDate.of(2023, 03, 31),
//                revenueMap,
//                simpleMap,
//                Set.of("test"));
//        System.out.println("obj is: " + obj);
//        String expected = "{\n" +
//                "  \"categories\": [\n" +
//                "    \"test\"\n" +
//                "  ],\n" +
//                "  \"localDate\": \"20230331\",\n" +
//                "  \"localDateTime\": \"d20230331_m122100\",\n" +
//                "  \"message\": \"message\",\n" +
//                "  \"reason\": \"reason\",\n" +
//                "  \"revenueMap\": {},\n" +
//                "  \"simpleMap\": {},\n" +
//                "  \"status\": 200,\n" +
//                "  \"str\": \"str\",\n" +
//                "  \"succeeded\": true\n" +
//                "}";
//        System.out.println("b2Json.toJson(obj): " + b2Json.toJson(obj));
//        JSONAssert.assertEquals(expected, b2Json.toJson(obj), true);
//    }

    @Test
    @Ignore
    public void testResponseUsingGson() throws JSONException {
        Map<Integer, Map<String, BigDecimal>> revenueMap = new TreeMap<>();
        revenueMap.put(123, new HashMap<>());
        Map<String, Long> simpleMap = new TreeMap<>();
        TestResponse obj = new TestResponse("str",
                "message",
                "reason",
                true,
                200,
                LocalDateTime.of(2023, 03, 31, 12, 21),
                LocalDate.of(2023, 03, 31),
                revenueMap,
                simpleMap,
                Set.of("test"));
        System.out.println("obj is: " + obj);
        String expected = "{\n" +
                "  \"str\": \"str\",\n" +
                "  \"message\": \"message\",\n" +
                "  \"reason\": \"reason\",\n" +
                "  \"succeeded\": true,\n" +
                "  \"status\": 200,\n" +
                "  \"localDateTime\": {\n" +
                "    \"date\": {\n" +
                "      \"year\": 2023,\n" +
                "      \"month\": 3,\n" +
                "      \"day\": 31\n" +
                "    },\n" +
                "    \"time\": {\n" +
                "      \"hour\": 12,\n" +
                "      \"minute\": 21,\n" +
                "      \"second\": 0,\n" +
                "      \"nano\": 0\n" +
                "    }\n" +
                "  },\n" +
                "  \"localDate\": {\n" +
                "    \"year\": 2023,\n" +
                "    \"month\": 3,\n" +
                "    \"day\": 31\n" +
                "  },\n" +
                "  \"revenueMap\": {},\n" +
                "  \"simpleMap\": {},\n" +
                "  \"categories\": [\n" +
                "    \"test\"\n" +
                "  ]\n" +
                "}";
        System.out.println("b2Json.toJson(obj): " + gson.toJson(obj));
        JSONAssert.assertEquals(expected, gson.toJson(obj), true);
    }

    @Test
    public void testObject() throws B2JsonException {
        String json = "{\n" +
                "  \"a\": 41,\n" +
                "  \"b\": \"hello\",\n" +
                "  \"d\": 15\n" +
                "}";
        Container obj = new Container(41, "hello", 15);
        assertEquals(json, b2Json.toJson(obj));
        System.out.println("obj is: " + obj);
        System.out.println("json is: " + json);
        assertEquals(obj, b2Json.fromJson(json, Container.class));
    }

    @Test
    public void testObjectUsingDefaultValue() throws B2JsonException {
        //in this json string, there's no field d which is an optional field with a default value
        String json = "{\n" +
                "  \"a\": 2023,\n" +
                "  \"b\": \"hello\"\n" +
                "}";
        System.out.println("json is: " + json);
        Container fromB2Json = b2Json.fromJson(json, Container.class);
        System.out.println("b2Json.fromJson(json, Container.class) is: " + fromB2Json);
        Container fromGson = gson.fromJson(json, Container.class);
        System.out.println("gson.fromJson(json) is: " + fromGson);
        System.out.println("about to check the equality between Gson and B2Json results..");
        assertEquals(fromGson, fromB2Json);
        String fromB2JsonString = b2Json.toJson(fromB2Json);
        String fromGsonString = gson.toJson(fromB2Json);
        String expectedFromGson = "{\n" +
                "  \"a\": 2023,\n" +
                "  \"b\": \"hello\",\n" +
                "  \"c\": 5,\n" +
                "  \"d\": 0\n" +
                "}";
        assertEquals(expectedFromGson, fromGsonString);
        //there's no field c as it's annotated by B2Json.ignored
        String expectedFromB2Json = "{\n" +
                "  \"a\": 2023,\n" +
                "  \"b\": \"hello\",\n" +
                "  \"d\": 0\n" +
                "}";
        assertEquals(expectedFromB2Json, fromB2JsonString);
    }

    @Test
    public void testRequestUsingB2Json() throws B2JsonException, JSONException {
        //B2Json always reorders the fields in the object in alphabetical order
        TestRequest obj = new TestRequest(123, "message", "aMessage", "bMessage", "pMessage", "zMessage", null, null, REASON_STR);
        System.out.println("obj is: " + obj);
        String expected = "{\n" +
                "  \"aMessage\": \"aMessage\",\n" +
                "  \"bMessage\": \"bMessage\",\n" +
                "  \"message\": \"message\",\n" +
                "  \"pMessage\": \"pMessage\",\n" +
                "  \"reason\": \"A TEST STRING FOR REASON\",\n" +
                "  \"str\": 123,\n" +
                "  \"zMessage\": \"zMessage\"\n" +
                "}";
        JSONAssert.assertEquals(expected, b2Json.toJson(obj), true);
    }

    @Test
    public void testRequestUsingGson() throws JSONException {
        //GSON deserializes object fields in their given order in the constructor
        TestRequest obj = new TestRequest(123, "message", "aMessage", "bMessage", "pMessage", "zMessage", null, null, REASON_STR);
        System.out.println("obj is: " + obj);
        String expected = "{\n" +
                "  \"aMessage\": \"aMessage\",\n" +
                "  \"bMessage\": \"bMessage\",\n" +
                "  \"message\": \"message\",\n" +
                "  \"pMessage\": \"pMessage\",\n" +
                "  \"reason\": \"A TEST STRING FOR REASON\",\n" +
                "  \"str\": 123,\n" +
                "  \"zMessage\": \"zMessage\"\n" +
                "}";
        JSONAssert.assertEquals(expected, gson.toJson(obj), false);
    }

    @Test
    public void testRequest2UsingB2Json() {
        /**
         * the field zip is declared as an Integer, but we are passing in a string, in this case
         * it throws com.backblaze.b2.json.B2JsonException: Bad number
         * */
        String request2Json = "{\n" + "\"zip\": \"95148\"\n" + "}";
        assertThrows(B2JsonException.class, () -> b2Json.fromJson(request2Json, TestRequest2.class));
    }

    private static class TestRequest2 {
        @B2Json.optional(omitNull = true)
        public final Integer zip;

        @B2Json.constructor(params = "zip")
        public TestRequest2(Integer zip) {
            this.zip = zip;
        }
    }

    @Test
    public void testRequest3UsingB2Json() {
        /**
         * the field zip is declared as a String, but we are passing in an Integer
         * in this case, it throws: com.backblaze.b2.json.B2JsonException: string does not start with quote
         * */
        String request2Json = "{\n" + "\"zip\": 95148\n" + "}";
        assertThrows(B2JsonException.class, () -> b2Json.fromJson(request2Json, TestRequest3.class));
    }

    private static class TestRequest3 {
        @B2Json.optional(omitNull = true)
        public final String zip;

        @B2Json.constructor(params = "zip")
        public TestRequest3(String zip) {
            this.zip = zip;
        }
    }

}
