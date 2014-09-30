package com.yahoo.twitterstream;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.yahoo.twitterstream.models.User;

import org.json.JSONObject;


public class ProfileActivity extends FragmentActivity {
    private ImageView   ivProfileImage;
    private TextView    tvName;
    private	TextView	tvTagLine;
    private	TextView 	tvFollowers;
    private	TextView 	tvFollowing;
    private String   userScreenName = ""; // 0 means self


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);
        tvName = (TextView)findViewById(R.id.tvName);
        tvTagLine = (TextView)findViewById(R.id.tvTagLine);
        tvFollowers = (TextView)findViewById(R.id.tvFollowers);
        tvFollowing = (TextView)findViewById(R.id.tvFollowing);
//		userId = 0;
//		userId = getIntent().getLongExtra("UserId", 0);
        userScreenName = getIntent().getStringExtra("UserScreenName");

        loadProfileInfo();
    }

    private void loadProfileInfo() {
        TwitterApplication.getRestClient().getMyInfo(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    String userProfileImageUrl = "";
                    String userName = json.getString("name");
                    String userScreenName = json.getString("screen_name");
                    String userTagLine = json.getString("description");
                    String uProfileIgUrl = json.getString("profile_image_url_https");
                    int iFollower = json.getInt("followers_count");
                    int iFollowing = json.getInt("friends_count");

                    if (userName != null)
                        tvName.setText(userName);
                    if (userScreenName != null)
                        getActionBar().setTitle(userScreenName);
                    else
                        getActionBar().setTitle(userName);
//					tvUserScreenName.setText("@" + userScreenName);
                    if (userTagLine != null)
                        tvTagLine.setText(userTagLine);

                    if (uProfileIgUrl != null)
                        userProfileImageUrl = uProfileIgUrl;
                    tvFollowers.setText(iFollower + " Followers");
                    tvFollowing.setText(iFollowing + " Following");

                    Picasso.with(getBaseContext()).load(userProfileImageUrl).into(ivProfileImage);

                }catch(Exception e){
                    e.printStackTrace();
                }
                User u = User.fromJson(json);
            }
        });
    }
}
