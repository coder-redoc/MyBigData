package com.bigdata.mr;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.bigdata.mr.TwitterMRJobNew.TweetMapper;
import com.bigdata.mr.TwitterMRJobNew.TweetReducer;

public class TweetMRUnitTest {

	 MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	 ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
	 MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
	 
	 @Before
	 public void setup(){
		 TweetMapper mapper = new TweetMapper();
		 TweetReducer reducer = new TweetReducer();
		 
		 mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
		 reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>();
		 mapDriver.setMapper(mapper);
		 reduceDriver.setReducer(reducer);
		 
		 mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>();
		 mapReduceDriver.setMapper(mapper);
		 mapReduceDriver.setReducer(reducer);
		 
	}
	 
	@Test
	public void testTweetMapper(){
		mapDriver.withInput(new LongWritable(), new Text(TweetConstants.SAMPLE_TWEET));
		mapDriver.withOutput(new Text("http://fb.me/162DKUWCN"), new IntWritable(1));
		mapDriver.runTest();
		assertEquals("Expected 1 counter increment", 1,mapDriver.getCounters().findCounter(TweetMapper.TweetParserCounter.EXTRACTED_URLS).getValue());
	}
	
	@Test
	public void testTweetReducer(){
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(1));
		values.add(new IntWritable(1));
		
		reduceDriver.withInput(new Text("http://fb.me/162DKUWCN"), values);
		reduceDriver.withOutput(new Text("http://fb.me/162DKUWCN"), new IntWritable(2));
		reduceDriver.runTest();
	}
	
}
