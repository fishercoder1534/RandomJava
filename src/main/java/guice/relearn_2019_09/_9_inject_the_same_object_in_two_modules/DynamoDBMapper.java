package guice.relearn_2019_09._9_inject_the_same_object_in_two_modules;

public class DynamoDBMapper {
    /**
     * This is a dummy class since AWS DynamoDB Maven depdency cannot be resolved in my local.
     */

    private final AmazonDynamoDB dynamodb;

    public DynamoDBMapper(AmazonDynamoDB dynamodb) {
        this.dynamodb = dynamodb;
    }
}
