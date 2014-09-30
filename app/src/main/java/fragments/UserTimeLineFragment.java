package fragments;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

/**
 * Created by jue on 9/29/14.
 */
public class UserTimeLineFragment extends TweetsListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateTimeline();
    }

    public void populateTimeline(){
        getClient().getMyInfo(new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(JSONObject json) {
                super.onSuccess(json);
                Log.d("Debug", json.toString());
//                aTweets.clear();
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
