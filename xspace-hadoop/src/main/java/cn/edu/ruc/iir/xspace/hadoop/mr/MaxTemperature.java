package cn.edu.ruc.iir.xspace.hadoop.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.hadoop.mr
 * @ClassName: MaxTemperature
 * @Description:
 * @author: tao
 * @date: Create in 2018-03-24 17:18
 **/
public class MaxTemperature {

    public static class MaxTempMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        private final static int MISSING = 9999;
        static int i = 0;

        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String year = line.substring(15, 19);
            int temperature;
            if (line.charAt(87) == '+') {
                temperature = Integer.parseInt(line.substring(88, 92));
            } else {
                temperature = Integer.parseInt(line.substring(87, 92));
            }
            String quality = line.substring(92, 93);
            if (temperature != MISSING && quality.matches("[012459]")) {
                context.write(new Text(year), new IntWritable(temperature));
            } else {
                System.out.println("No " + i++ + "\t" + quality + " " + temperature);
            }
        }
    }

    public static class MaxTempReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        static int i = 0;

        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int maxV = Integer.MIN_VALUE;
            for (IntWritable val : values) {
                maxV = Math.max(maxV, val.get());
            }
            System.out.println("No " + (i++) + " " + key + "\t" + maxV);
            context.write(key, new IntWritable(maxV));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "max temperature");
        job.setJarByClass(MaxTemperature.class);
        job.setMapperClass(MaxTemperature.MaxTempMapper.class);
        job.setReducerClass(MaxTemperature.MaxTempReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path("/2018/temperature/*"));
        FileOutputFormat.setOutputPath(job, new Path("/2018/res"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
