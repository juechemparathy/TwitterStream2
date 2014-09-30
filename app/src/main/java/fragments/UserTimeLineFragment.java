package fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.twitterstream.TwitterApplication;
import com.yahoo.twitterstream.models.Tweet;
import com.yahoo.twitterstream.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by jue on 9/29/14.
 */
public class UserTimeLineFragment extends TweetsListFragment {

    private User u;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateUserProfile();
        populateUserTimeline();
    }

    public void populateUserProfile(){

        TwitterApplication.getRestClient().getMyInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                u = User.fromJson(jsonObject);
                getActivity().getActionBar().setTitle("@" + u.getScreenanme());
            }
        });
    }

    public void populateUserTimeline(){
        getClient().getUserTimeLine(u,new JsonHttpResponseHandler() {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
