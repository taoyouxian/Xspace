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
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.hadoop.mr
 * @ClassName: InvertedIndex
 * @Description:
 * @author: tao
 * @date: Create in 2018-03-27 16:50
 **/
public class InvertedIndex {


    public static class InvertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {

        private final static Text one = new Text("1");
        private Text word = new Text();
        static int i = 0;

        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            Path path = inputSplit.getPath();
            String filePath = path.toString();
            int fileIndex = filePath.lastIndexOf("/");
            String fileName = filePath.substring(fileIndex + 1);
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken() + ":" + fileName);
                System.out.println("No " + (i++) + " " + key + "\t" + word);
                context.write(word, one);
            }
        }
    }

    public static class InvertedIndexCombiner extends Reducer<Text, Text, Text, Text> {

        private Text result = new Text();
        static int i = 0;

        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (Text val : values) {
                sum += Integer.valueOf(val.toString());
            }
            Pattern pattern = Pattern.compile(":");
            String[] keys = pattern.split(key.toString(), 0);

            result.set(keys[1] + "：" + sum);
            System.out.println("No " + (i++) + " " + key + "\t" + sum);
            context.write(new Text(keys[0]), result);
        }
    }

    public static class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {

        private Text result = new Text();
        static int i = 0;

        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            String sum = "";
            for (Text val : values) {
                sum += val + ",";
            }
            System.out.println("No " + (i++) + " " + key + "\t" + sum);
            sum =  sum.substring(0, sum.length() - 1);
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Inverted Index");
        job.setJarByClass(InvertedIndex.class);
        job.setMapperClass(InvertedIndex.InvertedIndexMapper.class);
        job.setCombinerClass(InvertedIndex.InvertedIndexCombiner.class);
        job.setReducerClass(InvertedIndex.InvertedIndexReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path("/2018/Index/"));
        FileOutputFormat.setOutputPath(job, new Path("/2018/res"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
