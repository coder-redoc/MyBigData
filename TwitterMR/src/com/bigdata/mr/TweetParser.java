package com.bigdata.mr;

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
     * JSON util method to parse url from the tweet
     * @param tweet
     * @return
     * @throws JSONException
     */
    public static String parseTwitterUrlData(String tweet) throws JSONException{

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

}

