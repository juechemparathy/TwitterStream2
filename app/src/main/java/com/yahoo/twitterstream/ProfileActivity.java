package com.yahoo.twitterstream;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.codepath.apps.restclienttemplate.R;


public class ProfileActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}
