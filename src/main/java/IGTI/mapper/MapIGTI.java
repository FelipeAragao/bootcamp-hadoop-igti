package IGTI.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

public class MapIGTI extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)  throws IOException {
        Text txtChave = new Text();
        Text txtValue = new Text();

        String[] dadosCovid = value.toString().split(",");
        String dataEvento = dadosCovid[0];
        String paisEvento = dadosCovid[2];
        int novosCasos = Integer.parseInt(dadosCovid[4]);
        int novosObitos = Integer.parseInt(dadosCovid[6]);

        String strChave = dataEvento;
        String strValor = paisEvento + "|" + String.valueOf(novosCasos) + "|" + String.valueOf(novosObitos);
        txtChave.set(strChave);
        txtValue.set(strValor);
        output.collect(txtChave, txtValue);

    }
}
