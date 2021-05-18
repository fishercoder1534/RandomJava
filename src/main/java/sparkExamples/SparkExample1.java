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
     * 1. mvn compile
     * 2. mvn exec:java -Dexec.mainClass=sparkExamples.SparkExample1 -Dexec.args="sparkExamples/File1.txt sparkExamples/Output1.txt"
     * <p>
     * TODO: right now, it throws java.lang.NoClassDefFoundError: org/apache/spark/sql/SparkSession, fix it.
     */

    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("args.length:" + args.length);
        if (args.length < 2) {
            System.err.println("Usage: JavaWordCount <inputFile> <outputFile>");
            System.exit(1);
        }

        SparkSession spark = SparkSession
                .builder()
                .appName("SparkJob")
                .getOrCreate();

        JavaRDD<String> textFile = spark.read().textFile(args[0]).toJavaRDD();

        JavaPairRDD<String, Integer> counts = textFile
                .flatMap(s -> Arrays.asList(SPACE.split(s)).iterator())
                .mapToPair(s -> new Tuple2<>(s, 1))
                .reduceByKey((a, b) -> a + b);

        counts.saveAsTextFile(args[1]);
    }
}