#!/usr/bin/env bash

USER_NAME=$1
PASSWORD=$2
TWEETS_OUTPUT_FILENAME=$3

if [ $# -lt 3 ] ; then
echo ""
  echo "Twitter Tracking KeyWords using Twitter Streaming API"
  echo "----------------------------------------------"
  echo "<USAGE>: tweetTracker.sh USER_NAME PASSWORD TWEETS_OUTPUT_FILE"
  echo ""
  exit 1
fi

echo "UserName=$USER_NAME, Password=$PASSWORD, TweetsOutputFile=$TWEETS_OUTPUT_FILENAME"


TRACK_WORDS="http,https"

curl -d 'track=http,https' https://stream.twitter.com/1/statuses/filter.json -u$USER_NAME:$PASSWORD >> $TWEETS_OUTPUT_FILE
