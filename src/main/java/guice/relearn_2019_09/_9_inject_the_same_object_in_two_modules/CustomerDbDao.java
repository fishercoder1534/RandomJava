package guice.relearn_2019_09._9_inject_the_same_object_in_two_modules;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class CustomerDbDao {
    public static final String CUSTOMER_DDB = "Customer.DDB";

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public CustomerDbDao(@Named(CUSTOMER_DDB) DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void work() {
        System.out.println("CustomerDbDao object is talking to Amazon DynamoDB and doing its work!");
    }
}
