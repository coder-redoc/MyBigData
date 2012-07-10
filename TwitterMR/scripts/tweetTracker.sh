#!/usr/bin/env bash

if [ $# -lt 3 ] ; then
echo ""
  echo "Twitter Tracking KeyWords using Twitter Streaming API"
  echo "----------------------------------------------"
  echo "<USAGE>: tweetTracker.sh USER_NAME PASSWORD DIR_TO_STORE_TWEETS<ABSOLUTE_PATH>"
  echo ""
  exit 1
fi

USER_NAME=$1
PASSWORD=$2
TWEETS_OUTPUT_DIR=$3

echo "UserName=$USER_NAME, Password=$PASSWORD"

mkdir -p $TWEETS_OUTPUT_DIR

TRACK_WORDS="http,https"
curl -d track=$TRACK_WORDS https://stream.twitter.com/1/statuses/filter.json -u$USER_NAME:$PASSWORD >> $TWEETS_OUTPUT_DIR/tweets.json 
