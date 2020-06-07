package IGTI.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

public class MapIGTIMaior extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable longWritable, Text text, OutputCollector<Text, Text> outputCollector, Reporter reporter) throws IOException {
        outputCollector.collect(new Text("1"), new Text((text.toString().split("\\t"))[1]));
    }
}
