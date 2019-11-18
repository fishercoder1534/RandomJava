package guice.relearn_2019_09._9_inject_the_same_object_in_two_modules;

public class AmazonDynamoDB {
    /**
     * This is a dummy class since AWS DynamoDB Maven depdency cannot be resolved in my local.
     */
    private final String awsRegion;
    private final String ddbEndPoint;

    public AmazonDynamoDB(String awsRegion, String ddbEndPoint) {
        this.awsRegion = awsRegion;
        this.ddbEndPoint = ddbEndPoint;
    }
}
