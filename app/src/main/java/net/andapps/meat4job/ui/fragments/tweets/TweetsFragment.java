package net.andapps.meat4job.ui.fragments.tweets;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import net.andapps.meat4job.R;


public class TweetsFragment extends ListFragment {

    @BindView(R.id.parentView)
    LinearLayout parentView;
//    @BindView(R.id.tweetsList)
//    ListView tweetsList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_tweets, container, false);
        ButterKnife.bind(this, rootView);


        final SearchTimeline searchTimeline =
                new SearchTimeline
                        .Builder()
                        .query("\"meat  is  healthy\"")
                        .build();

        final TweetTimelineListAdapter adapter =
                new TweetTimelineListAdapter
                        .Builder(getActivity())
                        .setTimeline(searchTimeline)
                        .build();
        setListAdapter(adapter);

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}


