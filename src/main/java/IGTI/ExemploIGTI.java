
package IGTI;

import IGTI.mapper.MapIGTI;
import IGTI.reducer.ReduceIGTI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ExemploIGTI extends Configured implements Tool {

    public static void main (final String[] args) throws Exception {
      int res = ToolRunner.run(new Configuration(), new ExemploIGTI(), args);        
      System.exit(res);           
    }   

    public int run (final String[] args) throws Exception {
      try {
          JobConf conf = new JobConf(getConf(), ExemploIGTI.class);
          conf.setJobName("Calculo Covid19 - 2");

          final FileSystem fs = FileSystem.get(conf);

          Path diretorioEntrada = new Path("PastaEntrada"), diretorioSaida = new Path("PastaSaida");
          fs.mkdirs(diretorioEntrada);

          fs.copyFromLocalFile(new Path("/usr/local/hadoop/Dados/covidData.txt"), diretorioEntrada);

          FileInputFormat.setInputPaths(conf, diretorioEntrada);
          FileOutputFormat.setOutputPath(conf, diretorioSaida);

          conf.setOutputKeyClass(Text.class);
          conf.setOutputValueClass(Text.class);

          conf.setMapperClass(MapIGTI.class);
          conf.setReducerClass(ReduceIGTI.class);
          JobClient.runJob(conf);
      }
        catch ( Exception e ) {
            throw e;
        }
        return 0;
     }
}