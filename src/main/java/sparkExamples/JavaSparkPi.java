package sparkExamples;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

import java.util.ArrayList;
import java.util.List;

/**
 * Computes an approximation to pi
 * Usage: JavaSparkPi [slices]
 *
 * NOTE: it's throwing Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 28499 when running locally through Intellij.
 * But it runs fine on AWS EMR, here's how to run it on AWS EMR:
 *    mvn package
 *    upload the compiled jar file: RandomJava-1.0-SNAPSHOT.jar to an S3 bucket
 *    click add steps to a ready cluster on AWS EMR
 *    --class sparkExamples.JavaSparkPi
 *    specify the jar in your S3 location
 */
public final class JavaSparkPi {

    public static void main(String[] args) throws Exception {
        SparkConf sparkConf = new SparkConf()
                .setAppName("JavaSparkPi")
//                .setMaster("local")//uncomment this if you run it on AWS EMR in cluster mode
                ;
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        System.out.println("JavaSparkContext is initiated.");

        int slices = (args.length == 1) ? Integer.parseInt(args[0]) : 2;
        int n = 1000 * slices;
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        System.out.println("Got list.size() is: " + list.size());

        JavaRDD<Integer> dataSet = jsc.parallelize(list, slices);
        System.out.println("dataSet.count() is: " + dataSet.count());

        int count = dataSet.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                double x = Math.random() * 2 - 1;
                double y = Math.random() * 2 - 1;
                return (x * x + y * y < 1) ? 1 : 0;
            }
        }).reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });

        System.out.println("Pi is roughly " + 4.0 * count / n);

        jsc.stop();
    }
}

