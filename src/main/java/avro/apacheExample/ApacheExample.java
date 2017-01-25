package avro.apacheExample;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;

/**
 * This code is credited to: http://hadooptutorial.info/avro-serializing-and-deserializing-example-java-api/
 * Created by stevesun on 1/24/17.
 */
public class ApacheExample {
    public static void main(String[] args) throws IOException {
        Schema schema = new Schema.Parser().parse(new File("/Users/stevesun/personal_dev/RandomJava/src/main/java/avro/apacheExample/Employee.avsc"));
        GenericRecord emp1 = new GenericData.Record(schema);
        emp1.put("name", "Siva");
        emp1.put("age", 45);

        GenericRecord emp2 = new GenericData.Record(schema);
        emp2.put("name", "Krish");
        emp2.put("age", 28);

        File file = new File("/Users/stevesun/personal_dev/RandomJava/src/main/java/avro/apacheExample/output.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(emp1);
        dataFileWriter.append(emp2);
        dataFileWriter.close();
        System.out.println("Serialization finished.");

        File fileToDeserialize = new File("/Users/stevesun/personal_dev/RandomJava/src/main/java/avro/apacheExample/output.avro");

        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);

        GenericRecord emp = new GenericData.Record(schema);
        while (dataFileReader.hasNext()) {
            emp = dataFileReader.next();
            System.out.println(emp);
        }

        System.out.println("Program finished.");
    }
}
