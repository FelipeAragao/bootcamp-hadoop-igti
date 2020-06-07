package IGTI.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class ReduceIGTI extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
    public void reduce (Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        int maiorCasos = 0, maiorObitos = 0;
        String paisCasos = "", paisObitos = "", strSaida = "";
        Text value = new Text();
        String[] campos = new String[3];
        while (values.hasNext()) {
            value = values.next();
            campos = value.toString().split("\\|");
            if (Integer.parseInt(campos[1]) > maiorCasos) {
                maiorCasos = Integer.parseInt(campos[1]);
                paisCasos = campos[0];
            }
            if (Integer.parseInt(campos[2]) > maiorObitos) {
                maiorObitos = Integer.parseInt(campos[2]);
                paisObitos = campos[0];
            }
        }
        strSaida = "Casos: " + String.valueOf(maiorCasos) + " em " + paisCasos + ".";
        strSaida += "Obitos: " + String.valueOf(maiorObitos) + " em " + paisObitos + ".";

        value.set(strSaida);
        output.collect(key, value);

    }
}
