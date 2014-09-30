package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.twitterstream.EndlessScrollListener;
import com.yahoo.twitterstream.PullToRefreshListView;
import com.yahoo.twitterstream.TweetArrayAdapter;
import com.yahoo.twitterstream.TwitterApplication;
import com.yahoo.twitterstream.TwitterClient;
import com.yahoo.twitterstream.models.Tweet;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by jue on 9/29/14.
 */
public class TweetsListFragment extends Fragment {

    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private ArrayAdapter<Tweet> aTweets;
    private PullToRefreshListView lvTweets;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweets = new ArrayList<Tweet>();
        client =  TwitterApplication.getRestClient();
        //Calling getActivity should be used as little as possible
        aTweets = new TweetArrayAdapter(getActivity(),tweets);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate layout
        View v  = inflater.inflate(R.layout.fragment_tweets_list,container,false);
        //Assign view references
        tweets =  new ArrayList<Tweet>();
        aTweets = new TweetArrayAdapter(getActivity(),tweets);
        final PullToRefreshListView lvTweets = (PullToRefreshListView)v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(aTweets);
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Log.d("DEBUG","onLoadMore called page num: "+page);
                Toast.makeText(getActivity(), "on-scroll called", Toast.LENGTH_SHORT).show();
                populateTimeline();
                aTweets.addAll(tweets);
            }
        });

        lvTweets.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("Debug","Refreshing list");
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
//                fetchTimelineAsync(0);
//                lvTweets.onRefreshComplete();
                populateTimeline();
                //Known outofmemory culprit
                aTweets.addAll(tweets);
                lvTweets.onRefreshComplete();
            }
        });

        //False - not to attach to the container yet
        return v;
    }


    public void addAll(ArrayList<Tweet> tweets){
        aTweets.addAll(tweets);
    }

    public void populateTimeline(){
        client.getHomeTimeLine(new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(JSONArray json) {
                super.onSuccess(json);
                Log.d("Debug",json.toString());
//                aTweets.clear();
                aTweets.addAll(Tweet.fromJson(json));
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


    public  TwitterClient getClient(){
        return client;
    }
}
