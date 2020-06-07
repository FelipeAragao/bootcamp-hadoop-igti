package IGTI.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class ReduceIGTIMaior extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
    public void reduce (Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

        int totalCasos = 0, totalObitos = 0;
        String paisCasos = "", paisObitos = "";

        while (values.hasNext()) {
            Text value = values.next();
            String[] field = value.toString().split("\\|");

            if (Integer.parseInt(field[0]) > totalCasos) {
                totalCasos = Integer.parseInt(field[0]);
                paisCasos = field[1];
            }

            if (Integer.parseInt(field[2]) > totalObitos) {
                totalObitos = Integer.parseInt(field[2]);
                paisObitos = field[3];
            }
        }

        String result = String.valueOf(totalCasos) +  "|" + paisCasos + "|";
        result += String.valueOf(totalObitos) + "|" + paisObitos;

        output.collect(key, new Text(result));
    }
}
