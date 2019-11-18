package guice.relearn_2019_09._9_inject_the_same_object_in_two_modules;

import com.google.inject.PrivateModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import static guice.relearn_2019_09._9_inject_the_same_object_in_two_modules.CustomerDbDao.CUSTOMER_DDB;

public class Worker1Module extends PrivateModule {

    public static final String WORKER1 = "Worker1";
    private static final String DDB_END_POINT = "dynamodb.us-west-2.amazonaws.com";
    private static final String AWS_REGION = "us-west-2";

    @Override
    protected void configure() {
        final Multibinder<Worker> multibinder = Multibinder.newSetBinder(binder(), Worker.class);
        multibinder.addBinding().to(Worker1.class);

        bind(String.class).annotatedWith(Names.named(AWS_REGION)).toInstance("cool");
        bind(String.class).annotatedWith(Names.named(DDB_END_POINT)).toInstance("cool1");
    }

    @Provides
    @Singleton
    @Named(WORKER1)
    public CustomerDbDao providesCustomerDbDao(@Named(CUSTOMER_DDB) DynamoDBMapper dynamoDBMapper) {
        return new CustomerDbDao(dynamoDBMapper);
    }

    @Provides
    @Singleton
    @Named(CUSTOMER_DDB)
    public DynamoDBMapper provideDynamoDBMapper(@Named(CUSTOMER_DDB) AmazonDynamoDB dynamodb) {
        return new DynamoDBMapper(dynamodb);
    }

    @Provides
    @Singleton
    @Named(CUSTOMER_DDB)
    public AmazonDynamoDB provideDynamoDBClient(final @Named(AWS_REGION) String awsRegion, final @Named(DDB_END_POINT) String ddbEndPoint) {
        return new AmazonDynamoDB(awsRegion, ddbEndPoint);
    }
}
