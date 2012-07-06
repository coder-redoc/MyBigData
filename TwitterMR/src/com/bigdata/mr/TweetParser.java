package com.bigdata.mr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * User: Satish Varma Dandu (dsvarma@gmail.com)
 * Time: 11:52 AM
 */
public class TweetParser {

	private static Logger log = Logger.getLogger(TweetParser.class.getName());

	/**
	 * JSON util method to parse tweet entities
	 * @param tweet
	 * @return
	 * @throws JSONException
	 */
	public static Map<String, String> parseTwitterUrlData(String tweet) throws JSONException{

		Map<String, String> tweetEntitiesMap = new HashMap<String, String>();

		JSONObject myjson = new JSONObject(tweet);
		JSONObject entitiesObj = myjson.getJSONObject(TweetEnums.ENTITIES.toString());
		JSONArray urlArray = entitiesObj.getJSONArray(TweetEnums.URLS.toString());
		JSONArray userMentionsArray = entitiesObj.getJSONArray(TweetEnums.USER_MENTIONS.toString());
		JSONArray hashtagsArray = entitiesObj.getJSONArray(TweetEnums.HASHTAGS.toString());

		//Parse tweet url entities
		if(null!=urlArray && urlArray.length()>0){
			JSONObject urlDataObj = (JSONObject)urlArray.get(0);
			tweetEntitiesMap.put(TweetEnums.URL.toString(),  		urlDataObj.getString(TweetEnums.URL.toString()));
			tweetEntitiesMap.put(TweetEnums.EXPANDED_URL.toString(), urlDataObj.getString(TweetEnums.EXPANDED_URL.toString()));
			tweetEntitiesMap.put(TweetEnums.DISPLAY_URL.toString(),  urlDataObj.getString(TweetEnums.DISPLAY_URL.toString()));
		}

		//Parse tweet user mentions entities
		if(null!=userMentionsArray && userMentionsArray.length()>0){
			JSONObject userMentionsObj = (JSONObject)userMentionsArray.get(0);
			tweetEntitiesMap.put(TweetEnums.USER_ID.toString(),  	userMentionsObj.getString(TweetEnums.USER_ID.toString()));
			tweetEntitiesMap.put(TweetEnums.SCREEN_NAME.toString(), userMentionsObj.getString(TweetEnums.SCREEN_NAME.toString()));
			tweetEntitiesMap.put(TweetEnums.NAME.toString(),  		userMentionsObj.getString(TweetEnums.NAME.toString()));
		}

		//Parse tweet hashtag entities
		if(null!=hashtagsArray && hashtagsArray.length()>0){
			List<String> hashTagList = new ArrayList();
			StringBuffer hashData = new StringBuffer();
			for(int i=0;i<hashtagsArray.length(); i++){
				JSONObject hashTagsObj = (JSONObject)hashtagsArray.get(i);
				hashData.append("#");
				hashData.append(hashTagsObj.getString(TweetEnums.HASHTAG_TEXT.toString()));
				if(!(i+1==hashtagsArray.length()))
					hashData.append(",");
			}
			tweetEntitiesMap.put(TweetEnums.HASHTAG_DISPLAY_KEY.toString(), hashData.toString());
		}

		return tweetEntitiesMap;
	}

}

