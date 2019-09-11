package object_mapper_example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ObjectMapperExample {
    public static void main(String... args) throws IOException {
        System.out.println("Hello world!");
        String str = "{\n" +
                "  \"Records\": [\n" +
                "    {\n" +
                "      \"eventVersion\": \"2.1\",\n" +
                "      \"eventSource\": \"aws:s3\",\n" +
                "      \"awsRegion\": \"us-east-1\",\n" +
                "      \"eventTime\": \"2019-09-10T23:02:48.052Z\",\n" +
                "      \"eventName\": \"ObjectCreated:Put\",\n" +
                "      \"userIdentity\": {\n" +
                "        \"principalId\": \"AWS:SOMETHING_COOL:i-1234\"\n" +
                "      },\n" +
                "      \"requestParameters\": {\n" +
                "        \"sourceIPAddress\": \"12.34.56.78\"\n" +
                "      },\n" +
                "      \"responseElements\": {\n" +
                "        \"x-amz-request-id\": \"SUPER_COOL_ID\",\n" +
                "        \"x-amz-id-2\": \"SOMETHING_AWESOME_CTU=\"\n" +
                "      },\n" +
                "      \"s3\": {\n" +
                "        \"s3SchemaVersion\": \"1.0\",\n" +
                "        \"configurationId\": \"QuarantineListAvailableSNS\",\n" +
                "        \"bucket\": {\n" +
                "          \"name\": \"staging-data-pact\",\n" +
                "          \"ownerIdentity\": {\n" +
                "            \"principalId\": \"WAS_IST_ES\"\n" +
                "          },\n" +
                "          \"arn\": \"arn:aws:s3:::staging-data-pact\"\n" +
                "        },\n" +
                "        \"object\": {\n" +
                "          \"key\": \"quarantined_classes/quar_out_2019-09-10-22-00-00.csv\",\n" +
                "          \"size\": 455211,\n" +
                "          \"eTag\": \"b39e0617b483c86500ec5319e0951d07\",\n" +
                "          \"sequencer\": \"005D782B97CD61A2EC\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode treeObject = mapper.readTree(str);
        assertNotNull(treeObject);
        JsonNode records = treeObject.get("Records");
        System.out.println("records: " + records);

        JsonNode s3 = records.get(0);
        System.out.println("s3: " + s3);

        JsonNode value = s3.get("s3");
        System.out.println("value: " + value);

        JsonNode object = value.get("object");
        System.out.println("object: " + object);

        String bucket = value.get("bucket").get("name").toString();
        System.out.println("bucket: " + bucket);

        String key = object.get("key").toString();
        System.out.println("key: " + key);

    }
}
