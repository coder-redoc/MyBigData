/**
 *  Junit Test case to perform unit testing for Tweet parser
 */
package com.bigdata.mr;
import static org.junit.Assert.*;
import java.util.Map;

import org.json.JSONException;
import org.junit.Test;

/**
 * @author Satish Varma Dandu (dsvarma@gmail.com)
 * @Time 7:52 PM
 */
public class TweetParserTest {

	public static final String SAMPLE_TWEET="{\"entities\":{\"urls\":[{\"display_url\":\"fb.me\\/162DKUWCN\",\"indices\":[25,45],\"expanded_url\":\"http:\\/\\/fb.me\\/162DKUWCN\",\"url\":\"http:\\/\\/t.co\\/qoLJhJvT\"}],\"user_mentions\":[],\"hashtags\":[]},\"text\":\"Possible Watch to our NW http:\\/\\/t.co\\/qoLJhJvT\",\"place\":null,\"in_reply_to_status_id\":null,\"in_reply_to_user_id\":null,\"truncated\":false,\"possibly_sensitive_editable\":true,\"id_str\":\"215523476402999296\",\"coordinates\":null,\"geo\":null,\"retweeted\":false,\"source\":\"\\u003Ca href=\\\"http:\\/\\/www.facebook.com\\/twitter\\\" rel=\\\"nofollow\\\"\\u003EFacebook\\u003C\\/a\\u003E\",\"possibly_sensitive\":false,\"in_reply_to_screen_name\":null,\"created_at\":\"Wed Jun 20 19:16:15 +0000 2012\",\"in_reply_to_status_id_str\":null,\"contributors\":null,\"user\":{\"default_profile\":false,\"lang\":\"en\",\"location\":\"Eastern Iowa\",\"is_translator\":false,\"contributors_enabled\":false,\"geo_enabled\":false,\"time_zone\":\"Central Time (US & Canada)\",\"profile_sidebar_border_color\":\"000000\",\"follow_request_sent\":null,\"following\":null,\"profile_use_background_image\":true,\"description\":\"KWWL-TV Chief Meteorologist\\nhttp:\\/\\/www.facebook.com\\/mark.schnackenberg\",\"id_str\":\"19655830\",\"default_profile_image\":false,\"show_all_inline_media\":false,\"verified\":false,\"profile_text_color\":\"05050a\",\"profile_background_image_url_https\":\"https:\\/\\/si0.twimg.com\\/profile_background_images\\/4513912\\/twitBGIjAOKF.jpg\",\"profile_background_image_url\":\"http:\\/\\/a0.twimg.com\\/profile_background_images\\/4513912\\/twitBGIjAOKF.jpg\",\"favourites_count\":0,\"friends_count\":2740,\"profile_link_color\":\"a8001a\",\"created_at\":\"Wed Jan 28 15:11:28 +0000 2009\",\"protected\":false,\"statuses_count\":8354,\"profile_background_color\":\"627ad0\",\"followers_count\":2497,\"url\":\"http:\\/\\/addins.kwwl.com\\/blogs\\/weather\\/\",\"profile_image_url_https\":\"https:\\/\\/si0.twimg.com\\/profile_images\\/1793407281\\/Mark_normal.png\",\"profile_image_url\":\"http:\\/\\/a0.twimg.com\\/profile_images\\/1793407281\\/Mark_normal.png\",\"screen_name\":\"KWWLSchnack\",\"name\":\"Mark Schnackenberg\",\"listed_count\":140,\"notifications\":null,\"profile_background_tile\":false,\"id\":19655830,\"utc_offset\":-21600,\"profile_sidebar_fill_color\":\"ffee38\"},\"retweet_count\":0,\"favorited\":false,\"id\":215523476402999296,\"in_reply_to_user_id_str\":null}";
	public static final String USER_MENTIONS_TWEET="{\"possibly_sensitive_editable\":true,\"entities\":{\"urls\":[{\"display_url\":\"read.bi\\/LWoseb\",\"indices\":[86,106],\"url\":\"http:\\/\\/t.co\\/xQ9mm3LG\",\"expanded_url\":\"http:\\/\\/read.bi\\/LWoseb\"}],\"user_mentions\":[{\"indices\":[74,85],\"screen_name\":\"owenthomas\",\"id_str\":\"3034251\",\"name\":\"Owen Thomas\",\"id\":3034251}],\"hashtags\":[]},\"text\":\"SAI: LinkedIn CEO Laughs At The Idea Of A Microsoft Buyout $MSFT $LNKD by @owenthomas http:\\/\\/t.co\\/xQ9mm3LG\",\"created_at\":\"Mon Jun 18 17:06:02 +0000 2012\",\"place\":null,\"truncated\":false,\"possibly_sensitive\":false,\"in_reply_to_user_id\":null,\"in_reply_to_status_id_str\":null,\"retweeted\":false,\"source\":\"\\u003Ca href=\\\"http:\\/\\/ifttt.com\\\" rel=\\\"nofollow\\\"\\u003Eifttt\\u003C\\/a\\u003E\",\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"id_str\":\"214765928594931712\",\"coordinates\":null,\"geo\":null,\"contributors\":null,\"user\":{\"lang\":\"en\",\"created_at\":\"Sat Jan 09 11:11:47 +0000 2010\",\"profile_background_color\":\"EDECE9\",\"default_profile\":false,\"contributors_enabled\":false,\"geo_enabled\":true,\"profile_background_tile\":false,\"profile_background_image_url_https\":\"https:\\/\\/si0.twimg.com\\/profile_background_images\\/98114012\\/Gorgeous_birds_male_Japanese_Robin.jpg\",\"time_zone\":\"Eastern Time (US & Canada)\",\"url\":null,\"is_translator\":false,\"follow_request_sent\":null,\"notifications\":null,\"profile_sidebar_fill_color\":\"E3E2DE\",\"following\":null,\"friends_count\":2111,\"description\":\"rants of my fuckn subconcious....\",\"show_all_inline_media\":false,\"verified\":false,\"profile_sidebar_border_color\":\"D3D2CF\",\"profile_image_url_https\":\"https:\\/\\/si0.twimg.com\\/profile_images\\/1890495824\\/0j74GROM_normal\",\"location\":\":)\",\"default_profile_image\":false,\"profile_use_background_image\":false,\"favourites_count\":2,\"followers_count\":726,\"profile_image_url\":\"http:\\/\\/a0.twimg.com\\/profile_images\\/1890495824\\/0j74GROM_normal\",\"screen_name\":\"lluccipha\",\"id_str\":\"103246788\",\"profile_text_color\":\"634047\",\"protected\":false,\"profile_background_image_url\":\"http:\\/\\/a0.twimg.com\\/profile_background_images\\/98114012\\/Gorgeous_birds_male_Japanese_Robin.jpg\",\"name\":\"lluccipha\",\"profile_link_color\":\"088253\",\"id\":103246788,\"listed_count\":5,\"statuses_count\":66027,\"utc_offset\":-18000},\"retweet_count\":0,\"favorited\":false,\"id\":214765928594931712,\"in_reply_to_status_id\":null}";
	public static final String HASHTAG_TWEETS =  "{\"possibly_sensitive_editable\":true,\"entities\":{\"urls\":[{\"display_url\":\"bit.ly\\/KOczWm\",\"indices\":[85,105],\"url\":\"http:\\/\\/t.co\\/nDGbj6wK\",\"expanded_url\":\"http:\\/\\/bit.ly\\/KOczWm\"}],\"user_mentions\":[],\"hashtags\":[{\"text\":\"WMT\",\"indices\":[106,110]},{\"text\":\"activists\",\"indices\":[111,121]},{\"text\":\"Build\",\"indices\":[122,128]}]},\"text\":\"$WMT News: Activists claim victory over Wal-Mart's decision to not build stores in \\u2026 http:\\/\\/t.co\\/nDGbj6wK #WMT #activists #Build\",\"created_at\":\"Mon Jun 18 17:16:57 +0000 2012\",\"place\":null,\"truncated\":false,\"possibly_sensitive\":false,\"in_reply_to_user_id\":null,\"in_reply_to_status_id_str\":null,\"retweeted\":false,\"source\":\"\\u003Ca href=\\\"http:\\/\\/www.bigticks.com\\\" rel=\\\"nofollow\\\"\\u003Ebigticks\\u003C\\/a\\u003E\",\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"id_str\":\"214768677084606464\",\"coordinates\":null,\"geo\":null,\"contributors\":null,\"user\":{\"lang\":\"en\",\"created_at\":\"Mon Jan 02 19:56:09 +0000 2012\",\"profile_background_color\":\"131516\",\"default_profile\":false,\"contributors_enabled\":false,\"geo_enabled\":false,\"profile_background_tile\":true,\"profile_background_image_url_https\":\"https:\\/\\/si0.twimg.com\\/images\\/themes\\/theme14\\/bg.gif\",\"time_zone\":null,\"url\":\"http:\\/\\/www.bigticks.com\",\"is_translator\":false,\"follow_request_sent\":null,\"notifications\":null,\"profile_sidebar_fill_color\":\"DDEEF6\",\"following\":null,\"friends_count\":596,\"description\":\"BigTicks.com offers news, real time stock charts & a network of niche sites focused on straight forward technical & fundamental analysis. \",\"show_all_inline_media\":false,\"verified\":false,\"profile_sidebar_border_color\":\"C0DEED\",\"profile_image_url_https\":\"https:\\/\\/si0.twimg.com\\/profile_images\\/2231068635\\/bticon_normal.png\",\"location\":\"Canada\",\"default_profile_image\":false,\"profile_use_background_image\":true,\"favourites_count\":0,\"followers_count\":585,\"profile_image_url\":\"http:\\/\\/a0.twimg.com\\/profile_images\\/2231068635\\/bticon_normal.png\",\"screen_name\":\"BigTicks\",\"id_str\":\"453306222\",\"profile_text_color\":\"333333\",\"protected\":false,\"profile_background_image_url\":\"http:\\/\\/a0.twimg.com\\/images\\/themes\\/theme14\\/bg.gif\",\"name\":\"Big Ticks\",\"profile_link_color\":\"009999\",\"id\":453306222,\"listed_count\":5,\"statuses_count\":12275,\"utc_offset\":null},\"retweet_count\":0,\"favorited\":false,\"id\":214768677084606464,\"in_reply_to_status_id\":null}";
	public static final String INVALID_TWEET="{\"entities\":{\"urls\":}";
	
	
	@Test
	public void testTweetParserForPositiveCases() throws Exception{
		
		Map<String, String> tweetEntitiesMap = TweetParser.parseTwitterUrlData(SAMPLE_TWEET);

		//Test for sample tweet
		assertNotNull(tweetEntitiesMap);
		assertNotNull(tweetEntitiesMap.get(TweetEnums.DISPLAY_URL.toString()));
		assertNotNull(tweetEntitiesMap.get(TweetEnums.EXPANDED_URL.toString()));
		assertNotNull(tweetEntitiesMap.get(TweetEnums.URL.toString()));
		
		//Test positive testcases for user_mentions_tweet
		tweetEntitiesMap = TweetParser.parseTwitterUrlData(USER_MENTIONS_TWEET);
		assertNotNull(tweetEntitiesMap);
		assertNotNull(tweetEntitiesMap.get(TweetEnums.EXPANDED_URL.toString()));
		assertNotNull(tweetEntitiesMap.get(TweetEnums.USER_ID.toString()));
		assertNotNull(tweetEntitiesMap.get(TweetEnums.SCREEN_NAME.toString()));
		assertNotNull(tweetEntitiesMap.get(TweetEnums.NAME.toString()));
		
		//Test positive testcases for hash_tag tweets
		tweetEntitiesMap = TweetParser.parseTwitterUrlData(HASHTAG_TWEETS);
		assertNotNull(tweetEntitiesMap);
		assertNotNull(tweetEntitiesMap.get(TweetEnums.HASHTAG_DISPLAY_KEY.toString()));
		
	}
	
	//This should throw an JSON Exception as we are passing INVALID_TWEET
	@Test(expected=JSONException.class)
	public void testTweetsForException() throws Exception{
		Map<String, String> tweetEntitiesMap = TweetParser.parseTwitterUrlData(INVALID_TWEET);
		assertNotNull(tweetEntitiesMap);
	}
	
	//This is for testing JSON parsing timeouts
	@Test(timeout = 100)
	public void testTweetsForTimeOut() throws Exception{
		Long time = System.currentTimeMillis();
		Map<String, String> tweetEntitiesMap = TweetParser.parseTwitterUrlData(SAMPLE_TWEET);
		System.out.println("Time taken to parse JSON="+(System.currentTimeMillis()-time));
	}
	
}
