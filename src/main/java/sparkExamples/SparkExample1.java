package sparkExamples;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;
import java.util.regex.Pattern;

public class SparkExample1 {
    /**
     * How to run this program:
     * 1. locally: mvn package && mvn exec:java -Dexec.mainClass=sparkExamples.SparkExample1 -Dexec.args="sparkExamples/Input1.txt sparkExamples/Output1.txt"
     * 2. on AWS EMR cluster:
     *      mvn package
     *      upload the compiled jar file: RandomJava-1.0-SNAPSHOT.jar to an S3 bucket
     *      click add steps to a ready cluster on AWS EMR
     *      --class sparkExamples.SparkExample1
     *      specify the jar in your S3 location
     *      --s3://sunstev-test/Input1.txt --s3://sunstev-test/Output1.txt (in your arguments section)
     */

    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("args.length:" + args.length);
        if (args.length < 2) {
            System.err.println("Usage: JavaWordCount <inputFile> <outputFile>");
            System.exit(1);
        }
        System.out.println("args.[0]:" + args[0] + ", args[1]: " + args[1]);
        try {
            SparkSession spark = SparkSession
                    .builder()
                    .appName("SparkJob")
//                    .config("spark.master", "local")//keep this one commented out if you want to run in cluster mode
                    .getOrCreate();
            System.out.println("SparkSession is initated.");

            JavaRDD<String> textFile = spark.read().textFile(args[0]).toJavaRDD();
            System.out.println("Finished reading this textFile: " + args[0]);

            JavaPairRDD<String, Integer> counts = textFile
                    .flatMap(s -> Arrays.asList(SPACE.split(s)).iterator())
                    .mapToPair(s -> new Tuple2<>(s, 1))
                    .reduceByKey((a, b) -> a + b);
            System.out.println("Finished doing MapReduce option on this textFile: " + args[0]);

            counts.saveAsTextFile(args[1]);
            System.out.println("Finished saving output to this textFile: " + args[1]);
        } catch (Exception e) {
            System.out.println("Caught exception when processing: " + e);
        }
    }
}
