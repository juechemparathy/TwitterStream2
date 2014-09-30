package fragments;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.twitterstream.models.Tweet;

import org.json.JSONArray;

/**
 * Created by jue on 9/29/14.
 */
public class HomeTimelineFragment extends TweetsListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateTimeline();
    }

    public void populateTimeline(){
        getClient().getHomeTimeLine(new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(JSONArray json) {
                super.onSuccess(json);
                Log.d("Debug", json.toString());
//                aTweets.clear();
                addAll(Tweet.fromJson(json));
//                aTweets.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                super.onFailure(throwable, s);
                Log.d("Debug", throwable.toString());
                Log.d("Debug", s.toString());
            }

        });
    }
}
