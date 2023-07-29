package b2SdkExamples;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.B2StorageClientFactory;
import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.client.structures.B2Bucket;
import com.backblaze.b2.client.structures.B2ListBucketsResponse;

import java.util.List;

public class B2SdkExamples {

    private static final String APPLICATION_KEY_ID = "xxx";
    private static final String APPLICATION_KEY = "yyy";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36";

    public static void main(String... args) throws B2Exception {
        System.out.println("Hello world from B2SdkExamples..");
//        B2StorageClient client = B2StorageClientFactory.createDefaultFactory().create(USER_AGENT);
        //set up B2 CLI
        //and then use b2 get-account-info to get applicationKeyId and applicationKey to fill in here and run
        B2ListBucketsResponse b2ListBucketsResponse;
        try (B2StorageClient client = B2StorageClientFactory.createDefaultFactory().create(APPLICATION_KEY_ID, APPLICATION_KEY, USER_AGENT)) {
            b2ListBucketsResponse = client.listBuckets();
        }
        List<B2Bucket> buckets = b2ListBucketsResponse.getBuckets();
        System.out.println("buckets.size() is: " + buckets.size());
        for (B2Bucket b2Bucket : buckets) {
            System.out.println("this bucket info is: " + b2Bucket.getBucketInfo());
            System.out.println("this bucket getBucketName is: " + b2Bucket.getBucketName());
        }
        System.out.println("Finished running in main method.");
    }
}
