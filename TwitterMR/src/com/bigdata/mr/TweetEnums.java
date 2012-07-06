package com.bigdata.mr;

public enum TweetEnums {

	//Tweet Entities
	ENTITIES("entities"),
	//Tweet UrlsEntity
	URL("url"), URLS("urls"), DISPLAY_URL("display_url"), EXPANDED_URL("expanded_url"),
	//Tweet user_mentions entity
	USER_MENTIONS("user_mentions"), USER_ID("id"), USER_ID_STR("id_str"), SCREEN_NAME("screen_name"), NAME("name"),
	//Tweet HashTag mentions
	HASHTAGS("hashtags"), HASHTAG_TEXT("text"), HASHTAG_DISPLAY_KEY("hashTags"); 
	
	private String tweetEntity;
	
	private TweetEnums(String tweetEntity) {
		this.tweetEntity=tweetEntity;
	}
	
	@Override
	public String toString(){
		return tweetEntity;
	}
}
