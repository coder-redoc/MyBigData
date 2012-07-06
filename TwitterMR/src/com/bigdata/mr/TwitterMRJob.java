package com.bigdata.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Iterator;

/**
 * User: Satish Varma Dandu (dsvarma@gmail.com)
 * Time: 11:52 AM
 */

public class TwitterMRJob extends Configured implements Tool {

	private static final Logger log = Logger.getLogger(TwitterMRJob.class.getName());

	/**
	 * 
	 * Mapper class reads each line from the file & extracts the http url out of the tweet. 
	 *
	 */
	public static class MapClass extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
		TweetParser tweetParser = new TweetParser();
		private final static IntWritable one = new IntWritable(1);
		private Text link = new Text();

		public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
			try {
				String url = tweetParser.parseTwitterUrlData(value.toString());
				if (null != url) {
					link.set(url);
					output.collect(link, one);
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
	public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
		public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
			int sum = 0;
			while (values.hasNext()) {
				sum += values.next().get();
			}
			output.collect(key, new IntWritable(sum));
		}
	}

	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		JobConf job = new JobConf(conf, TwitterMRJob.class);
		Path in = new Path(args[0]);
		Path out = new Path(args[1]);

		FileInputFormat.setInputPaths(job, in);
		FileOutputFormat.setOutputPath(job, out);

		job.setJobName("TwitterMapReduceJob");
		job.setMapperClass(MapClass.class);
		job.setReducerClass(Reduce.class);

		job.setInputFormat(TextInputFormat.class);
		job.setOutputFormat(TextOutputFormat.class);

		//set the outputs for the Map
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		//set the outputs for the Job
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		JobClient.runJob(job);
		return 0;
	}


	public static void main(String[] args) throws Exception {

		if (args.length < 2) {
			System.out.println("<USAGE> com.bigdata.mr.TwitterMRJob <INPUT_DIR> <OUTPUT_DIR> ");
			System.exit(0);
		}

		try{
			int res = ToolRunner.run(new Configuration(), new TwitterMRJob(), args);
			log.info("Executed Twitter MarReduce Job with response code=" + res);
		}catch(Exception e){
			log.error("Exception caught while running TwitterMapReduceJob",e);
		}
	}


}
