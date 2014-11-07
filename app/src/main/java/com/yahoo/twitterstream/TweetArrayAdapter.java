package com.yahoo.twitterstream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yahoo.twitterstream.models.Tweet;

import java.util.List;

/**
 * Created by jue on 9/21/14.
 */
public class TweetArrayAdapter extends ArrayAdapter<Tweet>{

    public TweetArrayAdapter(Context context, List<Tweet> objects) {
        super(context,0,objects);

    }

    public View getView(int position,View convertView,ViewGroup parent){
        Tweet tweet = getItem(position);

        View v;
        if(convertView ==  null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v =  inflater.inflate(R.layout.tweet_item,parent, false);
        }else{
            v=convertView;
        }

        ImageView imageView =  (ImageView)v.findViewById(R.id.imageView);
        TextView username =  (TextView)v.findViewById(R.id.tvUsername);
        TextView tweetMessage =  (TextView)v.findViewById(R.id.tvTweet);
        TextView tweteTime =  (TextView)v.findViewById(R.id.tvTime);
        imageView.setImageResource(android.R.color.transparent);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(tweet.getUser().getProfileImageUrl(),imageView);
        username.setText(tweet.getUser().getScreenanme());
        tweetMessage.setText(tweet.getBody());
        tweteTime.setText(tweet.getCreatedAt());
        return v;
    }
}
