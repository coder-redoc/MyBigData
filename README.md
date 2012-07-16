MyBigData - MapReduce jobs for Tweet Analytics

MyBigData contains MapReduce jobs to perform Tweet analytics. Users can specify some keywords to track in a file. We use Twitter's Streaming API to collect all the tweets matching user's specified keywords. For each tweet, we extract entities like urls, user_mentions, hashtags etc. This project contains MapReduce jobs  
- to find out most popular urls (for every hour). 
- inludes some performance tuning settings to improve MapReduce performace.
- contains mrunit (map reduce unit testing) & junit test cases for MapReduce jobs to demonstrate unit testing for Hadoop MapReduce jobs.
- Hadoop deprecated old API from 0.20.*. This projects contains MapReduce jobs using old & new hadoop API to demonstrate the migration.

