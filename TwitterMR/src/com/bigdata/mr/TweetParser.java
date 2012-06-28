package com.bigdata.mr;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * User: Satish Varma Dandu (dsvarma@gmail.com)
 * Time: 11:52 AM
 */
public class TweetParser {

    private static Logger log = Logger.getLogger(TweetParser.class.getName());

    public String parseTwitterUrlData(String tweet) throws Exception{

        String expandedUrl=null;
        JSONObject myjson = new JSONObject(tweet);
        JSONObject entitiesObj = myjson.getJSONObject(TweetConstants.ENTITIES);
        JSONArray urlArray = entitiesObj.getJSONArray(TweetConstants.URL);
        if(null!=urlArray && urlArray.length()>0){
            JSONObject urlDataObj = (JSONObject)urlArray.get(0);
            //String displayUrl = urlDataObj.getString("display_url");
            expandedUrl = urlDataObj.getString(TweetConstants.EXPANDED_URL);
        }

        return expandedUrl;
    }

    public  static void main(String[] args){
        TweetParser tweetJson = new TweetParser();
        //Sample Tweet for testing
        String tweet="{\"entities\":{\"urls\":[{\"display_url\":\"fb.me\\/162DKUWCN\",\"indices\":[25,45],\"expanded_url\":\"http:\\/\\/fb.me\\/162DKUWCN\",\"url\":\"http:\\/\\/t.co\\/qoLJhJvT\"}],\"user_mentions\":[],\"hashtags\":[]},\"text\":\"Possible Watch to our NW http:\\/\\/t.co\\/qoLJhJvT\",\"place\":null,\"in_reply_to_status_id\":null,\"in_reply_to_user_id\":null,\"truncated\":false,\"possibly_sensitive_editable\":true,\"id_str\":\"215523476402999296\",\"coordinates\":null,\"geo\":null,\"retweeted\":false,\"source\":\"\\u003Ca href=\\\"http:\\/\\/www.facebook.com\\/twitter\\\" rel=\\\"nofollow\\\"\\u003EFacebook\\u003C\\/a\\u003E\",\"possibly_sensitive\":false,\"in_reply_to_screen_name\":null,\"created_at\":\"Wed Jun 20 19:16:15 +0000 2012\",\"in_reply_to_status_id_str\":null,\"contributors\":null,\"user\":{\"default_profile\":false,\"lang\":\"en\",\"location\":\"Eastern Iowa\",\"is_translator\":false,\"contributors_enabled\":false,\"geo_enabled\":false,\"time_zone\":\"Central Time (US & Canada)\",\"profile_sidebar_border_color\":\"000000\",\"follow_request_sent\":null,\"following\":null,\"profile_use_background_image\":true,\"description\":\"KWWL-TV Chief Meteorologist\\nhttp:\\/\\/www.facebook.com\\/mark.schnackenberg\",\"id_str\":\"19655830\",\"default_profile_image\":false,\"show_all_inline_media\":false,\"verified\":false,\"profile_text_color\":\"05050a\",\"profile_background_image_url_https\":\"https:\\/\\/si0.twimg.com\\/profile_background_images\\/4513912\\/twitBGIjAOKF.jpg\",\"profile_background_image_url\":\"http:\\/\\/a0.twimg.com\\/profile_background_images\\/4513912\\/twitBGIjAOKF.jpg\",\"favourites_count\":0,\"friends_count\":2740,\"profile_link_color\":\"a8001a\",\"created_at\":\"Wed Jan 28 15:11:28 +0000 2009\",\"protected\":false,\"statuses_count\":8354,\"profile_background_color\":\"627ad0\",\"followers_count\":2497,\"url\":\"http:\\/\\/addins.kwwl.com\\/blogs\\/weather\\/\",\"profile_image_url_https\":\"https:\\/\\/si0.twimg.com\\/profile_images\\/1793407281\\/Mark_normal.png\",\"profile_image_url\":\"http:\\/\\/a0.twimg.com\\/profile_images\\/1793407281\\/Mark_normal.png\",\"screen_name\":\"KWWLSchnack\",\"name\":\"Mark Schnackenberg\",\"listed_count\":140,\"notifications\":null,\"profile_background_tile\":false,\"id\":19655830,\"utc_offset\":-21600,\"profile_sidebar_fill_color\":\"ffee38\"},\"retweet_count\":0,\"favorited\":false,\"id\":215523476402999296,\"in_reply_to_user_id_str\":null}";
        try{
            tweetJson.parseTwitterUrlData(TweetConstants.SAMPLE_TWEET);
        }catch(Exception e){
            log.error("Exception caught while trying to parse sample tweet",e);
        }
    }
}
