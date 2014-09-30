package com.yahoo.twitterstream.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jue on 9/21/14.
 */
public class Tweet {
    private String body;
    private long uuid;
    private String createdAt;
    private User user;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public String getCreatedAt() {
        //add logic for converting to expected format
        try {
            Date tweetDate =  getTwitterDate(createdAt);
            Date now = new Date();

            long diff = now.getTime() - tweetDate.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            if(diffDays >0){
                return Long.toString(diffDays) + "d";
            }
            if(diffHours >0 ){
                return Long.toString(diffHours)+ "h";
            }
            if(diffMinutes>0){
                return Long.toString(diffMinutes)+ "m";
            }
            if(diffSeconds>0){
                return Long.toString(diffMinutes)+ "s";
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Date getTwitterDate(String date) throws ParseException {
        final String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
        sf.setLenient(true);
        return sf.parse(date);
    }


    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static Tweet fromJson(JSONObject object) {
        Tweet t = new Tweet();
        try {
            t.body = object.getString("text");
            t.createdAt = object.getString("created_at");
            t.uuid = object.getLong("id");
            User user = User.fromJson(object.getJSONObject("user"));
            t.user = user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }


    public static ArrayList<Tweet> fromJson(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject tweetJson = null;
            try {
                tweetJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Tweet business = Tweet.fromJson(tweetJson);
            if (business != null) {
                tweets.add(business);
            }
        }

        return tweets;
    }

}
