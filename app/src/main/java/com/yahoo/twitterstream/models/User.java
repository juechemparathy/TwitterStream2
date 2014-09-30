package com.yahoo.twitterstream.models;

import org.json.JSONObject;

/**
 * Created by jue on 9/21/14.
 */
public class User {
    private String name;
    private long id;
    private String screenanme;
    private String profileImageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScreenanme() {
        return screenanme;
    }

    public void setScreenanme(String screenanme) {
        this.screenanme = screenanme;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public static User fromJson(JSONObject object) {
        User u = new User();
        try {
            u.name = object.getString("name");
            u.id = object.getLong("id");
            u.screenanme = object.getString("screen_name");
            u.profileImageUrl = object.getString("profile_image_url");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return u;
    }
}
