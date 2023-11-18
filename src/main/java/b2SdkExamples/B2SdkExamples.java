package b2SdkExamples;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.B2StorageClientFactory;
import com.backblaze.b2.client.contentSources.B2ByteArrayContentSource;
import com.backblaze.b2.client.contentSources.B2ContentTypes;
import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.client.structures.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class B2SdkExamples {
    private static final String APPLICATION_KEY_ID = "xxx";
    private static final String APPLICATION_KEY = "yyy";



    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36";

    private static final int minimumPartSize = 5000000;
    private static final byte[] large_file_in_bytes = new byte[minimumPartSize + 1];

    public static void main(String... args) throws B2Exception {
        System.out.println("Hello world from B2SdkExamples..");
//        B2StorageClient client = B2StorageClientFactory.createDefaultFactory().create(USER_AGENT);
        //set up B2 CLI
        //and then use b2 get-account-info to get applicationKeyId and applicationKey to fill in here and run
        B2ListBucketsResponse b2ListBucketsResponse;
        try (B2StorageClient b2Client = B2StorageClientFactory.createDefaultFactory().create(APPLICATION_KEY_ID, APPLICATION_KEY, USER_AGENT)) {
            b2ListBucketsResponse = b2Client.listBuckets();
            List<B2Bucket> buckets = b2ListBucketsResponse.getBuckets();
            System.out.println("buckets.size() is: " + buckets.size());
            B2Bucket bucketOne = null;
            for (B2Bucket b2Bucket : buckets) {
                System.out.println("this bucket info is: " + b2Bucket.getBucketInfo());
                System.out.println("this bucket getBucketName is: " + b2Bucket.getBucketName());
                bucketOne = b2Bucket;
                break;
            }
            String b2AccountId = b2Client.getAccountId();
            System.out.println("b2AccountId is: " + b2AccountId);

            B2FileVersion largeFile = b2Client.startLargeFile(
                    B2StartLargeFileRequest
                            .builder(bucketOne.getBucketId(), "this_is_a_large_test_file_jsun", "text/plain")
                            .build()
            );
            B2UploadPartUrlResponse uploadPartUrl = b2Client.getUploadPartUrl(B2GetUploadPartUrlRequest.builder(largeFile.getFileId()).build());
            System.out.println("uploadPartUrl.getFileId() is: " + uploadPartUrl.getFileId());
            System.out.println("uploadPartUrl.getUploadUrl() is: " + uploadPartUrl.getUploadUrl());
//            example output:
//            uploadPartUrl.getFileId() is: 4_zc0c2ee6e6dccd2d788960d17_f231f3059ce9d1672_d20231118_m042524_c004_v0402007_t0004_u01700281524855
//            uploadPartUrl.getUploadUrl() is: https://pod-040-2007-12.backblaze.com/b2api/v2/b2_upload_part/4_zc0c2ee6e6dccd2d788960d17_f231f3059ce9d1672_d20231118_m042524_c004_v0402007_t0004_u01700281524855/0014

            final B2UploadFileRequest request = B2UploadFileRequest.builder(
                    bucketOne.getBucketId(),
                    "largeFile",
                    B2ContentTypes.TEXT_PLAIN,
                    B2ByteArrayContentSource.build(large_file_in_bytes)
            ).build();
            ExecutorService executor = Executors.newScheduledThreadPool(15);
            B2FileVersion uploaded = b2Client.uploadLargeFile(request, executor);
            System.out.println("uploaded.getLargeFileSha1OrNull(): " + uploaded.getLargeFileSha1OrNull());
        }
        System.out.println("Finished running in main method.");
    }
}
