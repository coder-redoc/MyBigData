package com.bigdata.mr;

import java.io.IOException;
import java.lang.InterruptedException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.Logger;

/**
 * User: Satish Varma Dandu (dsvarma@gmail.com)
 * Time: 11:52 AM
 * org.apache.hadoop.map.* has been deprecated from version hadoop 0.20.*. So this class deals with Hadoop new API.
 */

public class TwitterMRJobNew {

    private static final Logger log = Logger.getLogger(TwitterMRJob.class.getName());

    /**
     * 
     * Mapper class reads each line from the file & extracts the http url out of the tweet. 
     *
     */
    public static class MapClass extends Mapper<LongWritable, Text, Text, IntWritable> {
        TweetParser tweetParser = new TweetParser();
        private final static IntWritable one = new IntWritable(1);
        private Text link = new Text();

        public void map(LongWritable key, Text value, Context context) throws IOException {
            try {
                String url = tweetParser.parseTwitterUrlData(value.toString());
                if (null != url) {
                    link.set(url);
                    context.write(link, one);
                    log.info("Inside Map with url="+link);
                }

            } catch (Exception e) {
                log.error("Exception caught while trying to create a map", e);
            }
        }
    }

    /**
     * 
     * Reducer class outputs the counts for each http url
     *	
     */
    public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for(IntWritable value: values){
                sum+=value.get();
            }
            log.info("Inside reducer with key="+key+", sum="+sum);
            context.write(key, new IntWritable(sum));
        }
    }

    public static  void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        String[] jobArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        Job job = new Job(conf, "Twitter MapReduce Job with new hadoop API");

        //Set Input & output directories
        FileInputFormat.addInputPath(job, new Path(jobArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(jobArgs[1]));

        //Set Mapper & Reducer classes
        job.setJarByClass(TwitterMRJobNew.class);
        job.setMapperClass(MapClass.class);
        job.setReducerClass(Reduce.class);

        //set the outputs for the Map
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //set the outputs for the Job
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
