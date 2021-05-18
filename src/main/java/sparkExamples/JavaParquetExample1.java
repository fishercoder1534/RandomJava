package sparkExamples;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.regex.Pattern;

public class JavaParquetExample1 {
    /**
     * How to run this program:
     * 1. locally: mvn package && mvn exec:java -Dexec.mainClass=sparkExamples.SparkExample1 -Dexec.args="sparkExamples/Input1.txt sparkExamples/Output1.txt"
     * 2. on AWS EMR cluster:
     * mvn package
     * upload the compiled jar file: RandomJava-1.0-SNAPSHOT.jar to an S3 bucket
     * click add steps to a ready cluster on AWS EMR
     * --class sparkExamples.JavaParquetExample1
     * specify the jar in your S3 location
     * --s3://sunstev-test/your-file.parquet (in your arguments section)
     */

    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("args.length:" + args.length);
        if (args.length > 0) {
            Arrays.stream(args).map(arg -> "arg: " + arg).forEach(System.out::println);
        }
        try {
            SparkSession spark = SparkSession
                    .builder()
                    .appName("SparkJob")
                    .config("spark.eventLog.enabled", "true")
//                    .config("spark.master", "local")//keep this one commented out if you want to run in cluster mode
                    .getOrCreate();
            System.out.println("SparkSession is initated.");

            Dataset<Row> parquetFileDF = spark.read().parquet(args[0]);
            JavaRDD<Row> rdd = parquetFileDF.toJavaRDD();
            rdd.saveAsTextFile(args[1]);
        } catch (Exception e) {
            System.out.println("Caught exception when processing: " + e);
        }

    }
}
